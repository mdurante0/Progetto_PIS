package DAO;

import DbInterface.IDbConnection;
import DbInterface.command.DbOperationExecutor;
import DbInterface.command.IDbOperation;
import DbInterface.command.ReadOperation;
import DbInterface.command.WriteOperation;
import Model.Servizio;
import Model.Servizio;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ServizioDAO implements IServizioDAO {
    private static ServizioDAO instance = new ServizioDAO();
    private Servizio servizio;
    private static IDbConnection conn;
    private static ResultSet rs;

    private ServizioDAO() {
        servizio = null;
        conn = null;
        rs = null;
    }

    public static ServizioDAO getInstance() {
        return instance;
    }
    @Override
    public Servizio findById(int idServizio) {
        String sql = "SELECT articolo_idarticolo, fornitore_idfornitore " +
                "FROM progetto_pis.servizio " +
                "WHERE articolo_idarticolo = '" + idServizio + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        try {
            rs.next();
            if (rs.getRow()==1) {
                servizio = new Servizio();
                servizio.setIdArticolo(rs.getInt("articolo_idarticolo"));
                servizio.setIdFornitore(rs.getInt("fornitore_idfornitore"));
                return servizio;
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
    public ArrayList<Servizio> findAll() {
        String sql = "SELECT articolo_idarticolo, fornitore_idfornitore " +
                "FROM progetto_pis.servizio ;";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        ArrayList<Servizio> servizi = new ArrayList<>();
        try {
            while (rs.next()) {
                servizio = new Servizio();
                servizio.setIdArticolo(rs.getInt("articolo_idarticolo"));
                servizio.setIdFornitore(rs.getInt("fornitore_idfornitore"));
                servizi.add(servizio);
            }
            return servizi;
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
    public int add(Servizio servizio) {
        String sql = "INSERT INTO progetto_pis.servizio (fornitore_idfornitore) VALUES ('"+
                servizio.getIdFornitore() + "');";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        return executor.executeOperation(writeOp).getRowsAffected();
    }

    @Override
    public int removeById(int id) {

        String sql = "DELETE FROM progetto_pis.servizio " +
                "WHERE articolo_idarticolo = '" + id + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        return executor.executeOperation(writeOp).getRowsAffected();
    }
    

    @Override
    public int update(Servizio servizio) {
        String sql = "UPDATE progetto_pis.prenotazione " +
                "SET fornitore_idfornitore= '" + servizio.getIdFornitore() +
                "' WHERE articolo_idarticolo = '" + servizio.getIdArticolo() + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        return executor.executeOperation(writeOp).getRowsAffected();
    }
}
