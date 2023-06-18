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

        String sql = "SELECT idprenotazione, utente_acquirente_utente_idutente, data_prenotazione, prodotto_articolo_idarticolo, quantita " +
                "FROM progetto_pis.prenotazione AS p INNER JOIN progetto_pis.prenotazione_has_prodotto AS pp " +
                "ON p.idprenotazione = pp.prenotazione_idprenotazione " +
                "WHERE idprenotazione = '" + idPrenotazione + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        try {
            rs.next();
            if (rs.getRow()==1) {
                prenotazione = new Prenotazione();
                prenotazione.setIdPrenotazione(rs.getInt("idprenotazione"));
                prenotazione.setIdUtente(rs.getInt("utente_acquirente_utente_idutente"));
                prenotazione.setDataPrenotazione(rs.getDate("data_prenotazione"));

                ProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
                int idProdotto;
                int quantita;
                do {
                    idProdotto = rs.getInt("prodotto_articolo_idarticolo");
                    quantita = rs.getInt("quantita");
                    IProdotto prodotto = prodottoDAO.findById(idProdotto);
                    prodotto.setQuantita(quantita);
                    prenotazione.add(prodotto);
                }while (rs.next());

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
        }
        return null;
    }

    public ArrayList<Prenotazione> findByUser(int idUtenteAcquirente){

        String sql = "SELECT idprenotazione, utente_acquirente_utente_idutente, data_prenotazione, prodotto_articolo_idarticolo, quantita " +
                "FROM progetto_pis.prenotazione AS p INNER JOIN progetto_pis.prenotazione_has_prodotto AS pp " +
        "ON p.idprenotazione = pp.prenotazione_idprenotazione " +
                "WHERE utente_acquirente_utente_idutente = '" + idUtenteAcquirente + "';";
        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        ArrayList<Prenotazione> prenotazioni = new ArrayList<>();
        try {
            while (rs.next()) {
                prenotazione = new Prenotazione();
                prenotazione.setIdPrenotazione(rs.getInt("idprenotazione"));
                prenotazione.setIdUtente(rs.getInt("utente_acquirente_utente_idutente"));
                prenotazione.setDataPrenotazione(rs.getDate("data_prenotazione"));

                ProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
                int idProdotto;
                int quantita;
                do {
                    idProdotto = rs.getInt("prodotto_articolo_idarticolo");
                    quantita = rs.getInt("quantita");
                    IProdotto prodotto = prodottoDAO.findById(idProdotto);
                    prodotto.setQuantita(quantita);
                    prenotazione.add(prodotto);
                }while (rs.next());

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
        }

        return null;
    }

    @Override
    public ArrayList<Prenotazione> findAll() {

        String sql = "SELECT idprenotazione, utente_acquirente_utente_idutente, data_prenotazione, prodotto_articolo_idarticolo, quantita " +
                "FROM progetto_pis.prenotazione AS p INNER JOIN progetto_pis.prenotazione_has_prodotto AS pp " +
                "ON p.idprenotazione = pp.prenotazione_idprenotazione;";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        ArrayList<Prenotazione> prenotazioni = new ArrayList<>();
        try {
            while (rs.next()) {
                prenotazione = new Prenotazione();
                prenotazione.setIdPrenotazione(rs.getInt("idprenotazione"));
                prenotazione.setIdUtente(rs.getInt("utente_acquirente_utente_idutente"));
                prenotazione.setDataPrenotazione(rs.getDate("data_prenotazione"));

                ProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
                int idProdotto;
                int quantita;
                do {
                    idProdotto = rs.getInt("prodotto_articolo_idarticolo");
                    quantita = rs.getInt("quantita");
                    IProdotto prodotto = prodottoDAO.findById(idProdotto);
                    prodotto.setQuantita(quantita);
                    prenotazione.add(prodotto);
                }while (rs.next());

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
        }

        return null;
    }

    @Override
    public int add(Prenotazione prenotazione) {
        Date data = prenotazione.getDataPrenotazione();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int rowCount = 0;
        try{
        String s = formato.format(data);
        Date d = formato.parse(s);
        formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String sql = "INSERT INTO progetto_pis.prenotazione (utente_acquirente_utente_idutente, data_prenotazione) VALUES ('"+
                prenotazione.getIdUtente() + "','" +  formato.format(d) + "');";

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
        }catch  (ParseException e) {
            System.out.println("Formato data non valido.");
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
        try {
            String s = formato.format(data);
            Date d = formato.parse(s);
            formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String sql = "UPDATE progetto_pis.prenotazione " +
                    "SET  data_prenotazione = '" + formato.format(d) +
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
        }catch  (ParseException e) {
            System.out.println("Formato data non valido.");
        }
        return rowCount;
    }
}
