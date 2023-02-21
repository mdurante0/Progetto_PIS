package DAO;

import DbInterface.IDbConnection;
import DbInterface.command.DbOperationExecutor;
import DbInterface.command.IDbOperation;
import DbInterface.command.ReadOperation;
import DbInterface.command.WriteOperation;
import Model.Utente;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UtenteDAO implements IUtenteDAO {
    private static UtenteDAO instance = new UtenteDAO();
    private Utente utente;
    private static IDbConnection conn;
    private static ResultSet rs;

    private UtenteDAO() {
        utente = null;
        conn = null;
        rs = null;
    }

    public static UtenteDAO getInstance() {
        return instance;
    }

    @Override
    public Utente findById(String username) {

        //senza design pattern command
        //conn = DbConnection.getInstance();
        //rs = conn.executeQuery("SELECT Nome, Cognome, Username, email FROM Utente WHERE Username = '" + username + "';");

        //con metà design pattern command
        //String sql = "SELECT Nome, Cognome, Username, email FROM Utente WHERE Username = '" + username + "';";
        //IDbOperation readOp = new ReadOperation(sql);
        //rs = readOp.execute();

        //design pattern command completo
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "SELECT idutente, nome, cognome, username, email, tipo FROM progetto_pis.utente WHERE username = '" + username + "';";
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        try {
            rs.next();
            if (rs.getRow()==1) {
                utente = new Utente();
                utente.setIdUtente(rs.getInt("idutente"));
                utente.setName(rs.getString("nome"));
                utente.setSurname(rs.getString("cognome"));
                utente.setUsername(rs.getString("username"));
                utente.setEmail(rs.getString("email"));
                utente.setTipo(rs.getString("tipo"));
                return utente;
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
            //conn.close();
        }
        return null;
    }

    @Override
    public ArrayList<Utente> findAll() {

        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "SELECT idutente, nome, cognome, username, email, tipo FROM progetto_pis.utente;";
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        ArrayList<Utente> utenti = new ArrayList<>();
        try {
            while (rs.next()) {
                utente = new Utente();
                utente.setIdUtente(rs.getInt("idutente"));
                utente.setName(rs.getString("nome"));
                utente.setSurname(rs.getString("cognome"));
                utente.setUsername(rs.getString("username"));
                utente.setEmail(rs.getString("email"));
                utente.setTipo(rs.getString("tipo"));
                utenti.add(utente);
            }
            return utenti;
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
    public int add(Utente utente) {

        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "INSERT INTO progetto_pis.utente (nome, cognome, username, password, email, tipo) VALUES ('"+
                utente.getName() + "','" +
                utente.getSurname() + "','" +
                utente.getUsername() + "','" +
                utente.getPwd() + "','" +
                utente.getEmail() + "','" +
                utente.getTipo() + "');";
        IDbOperation writeOp = new WriteOperation(sql);
        return executor.executeOperation(writeOp).getRowsAffected();
    }

    @Override
    public int removeById(String username) {

        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "DELETE FROM progetto_pis.utente WHERE username = '"+ username + "';";
        IDbOperation writeOp = new WriteOperation(sql);
        return executor.executeOperation(writeOp).getRowsAffected();
    }

    @Override
    public int update(Utente utente) {

        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "UPDATE progetto_pis.utente SET nome = '" + utente.getName() +
                "', cognome = '" + utente.getSurname() +
                "', username = '" + utente.getUsername() +
                "', email = '" + utente.getEmail() +
                "' WHERE idutente = '" + utente.getIdUtente() + "';";
        IDbOperation writeOp = new WriteOperation(sql);
        return executor.executeOperation(writeOp).getRowsAffected();
    }

    public boolean userExists(String username) {

        String sql = "SELECT count(*) AS count FROM progetto_pis.utente AS u WHERE u.username='"+username+"';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        try {
            rs.next();
            if (rs.getRow() == 1) {
                int count = rs.getInt("count");
                return count == 1;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean checkCredentials(String username, String password) {

        String sql = "SELECT count(*) AS count FROM progetto_pis.utente AS u WHERE u.username='" + username + "' AND u.password='" + password + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        try {
            rs.next();
            if (rs.getRow() == 1) {
                int count = rs.getInt("count");
                return count == 1;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isCliente(String username) {

        String sql = "SELECT count(*) as count FROM progetto_pis.utente AS u WHERE u.username='"+username+"' and u.tipo='CL';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        try {
            rs.next();
            if (rs.getRow() == 1) {
                int count = rs.getInt("count");
                return count == 1;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isManager(String username) {

        String sql = "SELECT count(*) as count FROM progetto_pis.utente AS u WHERE u.username='"+username+"' and u.tipo='MN';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        try {
            rs.next();
            if (rs.getRow() == 1) {
                int count = rs.getInt("count");
                return count == 1;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
/*  CODICE PROBLEMATICO PERCHè PRENDE IL TIPO DALLA TABELLA MANAGER E NON DA QUELLA DI UTENTE

        String sql = "SELECT count(*) AS count FROM progetto_pis.utente AS u INNER JOIN progetto_pis.manager AS m ON u.idutente = m.utente_idutente WHERE u.username='"+username+"';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        try {
            rs.next();
            if (rs.getRow() == 1) {
                int count = rs.getInt("count");
                return count == 1;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }*/
    }
    public boolean isAmministratore(String username) {

        String sql ="SELECT count(*) as count FROM progetto_pis.utente AS u WHERE u.username='"+username+"' and u.tipo='AM';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        try {
            rs.next();
            if (rs.getRow() == 1) {
                int count = rs.getInt("count");
                return count == 1;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}