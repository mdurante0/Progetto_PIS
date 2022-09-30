package DAO;

import DbInterface.IDbConnection;
import DbInterface.command.DbOperationExecutor;
import DbInterface.command.IDbOperation;
import DbInterface.command.ReadOperation;
import DbInterface.command.WriteOperation;
import Model.Collocazione;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CollocazioneDAO implements ICollocazioneDAO {
    private static CollocazioneDAO instance = new CollocazioneDAO();
    private Collocazione collocazione;
    private static IDbConnection conn;
    private static ResultSet rs;

    private CollocazioneDAO() {
        collocazione = null;
        conn = null;
        rs = null;
    }

    public static CollocazioneDAO getInstance() {
        return instance;
    }

    @Override
    public Collocazione findById(int corsia, int scaffale) {

        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "SELECT idcollocazione, scaffale, corsia FROM progetto_pis.collocazione " +
                "WHERE corsia = '" + corsia + "'&& scaffale = '" + scaffale + "';";
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        try {
            rs.next();
            if (rs.getRow()==1) {
                collocazione = new Collocazione();
                collocazione.setIdCollocazione(rs.getInt("idcollocazione"));
                collocazione.setCorsia(rs.getInt("corsia"));
                collocazione.setScaffale(rs.getInt("scaffale"));

                return collocazione;
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
    public ArrayList<Collocazione> findAll() {
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "SELECT corsia, scaffale FROM progetto_pis.collocazione ;";
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        ArrayList<Collocazione> collocazioni = new ArrayList<>();
        try {
            while (rs.next()) {
                collocazione = new Collocazione();
                collocazione.setIdCollocazione(rs.getInt("idcollocazione"));
                collocazione.setScaffale(rs.getInt("scaffale"));
                collocazione.setCorsia(rs.getInt("corsia"));

                collocazioni.add(collocazione);
            }
            return collocazioni;
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
    public int add(Collocazione collocazione) {

        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "INSERT INTO progetto_pis.collocazione (corsia, scaffale) VALUES ('"+
                collocazione.getCorsia() + "','" +
                collocazione.getScaffale() + ");";
        IDbOperation writeOp = new WriteOperation(sql);

        return  executor.executeOperation(writeOp).getRowsAffected();
    }

    @Override
    public int removeById(int id) {
        String sql = "DELETE FROM progetto_pis.collocazione " +
                "WHERE idcollocazione = '"+ id + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        return executor.executeOperation(writeOp).getRowsAffected();
    }


    @Override
    public int update(Collocazione collocazione) {

        String sql = "UPDATE progetto_pis.collocazione " +
                "SET corsia = '" + collocazione.getCorsia() +
                "', scaffale = '"+ collocazione.getScaffale() +
                "' WHERE idcollocazione = '" + collocazione.getIdCollocazione() + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        return executor.executeOperation(writeOp).getRowsAffected();
    }

}