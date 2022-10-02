package DAO;

import DbInterface.IDbConnection;
import Model.composite.ProdottoComposito;

import java.sql.ResultSet;


public class ProdottoCompositoDAO{
    private static ProdottoCompositoDAO instance = new ProdottoCompositoDAO();
    private ProdottoComposito prodottoComposito;
    private static IDbConnection conn;
    private static ResultSet rs;

    private ProdottoCompositoDAO() {
        prodottoComposito = null;
        conn = null;
        rs = null;
    }

    public static ProdottoCompositoDAO getInstance() {
        return instance;
    }
/*
    @Override
    public ProdottoComposito findById(int idProdottoComposito) {
        String sql = "SELECT prodotto_articolo_idarticolo, prodotto_articolo_idarticolo1 " +
                "FROM progetto_pis.prodotto_has_prodotto " +
                "WHERE prodotto_articolo_idarticolo = '" + idProdottoComposito + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        try {
            rs.next();
            if (rs.getRow()==1) {
                prodottoComposito = new ProdottoComposito();
                prodottoComposito.setIdPrenotazione(rs.getInt("idprenotazione"));
                prodottoComposito.setIdUtente(rs.getInt("utente_acquirente_utente_idutente"));
                return prodottoComposito;
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

    public ArrayList<ProdottoComposito> findByUser(int idUtenteAcquirente){

        String sql = "SELECT idprenotazione, utente_acquirente_utente_idutente " +
                "FROM progetto_pis.prenotazione " +
                "WHERE utente_acquirente_utente_idutente = '" + idUtenteAcquirente + "';";
        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        ArrayList<ProdottoComposito> prenotazioni = new ArrayList<>();
        try {
            while (rs.next()) {
                prodottoComposito = new ProdottoComposito();
                prodottoComposito.setIdPrenotazione(rs.getInt("idlista_acquisto"));
                prodottoComposito.setIdUtente(rs.getInt("utente_acquirente_utente_idutente"));

                prenotazioni.add(prodottoComposito);
            }
            return prenotazioni;
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
    public ArrayList<ProdottoComposito> findAll() {
        String sql = "SELECT idprenotazione, utente_acquirente_utente_idutente " +
                "FROM progetto_pis.prenotazione;";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        ArrayList<ProdottoComposito> prenotazioni = new ArrayList<>();
        try {
            while (rs.next()) {
                prodottoComposito = new ProdottoComposito();
                prodottoComposito.setIdPrenotazione(rs.getInt("idprenotazione"));
                prodottoComposito.setIdUtente(rs.getInt("utente_acquirente_utente_idutente"));
                prenotazioni.add(prodottoComposito);
            }
            return prenotazioni;
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
    public int add(ProdottoComposito prenotazione) {
        String sql = "INSERT INTO progetto_pis.prenotazione (utente_acquirente_utente_idutente) VALUES ('"+
                prenotazione.getIdUtente() + "');";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        return executor.executeOperation(writeOp).getRowsAffected();
    }

    @Override
    public int removeById(int id) {

        String sql = "DELETE FROM progetto_pis.prenotazione " +
                "WHERE idprenotazione = '" + id + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        return executor.executeOperation(writeOp).getRowsAffected();
    }

    public int removeByUser(int idUtenteAcquirente) {

        String sql = "DELETE FROM progetto_pis.prenotazione " +
                "WHERE utente_acquirente_utente_idutente = '" + idUtenteAcquirente + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        return executor.executeOperation(writeOp).getRowsAffected();
    }

    @Override
    public int update(ProdottoComposito prenotazione) {
        String sql = "UPDATE progetto_pis.prenotazione " +
                "SET utente_acquirente_utente_idutente = '" + prenotazione.getIdUtente() +
                "' WHERE idprenotazione = '" + prenotazione.getIdPrenotazione() + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        return executor.executeOperation(writeOp).getRowsAffected();
    }

 */
}
