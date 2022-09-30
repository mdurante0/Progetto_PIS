package DAO;

import DbInterface.IDbConnection;
import DbInterface.command.DbOperationExecutor;
import DbInterface.command.IDbOperation;
import DbInterface.command.ReadOperation;
import DbInterface.command.WriteOperation;
import Model.Amministratore;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AmministratoreDAO implements IAmministratoreDAO {
    private static AmministratoreDAO instance = new AmministratoreDAO();
    private Amministratore amministratore;
    private static IDbConnection conn;
    private static ResultSet rs;

    private AmministratoreDAO() {
        amministratore = null;
        conn = null;
        rs = null;
    }

    public static AmministratoreDAO getInstance() {
        return instance;
    }

    @Override
    public Amministratore findById(String username) {

        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "SELECT idutente, nome, cognome, email, username FROM progetto_pis.utente " +
                "AS u INNER JOIN progetto_pis.amministratore AS a ON u.idutente = a.utente_idutente " +
                "WHERE u.username = '"+username+"';";
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        try {
            rs.next();
            if (rs.getRow()==1) {
                amministratore = new Amministratore();
                amministratore.setIdUtente(rs.getInt("idutente"));
                amministratore.setName(rs.getString("nome"));
                amministratore.setSurname(rs.getString("cognome"));
                amministratore.setUsername(rs.getString("username"));
                amministratore.setEmail(rs.getString("email"));

                return amministratore;
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
    public ArrayList<Amministratore> findAll() {
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "SELECT idutente, nome, cognome, username, email FROM progetto_pis.utente " +
                "AS u INNER JOIN progetto_pis.amministratore AS a ON u.idutente = a.utente_idutente;";
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        ArrayList<Amministratore> amministratori = new ArrayList<>();
        try {
            while (rs.next()) {
                amministratore = new Amministratore();
                amministratore.setIdUtente(rs.getInt("idutente"));
                amministratore.setName(rs.getString("nome"));
                amministratore.setSurname(rs.getString("cognome"));
                amministratore.setUsername(rs.getString("username"));
                amministratore.setEmail(rs.getString("email"));
                amministratori.add(amministratore);
            }
            return amministratori;
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
    public int add(Amministratore amministratore) {

        UtenteDAO utenteDAO = UtenteDAO.getInstance();
        utenteDAO.add(amministratore);

        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "SELECT max(idutente) FROM progetto_pis.utente;";
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        int rowCount = 0;
        try {
            rs.next();
            amministratore.setIdUtente(rs.getInt("max(idutente)"));
            sql = "INSERT INTO progetto_pis.amministratore (utente_idutente) VALUES ('" +
                amministratore.getIdUtente() + "');";
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
    public int removeById(String username) {

        UtenteDAO utente = UtenteDAO.getInstance();
        return utente.removeById(username);
    }

    @Override
    public int update(Amministratore amministratore) {

        UtenteDAO utenteDAO = UtenteDAO.getInstance();
        return utenteDAO.update(amministratore);
    }
}