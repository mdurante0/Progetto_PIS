package DAO;

import DbInterface.IDbConnection;
import DbInterface.command.DbOperationExecutor;
import DbInterface.command.IDbOperation;
import DbInterface.command.ReadOperation;
import DbInterface.command.WriteOperation;
import Model.Feedback;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FeedbackDAO implements IFeedbackDAO {
    private static FeedbackDAO instance = new FeedbackDAO();
    private Feedback feedback;
    private static IDbConnection conn;
    private static ResultSet rs;

    private FeedbackDAO() {
        feedback = null;
        conn = null;
        rs = null;
    }

    public static FeedbackDAO getInstance() {
        return instance;
    }

    @Override
    public Feedback findById(int id) {

        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "SELECT idfeedback, commento, gradimento, " +
                "articolo_idarticolo, utente_acquirente_utente_idutente, " +
                "risposta, data FROM progetto_pis.feedback " +
                "WHERE idfeedback = '" + id + "';";
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        try {
            rs.next();
            if (rs.getRow()==1) {
                feedback = new Feedback();
                feedback.setIdFeedback(rs.getInt("idfeedback"));
                feedback.setCommento(rs.getString("commento"));
                feedback.setRisposta(rs.getString("risposta"));
                feedback.setData(rs.getDate("data"));
                feedback.setGradimento(rs.getObject("gradimento", Feedback.Punteggio.class));
                feedback.setIdArticolo(rs.getInt("articolo_idarticolo"));
                feedback.setIdUtente(rs.getInt("utente_acquirente_utente_idutente"));

                return feedback;
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
    public ArrayList<Feedback> findAll() {
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "SELECT idfeedback, commento, gradimento, " +
                "articolo_idarticolo, utente_acquirente_utente_idutente, " +
                "risposta, data FROM progetto_pis.feedback ;";
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        ArrayList<Feedback> feedbacks = new ArrayList<>();
        try {
            while (rs.next()) {
                feedback = new Feedback();
                feedback.setIdFeedback(rs.getInt("idfeedback"));
                feedback.setCommento(rs.getString("commento"));
                feedback.setRisposta(rs.getString("risposta"));
                feedback.setData(rs.getDate("data"));
                feedback.setGradimento(rs.getObject("gradimento", Feedback.Punteggio.class));
                feedback.setIdArticolo(rs.getInt("articolo_idarticolo"));
                feedback.setIdUtente(rs.getInt("utente_acquirente_utente_idutente"));
                feedbacks.add(feedback);
            }
            return feedbacks;
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

    public ArrayList<Feedback> findByUser(int idUtenteAcquirente) {
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "SELECT idfeedback, commento, gradimento, " +
                "articolo_idarticolo, utente_acquirente_utente_idutente, " +
                "risposta, data FROM progetto_pis.feedback " +
                "WHERE utente_acquirente_utente_idutente = '" + idUtenteAcquirente + "' ;";
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        ArrayList<Feedback> feedbacks = new ArrayList<>();
        try {
            while (rs.next()) {
                feedback = new Feedback();
                feedback.setIdFeedback(rs.getInt("idfeedback"));
                feedback.setCommento(rs.getString("commento"));
                feedback.setRisposta(rs.getString("risposta"));
                feedback.setData(rs.getDate("data"));
                feedback.setGradimento(rs.getObject("gradimento", Feedback.Punteggio.class));
                feedback.setIdArticolo(rs.getInt("articolo_idarticolo"));
                feedback.setIdUtente(rs.getInt("utente_acquirente_utente_idutente"));
                feedbacks.add(feedback);
            }
            return feedbacks;
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

    public ArrayList<Feedback> findByArticolo(int idArticolo) {
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "SELECT idfeedback, commento, gradimento, " +
                "articolo_idarticolo, utente_acquirente_utente_idutente, " +
                "risposta, data FROM progetto_pis.feedback " +
                "WHERE articolo_idarticolo = '" + idArticolo + "' ;";
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        ArrayList<Feedback> feedbacks = new ArrayList<>();
        try {
            while (rs.next()) {
                feedback = new Feedback();
                feedback.setIdFeedback(rs.getInt("idfeedback"));
                feedback.setCommento(rs.getString("commento"));
                feedback.setRisposta(rs.getString("risposta"));
                feedback.setData(rs.getDate("data"));
                feedback.setGradimento(rs.getObject("gradimento", Feedback.Punteggio.class));
                feedback.setIdArticolo(rs.getInt("articolo_idarticolo"));
                feedback.setIdUtente(rs.getInt("utente_acquirente_utente_idutente"));
                feedbacks.add(feedback);
            }
            return feedbacks;
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
    public int add(Feedback feedback) {

        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "INSERT INTO progetto_pis.feedback (commento, gradimento, " +
                "articolo_idarticolo, utente_acquirente_utente_idutente, " +
                "risposta, data) VALUES ('"+
                feedback.getCommento() + "','" +
                feedback.getGradimento() + "','" +
                feedback.getIdArticolo() + "','" +
                feedback.getIdUtente() + "','" +
                feedback.getRisposta() + "','" +
                feedback.getData() + ");";
        IDbOperation writeOp = new WriteOperation(sql);

        return  executor.executeOperation(writeOp).getRowsAffected();
    }

    @Override
    public int removeById(int id) {
        String sql = "DELETE FROM progetto_pis.feedback " +
                "WHERE idfeedback = '"+ id + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        return executor.executeOperation(writeOp).getRowsAffected();
    }

    public int removeByUser(int idUtenteAcquirente) {
        String sql = "DELETE FROM progetto_pis.feedback " +
                "WHERE utente_acquirente_utente_idutente = '"+ idUtenteAcquirente + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        return executor.executeOperation(writeOp).getRowsAffected();
    }

    public int removeByArticolo(int idArticolo) {
        String sql = "DELETE FROM progetto_pis.feedback " +
                "WHERE articolo_idarticolo = '"+ idArticolo + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        return executor.executeOperation(writeOp).getRowsAffected();
    }

    @Override
    public int update(Feedback feedback) {

        String sql = "UPDATE progetto_pis.feedback " +
                "SET commento = '" + feedback.getCommento() +
                "', gradimento = '"+ feedback.getGradimento() +
                "', articolo_idarticolo = '"+ feedback.getIdArticolo() +
                "', utente_acquirente_utente_idutente = '"+ feedback.getIdUtente() +
                "', risposta = '"+ feedback.getRisposta() +
                "', data = '"+ feedback.getData() +
                "' WHERE idfeedback = '" + feedback.getIdFeedback() + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        return executor.executeOperation(writeOp).getRowsAffected();
    }

}