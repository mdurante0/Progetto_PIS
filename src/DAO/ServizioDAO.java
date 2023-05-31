package DAO;

import DbInterface.IDbConnection;
import DbInterface.command.DbOperationExecutor;
import DbInterface.command.IDbOperation;
import DbInterface.command.ReadOperation;
import DbInterface.command.WriteOperation;
import Model.Servizio;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ServizioDAO implements IServizioDAO {
    private static ServizioDAO instance = new ServizioDAO();
    private Servizio servizio;
    private static IDbConnection conn;
    private static ResultSet rs;

    private ServizioDAO() {
        servizio = null;
        conn = null;
        rs = null;
    }

    public static ServizioDAO getInstance() {
        return instance;
    }
    @Override
    public Servizio findById(int idServizio) {

        String sql = "SELECT articolo_idarticolo, fornitore_idfornitore, nome, descrizione, costo, categoria_servizio_idcategoria_servizio " +
                "FROM progetto_pis.servizio AS s INNER JOIN progetto_pis.articolo AS a " +
                "ON a.idarticolo = s.articolo_idarticolo" +
                " WHERE articolo_idarticolo = '" + idServizio + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        try {
            rs.next();
            if (rs.getRow()==1) {
                servizio = new Servizio();
                servizio.setIdArticolo(rs.getInt("articolo_idarticolo"));
                servizio.getFornitore().setIdFornitore(rs.getInt("fornitore_idfornitore"));
                servizio.setName(rs.getString("nome"));
                servizio.setDescrizione(rs.getString("descrizione"));
                servizio.setPrezzo(rs.getFloat("costo"));
                servizio.getCategoria().setIdCategoria(rs.getInt("categoria_servizio_idcategoria_servizio"));

                return servizio;
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

    public Servizio findByName(String name) {

        String sql = "SELECT articolo_idarticolo, fornitore_idfornitore, nome, descrizione, costo, categoria_servizio_idcategoria_servizio " +
                "FROM progetto_pis.servizio AS s INNER JOIN progetto_pis.articolo AS a " +
                "ON a.idarticolo = s.articolo_idarticolo" +
                " WHERE nome = '" + name + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        try {
            rs.next();
            if (rs.getRow()==1) {
                servizio = new Servizio();
                servizio.setIdArticolo(rs.getInt("articolo_idarticolo"));
                servizio.getFornitore().setIdFornitore(rs.getInt("fornitore_idfornitore"));
                servizio.setName(rs.getString("nome"));
                servizio.setDescrizione(rs.getString("descrizione"));
                servizio.setPrezzo(rs.getFloat("costo"));
                servizio.getCategoria().setIdCategoria(rs.getInt("categoria_servizio_idcategoria_servizio"));

                return servizio;
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
    public ArrayList<Servizio> findAll() {

        String sql = "SELECT articolo_idarticolo, fornitore_idfornitore, nome, descrizione, costo, categoria_servizio_idcategoria_servizio " +
                "FROM progetto_pis.servizio AS s INNER JOIN progetto_pis.articolo AS a " +
                "ON a.idarticolo = s.articolo_idarticolo;";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        ArrayList<Servizio> servizi = new ArrayList<>();
        try {
            while (rs.next()) {
                servizio = new Servizio();
                servizio.setIdArticolo(rs.getInt("articolo_idarticolo"));
                servizio.getFornitore().setIdFornitore(rs.getInt("fornitore_idfornitore"));
                servizio.setName(rs.getString("nome"));
                servizio.setDescrizione(rs.getString("descrizione"));
                servizio.setPrezzo(rs.getFloat("costo"));
                servizio.getCategoria().setIdCategoria(rs.getInt("categoria_servizio_idcategoria_servizio"));

                servizi.add(servizio);
            }
            return servizi;
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


    public ArrayList<Servizio> findAllByCategoria(int idCategoria) {

        String sql = "SELECT articolo_idarticolo, fornitore_idfornitore, nome, descrizione, costo, categoria_servizio_idcategoria_servizio " +
                "FROM progetto_pis.servizio AS s INNER JOIN progetto_pis.articolo AS a " +
                "ON a.idarticolo = s.articolo_idarticolo " +
                "WHERE categoria_servizio_idcategoria_servizio = '" + idCategoria + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        ArrayList<Servizio> servizi = new ArrayList<>();
        try {
            while (rs.next()) {
                servizio = new Servizio();
                servizio.setIdArticolo(rs.getInt("articolo_idarticolo"));
                servizio.getFornitore().setIdFornitore(rs.getInt("fornitore_idfornitore"));
                servizio.setName(rs.getString("nome"));
                servizio.setDescrizione(rs.getString("descrizione"));
                servizio.setPrezzo(rs.getFloat("costo"));
                servizio.getCategoria().setIdCategoria(rs.getInt("categoria_servizio_idcategoria_servizio"));

                servizi.add(servizio);
            }
            return servizi;
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
    public int add(Servizio servizio) {

        ArticoloDAO articoloDAO = ArticoloDAO.getInstance();
        articoloDAO.add(servizio);

        String sql = "SELECT max(idarticolo) FROM progetto_pis.articolo;";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        int rowCount = 0;
        try {
            rs.next();
            servizio.setIdArticolo(rs.getInt("max(idarticolo)"));

            if(servizio.getCategoria() != null)
                sql = "INSERT INTO progetto_pis.servizio (articolo_idarticolo, categoria_servizio_idcategoria_servizio, fornitore_idfornitore) " +
                        "VALUES ('" +
                        servizio.getIdArticolo() + "','" +
                        servizio.getCategoria().getIdCategoria() + "','" +
                        servizio.getFornitore().getIdFornitore() + "');";
            else
                sql = "INSERT INTO progetto_pis.servizio (articolo_idarticolo, fornitore_idfornitore) " +
                        "VALUES ('" +
                        servizio.getIdArticolo() + "','" +
                        servizio.getFornitore().getIdFornitore() + "');";

            IDbOperation writeOp = new WriteOperation(sql);

            rowCount = executor.executeOperation(writeOp).getRowsAffected();

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
    public int removeById(int idServizio) {

        ArticoloDAO articoloDAO = ArticoloDAO.getInstance();
        return articoloDAO.removeById(idServizio);
    }
    

    @Override
    public int update(Servizio servizio) {

        ArticoloDAO articoloDAO = ArticoloDAO.getInstance();
        articoloDAO.update(servizio);

        String sql;

        if(servizio.getCategoria() != null)
            sql = "UPDATE progetto_pis.servizio " +
                    "SET fornitore_idfornitore= '" + servizio.getFornitore().getIdFornitore() +
                    "', categoria_servizio_idcategoria_servizio = '" + servizio.getCategoria().getIdCategoria() +
                    "' WHERE articolo_idarticolo = '" + servizio.getIdArticolo() + "';";
        else
            sql = "UPDATE progetto_pis.servizio " +
                    "SET fornitore_idfornitore= '" + servizio.getFornitore().getIdFornitore() +
                    "' WHERE articolo_idarticolo = '" + servizio.getIdArticolo() + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        return executor.executeOperation(writeOp).getRowsAffected();
    }
}
