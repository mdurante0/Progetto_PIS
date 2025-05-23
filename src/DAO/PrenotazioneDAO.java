package DAO;

import DbInterface.IDbConnection;
import DbInterface.command.DbOperationExecutor;
import DbInterface.command.IDbOperation;
import DbInterface.command.ReadOperation;
import DbInterface.command.WriteOperation;
import Model.Prenotazione;
import Model.composite.IProdotto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class PrenotazioneDAO implements IPrenotazioneDAO {
    private static PrenotazioneDAO instance = new PrenotazioneDAO();
    private Prenotazione prenotazione;
    private static IDbConnection conn;
    private static ResultSet rs;

    private PrenotazioneDAO (){
        prenotazione = null;
        conn = null;
        rs = null;
    }
    public static PrenotazioneDAO getInstance() {
        return instance;
    }

    @Override
    public Prenotazione findById(int idPrenotazione) {

        String sql = "SELECT * FROM progetto_pis.prenotazione WHERE idprenotazione = '" + idPrenotazione + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            rs.next();
            if (rs.getRow()==1) {
                prenotazione = new Prenotazione();
                prenotazione.setIdPrenotazione(rs.getInt("idprenotazione"));
                prenotazione.setIdUtente(rs.getInt("utente_acquirente_utente_idutente"));
                prenotazione.setDataPrenotazione(formato.parse(rs.getString("data_prenotazione")));

                sql = "SELECT * FROM progetto_pis.prenotazione_has_prodotto WHERE prenotazione_idprenotazione = '" + prenotazione.getIdPrenotazione() + "';";
                executor = new DbOperationExecutor();
                readOp = new ReadOperation(sql);
                rs = executor.executeOperation(readOp).getResultSet();

                ProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
                while (rs.next()) {
                    IProdotto prodotto = prodottoDAO.findById(rs.getInt("prodotto_articolo_idarticolo"));
                    prodotto.setQuantita(rs.getInt("quantita"));
                    prenotazione.add(prodotto);
                }

                return prenotazione;
            }
        } catch (SQLException e) {
            // handle any errors
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } catch (NullPointerException e) {
            // handle any errors
            System.out.println("Resultset: " + e.getMessage());
        } catch (ParseException e) {
            System.out.println("Date format " + e.getMessage());;
        }
        return null;
    }

    public ArrayList<Prenotazione> findByUser(int idUtenteAcquirente){

        String sql = "SELECT * FROM progetto_pis.prenotazione WHERE utente_acquirente_utente_idutente = '" + idUtenteAcquirente + "';";
        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        ArrayList<Prenotazione> prenotazioni = new ArrayList<>();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            while (rs.next()) {
                prenotazione = new Prenotazione();
                prenotazione.setIdPrenotazione(rs.getInt("idprenotazione"));
                prenotazione.setIdUtente(rs.getInt("utente_acquirente_utente_idutente"));
                prenotazione.setDataPrenotazione(formato.parse(rs.getString("data_prenotazione")));

                sql = "SELECT * FROM progetto_pis.prenotazione_has_prodotto WHERE prenotazione_idprenotazione = '" + prenotazione.getIdPrenotazione() + "';";
                DbOperationExecutor executor2 = new DbOperationExecutor();
                IDbOperation readOp2 = new ReadOperation(sql);
                ResultSet rs2 = executor2.executeOperation(readOp2).getResultSet();

                ProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
                while (rs2.next()) {
                    IProdotto prodotto = prodottoDAO.findById(rs2.getInt("prodotto_articolo_idarticolo"));
                    prodotto.setQuantita(rs2.getInt("quantita"));
                    prenotazione.add(prodotto);
                }

                prenotazioni.add(prenotazione);
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
        } catch (ParseException e) {
            System.out.println("Date format " + e.getMessage());;
        }

        return null;
    }

    @Override
    public ArrayList<Prenotazione> findAll() {

        String sql = "SELECT * FROM progetto_pis.prenotazione;";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        ArrayList<Prenotazione> prenotazioni = new ArrayList<>();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            while (rs.next()) {
                prenotazione = new Prenotazione();
                prenotazione.setIdPrenotazione(rs.getInt("idprenotazione"));
                prenotazione.setIdUtente(rs.getInt("utente_acquirente_utente_idutente"));
                prenotazione.setDataPrenotazione(formato.parse(rs.getString("data_prenotazione")));

                sql = "SELECT * FROM progetto_pis.prenotazione_has_prodotto WHERE prenotazione_idprenotazione = '" + prenotazione.getIdPrenotazione() + "';";
                DbOperationExecutor executor2 = new DbOperationExecutor();
                IDbOperation readOp2 = new ReadOperation(sql);
                ResultSet rs2 = executor2.executeOperation(readOp2).getResultSet();

                ProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
                while (rs2.next()) {
                    IProdotto prodotto = prodottoDAO.findById(rs2.getInt("prodotto_articolo_idarticolo"));
                    prodotto.setQuantita(rs2.getInt("quantita"));
                    prenotazione.add(prodotto);
                }

                prenotazioni.add(prenotazione);
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
        } catch (ParseException e) {
            System.out.println("Date format " + e.getMessage());;
        }

        return null;
    }

    @Override
    public int add(Prenotazione prenotazione) {
        Date data = prenotazione.getDataPrenotazione();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int rowCount = 0;
        try{
        String formatted = formato.format(data);

        String sql = "INSERT INTO progetto_pis.prenotazione (utente_acquirente_utente_idutente, data_prenotazione) VALUES ('"+
                prenotazione.getIdUtente() + "','" +  formatted + "');";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        executor.executeOperation(writeOp);

        executor = new DbOperationExecutor();
        sql = "SELECT max(idprenotazione) FROM progetto_pis.prenotazione;";
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

            rs.next();
            prenotazione.setIdPrenotazione(rs.getInt("max(idprenotazione)"));

            Iterator<IProdotto> prodottoIterator = prenotazione.getProdotti().iterator();
            while (prodottoIterator.hasNext()) {

                IProdotto prodottoPrenotato = prodottoIterator.next();
                sql = "INSERT INTO progetto_pis.prenotazione_has_prodotto " +
                        "(prenotazione_idprenotazione, prodotto_articolo_idarticolo, quantita) VALUES ('" +
                        prenotazione.getIdPrenotazione() + "','" +
                        prodottoPrenotato.getIdArticolo() + "','" +
                        prodottoPrenotato.getQuantita() + "');";
                writeOp = new WriteOperation(sql);

                rowCount += executor.executeOperation(writeOp).getRowsAffected();
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
        return rowCount;
    }

    @Override
    public int addProdotto(int idPrenotazione, IProdotto prodottoPrenotato) {

        String sql = "INSERT INTO progetto_pis.prenotazione_has_prodotto " +
                "(prenotazione_idprenotazione, prodotto_articolo_idarticolo, quantita) VALUES ('" +
                idPrenotazione + "','" +
                prodottoPrenotato.getIdArticolo() + "','" +
                prodottoPrenotato.getQuantita() + "');";
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

    @Override
    public int removeByUser(int idUtenteAcquirente) {

        String sql = "DELETE FROM progetto_pis.prenotazione " +
                "WHERE utente_acquirente_utente_idutente = '" + idUtenteAcquirente + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        return executor.executeOperation(writeOp).getRowsAffected();
    }

    @Override
    public int removeProdotto(int idPrenotazione, IProdotto prodottoPrenotato){

        String sql = "DELETE FROM progetto_pis.prenotazione_has_prodotto " +
                "WHERE prenotazione_idprenotazione = '" + idPrenotazione +
                "' AND prodotto_articolo_idarticolo = '" + prodottoPrenotato.getIdArticolo() + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        return executor.executeOperation(writeOp).getRowsAffected();
    }

    @Override
    public int update(Prenotazione prenotazione) {
        Date data = prenotazione.getDataPrenotazione();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int rowCount = 0;
        String formatted = formato.format(data);
        String sql = "UPDATE progetto_pis.prenotazione " +
                "SET  data_prenotazione = '" + formatted +
                "' WHERE idprenotazione = '" + prenotazione.getIdPrenotazione() + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        executor.executeOperation(writeOp);

        Iterator<IProdotto> prodottoIterator = prenotazione.getProdotti().iterator();
        int i=0;
        while (prodottoIterator.hasNext()) {

            IProdotto prodottoPrenotato = prodottoIterator.next();

            sql = "UPDATE progetto_pis.prenotazione_has_prodotto " +
                    "SET  quantita = '" + prodottoPrenotato.getQuantita() +
                    "' WHERE prenotazione_idprenotazione = '" + prenotazione.getIdPrenotazione() + "' && prodotto_articolo_idarticolo = '"+prenotazione.getProdotti().get(i).getIdArticolo()+"';";

            writeOp = new WriteOperation(sql);
            rowCount += executor.executeOperation(writeOp).getRowsAffected();
            i++;
        }
        return rowCount;
    }
}
