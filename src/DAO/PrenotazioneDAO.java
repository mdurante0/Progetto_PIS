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
        String sql = "SELECT idprenotazione, utente_acquirente_utente_idutente " +
                "FROM progetto_pis.prenotazione " +
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

        String sql = "SELECT idprenotazione, utente_acquirente_utente_idutente " +
                "FROM progetto_pis.prenotazione " +
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
        String sql = "SELECT idprenotazione, utente_acquirente_utente_idutente " +
                "FROM progetto_pis.prenotazione;";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        ArrayList<Prenotazione> prenotazioni = new ArrayList<>();
        try {
            while (rs.next()) {
                prenotazione = new Prenotazione();
                prenotazione.setIdPrenotazione(rs.getInt("idprenotazione"));
                prenotazione.setIdUtente(rs.getInt("utente_acquirente_utente_idutente"));
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
                "' WHERE idprenotazione = '" + prenotazione.getIdPrenotazione() + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        return executor.executeOperation(writeOp).getRowsAffected();
    }
}
