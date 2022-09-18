package DAO;

import DbInterface.DbConnection;
import DbInterface.IDbConnection;
import DbInterface.command.DbOperationExecutor;
import DbInterface.command.IDbOperation;
import DbInterface.command.ReadOperation;
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

        //senza design pattern command
        //conn = DbConnection.getInstance();
        //rs = conn.executeQuery("SELECT Nome, Cognome, Username, email FROM Utente WHERE Username = '" + username + "';");

        //con metà design pattern command
        //String sql = "SELECT Nome, Cognome, Username, email FROM Utente WHERE Username = '" + username + "';";
        //IDbOperation readOp = new ReadOperation(sql);
        //rs = readOp.execute();

        //design pattern command completo
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
        } finally {
            conn.close();
        }
        return null;
    }

    @Override
    public ArrayList<Amministratore> findAll() {
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "SELECT nome, cognome, username, email FROM progetto_pis.utente " +
                "AS u INNER JOIN progetto_pis.amministratore AS a ON u.idutente = a.utente_idutente;";
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        ArrayList<Amministratore> amministratori = new ArrayList<>();
        try {
            while (rs.next()) {
                amministratore = new Amministratore();
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
        } finally {
            conn.close();
        }
        return null;
    }

    @Override
    public int add(Amministratore amministratore) {

        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("INSERT INTO progetto_pis.utente " +
                "(nome, cognome, username, password, email, tipo) VALUES ('" +
                amministratore.getName() + "','" +
                amministratore.getSurname() + "','" +
                amministratore.getUsername() + "','" +
                amministratore.getPwd() + "','" +
                amministratore.getEmail() + "','" +
                amministratore.getTipo() + "');");

        rs = conn.executeQuery("SELECT max(idutente) FROM progetto_pis.utente;");
        try {
            rs.next();
            amministratore.setIdUtente(rs.getInt("max(idutente)"));
            rowCount = conn.executeUpdate("INSERT INTO progetto_pis.amministratore (utente_idutente) VALUES ('" + amministratore.getIdUtente() + "');");

        } catch (SQLException e) {
            // handle any errors
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } catch (NullPointerException e) {
            // handle any errors
            System.out.println("Resultset: " + e.getMessage());
        } finally {
            conn.close();
        }

        return rowCount;
    }

    @Override
    public int removeById(String username) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("DELETE FROM progetto_pis.utente WHERE username = '"+ username + "';");
        conn.close();
        return rowCount;
    }

    @Override
    public int update(Amministratore amministratore) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("UPDATE Utente SET Nome = '" + amministratore.getName() + "', Cognome = '" + amministratore.getSurname() + "', Email = '" + amministratore.getEmail() + "' WHERE username = '" + amministratore.getUsername() + "';");
        conn.close();
        return rowCount;
    }

    /* public Cliente caricaCliente(String username) {
        Cliente c = new Cliente();
        String sql = "SELECT U.idUtente, U.nome, U.cognome, U.email, U.username, C.canale_preferito FROM myshop.utente AS U INNER JOIN myshop.cliente AS C ON U.idutente = C.utente_idutente WHERE U.username = '"+username+"';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        try {
            rs.next();
            if (rs.getRow() == 1) {
                c.setIdUtente(rs.getInt("idUtente"));
                c.setName(rs.getString("nome"));
                c.setSurname(rs.getString("cognome"));
                c.setEmail(rs.getString("email"));
                c.setUsername(rs.getString("username"));
                //c.setCanalePreferito();
                return c;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }*/
}