package DAO;

import DbInterface.IDbConnection;
import DbInterface.command.DbOperationExecutor;
import DbInterface.command.IDbOperation;
import DbInterface.command.ReadOperation;
import DbInterface.command.WriteOperation;
import Model.Articolo;
import Model.CategoriaServizio;
import Model.Servizio;
import Model.composite.IProdotto;
import Model.composite.Prodotto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

public class CategoriaServizioDAO implements ICategoriaServizioDAO {
    private static CategoriaServizioDAO instance = new CategoriaServizioDAO();
    private CategoriaServizio categoriaServizio;
    private static IDbConnection conn;
    private static ResultSet rs;

    private CategoriaServizioDAO() {
        categoriaServizio = null;
        conn = null;
        rs = null;
    }

    public static CategoriaServizioDAO getInstance() {
        return instance;
    }

    @Override
    public CategoriaServizio findById(String name) {

        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "SELECT idcategoria_servizio, nome, servizio_articolo_idarticolo FROM progetto_pis.categoria_servizio AS c INNER JOIN progetto_pis.categoria_servizio AS cs ON c.idcategoria_servizio = cs.categoria_servizio_idcategoria_servizio " +
                "WHERE nome = '"+name+"';";
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        try {
            rs.next();
            if (rs.getRow()==1) {
                categoriaServizio = new CategoriaServizio();
                categoriaServizio.setIdCategoriaServizio(rs.getInt("idcategoria_servizio"));
                categoriaServizio.setNome(rs.getString("nome"));

                ServizioDAO servizioDAO = ServizioDAO.getInstance();
                Model.Servizio servizio;
                while (rs.next()){
                    servizio = servizioDAO.findById(rs.getInt("articolo_idarticolo"));
                    servizio.setQuantita(rs.getInt("quantita"));
                    categoriaServizio.add(servizio);

                }
                return categoriaServizio;
            }
        } catch (SQLException e) {
            // handle any errors
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } catch (NullPointerException e) {
            // handle any errors
            System.out.println("Resultset: " + e.getMessage());
        }
        return null;
    }

    @Override
    public ArrayList<CategoriaServizio> findAll() {
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "SELECT idcategoria_servizio, nome, servizio_articolo_idarticolo FROM progetto_pis.categoria_servizio AS c INNER JOIN progetto_pis.categoria_servizio AS cs ON c.idcategoria_servizio = cs.categoria_servizio_idcategoria_servizio;";
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        ArrayList<CategoriaServizio> categorie = new ArrayList<>();
        try {
            while (rs.next()) {
                categoriaServizio = new CategoriaServizio();
                categoriaServizio.setIdCategoriaServizio(rs.getInt("idcategoria_servizio"));
                categoriaServizio.setNome(rs.getString("nome"));

                ServizioDAO servizioDAO = ServizioDAO.getInstance();
                Model.Servizio servizio;
                while (rs.next()){
                    servizio = servizioDAO.findById(rs.getInt("articolo_idarticolo"));
                    servizio.setQuantita(rs.getInt("quantita"));
                    categoriaServizio.add(servizio);

                }
                categorie.add(categoriaServizio);
            }
            return categorie;
        } catch (SQLException e) {
            // Gestisce le differenti categorie d'errore
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } catch (NullPointerException e) {
            // Gestisce le differenti categorie d'errore
            System.out.println("Resultset: " + e.getMessage());
        }

        return null;
    }

    @Override
    public int add(CategoriaServizio categoria) {

        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "INSERT INTO progetto_pis.categoria_servizio (nome) VALUES ('"+categoria.getNome()+"');";
        IDbOperation writeOp = new WriteOperation(sql);

        executor.executeOperation(writeOp);

        sql = "SELECT max(idcategoria_servizio) FROM progetto_pis.categoria_servizio;";
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        int rowCount = 0;
        try {
            rs.next();
            categoriaServizio.setIdCategoriaServizio(rs.getInt("max(idcategoria_servizio)"));

            ServizioDAO servizioDao = ServizioDAO.getInstance();
            Iterator<Servizio> servizioIterator = categoriaServizio.getServizi().iterator();
            Servizio servizio;
            while (servizioIterator.hasNext()) {

                servizio = servizioDao.findByName(servizioIterator.next().getName());
                sql = "INSERT INTO progetto_pis.categoria_servizio_has_servizio" +
                        "(categoria_servizio_idcategoria_servizio, servizio_articolo_idarticolo) " +
                        "VALUES ('" +categoriaServizio.getIdCategoriaServizio() +  "','" +
                        servizio.getIdArticolo() + "');";

                writeOp = new WriteOperation(sql);
                rowCount += executor.executeOperation(writeOp).getRowsAffected();
            }

        } catch (SQLException e) {
            // handle any errors
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } catch (NullPointerException e) {
            // handle any errors
            System.out.println("Resultset: " + e.getMessage());
        }
        return rowCount;


    }

    @Override
    public int removeById(String name) {
        String sql = "DELETE FROM progetto_pis.categoria_servizio " +
                "WHERE nome = '"+ name + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        return executor.executeOperation(writeOp).getRowsAffected();
    }
    public int addServizio(int idCategoriaServizio, Servizio servizio){

        String sql = "INSERT INTO progetto_pis.categoria_servizio_has_servizio" +
                "(categoria_servizio_idcategoria_servizio, servizio_articolo_idarticolo) VALUES ('" +
                idCategoriaServizio + "','" +
                servizio.getIdArticolo() +"');";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        return executor.executeOperation(writeOp).getRowsAffected();
    }

    public int removeServizio(int idCategoriaServizio, Servizio servizio){

        String sql = "DELETE FROM progetto_pis.categoria_servizio_has_servizio " +
                "WHERE categoria_servizio_idcategoria_servizio = '" + idCategoriaServizio +
                "' AND servizio_articolo_idarticolo = '" + servizio.getIdArticolo() + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        return executor.executeOperation(writeOp).getRowsAffected();
    }


    @Override
    public int update(CategoriaServizio categoriaServizio) {

        String sql = "UPDATE progetto_pis.categoria_servizio " +
                "SET nome = '" + categoriaServizio.getNome() +
                "' WHERE idcategoria_servizio = '" + categoriaServizio.getIdCategoriaServizio() + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        return executor.executeOperation(writeOp).getRowsAffected();
    }

}