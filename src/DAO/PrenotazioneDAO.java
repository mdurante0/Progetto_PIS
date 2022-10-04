package DAO;

import DbInterface.IDbConnection;
import DbInterface.command.DbOperationExecutor;
import DbInterface.command.IDbOperation;
import DbInterface.command.ReadOperation;
import DbInterface.command.WriteOperation;
import Model.Prenotazione;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PrenotazioneDAO implements IPrenotazioneDAO {
    private static PrenotazioneDAO instance = new PrenotazioneDAO();
    private Prenotazione prenotazione;
    private static IDbConnection conn;
    private static ResultSet rs;

    private PrenotazioneDAO (){
        prenotazione = null;
        conn = null;
        rs = null;
    }
    public static PrenotazioneDAO getInstance() {
        return instance;
    }

    @Override
    public Prenotazione findById(int idPrenotazione) {

        String sql = "SELECT idprenotazione, utente_acquirente_utente_idutente, data_prenotazione, prodotto_articolo_idarticolo, quantita " +
                "FROM progetto_pis.prenotazione AS p INNER JOIN progetto_pis.prenotazione_has_prodotto AS pp " +
                "ON p.idprenotazione = pp.prenotazione_idprenotazione " +
                "WHERE idprenotazione = '" + idPrenotazione + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        try {
            rs.next();
            if (rs.getRow()==1) {
                prenotazione = new Prenotazione();
                prenotazione.setIdPrenotazione(rs.getInt("idprenotazione"));
                prenotazione.setIdUtente(rs.getInt("utente_acquirente_utente_idutente"));
                prenotazione.setDataPrenotazione(rs.getDate("data_prenotazione"));
                prenotazione.setIdProdotto(rs.getInt("prodotto_articolo_idarticolo"));
                prenotazione.setQuantita(rs.getInt("quantita"));

                return prenotazione;
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

    public ArrayList<Prenotazione> findByUser(int idUtenteAcquirente){

        String sql = "SELECT idprenotazione, utente_acquirente_utente_idutente, data_prenotazione, prodotto_articolo_idarticolo, quantita " +
                "FROM progetto_pis.prenotazione AS p INNER JOIN progetto_pis.prenotazione_has_prodotto AS pp " +
        "ON p.idprenotazione = pp.prenotazione_idprenotazione " +
                "WHERE utente_acquirente_utente_idutente = '" + idUtenteAcquirente + "';";
        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        ArrayList<Prenotazione> prenotazioni = new ArrayList<>();
        try {
            while (rs.next()) {
                prenotazione = new Prenotazione();
                prenotazione.setIdPrenotazione(rs.getInt("idlista_acquisto"));
                prenotazione.setIdUtente(rs.getInt("utente_acquirente_utente_idutente"));
                prenotazione.setDataPrenotazione(rs.getDate("data_prenotazione"));
                prenotazione.setIdProdotto(rs.getInt("prodotto_articolo_idarticolo"));
                prenotazione.setQuantita(rs.getInt("quantita"));

                prenotazioni.add(prenotazione);
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
    public ArrayList<Prenotazione> findAll() {

        String sql = "SELECT idprenotazione, utente_acquirente_utente_idutente, data_prenotazione, prodotto_articolo_idarticolo, quantita " +
                "FROM progetto_pis.prenotazione AS p INNER JOIN progetto_pis.prenotazione_has_prodotto AS pp " +
                "ON p.idprenotazione = pp.prenotazione_idprenotazione;";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        ArrayList<Prenotazione> prenotazioni = new ArrayList<>();
        try {
            while (rs.next()) {
                prenotazione = new Prenotazione();
                prenotazione.setIdPrenotazione(rs.getInt("idprenotazione"));
                prenotazione.setIdUtente(rs.getInt("utente_acquirente_utente_idutente"));
                prenotazione.setDataPrenotazione(rs.getDate("data_prenotazione"));
                prenotazione.setIdProdotto(rs.getInt("prodotto_articolo_idarticolo"));
                prenotazione.setQuantita(rs.getInt("quantita"));

                prenotazioni.add(prenotazione);
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
    public int add(Prenotazione prenotazione) {

        String sql = "INSERT INTO progetto_pis.prenotazione (utente_acquirente_utente_idutente, data_prenotazione) VALUES ('"+
                prenotazione.getIdUtente() + "','" + prenotazione.getDataPrenotazione() + "');";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        executor.executeOperation(writeOp).getRowsAffected();

        executor = new DbOperationExecutor();
        sql = "SELECT max(idprenotazione) FROM progetto_pis.prenotazione;";
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        int rowCount = 0;
        try {
            rs.next();
            prenotazione.setIdPrenotazione(rs.getInt("max(idprenotazione)"));
            sql = "INSERT INTO progetto_pis.prenotazione_has_prodotto " +
                    "(prenotazione_idprenotazione, prodotto_articolo_idarticolo, quantita) VALUES ('" +
                    prenotazione.getIdPrenotazione() + "','" +
                    prenotazione.getIdProdotto() + "','" +
                    prenotazione.getQuantita() + "');";
            writeOp = new WriteOperation(sql);

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
    public int removeById(int id) {

        String sql = "DELETE FROM progetto_pis.prenotazione " +
                "WHERE idprenotazione = '" + id + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        return executor.executeOperation(writeOp).getRowsAffected();
    }

    public int removeByUser(int idUtenteAcquirente) {

        String sql = "DELETE FROM progetto_pis.prenotazione " +
                "WHERE utente_acquirente_utente_idutente = '" + idUtenteAcquirente + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        return executor.executeOperation(writeOp).getRowsAffected();
    }

    @Override
    public int update(Prenotazione prenotazione) {
        String sql = "UPDATE progetto_pis.prenotazione " +
                "SET utente_acquirente_utente_idutente = '" + prenotazione.getIdUtente() +
                "', data_prenotazione = '" + prenotazione.getDataPrenotazione() +
                "' WHERE idprenotazione = '" + prenotazione.getIdPrenotazione() + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        executor.executeOperation(writeOp).getRowsAffected();

        sql = "UPDATE progetto_pis.prenotazione_has_prodotto " +
                "SET prodotto_articolo_idarticolo = '" + prenotazione.getIdProdotto() +
                "', quantita = '" + prenotazione.getQuantita() +
                "' WHERE prenotazione_idprenotazione = '" + prenotazione.getIdPrenotazione() + "';";
        writeOp = new WriteOperation(sql);
        return executor.executeOperation(writeOp).getRowsAffected();
    }
}
