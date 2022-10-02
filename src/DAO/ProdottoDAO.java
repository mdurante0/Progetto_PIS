package DAO;

import DbInterface.IDbConnection;
import DbInterface.command.DbOperationExecutor;
import DbInterface.command.IDbOperation;
import DbInterface.command.ReadOperation;
import DbInterface.command.WriteOperation;
import Model.composite.Prodotto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdottoDAO implements IProdottoDAO {
    private static ProdottoDAO instance = new ProdottoDAO();
    private Prodotto prodotto;
    private static IDbConnection conn;
    private static ResultSet rs;

    private ProdottoDAO() {
        prodotto = null;
        conn = null;
        rs = null;
    }

    public static ProdottoDAO getInstance() {
        return instance;
    }
    @Override
    public Prodotto findById(int idProdotto) {
        String sql = "SELECT articolo_idarticolo, produttore_idproduttore " +
                "FROM progetto_pis.prodotto " +
                "WHERE articolo_idarticolo = '" + idProdotto + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        try {
            rs.next();
            if (rs.getRow()==1) {
                prodotto = new Prodotto();
                prodotto.setIdArticolo(rs.getInt("articolo_idarticolo"));
                prodotto.setIdProduttore(rs.getInt("produttore_idproduttore"));
                return prodotto;
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
    public ArrayList<Prodotto> findAll() {
        String sql = "SELECT articolo_idarticolo, produttore_idproduttore " +
                "FROM progetto_pis.prodotto;";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        ArrayList<Prodotto> prodotti = new ArrayList<>();
        try {
            while (rs.next()) {
                prodotto = new Prodotto();
                prodotto.setIdArticolo(rs.getInt("articolo_idarticolo"));
                prodotto.setIdProduttore(rs.getInt("utente_acquirente_utente_idutente"));
                prodotti.add(prodotto);
            }
            return prodotti;
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
    public int add(Prodotto prodotto) {

        ArticoloDAO articoloDAO = ArticoloDAO.getInstance();
        articoloDAO.add(prodotto);

        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "INSERT INTO progetto_pis.prodotto (produttore_idproduttore) VALUES ('"+
                prodotto.getIdProduttore() + "');";
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        int rowCount = 0;
        try {
            rs.next();
            prodotto.setIdArticolo(rs.getInt("max(idutente)"));
            sql = "INSERT INTO progetto_pis.prodotto (produttore_idproduttore) VALUES ('" +
                    prodotto.getIdProduttore() + "');";
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
    public int removeById(String nome) {

        ArticoloDAO articoloDAO = ArticoloDAO.getInstance();
        return articoloDAO.removeById(nome);
    }


    @Override
    public int update(Prodotto prodotto) {

        ArticoloDAO articoloDAO = ArticoloDAO.getInstance();
        articoloDAO.update(prodotto);

        String sql = "UPDATE progetto_pis.prodotto " +
                "SET produttore_idproduttore = '" + prodotto.getIdProduttore() +
                "' WHERE articolo_idarticolo = '" + prodotto.getIdArticolo() + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        return executor.executeOperation(writeOp).getRowsAffected();
    }
}
