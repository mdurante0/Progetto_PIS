package DAO;

import DbInterface.IDbConnection;
import DbInterface.command.DbOperationExecutor;
import DbInterface.command.IDbOperation;
import DbInterface.command.ReadOperation;
import DbInterface.command.WriteOperation;
import Model.Cliente;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClienteDAO implements IClienteDAO {
    private static ClienteDAO instance = new ClienteDAO();
    private Cliente cliente;
    private static IDbConnection conn;
    private static ResultSet rs;

    private ClienteDAO() {
        cliente = null;
        conn = null;
        rs = null;
    }

    public static ClienteDAO getInstance() {
        return instance;
    }

    @Override
    public Cliente findById(String username) {

        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "SELECT idutente, nome, cognome, email, username FROM progetto_pis.utente " +
                "AS u INNER JOIN progetto_pis.utente_acquirente AS m ON u.idutente = m.utente_idutente " +
                "WHERE u.username = '"+username+"';";
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        try {
            rs.next();
            if (rs.getRow()==1) {
                cliente = new Cliente();
                cliente.setIdUtente(rs.getInt("idutente"));
                cliente.setName(rs.getString("nome"));
                cliente.setSurname(rs.getString("cognome"));
                cliente.setUsername(rs.getString("username"));
                cliente.setEmail(rs.getString("email"));

                return cliente;
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
    public ArrayList<Cliente> findAll() {
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "SELECT idutente, nome, cognome, username, email FROM progetto_pis.utente " +
                "AS u INNER JOIN progetto_pis.utente_acquirente AS c ON u.idutente = c.utente_idutente;";
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        ArrayList<Cliente> clienti = new ArrayList<>();
        try {
            while (rs.next()) {
                cliente = new Cliente();
                cliente.setIdUtente(rs.getInt("idutente"));
                cliente.setName(rs.getString("nome"));
                cliente.setSurname(rs.getString("cognome"));
                cliente.setUsername(rs.getString("username"));
                cliente.setEmail(rs.getString("email"));
                clienti.add(cliente);
            }
            return clienti;
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
    public int add(Cliente cliente) {

        UtenteDAO utenteDAO = UtenteDAO.getInstance();
        utenteDAO.add(cliente);

        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "SELECT max(idutente) FROM progetto_pis.utente;";
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        int rowCount = 0;
        try {
            rs.next();
            cliente.setIdUtente(rs.getInt("max(idutente)"));
            sql = "INSERT INTO progetto_pis.utente_acquirente " +
                    "(utente_idutente, punto_vendita_idpunto_vendita, abilitazione, data_registrazione) VALUES ('" +
                    cliente.getIdUtente() + "','" +
                    cliente.getIdPuntoVendita() + "','" +
                    cliente.isAbilitazione() + "','" +
                    cliente.getRegistrazione() + ");";
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

        UtenteDAO utenteDAO = UtenteDAO.getInstance();
        return utenteDAO.removeById(username);
    }

    @Override
    public int update(Cliente cliente) {

        UtenteDAO utenteDAO = UtenteDAO.getInstance();
        utenteDAO.update(cliente);

        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "UPDATE progetto_pis.cliente " +
                "SET punto_vendita_idpunto_vendita = '" + cliente.getIdPuntoVendita() +
                "', abilitazione = '" + cliente.isAbilitazione() +
                "', data_registrazione = '" + cliente.getRegistrazione() +
                "' WHERE utente_idutente = '" + cliente.getIdUtente() + "';";
        IDbOperation writeOp = new WriteOperation(sql);
        return executor.executeOperation(writeOp).getRowsAffected();
    }
}