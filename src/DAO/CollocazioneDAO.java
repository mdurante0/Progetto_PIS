package DAO;

import DbInterface.IDbConnection;
import DbInterface.command.DbOperationExecutor;
import DbInterface.command.IDbOperation;
import DbInterface.command.ReadOperation;
import DbInterface.command.WriteOperation;
import Model.Collocazione;
import Model.Magazzino;

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
    public Collocazione findById(int idCollocazione) {

        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "SELECT idcollocazione, scaffale, corsia, magazzino_idmagazzino FROM progetto_pis.collocazione " +
                "WHERE idcollocazione = '"+ idCollocazione +"';";
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        try {
            rs.next();
            if (rs.getRow()==1) {
                collocazione = new Collocazione();
                collocazione.getMagazzino().setIdMagazzino(rs.getInt("magazzino_idmagazzino"));
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
    public Collocazione findByCorsiaScaffaleAndMagazzino(int corsia, int scaffale, int idMagazzino) {

        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "SELECT idcollocazione, scaffale, corsia, magazzino_idmagazzino FROM progetto_pis.collocazione " +
                "WHERE corsia = '" + corsia + "' AND scaffale = '" + scaffale + "' AND magazzino_idmagazzino = '" + idMagazzino + "';";
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        try {
            rs.next();
            if (rs.getRow()==1) {
                collocazione = new Collocazione();
                collocazione.getMagazzino().setIdMagazzino(rs.getInt("magazzino_idmagazzino"));
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
    public Collocazione findByMagazzinoAndProdotto(int idMagazzino, int idProdotto) {

        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "SELECT * FROM progetto_pis.collocazione AS c INNER JOIN progetto_pis.magazzino_has_prodotto AS mp ON c.idcollocazione = mp.collocazione_idcollocazione WHERE c.magazzino_idmagazzino = '"+ idMagazzino +"' AND mp.prodotto_articolo_idarticolo = '" + idProdotto + "';";
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        try {
            rs.next();
            if (rs.getRow()==1) {
                collocazione = new Collocazione();
                collocazione.getMagazzino().setIdMagazzino(rs.getInt("magazzino_idmagazzino"));
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
        String sql = "SELECT idcollocazione, corsia, scaffale, magazzino_idmagazzino FROM progetto_pis.collocazione;";
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        ArrayList<Collocazione> collocazioni = new ArrayList<>();
        try {
            while (rs.next()) {
                collocazione = new Collocazione();
                collocazione.getMagazzino().setIdMagazzino(rs.getInt("magazzino_idmagazzino"));
                collocazione.setIdCollocazione(rs.getInt("idcollocazione"));
                collocazione.setCorsia(rs.getInt("corsia"));
                collocazione.setScaffale(rs.getInt("scaffale"));
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
    public ArrayList<Collocazione> findAllByProdotto(int idProdotto) {
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "SELECT * FROM progetto_pis.collocazione AS c INNER JOIN progetto_pis.magazzino_has_prodotto AS mp ON c.idcollocazione = mp.collocazione_idcollocazione WHERE mp.prodotto_articolo_idarticolo = '" + idProdotto + "';";
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        ArrayList<Collocazione> collocazioni = new ArrayList<>();
        try {
            while (rs.next()) {
                collocazione = new Collocazione();
                collocazione.getMagazzino().setIdMagazzino(rs.getInt("magazzino_idmagazzino"));
                collocazione.setIdCollocazione(rs.getInt("idcollocazione"));
                collocazione.setCorsia(rs.getInt("corsia"));
                collocazione.setScaffale(rs.getInt("scaffale"));
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
    public ArrayList<Collocazione> findAllByMagazzino(int idMagazzino) {
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "SELECT idcollocazione, corsia, scaffale, magazzino_idmagazzino FROM progetto_pis.collocazione WHERE magazzino_idmagazzino = '"+ idMagazzino +"';";
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        ArrayList<Collocazione> collocazioni = new ArrayList<>();
        try {
            while (rs.next()) {
                collocazione = new Collocazione();
                collocazione.getMagazzino().setIdMagazzino(rs.getInt("magazzino_idmagazzino"));
                collocazione.setIdCollocazione(rs.getInt("idcollocazione"));
                collocazione.setCorsia(rs.getInt("corsia"));
                collocazione.setScaffale(rs.getInt("scaffale"));
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

        if(!isFree(collocazione))
            return 0;

        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "INSERT INTO progetto_pis.collocazione (corsia, scaffale, magazzino_idmagazzino) VALUES ('"+
                collocazione.getCorsia() + "','" +
                collocazione.getScaffale() + "','" +
                collocazione.getMagazzino().getIdMagazzino() + "');";
        IDbOperation writeOp = new WriteOperation(sql);
        int rowCount = executor.executeOperation(writeOp).getRowsAffected();

        sql = "SELECT max(idcollocazione) FROM progetto_pis.collocazione;";
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        try {
            rs.next();
            collocazione.setIdCollocazione(rs.getInt("max(idcollocazione)"));

        } catch (SQLException e) {
            // handle any errors
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } catch (NullPointerException e) {
            // handle any errors
            System.out.println("Resultset: " + e.getMessage());
        }

        return  rowCount;
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

        if(!checkCollocazione(collocazione))
            return 0;

        String sql = "UPDATE progetto_pis.collocazione " +
                "SET corsia = '" + collocazione.getCorsia() +
                "', scaffale = '"+ collocazione.getScaffale() +
                "', magazzino_idmagazzino = '"+ collocazione.getMagazzino().getIdMagazzino() +
                "' WHERE idcollocazione = '" + collocazione.getIdCollocazione() + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        return executor.executeOperation(writeOp).getRowsAffected();
    }

    public boolean checkCollocazione(Collocazione collocazione){

        MagazzinoDAO magazzinoDAO = MagazzinoDAO.getInstance();
        Magazzino magazzino = magazzinoDAO.findById(collocazione.getMagazzino().getIdMagazzino());

        //se il numero di corsie presenti nel magazzino è maggiore o uguale alla corsia scelta
        // e se il numero di scaffali presenti è maggiore o uguale allo scaffale scelto
        //allora la collocazione scelta esiste nel magazzino, restituiamo true
        return magazzino.getQuantitaCorsie() >= collocazione.getCorsia() &&
                magazzino.getQuantitaScaffali() >= collocazione.getScaffale();
    }

    public boolean isFree(Collocazione collocazione) {

        if(!checkCollocazione(collocazione))
            return false; //la posizione non esiste, impedisco l'inserimento del prodotto

        String sql = "SELECT count(*) AS count FROM progetto_pis.collocazione AS c " +
                "WHERE c.corsia='"+ collocazione.getCorsia() +
                "' && c.scaffale = '" + collocazione.getScaffale() +
                "' && c.magazzino_idmagazzino = '" + collocazione.getMagazzino().getIdMagazzino() + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        try {
            rs.next();
            if (rs.getRow() == 1) {
                int count = rs.getInt("count");
                return count == 0;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}