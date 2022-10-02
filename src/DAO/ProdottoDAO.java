package DAO;

import DbInterface.IDbConnection;
import DbInterface.command.DbOperationExecutor;
import DbInterface.command.IDbOperation;
import DbInterface.command.ReadOperation;
import DbInterface.command.WriteOperation;
import Model.Prodotto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdottoDAO implements IPrenotazioneDAO {
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
                prodotto.set(rs.getInt("idprenotazione"));
                prodotto.setIdUtente(rs.getInt("utente_acquirente_utente_idutente"));
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
        String sql = "SELECT idprenotazione, utente_acquirente_utente_idutente " +
                "FROM progetto_pis.prenotazione;";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        ArrayList<Prodotto> prenotazioni = new ArrayList<>();
        try {
            while (rs.next()) {
                prodotto = new Prodotto();
                prodotto.setIdPrenotazione(rs.getInt("idprenotazione"));
                prodotto.setIdUtente(rs.getInt("utente_acquirente_utente_idutente"));
                prenotazioni.add(prodotto);
            }
            return prenotazioni;
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
    public int add(Prodotto prenotazione) {
        String sql = "INSERT INTO progetto_pis.prenotazione (utente_acquirente_utente_idutente) VALUES ('"+
                prenotazione.getIdUtente() + "');";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        return executor.executeOperation(writeOp).getRowsAffected();
    }

    @Override
    public int removeById(int id) {

        String sql = "DELETE FROM progetto_pis.prenotazione " +
                "WHERE idprenotazione = '" + id + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        return executor.executeOperation(writeOp).getRowsAffected();
    }


    @Override
    public int update(Prodotto prenotazione) {
        String sql = "UPDATE progetto_pis.prenotazione " +
                "SET utente_acquirente_utente_idutente = '" + prenotazione.getIdUtente() +
                "' WHERE idprenotazione = '" + prenotazione.getIdPrenotazione() + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        return executor.executeOperation(writeOp).getRowsAffected();
    }
}
