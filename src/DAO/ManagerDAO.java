package DAO;

import DbInterface.IDbConnection;
import DbInterface.command.DbOperationExecutor;
import DbInterface.command.IDbOperation;
import DbInterface.command.ReadOperation;
import DbInterface.command.WriteOperation;
import Model.Manager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ManagerDAO implements IManagerDAO {
    private static ManagerDAO instance = new ManagerDAO();
    private Manager manager;
    private static IDbConnection conn;
    private static ResultSet rs;

    private ManagerDAO() {
        manager = null;
        conn = null;
        rs = null;
    }

    public static ManagerDAO getInstance() {
        return instance;
    }

    @Override
    public Manager findById(String username) {

        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "SELECT idutente, nome, cognome, email, username, durata_incarico FROM progetto_pis.utente " +
                "AS u INNER JOIN progetto_pis.manager AS m ON u.idutente = m.utente_idutente " +
                "WHERE u.username = '"+username+"';";
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        try {
            rs.next();
            if (rs.getRow()==1) {
                manager = new Manager();
                manager.setIdUtente(rs.getInt("idutente"));
                manager.setName(rs.getString("nome"));
                manager.setSurname(rs.getString("cognome"));
                manager.setUsername(rs.getString("username"));
                manager.setEmail(rs.getString("email"));
                manager.setDurataIncarico(rs.getInt("durata_incarico"));
                return manager;
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
    public ArrayList<Manager> findAll() {
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "SELECT idutente, nome, cognome, username, email, durata_incarico FROM progetto_pis.utente " +
                "AS u INNER JOIN progetto_pis.manager AS m ON u.idutente = m.utente_idutente;";
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        ArrayList<Manager> managers = new ArrayList<>();
        try {
            while (rs.next()) {
                manager = new Manager();
                manager.setIdUtente(rs.getInt("idutente"));
                manager.setName(rs.getString("nome"));
                manager.setSurname(rs.getString("cognome"));
                manager.setUsername(rs.getString("username"));
                manager.setEmail(rs.getString("email"));
                manager.setDurataIncarico(rs.getInt("durata_incarico"));
                managers.add(manager);
            }
            return managers;
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
    public int add(Manager manager) {

        UtenteDAO utenteDAO = UtenteDAO.getInstance();
        utenteDAO.add(manager);

        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "SELECT max(idutente) FROM progetto_pis.utente;";
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        int rowCount = 0;
        try {
            rs.next();
            manager.setIdUtente(rs.getInt("max(idutente)"));
            sql = "INSERT INTO progetto_pis.manager (utente_idutente, durata_incarico) VALUES ('" +
                manager.getIdUtente() + "','" + manager.getDurataIncarico() + "');";
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
    public int update(Manager manager) {

        UtenteDAO utenteDAO = UtenteDAO.getInstance();
        utenteDAO.update(manager);

        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "UPDATE progetto_pis.manager " +
                "SET durata_incarico = '" + manager.getDurataIncarico() +
                "' WHERE utente_idutente = '" + manager.getIdUtente() + "';";
        IDbOperation writeOp = new WriteOperation(sql);
        return executor.executeOperation(writeOp).getRowsAffected();
    }
}