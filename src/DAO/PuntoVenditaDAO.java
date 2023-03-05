package DAO;

import DbInterface.IDbConnection;
import DbInterface.command.DbOperationExecutor;
import DbInterface.command.IDbOperation;
import DbInterface.command.ReadOperation;
import DbInterface.command.WriteOperation;
import Model.PuntoVendita;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PuntoVenditaDAO implements IPuntoVenditaDAO {
    private static PuntoVenditaDAO instance = new PuntoVenditaDAO();
    private PuntoVendita puntoVendita;
    private static IDbConnection conn;
    private static ResultSet rs;

    private PuntoVenditaDAO() {
        puntoVendita = null;
        conn = null;
        rs = null;
    }

    public static PuntoVenditaDAO getInstance() {
        return instance;
    }
    @Override
    public PuntoVendita findById(int idPuntoVendita) {
        String sql = "SELECT * FROM progetto_pis.punto_vendita WHERE idpunto_vendita = '" + idPuntoVendita + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        try {
            rs.next();
            if (rs.getRow()==1) {
                puntoVendita = new PuntoVendita();
                puntoVendita.setIdPuntoVendita(rs.getInt("idpunto_vendita"));
                puntoVendita.setIdManager(rs.getInt("manager_utente_idutente"));
                puntoVendita.setIdMagazzino(rs.getInt("magazzino_idmagazzino"));
                puntoVendita.setCitta(rs.getString("citta"));
                puntoVendita.setIndirizzo(rs.getString("indirizzo"));
                puntoVendita.setTelefono(rs.getString("telefono"));
                puntoVendita.setNome(rs.getString("nome"));
                return puntoVendita;
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
    public PuntoVendita findByName(String nome) {
        String sql = "SELECT * FROM progetto_pis.punto_vendita WHERE nome = '" + nome + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        try {
            rs.next();
            if (rs.getRow()==1) {
                puntoVendita = new PuntoVendita();
                puntoVendita.setIdPuntoVendita(rs.getInt("idpunto_vendita"));
                puntoVendita.setIdManager(rs.getInt("manager_utente_idutente"));
                puntoVendita.setIdMagazzino(rs.getInt("magazzino_idmagazzino"));
                puntoVendita.setCitta(rs.getString("citta"));
                puntoVendita.setIndirizzo(rs.getString("indirizzo"));
                puntoVendita.setTelefono(rs.getString("telefono"));
                puntoVendita.setNome(rs.getString("nome"));
                return puntoVendita;
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
    public ArrayList<PuntoVendita> findByManager(int idManager){
        String sql = "SELECT * FROM progetto_pis.punto_vendita WHERE manager_utente_idutente = '" + idManager + "';";
        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        ArrayList<PuntoVendita> puntiVendita = new ArrayList<>();
        try {
            while (rs.next()) {
                puntoVendita = new PuntoVendita();
                puntoVendita.setIdPuntoVendita(rs.getInt("idpunto_vendita"));
                puntoVendita.setIdManager(rs.getInt("manager_utente_idutente"));
                puntoVendita.setIdMagazzino(rs.getInt("magazzino_idmagazzino"));
                puntoVendita.setCitta(rs.getString("citta"));
                puntoVendita.setIndirizzo(rs.getString("indirizzo"));
                puntoVendita.setTelefono(rs.getString("telefono"));
                puntoVendita.setNome(rs.getString("nome"));
                puntiVendita.add(puntoVendita);
            }
            return puntiVendita;
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
    public ArrayList<PuntoVendita> findAll() {
        String sql = "SELECT * FROM progetto_pis.punto_vendita ;";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        ArrayList<PuntoVendita> puntiVendita = new ArrayList<>();
        try {
            while (rs.next()) {
                puntoVendita = new PuntoVendita();
                puntoVendita.setIdPuntoVendita(rs.getInt("idpunto_vendita"));
                puntoVendita.setIdManager(rs.getInt("manager_utente_idutente"));
                puntoVendita.setIdMagazzino(rs.getInt("magazzino_idmagazzino"));
                puntoVendita.setCitta(rs.getString("citta"));
                puntoVendita.setIndirizzo(rs.getString("indirizzo"));
                puntoVendita.setTelefono(rs.getString("telefono"));
                puntoVendita.setNome(rs.getString("nome"));

                puntiVendita.add(puntoVendita);
            }
            return puntiVendita;
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
    public int add(PuntoVendita puntoVendita) {
        String sql = "INSERT INTO progetto_pis.puntoVendita (manager_utente_idutente, magazzino_idmagazzino, citta, indirizzo, telefono) VALUES ('"+
                puntoVendita.getIdManager() + "',"+
                puntoVendita.getIdMagazzino()+ "',"+
                puntoVendita.getCitta()+ "',"+
                puntoVendita.getIndirizzo()+ "',"+
                puntoVendita.getTelefono() + "');";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        return executor.executeOperation(writeOp).getRowsAffected();
    }

    @Override
    public int removeById(int id) {

        String sql = "DELETE FROM progetto_pis.punto_vendita " +
                "WHERE idpunto_vendita = '" + id + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        return executor.executeOperation(writeOp).getRowsAffected();
    }

    public int removeByManager(int idManager) {

        String sql = "DELETE FROM progetto_pis.punto_vendita " +
                "WHERE manager_utente_idutente = '" + idManager + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        return executor.executeOperation(writeOp).getRowsAffected();
    }

    @Override
    public int update(PuntoVendita puntoVendita) {
        String sql = "UPDATE progetto_pis.punto_vendita " +
                "SET manager_utente_idutente = '" + puntoVendita.getIdManager() +
                "', magazzino_idmagazzino ='" + puntoVendita.getIdMagazzino() +
                "', citta ='" + puntoVendita.getCitta() +
                "', indirizzo ='" + puntoVendita.getIndirizzo() +
                "', telefono ='" + puntoVendita.getTelefono() +
                "' WHERE idpunto_vendita = '" + puntoVendita.getIdPuntoVendita() + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        return executor.executeOperation(writeOp).getRowsAffected();
    }
}
