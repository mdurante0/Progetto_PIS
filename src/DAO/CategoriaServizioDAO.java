package DAO;

import DbInterface.IDbConnection;
import DbInterface.command.DbOperationExecutor;
import DbInterface.command.IDbOperation;
import DbInterface.command.ReadOperation;
import DbInterface.command.WriteOperation;
import Model.CategoriaServizio;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
        String sql = "SELECT idcategoria_servizio, nome FROM progetto_pis.categoria_servizio " +
                "WHERE nome = '"+name+"';";
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        try {
            rs.next();
            if (rs.getRow()==1) {
                categoriaServizio = new CategoriaServizio();
                categoriaServizio.setNome(rs.getString("nome"));

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
        String sql = "SELECT nome FROM progetto_pis.categoria_servizio ;";
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        ArrayList<CategoriaServizio> categorie = new ArrayList<>();
        try {
            while (rs.next()) {
                categoriaServizio = new CategoriaServizio();
                categoriaServizio.setNome(rs.getString("nome"));

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

        return  executor.executeOperation(writeOp).getRowsAffected();


    }

    @Override
    public int removeById(String name) {
        String sql = "DELETE FROM progetto_pis.categoria_servizio " +
                "WHERE nome = '"+ name + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        return executor.executeOperation(writeOp).getRowsAffected();
    }

    /*
    @Override
    public int update(CategoriaServizio categoria) {

        String sql = "UPDATE progetto_pis.categoria " +
                "SET nome = '" + categoria.getNome() +
                "' WHERE nome = '" + categoria.getNome() + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        return executor.executeOperation(writeOp).getRowsAffected();
    }
        STAI CERCANDO DI MODIFICARE IL NOME DI UNA CATEGORIA RIMETTENDO LO STESSO NOME!!!
        MAESTRO, MA COSA MI COMBINA?!
     */
}