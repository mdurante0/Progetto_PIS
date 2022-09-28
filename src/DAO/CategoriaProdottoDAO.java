package DAO;

import DbInterface.IDbConnection;
import DbInterface.command.DbOperationExecutor;
import DbInterface.command.IDbOperation;
import DbInterface.command.ReadOperation;
import DbInterface.command.WriteOperation;
import Model.CategoriaProdotto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoriaProdottoDAO implements ICategoriaProdottoDAO {
    private static CategoriaProdottoDAO instance = new CategoriaProdottoDAO();
    private CategoriaProdotto categoriaProdotto;
    private static IDbConnection conn;
    private static ResultSet rs;

    private CategoriaProdottoDAO() {
        categoriaProdotto = null;
        conn = null;
        rs = null;
    }

    public static CategoriaProdottoDAO getInstance() {
        return instance;
    }

    @Override
    public CategoriaProdotto findById(String name) {

        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "SELECT idcategoria_prodotto, nome FROM progetto_pis.categoria_prodotto " +
                "WHERE nome = '"+name+"';";
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        try {
            rs.next();
            if (rs.getRow()==1) {
                categoriaProdotto = new CategoriaProdotto();
                categoriaProdotto.setNome(rs.getString("nome"));

                return categoriaProdotto;
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
    public ArrayList<CategoriaProdotto> findAll() {
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "SELECT nome FROM progetto_pis.categoria_prodotto ;";
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        ArrayList<CategoriaProdotto> categorie = new ArrayList<>();
        try {
            while (rs.next()) {
                categoriaProdotto = new CategoriaProdotto();
                categoriaProdotto.setNome(rs.getString("nome"));

                categorie.add(categoriaProdotto);
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
    public int add(CategoriaProdotto categoria) {

        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "INSERT INTO progetto_pis.categoria_prodotto (nome) VALUES ('"+categoria.getNome()+"');";
        IDbOperation writeOp = new WriteOperation(sql);

        return  executor.executeOperation(writeOp).getRowsAffected();


    }

    @Override
    public int removeById(String name) {
        String sql = "DELETE FROM progetto_pis.categoria_prodotto " +
                "WHERE nome = '"+ name + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        return executor.executeOperation(writeOp).getRowsAffected();
    }


    @Override
    public int update(CategoriaProdotto categoria) {

        String sql = "UPDATE progetto_pis.categoria_prodotto " +
                "SET nome = '" + categoria.getNome() +
                "' WHERE idcategoria_prodotto = '" + categoria.getIdCategoriaProdotto() + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        return executor.executeOperation(writeOp).getRowsAffected();
    }


}