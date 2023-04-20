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
    public CategoriaServizio findById(int idCategoria) {

        String sql = "SELECT idcategoria_servizio, nome " +
                "FROM progetto_pis.categoria_servizio " +
                "WHERE idcategoria_servizio = '" + idCategoria + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        try {
            rs.next();
            if (rs.getRow()==1) {
                categoriaServizio = new CategoriaServizio();
                categoriaServizio.setIdCategoriaServizio(rs.getInt("idcategoria_servizio"));
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

    public CategoriaServizio findByName(String name) {

        String sql = "SELECT idcategoria_servizio, nome " +
                "FROM progetto_pis.categoria_servizio " +
                "WHERE nome = '" + name + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        try {
            rs.next();
            if (rs.getRow()==1) {
                categoriaServizio = new CategoriaServizio();
                categoriaServizio.setIdCategoriaServizio(rs.getInt("idcategoria_servizio"));
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

        String sql = "SELECT idcategoria_servizio, nome " +
                "FROM progetto_pis.categoria_servizio;";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        ArrayList<CategoriaServizio> categorieServizio = new ArrayList<>();
        try {
            while (rs.next()) {
                categoriaServizio = new CategoriaServizio();
                categoriaServizio.setIdCategoriaServizio(rs.getInt("idcategoria_servizio"));
                categoriaServizio.setNome(rs.getString("nome"));

                categorieServizio.add(categoriaServizio);
            }
            return categorieServizio;
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
    public int add(CategoriaServizio categoriaServizio) {

        String sql = "INSERT INTO progetto_pis.categoria_servizio (nome) VALUES ('" + categoriaServizio.getNome() + "');";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        return executor.executeOperation(writeOp).getRowsAffected();
    }

    @Override
    public int removeById(String name) {

        String sql = "DELETE FROM progetto_pis.categoria_servizio " +
                "WHERE nome = '" + name + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        return executor.executeOperation(writeOp).getRowsAffected();
    }

    @Override
    public int update(CategoriaServizio categoriaServizio) {

        String sql = "UPDATE progetto_pis.categoria_servizio " +
                "SET nome = '" + categoriaServizio.getNome() +
                "' WHERE idcategoria_servizio = '" + categoriaServizio.getIdCategoria() + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        return executor.executeOperation(writeOp).getRowsAffected();
    }
}