package DAO;

import DbInterface.IDbConnection;
import DbInterface.command.DbOperationExecutor;
import DbInterface.command.IDbOperation;
import DbInterface.command.ReadOperation;
import DbInterface.command.WriteOperation;
import Model.Articolo;
import Model.Cliente;
import Model.ListaAcquisto;
import Model.Servizio;
import Model.composite.IProdotto;
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

        String sql = "SELECT * FROM progetto_pis.lista_acquisto WHERE idlista_acquisto = '" + idLista + "';";
        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        try {
            rs.next();
            if (rs.getRow()==1) {
                listaAcquisto = new ListaAcquisto();
                listaAcquisto.setIdLista(rs.getInt("idlista_acquisto"));
                listaAcquisto.setNome(rs.getString("nome"));
                listaAcquisto.setPagata(rs.getBoolean("pagata"));
                listaAcquisto.setCostoFinale(rs.getFloat("costo_finale"));

                Cliente cliente = ClienteDAO.getInstance().findById(rs.getInt("utente_acquirente_utente_idutente"));
                if (cliente != null)
                    listaAcquisto.setCliente(cliente);

                ProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
                ServizioDAO servizioDAO = ServizioDAO.getInstance();

                sql = "SELECT * FROM progetto_pis.lista_acquisto_has_articolo WHERE lista_acquisto_idlista_acquisto = '" + idLista + "';";
                executor = new DbOperationExecutor();
                readOp = new ReadOperation(sql);
                rs = executor.executeOperation(readOp).getResultSet();

                while(rs.next()){
                    if(rs.getString("quantita") != null) {
                        IProdotto prodotto = prodottoDAO.findById(rs.getInt("articolo_idarticolo"));
                        prodotto.setQuantita(rs.getInt("quantita"));
                        listaAcquisto.add((Prodotto)prodotto);
                    }else {
                        Servizio servizio = servizioDAO.findById(rs.getInt("articolo_idarticolo"));
                        listaAcquisto.add(servizio);
                    }
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
    public ListaAcquisto findByName(String nome) {

        String sql = "SELECT * FROM progetto_pis.lista_acquisto WHERE nome = '" + nome + "';";
        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        try {
            rs.next();
            if (rs.getRow()==1) {
                listaAcquisto = new ListaAcquisto();
                listaAcquisto.setIdLista(rs.getInt("idlista_acquisto"));
                listaAcquisto.setNome(rs.getString("nome"));
                listaAcquisto.setPagata(rs.getBoolean("pagata"));
                listaAcquisto.setCostoFinale(rs.getFloat("costo_finale"));

                Cliente cliente = ClienteDAO.getInstance().findById(rs.getInt("utente_acquirente_utente_idutente"));
                if (cliente != null)
                    listaAcquisto.setCliente(cliente);

                ProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
                ServizioDAO servizioDAO = ServizioDAO.getInstance();

                sql = "SELECT * FROM progetto_pis.lista_acquisto_has_articolo WHERE lista_acquisto_idlista_acquisto = '" + listaAcquisto.getIdLista() + "';";
                executor = new DbOperationExecutor();
                readOp = new ReadOperation(sql);
                rs = executor.executeOperation(readOp).getResultSet();

                while(rs.next()){
                    if(rs.getString("quantita") != null) {
                        IProdotto prodotto = prodottoDAO.findById(rs.getInt("articolo_idarticolo"));
                        prodotto.setQuantita(rs.getInt("quantita"));
                        listaAcquisto.add((Prodotto)prodotto);
                    }else {
                        Servizio servizio = servizioDAO.findById(rs.getInt("articolo_idarticolo"));
                        listaAcquisto.add(servizio);
                    }
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
        String sql =  "SELECT * FROM progetto_pis.lista_acquisto;";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        ArrayList<ListaAcquisto> listeAcquisto = new ArrayList<>();
        try {
            while (rs.next()) {
                listaAcquisto = new ListaAcquisto();
                listaAcquisto.setIdLista(rs.getInt("idlista_acquisto"));
                listaAcquisto.setNome(rs.getString("nome"));
                listaAcquisto.setPagata(rs.getBoolean("pagata"));
                listaAcquisto.setCostoFinale(rs.getFloat("costo_finale"));

                Cliente cliente = ClienteDAO.getInstance().findById(rs.getInt("utente_acquirente_utente_idutente"));
                if (cliente != null)
                    listaAcquisto.setCliente(cliente);

                sql = "SELECT * FROM progetto_pis.lista_acquisto_has_articolo WHERE lista_acquisto_idlista_acquisto = '" + listaAcquisto.getIdLista() + "';";
                DbOperationExecutor executor2 = new DbOperationExecutor();
                IDbOperation readOp2 = new ReadOperation(sql);
                ResultSet rs2 = executor2.executeOperation(readOp2).getResultSet();

                ProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
                ServizioDAO servizioDAO = ServizioDAO.getInstance();
                while(rs2.next()){
                    if(rs2.getString("quantita") != null) {
                        IProdotto prodotto = prodottoDAO.findById(rs2.getInt("articolo_idarticolo"));
                        prodotto.setQuantita(rs2.getInt("quantita"));
                        listaAcquisto.add((Prodotto)prodotto);
                    }else {
                        Servizio servizio = servizioDAO.findById(rs2.getInt("articolo_idarticolo"));
                        listaAcquisto.add(servizio);
                    }
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
    public ArrayList<ListaAcquisto> findAllByPuntoVendita(int idPuntoVendita) {

        String sql = "SELECT * FROM progetto_pis.lista_acquisto AS l " +
                "INNER JOIN progetto_pis.utente_acquirente AS c ON l.utente_acquirente_utente_idutente = c.utente_idutente " +
                "WHERE c.punto_vendita_idpunto_vendita = '" + idPuntoVendita + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        ArrayList<ListaAcquisto> listeAcquisto = new ArrayList<>();
        try {
            while (rs.next()) {
                listaAcquisto = new ListaAcquisto();
                listaAcquisto.setIdLista(rs.getInt("idlista_acquisto"));
                listaAcquisto.setNome(rs.getString("nome"));
                listaAcquisto.setPagata(rs.getBoolean("pagata"));
                listaAcquisto.setCostoFinale(rs.getFloat("costo_finale"));

                Cliente cliente = ClienteDAO.getInstance().findById(rs.getInt("utente_acquirente_utente_idutente"));
                if (cliente != null)
                    listaAcquisto.setCliente(cliente);

                sql = "SELECT * FROM progetto_pis.lista_acquisto_has_articolo WHERE lista_acquisto_idlista_acquisto = '" + listaAcquisto.getIdLista() + "';";
                DbOperationExecutor executor2 = new DbOperationExecutor();
                IDbOperation readOp2 = new ReadOperation(sql);
                ResultSet rs2 = executor2.executeOperation(readOp2).getResultSet();

                ProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
                ServizioDAO servizioDAO = ServizioDAO.getInstance();
                while(rs2.next()){
                    if(rs2.getString("quantita") != null) {
                        IProdotto prodotto = prodottoDAO.findById(rs2.getInt("articolo_idarticolo"));
                        prodotto.setQuantita(rs2.getInt("quantita"));
                        listaAcquisto.add((Prodotto)prodotto);
                    }else {
                        Servizio servizio = servizioDAO.findById(rs2.getInt("articolo_idarticolo"));
                        listaAcquisto.add(servizio);
                    }
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
    public ArrayList<ListaAcquisto> findByUser(int idUtenteAcquirente){

        String sql = "SELECT * FROM progetto_pis.lista_acquisto WHERE utente_acquirente_utente_idutente = '" + idUtenteAcquirente + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        ArrayList<ListaAcquisto> listeAcquisto = new ArrayList<>();
        try {
            while (rs.next()) {
                listaAcquisto = new ListaAcquisto();
                listaAcquisto.setIdLista(rs.getInt("idlista_acquisto"));
                listaAcquisto.setNome(rs.getString("nome"));
                listaAcquisto.setPagata(rs.getBoolean("pagata"));
                listaAcquisto.setCostoFinale(rs.getFloat("costo_finale"));

                Cliente cliente = ClienteDAO.getInstance().findById(rs.getInt("utente_acquirente_utente_idutente"));
                if (cliente != null)
                    listaAcquisto.setCliente(cliente);

                sql = "SELECT * FROM progetto_pis.lista_acquisto_has_articolo WHERE lista_acquisto_idlista_acquisto = '" + listaAcquisto.getIdLista() + "';";
                DbOperationExecutor executor2 = new DbOperationExecutor();
                IDbOperation readOp2 = new ReadOperation(sql);
                ResultSet rs2 = executor2.executeOperation(readOp2).getResultSet();

                ProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
                ServizioDAO servizioDAO = ServizioDAO.getInstance();
                while(rs2.next()){
                    if(rs2.getString("quantita") != null) {
                        IProdotto prodotto = prodottoDAO.findById(rs2.getInt("articolo_idarticolo"));
                        prodotto.setQuantita(rs2.getInt("quantita"));
                        listaAcquisto.add((Prodotto)prodotto);
                    }else {
                        Servizio servizio = servizioDAO.findById(rs2.getInt("articolo_idarticolo"));
                        listaAcquisto.add(servizio);
                    }
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

        String sql = "INSERT INTO progetto_pis.lista_acquisto " +
                "(utente_acquirente_utente_idutente, nome, pagata, costo_finale) VALUES ('"+
                listaAcquisto.getCliente().getIdUtente() + "','" +
                listaAcquisto.getNome() + "','" +
                listaAcquisto.getPagata() + "','" +
                listaAcquisto.getCostoFinale()+"');";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        executor.executeOperation(writeOp);

        sql = "SELECT max(idlista_acquisto) FROM progetto_pis.lista_acquisto;";
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        int rowCount = 0;
        try {
            rs.next();
            listaAcquisto.setIdLista(rs.getInt("max(idlista_acquisto)"));


            Iterator<Articolo> articoloIterator = listaAcquisto.getArticoli().iterator();
            Articolo articolo;
            while (articoloIterator.hasNext()) {

                articolo = articoloIterator.next();
                if(articolo.getQuantita() > 0)
                    sql = "INSERT INTO progetto_pis.lista_acquisto_has_articolo " +
                            "(lista_acquisto_idlista_acquisto, articolo_idarticolo, quantita) VALUES ('" +
                            listaAcquisto.getIdLista() + "','" +
                            articolo.getIdArticolo() + "','" +
                            articolo.getQuantita() + "');";
                else
                    sql = "INSERT INTO progetto_pis.lista_acquisto_has_articolo " +
                            "(lista_acquisto_idlista_acquisto, articolo_idarticolo) VALUES ('" +
                            listaAcquisto.getIdLista() + "','" +
                            articolo.getIdArticolo() + "');";

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
    public int addArticolo(int idListaAcquisto, Articolo articolo){
        String sql;
        if(articolo instanceof IProdotto prodotto)
            sql = "INSERT INTO progetto_pis.lista_acquisto_has_articolo " +
                "(lista_acquisto_idlista_acquisto, articolo_idarticolo, quantita) VALUES ('" +
                idListaAcquisto + "','" +
                prodotto.getIdArticolo() + "','" +
                prodotto.getQuantita() + "');";
        else if(articolo instanceof Servizio servizio)
            sql = "INSERT INTO progetto_pis.lista_acquisto_has_articolo " +
                    "(lista_acquisto_idlista_acquisto, articolo_idarticolo) VALUES ('" +
                    idListaAcquisto + "','" +
                    servizio.getIdArticolo() + "');";
        else return -1;

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        int rowCount = executor.executeOperation(writeOp).getRowsAffected();

        rowCount += update(findById(idListaAcquisto));

        return rowCount;
    }

    @Override
    public int removeById(int id) {

        String sql = "DELETE FROM progetto_pis.lista_acquisto " +
                "WHERE idlista_acquisto = '" + id + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        return executor.executeOperation(writeOp).getRowsAffected();
    }

    @Override
    public int removeByUser(int idUtenteAcquirente) {

        String sql = "DELETE FROM progetto_pis.lista_acquisto " +
                "WHERE utente_acquirente_utente_idutente = '" + idUtenteAcquirente + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        return executor.executeOperation(writeOp).getRowsAffected();
    }

    @Override
    public int removeArticolo(int idListaAcquisto, Articolo articolo){

        String sql = "DELETE FROM progetto_pis.lista_acquisto_has_articolo " +
                "WHERE lista_acquisto_idlista_acquisto = '" + idListaAcquisto +
                "' AND articolo_idarticolo = '" + articolo.getIdArticolo() + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        int rowCount = executor.executeOperation(writeOp).getRowsAffected();

        rowCount += update(findById(idListaAcquisto));

        return rowCount;
    }

    @Override
    public int setPagata(int idListaAcquisto){

        String sql = "UPDATE progetto_pis.lista_acquisto SET pagata = 1 WHERE idlista_acquisto = '" + idListaAcquisto + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        return executor.executeOperation(writeOp).getRowsAffected();
    }
    @Override
    public int update(ListaAcquisto listaAcquisto) {

        String sql = "UPDATE progetto_pis.lista_acquisto " +
                "SET utente_acquirente_utente_idutente = '" + listaAcquisto.getCliente().getIdUtente() +
                "', nome = '" + listaAcquisto.getNome() +
                "', pagata = '" + listaAcquisto.getPagata() +
                "', costo_finale = '" + listaAcquisto.getCostoFinale() +
                "' WHERE idlista_acquisto = '" + listaAcquisto.getIdLista() + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        int rowCount =  executor.executeOperation(writeOp).getRowsAffected();

        Iterator<Articolo> articoloIterator = listaAcquisto.getArticoli().iterator();
        Articolo articolo;
        while (articoloIterator.hasNext()) {

            articolo = articoloIterator.next();
            if(articolo.getQuantita() > 0)

                sql = "UPDATE progetto_pis.lista_acquisto_has_articolo " +
                        "SET quantita = '" + articolo.getQuantita() +
                        "' WHERE lista_acquisto_idlista_acquisto = '" + listaAcquisto.getIdLista() +
                        "' AND articolo_idarticolo = '" + articolo.getIdArticolo() + "';";

            writeOp = new WriteOperation(sql);
            rowCount += executor.executeOperation(writeOp).getRowsAffected();
        }

        return rowCount;
    }
}
