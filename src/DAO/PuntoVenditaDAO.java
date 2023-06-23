package DAO;

import DbInterface.IDbConnection;
import DbInterface.command.DbOperationExecutor;
import DbInterface.command.IDbOperation;
import DbInterface.command.ReadOperation;
import DbInterface.command.WriteOperation;
import Model.Magazzino;
import Model.Manager;
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
                puntoVendita.setCitta(rs.getString("citta"));
                puntoVendita.setIndirizzo(rs.getString("indirizzo"));
                puntoVendita.setTelefono(rs.getString("telefono"));
                puntoVendita.setNome(rs.getString("nome"));

                Manager manager = ManagerDAO.getInstance().findById(rs.getInt("manager_utente_idutente"));
                if(manager != null)
                    puntoVendita.setManager(manager);

                Magazzino magazzino = MagazzinoDAO.getInstance().findById(rs.getInt("magazzino_idmagazzino"));
                if(magazzino != null)
                    puntoVendita.setMagazzino(magazzino);

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
                puntoVendita.setCitta(rs.getString("citta"));
                puntoVendita.setIndirizzo(rs.getString("indirizzo"));
                puntoVendita.setTelefono(rs.getString("telefono"));
                puntoVendita.setNome(rs.getString("nome"));

                Manager manager = ManagerDAO.getInstance().findById(rs.getInt("manager_utente_idutente"));
                if(manager != null)
                    puntoVendita.setManager(manager);

                Magazzino magazzino = MagazzinoDAO.getInstance().findById(rs.getInt("magazzino_idmagazzino"));
                if(magazzino != null)
                    puntoVendita.setMagazzino(magazzino);

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
    public PuntoVendita findByManager(int idManager) {
        String sql = "SELECT * FROM progetto_pis.punto_vendita WHERE manager_utente_idutente = '" + idManager + "';";
        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        try {
            rs.next();
            if (rs.getRow()==1) {
                puntoVendita = new PuntoVendita();
                puntoVendita.setIdPuntoVendita(rs.getInt("idpunto_vendita"));
                puntoVendita.setCitta(rs.getString("citta"));
                puntoVendita.setIndirizzo(rs.getString("indirizzo"));
                puntoVendita.setTelefono(rs.getString("telefono"));
                puntoVendita.setNome(rs.getString("nome"));

                Manager manager = ManagerDAO.getInstance().findById(rs.getInt("manager_utente_idutente"));
                if(manager != null)
                    puntoVendita.setManager(manager);

                Magazzino magazzino = MagazzinoDAO.getInstance().findById(rs.getInt("magazzino_idmagazzino"));
                if(magazzino != null)
                    puntoVendita.setMagazzino(magazzino);

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
                puntoVendita.setCitta(rs.getString("citta"));
                puntoVendita.setIndirizzo(rs.getString("indirizzo"));
                puntoVendita.setTelefono(rs.getString("telefono"));
                puntoVendita.setNome(rs.getString("nome"));

                Manager manager = ManagerDAO.getInstance().findById(rs.getInt("manager_utente_idutente"));
                if(manager != null)
                    puntoVendita.setManager(manager);

                Magazzino magazzino = MagazzinoDAO.getInstance().findById(rs.getInt("magazzino_idmagazzino"));
                if(magazzino != null)
                    puntoVendita.setMagazzino(magazzino);

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
        String sql;

        //ne manager ne magazzino sono null
        if(puntoVendita.getManager().getUsername() != null && puntoVendita.getMagazzino().getIndirizzo() != null)
            sql = "INSERT INTO progetto_pis.punto_vendita (manager_utente_idutente, magazzino_idmagazzino, citta, indirizzo, telefono, nome) VALUES ('"+
                    puntoVendita.getManager().getIdUtente() + "', '" +
                    puntoVendita.getMagazzino().getIdMagazzino() + "', '" +
                    puntoVendita.getCitta() + "', '" +
                    puntoVendita.getIndirizzo() + "', '" +
                    puntoVendita.getTelefono() + "', '" +
                    puntoVendita.getNome() + "');";

        //manager è vuoto
        else if(puntoVendita.getManager().getUsername() == null && puntoVendita.getMagazzino().getIndirizzo() != null)
            sql = "INSERT INTO progetto_pis.punto_vendita (magazzino_idmagazzino, citta, indirizzo, telefono, nome) VALUES ('"+
                    puntoVendita.getMagazzino().getIdMagazzino() + "', '" +
                    puntoVendita.getCitta() + "', '" +
                    puntoVendita.getIndirizzo() + "', '" +
                    puntoVendita.getTelefono() + "', '" +
                    puntoVendita.getNome() + "');";

        //magazzino è null
        else if(puntoVendita.getManager().getUsername() != null && puntoVendita.getMagazzino().getIndirizzo() == null)
            sql = "INSERT INTO progetto_pis.punto_vendita (manager_utente_idutente, citta, indirizzo, telefono, nome) VALUES ('"+
                    puntoVendita.getManager().getIdUtente() + "', '" +
                    puntoVendita.getCitta() + "', '" +
                    puntoVendita.getIndirizzo() + "', '" +
                    puntoVendita.getTelefono() + "', '" +
                    puntoVendita.getNome() + "');";

        //sia magazzino che manager sono null
        else
            sql = "INSERT INTO progetto_pis.punto_vendita (citta, indirizzo, telefono, nome) VALUES ('"+
                    puntoVendita.getCitta() + "', '" +
                    puntoVendita.getIndirizzo() + "', '" +
                    puntoVendita.getTelefono() + "', '" +
                    puntoVendita.getNome() + "');";

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
        String sql;

        //ne manager ne magazzino sono null
        if(puntoVendita.getManager() != null && puntoVendita.getMagazzino() != null)
            sql = "UPDATE progetto_pis.punto_vendita " +
                    "SET manager_utente_idutente = '" + puntoVendita.getManager().getIdUtente() +
                    "', magazzino_idmagazzino ='" + puntoVendita.getMagazzino().getIdMagazzino() +
                    "', citta ='" + puntoVendita.getCitta() +
                    "', indirizzo ='" + puntoVendita.getIndirizzo() +
                    "', telefono ='" + puntoVendita.getTelefono() +
                    "' WHERE idpunto_vendita = '" + puntoVendita.getIdPuntoVendita() + "';";

        //manager è null
        else if(puntoVendita.getManager() == null && puntoVendita.getMagazzino() != null)
            sql = "UPDATE progetto_pis.punto_vendita " +
                    "SET magazzino_idmagazzino ='" + puntoVendita.getMagazzino().getIdMagazzino() +
                    "', citta ='" + puntoVendita.getCitta() +
                    "', indirizzo ='" + puntoVendita.getIndirizzo() +
                    "', telefono ='" + puntoVendita.getTelefono() +
                    "' WHERE idpunto_vendita = '" + puntoVendita.getIdPuntoVendita() + "';";

        //magazzino è null
        else if(puntoVendita.getManager() != null && puntoVendita.getMagazzino() == null)
            sql = "UPDATE progetto_pis.punto_vendita " +
                    "SET manager_utente_idutente = '" + puntoVendita.getManager().getIdUtente() +
                    "', citta ='" + puntoVendita.getCitta() +
                    "', indirizzo ='" + puntoVendita.getIndirizzo() +
                    "', telefono ='" + puntoVendita.getTelefono() +
                    "' WHERE idpunto_vendita = '" + puntoVendita.getIdPuntoVendita() + "';";

            //sia magazzino che manager sono null
        else
            sql = "UPDATE progetto_pis.punto_vendita " +
                    "SET citta ='" + puntoVendita.getCitta() +
                    "', indirizzo ='" + puntoVendita.getIndirizzo() +
                    "', telefono ='" + puntoVendita.getTelefono() +
                    "' WHERE idpunto_vendita = '" + puntoVendita.getIdPuntoVendita() + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        return executor.executeOperation(writeOp).getRowsAffected();
    }
}
