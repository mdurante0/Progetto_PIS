package DAO;

import DbInterface.IDbConnection;
import DbInterface.command.DbOperationExecutor;
import DbInterface.command.IDbOperation;
import DbInterface.command.ReadOperation;
import DbInterface.command.WriteOperation;
import Model.Articolo;
import Model.ListaAcquisto;
import Model.composite.Prodotto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

public class ListaAcquistoDAO implements IListaAcquistoDAO {
    private static ListaAcquistoDAO instance = new ListaAcquistoDAO();
    private ListaAcquisto listaAcquisto;
    private static IDbConnection conn;
    private static ResultSet rs;

    private ListaAcquistoDAO(){
        listaAcquisto = null;
        conn = null;
        rs = null;
    }

    public static ListaAcquistoDAO getInstance() {
        return instance;
    }

    @Override
    public ListaAcquisto findById(int idLista) {
        String sql = "SELECT idlista_acquisto, utente_acquirente_utente_idutente, pagata, costo_finale, " +
                "prodotto_articolo_idarticolo, quantita " +
                "FROM progetto_pis.lista_acquisto AS l INNER JOIN progetto_pis.lista_acquisto_has_articolo AS la" +
                " ON l.idlista_acquisto = la.lista_acquisto_idlista_acquisto" +
                "WHERE idlista_acquisto = '" + idLista + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        try {
            rs.next();
            if (rs.getRow()==1) {
                listaAcquisto = new ListaAcquisto();
                listaAcquisto.setIdLista(rs.getInt("idlista_acquisto"));
                listaAcquisto.setIdUtente(rs.getInt("utente_acquirente_utente_idutente"));
                listaAcquisto.setPagata(rs.getBoolean("pagata"));
                listaAcquisto.setCostoFinale(rs.getFloat("costo_finale"));

                ProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
                ServizioDAO servizioDAO = ServizioDAO.getInstance();
                Prodotto prodotto;
                while (rs.next()){
                    prodotto = prodottoDAO.findById(rs.getInt("prodotto_articolo_idarticolo"));
                    prodotto.setQuantita(rs.getInt("quantita"));
                    listaAcquisto.add(prodotto);
                    listaAcquisto.add(servizioDAO.findById(rs.getInt("servizio_articolo_idarticolo")));

                }

                return listaAcquisto;
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
    public ArrayList<ListaAcquisto> findAll() {
        String sql = "SELECT idlista_acquisto, utente_acquirente_utente_idutente, pagata, costo_finale, " +
                "articolo_idarticolo, quantita " +
                "FROM progetto_pis.lista_acquisto AS l INNER JOIN progetto_pis.lista_acquisto_has_articolo AS la" +
                " ON l.idlista_acquisto = la.lista_acquisto_idlista_acquisto";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        ArrayList<ListaAcquisto> listeAcquisto = new ArrayList<>();
        try {
            while (rs.next()) {
                listaAcquisto = new ListaAcquisto();
                listaAcquisto.setIdLista(rs.getInt("idlista_acquisto"));
                listaAcquisto.setIdUtente(rs.getInt("utente_acquirente_utente_idutente"));
                listaAcquisto.setPagata(rs.getBoolean("pagata"));
                listaAcquisto.setCostoFinale(rs.getFloat("costo_finale"));

                ArticoloDAO articoloDAO = ArticoloDAO.getInstance();
                Articolo articolo;
                while (rs.next()){
                    articolo = articoloDAO.findById(rs.getInt("prodotto_articolo_idarticolo"));
                    articolo.setQuantita(rs.getInt("quantita"));
                    listaAcquisto.add(articolo);
                    listaAcquisto.add(articoloDAO.findById(rs.getInt("servizio_articolo_idarticolo")));

                }

                listeAcquisto.add(listaAcquisto);
            }
            return listeAcquisto;
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

    public ArrayList<ListaAcquisto> findNotPaid() {
        String sql = "SELECT idlista_acquisto, utente_acquirente_utente_idutente, pagata, costo_finale, " +
                "articolo_idarticolo, quantita " +
                "FROM progetto_pis.lista_acquisto AS l INNER JOIN progetto_pis.lista_acquisto_has_acquisto AS la" +
                " ON l.idlista_acquisto = la.lista_acquisto_idlista_acquisto" +
                "WHERE pagata = '0';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        ArrayList<ListaAcquisto> listeAcquisto = new ArrayList<>();
        try {
            while (rs.next()) {
                listaAcquisto = new ListaAcquisto();
                listaAcquisto.setIdLista(rs.getInt("idlista_acquisto"));
                listaAcquisto.setIdUtente(rs.getInt("utente_acquirente_utente_idutente"));
                listaAcquisto.setPagata(rs.getBoolean("pagata"));
                listaAcquisto.setCostoFinale(rs.getFloat("costo_finale"));

                ProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
                ServizioDAO servizioDAO = ServizioDAO.getInstance();
                Prodotto prodotto;
                while (rs.next()){
                    prodotto = prodottoDAO.findById(rs.getInt("prodotto_articolo_idarticolo"));
                    prodotto.setQuantita(rs.getInt("quantita"));
                    listaAcquisto.add(prodotto);
                    listaAcquisto.add(servizioDAO.findById(rs.getInt("servizio_articolo_idarticolo")));

                }

                listeAcquisto.add(listaAcquisto);
            }
            return listeAcquisto;
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

    public ArrayList<ListaAcquisto> findByUser(int idUtenteAcquirente){

        String sql = "SELECT idlista_acquisto, utente_acquirente_utente_idutente, pagata, costo_finale, " +
                "articolo_idarticolo, quantita " +
                "FROM progetto_pis.lista_acquisto AS l INNER JOIN progetto_pis.lista_acquisto_has_articolo AS la" +
                " ON l.idlista_acquisto = la.lista_acquisto_idlista_acquisto" +
                "WHERE utente_acquirente_utente_idutente = '" + idUtenteAcquirente + "';";
        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        ArrayList<ListaAcquisto> listeAcquisto = new ArrayList<>();
        try {
            while (rs.next()) {
                listaAcquisto = new ListaAcquisto();
                listaAcquisto.setIdLista(rs.getInt("idlista_acquisto"));
                listaAcquisto.setIdUtente(rs.getInt("utente_acquirente_utente_idutente"));
                listaAcquisto.setPagata(rs.getBoolean("pagata"));
                listaAcquisto.setCostoFinale(rs.getFloat("costo_finale"));

                ArticoloDAO articoloDAO = ArticoloDAO.getInstance();
                Articolo articolo;
                while (rs.next()){
                    articolo = articoloDAO.findById(rs.getInt("prodotto_articolo_idarticolo"));
                    articolo.setQuantita(rs.getInt("quantita"));
                    listaAcquisto.add(articolo);
                    listaAcquisto.add(articoloDAO.findById(rs.getInt("servizio_articolo_idarticolo")));

                }

                listeAcquisto.add(listaAcquisto);
            }
            return listeAcquisto;
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
    public int add(ListaAcquisto listaAcquisto) {
        String sql = "INSERT INTO progetto_pis.lista_acquisto (utente_acquirente_utente_idutente, pagata, costo_finale) VALUES ('"+
                listaAcquisto.getIdUtente() + "','" +
                listaAcquisto.isPagata() + "','" +
                listaAcquisto.getCostoFinale()+"');";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        executor.executeOperation(writeOp);

        executor = new DbOperationExecutor();
        sql = "SELECT max(idlista_acquisto) FROM progetto_pis.lista_acquisto;";
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        int rowCount = 0;
        try {
            rs.next();
            listaAcquisto.setIdLista(rs.getInt("max(idlista_acquisto)"));

            ArticoloDAO articoloDAO = ArticoloDAO.getInstance();
            Iterator<Articolo> articoloIterator = listaAcquisto.getArticoli().iterator();
            Articolo articolo;
            while (articoloIterator.hasNext()) {

                articolo = articoloDAO.findByName(articoloIterator.next().getName());
                sql = "INSERT INTO progetto_pis.lista_acquisto_has_articolo (lista_acquisto_idlista_acquisto, articolo_idarticolo, quantita) " +
                    "VALUES ('" + listaAcquisto.getIdLista() + "','" + articolo.getIdArticolo() + "','" + articolo.getQuantita() + "');";
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

    public int addArticolo(int idListaAcquisto, Articolo articolo){
        String sql = "INSERT INTO progetto_pis.lista_acquisto_has_articolo " +
                "(lista_acquisto_idlista_acquisto, articolo_idarticolo, quantita) VALUES ('" +
                idListaAcquisto + "','" +
                articolo.getIdArticolo() + "','" +
                articolo.getQuantita() + "');";
        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        return executor.executeOperation(writeOp).getRowsAffected();

    }

    @Override
    public int removeById(int id) {

        String sql = "DELETE FROM progetto_pis.lista_acquisto " +
                "WHERE idlista_acquisto = '" + id + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        return executor.executeOperation(writeOp).getRowsAffected();
    }

    public int removeByUser(int idUtenteAcquirente) {

        String sql = "DELETE FROM progetto_pis.lista_acquisto " +
                "WHERE utente_acquirente_utente_idutente = '" + idUtenteAcquirente + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        return executor.executeOperation(writeOp).getRowsAffected();
    }

    public int removeProdotto(int idListaAcquisto, Articolo articolo){

        String sql = "DELETE FROM progetto_pis.lista_acquisto_has_articolo " +
                "WHERE lista_acquisto_idlista_acquisto = '" + idListaAcquisto +
                "' AND articolo_idarticolo = '" + articolo.getIdArticolo() + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        return executor.executeOperation(writeOp).getRowsAffected();
    }

    @Override
    public int update(ListaAcquisto listaAcquisto) {
        String sql = "UPDATE progetto_pis.lista_acquisto " +
                "SET utente_acquirente_utente_idutente = '" + listaAcquisto.getIdUtente() +
                "', pagata = '" + listaAcquisto.isPagata() +
                "', costo_finale = '" + listaAcquisto.getCostoFinale() +
                "' WHERE idlista_acquisto = '" + listaAcquisto.getIdLista() + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        return executor.executeOperation(writeOp).getRowsAffected();
    }
}
