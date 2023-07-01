package DAO;

import DbInterface.IDbConnection;
import DbInterface.command.DbOperationExecutor;
import DbInterface.command.IDbOperation;
import DbInterface.command.ReadOperation;
import DbInterface.command.WriteOperation;
import Model.Cliente;
import Model.PuntoVendita;
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
    public Cliente findById(int idUtente) {

        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "SELECT * FROM progetto_pis.utente AS u INNER JOIN progetto_pis.utente_acquirente AS c ON u.idutente = c.utente_idutente " +
                "WHERE u.idutente = '"+ idUtente +"';";
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
                cliente.setPwd(rs.getString("password"));
                cliente.setTipo(rs.getString("tipo"));
                cliente.setResidenza(rs.getString("residenza"));
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setProfessione(rs.getString("professione"));
                cliente.setEta(rs.getInt("eta"));
                cliente.setAbilitazione(rs.getBoolean("abilitazione"));

                PuntoVendita puntoVendita = PuntoVenditaDAO.getInstance().findById(rs.getInt("punto_vendita_idpunto_vendita"));
                if(puntoVendita != null)
                    cliente.setPuntoVenditaDiRegistrazione(puntoVendita);

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
    public Cliente findByUsername(String username) {

        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "SELECT * FROM progetto_pis.utente AS u INNER JOIN progetto_pis.utente_acquirente AS c ON u.idutente = c.utente_idutente " +
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
                cliente.setPwd(rs.getString("password"));
                cliente.setTipo(rs.getString("tipo"));
                cliente.setResidenza(rs.getString("residenza"));
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setProfessione(rs.getString("professione"));
                cliente.setEta(rs.getInt("eta"));
                cliente.setAbilitazione(rs.getBoolean("abilitazione"));

                PuntoVendita puntoVendita = PuntoVenditaDAO.getInstance().findById(rs.getInt("punto_vendita_idpunto_vendita"));
                if(puntoVendita != null)
                    cliente.setPuntoVenditaDiRegistrazione(puntoVendita);

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
        String sql = "SELECT * FROM progetto_pis.utente AS u INNER JOIN progetto_pis.utente_acquirente AS c ON u.idutente = c.utente_idutente;";
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
                cliente.setPwd(rs.getString("password"));
                cliente.setTipo(rs.getString("tipo"));
                cliente.setResidenza(rs.getString("residenza"));
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setProfessione(rs.getString("professione"));
                cliente.setEta(rs.getInt("eta"));
                cliente.setAbilitazione(rs.getBoolean("abilitazione"));

                PuntoVendita puntoVendita = PuntoVenditaDAO.getInstance().findById(rs.getInt("punto_vendita_idpunto_vendita"));
                if(puntoVendita != null)
                    cliente.setPuntoVenditaDiRegistrazione(puntoVendita);

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
    public ArrayList<Cliente> findAllByPuntoVendita(int idPuntoVendita) {
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "SELECT * FROM progetto_pis.utente AS u INNER JOIN progetto_pis.utente_acquirente AS c ON u.idutente = c.utente_idutente WHERE c.punto_vendita_idpunto_vendita = '" + idPuntoVendita + "';";
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
                cliente.setPwd(rs.getString("password"));
                cliente.setTipo(rs.getString("tipo"));
                cliente.setResidenza(rs.getString("residenza"));
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setProfessione(rs.getString("professione"));
                cliente.setEta(rs.getInt("eta"));
                cliente.setAbilitazione(rs.getBoolean("abilitazione"));

                PuntoVendita puntoVendita = PuntoVenditaDAO.getInstance().findById(rs.getInt("punto_vendita_idpunto_vendita"));
                if(puntoVendita != null)
                    cliente.setPuntoVenditaDiRegistrazione(puntoVendita);

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
            sql = "INSERT INTO progetto_pis.utente_acquirente (utente_idutente, punto_vendita_idpunto_vendita, abilitazione, eta, residenza, professione, telefono) VALUES ('"+ cliente.getIdUtente()+"','" +  cliente.getPuntoVenditaDiRegistrazione().getIdPuntoVendita() + "','"+ cliente.getAbilitazione() +"','"+ cliente.getEta() +"','" +  cliente.getResidenza() + "','"+  cliente.getProfessione() +"','" + cliente.getTelefono() + "');";

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
        String sql = "UPDATE progetto_pis.utente_acquirente " +
                "SET punto_vendita_idpunto_vendita = '" + cliente.getPuntoVenditaDiRegistrazione().getIdPuntoVendita() +
                "', eta = '" + cliente.getEta() +
                "', residenza = '" + cliente.getResidenza() +
                "', professione = '" + cliente.getProfessione() +
                "', telefono = '" + cliente.getTelefono() +
                "', abilitazione = '" + cliente.getAbilitazione() +
                "' WHERE utente_idutente = '" + cliente.getIdUtente() + "';";
        IDbOperation writeOp = new WriteOperation(sql);
        return executor.executeOperation(writeOp).getRowsAffected();
    }
}