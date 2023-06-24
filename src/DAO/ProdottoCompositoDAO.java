package DAO;

import DbInterface.IDbConnection;
import DbInterface.command.DbOperationExecutor;
import DbInterface.command.IDbOperation;
import DbInterface.command.ReadOperation;
import DbInterface.command.WriteOperation;
import Model.CategoriaProdotto;
import Model.Produttore;
import Model.composite.IProdotto;
import Model.composite.ProdottoComposito;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;


public class ProdottoCompositoDAO implements IProdottoCompositoDAO{
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

    @Override
    public ProdottoComposito findById(int idProdottoComposito) {

        String sql = "SELECT * FROM progetto_pis.articolo INNER JOIN progetto_pis.prodotto  ON idarticolo = articolo_idarticolo WHERE articolo_idarticolo = '" + idProdottoComposito + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        try {
            rs.next();
            if (rs.getRow()==1) {
                prodottoComposito = new ProdottoComposito();
                prodottoComposito.setIdArticolo(rs.getInt("articolo_idarticolo"));
                prodottoComposito.setName(rs.getString("nome"));
                prodottoComposito.setDescrizione(rs.getString("descrizione"));
                prodottoComposito.setPrezzo(rs.getFloat("costo"));

                CategoriaProdotto categoriaProdotto = CategoriaProdottoDAO.getInstance().findById(rs.getInt("categoria_prodotto_idcategoria_prodotto"));
                if (categoriaProdotto != null)
                    prodottoComposito.setCategoria(categoriaProdotto);

                Produttore produttore = ProduttoreDAO.getInstance().findById(rs.getInt("produttore_idproduttore"));
                if (produttore != null)
                    prodottoComposito.setProduttore(produttore);

                //aggiungo i componenti del prodotto composito
                sql = "SELECT * FROM progetto_pis.prodotto_has_prodotto WHERE prodotto_articolo_idarticolo = '" + prodottoComposito.getIdArticolo() + "';";
                executor = new DbOperationExecutor();
                readOp = new ReadOperation(sql);
                rs = executor.executeOperation(readOp).getResultSet();
                ProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
                while(rs.next()){
                    IProdotto sottoprodotto = prodottoDAO.findById(rs.getInt("prodotto_articolo_idarticolo1"));
                    sottoprodotto.setQuantita(rs.getInt("quantita"));
                    prodottoComposito.add(sottoprodotto);
                }
            }
            return prodottoComposito;

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

    public ProdottoComposito findByName(String name) {

        String sql = "SELECT * FROM progetto_pis.articolo INNER JOIN progetto_pis.prodotto  ON idarticolo = articolo_idarticolo WHERE nome = '" + name + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        try {
            rs.next();
            if (rs.getRow()==1) {
                prodottoComposito = new ProdottoComposito();
                prodottoComposito.setIdArticolo(rs.getInt("articolo_idarticolo"));
                prodottoComposito.setName(rs.getString("nome"));
                prodottoComposito.setDescrizione(rs.getString("descrizione"));
                prodottoComposito.setPrezzo(rs.getFloat("costo"));

                CategoriaProdotto categoriaProdotto = CategoriaProdottoDAO.getInstance().findById(rs.getInt("categoria_prodotto_idcategoria_prodotto"));
                if (categoriaProdotto != null)
                    prodottoComposito.setCategoria(categoriaProdotto);

                Produttore produttore = ProduttoreDAO.getInstance().findById(rs.getInt("produttore_idproduttore"));
                if (produttore != null)
                    prodottoComposito.setProduttore(produttore);


                //aggiungo i componenti del prodotto composito
                sql = "SELECT * FROM progetto_pis.prodotto_has_prodotto WHERE prodotto_articolo_idarticolo = '" + prodottoComposito.getIdArticolo() + "';";
                executor = new DbOperationExecutor();
                readOp = new ReadOperation(sql);
                rs = executor.executeOperation(readOp).getResultSet();
                ProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
                while(rs.next()){
                    IProdotto sottoprodotto = prodottoDAO.findById(rs.getInt("prodotto_articolo_idarticolo1"));
                    sottoprodotto.setQuantita(rs.getInt("quantita"));
                    prodottoComposito.add(sottoprodotto);
                }
            }
            return prodottoComposito;

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
    public ArrayList<ProdottoComposito> findAll() {

        String sql = "SELECT * FROM progetto_pis.articolo INNER JOIN progetto_pis.prodotto ON idarticolo = articolo_idarticolo;";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        ArrayList<ProdottoComposito> prodottiCompositi = new ArrayList<>();
        try {
            while (rs.next()) {
                prodottoComposito = new ProdottoComposito();
                prodottoComposito.setIdArticolo(rs.getInt("articolo_idarticolo"));
                prodottoComposito.setName(rs.getString("nome"));
                prodottoComposito.setDescrizione(rs.getString("descrizione"));
                prodottoComposito.setPrezzo(rs.getFloat("costo"));

                CategoriaProdotto categoriaProdotto = CategoriaProdottoDAO.getInstance().findById(rs.getInt("categoria_prodotto_idcategoria_prodotto"));
                if (categoriaProdotto != null)
                    prodottoComposito.setCategoria(categoriaProdotto);

                Produttore produttore = ProduttoreDAO.getInstance().findById(rs.getInt("produttore_idproduttore"));
                if (produttore != null)
                    prodottoComposito.setProduttore(produttore);

                //aggiungo i componenti del prodotto composito
                sql = "SELECT * FROM progetto_pis.prodotto_has_prodotto WHERE prodotto_articolo_idarticolo = '" + prodottoComposito.getIdArticolo() + "';";
                DbOperationExecutor executor2 = new DbOperationExecutor();
                IDbOperation readOp2 = new ReadOperation(sql);
                ResultSet rs2 = executor2.executeOperation(readOp2).getResultSet();
                ProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
                while(rs2.next()){
                    IProdotto sottoprodotto = prodottoDAO.findById(rs2.getInt("prodotto_articolo_idarticolo1"));
                    sottoprodotto.setQuantita(rs2.getInt("quantita"));
                    prodottoComposito.add(sottoprodotto);
                }
                prodottiCompositi.add(prodottoComposito);
            }
            return prodottiCompositi;
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
    public int add(ProdottoComposito prodottoComposito) {

        ProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
        prodottoDAO.add(prodottoComposito);

        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "SELECT max(idarticolo) FROM progetto_pis.articolo;";
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        int rowCount = 0;
        try {
            rs.next();
            prodottoComposito.setIdArticolo(rs.getInt("max(idarticolo)"));

            Iterator<IProdotto> sottoprodottiIterator = prodottoComposito.getSottoprodotti().iterator();
            while (sottoprodottiIterator.hasNext()) {

                IProdotto sottoprodotto = sottoprodottiIterator.next();
                int idSottoprodotto = prodottoDAO.findByName(sottoprodotto.getName()).getIdArticolo();

                sql = "INSERT INTO progetto_pis.prodotto_has_prodotto " +
                        "(prodotto_articolo_idarticolo, prodotto_articolo_idarticolo1, quantita) " +
                        "VALUES ('" + prodottoComposito.getIdArticolo() + "','" + idSottoprodotto + "','" + sottoprodotto.getQuantita() + "');";

                IDbOperation writeOp = new WriteOperation(sql);
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
    public int addSottoprodotto(int idProdottoComposito, IProdotto sottoprodotto) {

        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "INSERT INTO progetto_pis.prodotto_has_prodotto (prodotto_articolo_idarticolo, prodotto_articolo_idarticolo1, quantita) VALUES ('"+
                idProdottoComposito + "','" + sottoprodotto.getIdArticolo() + "','" + sottoprodotto.getQuantita() + "');";
        IDbOperation readOp = new WriteOperation(sql);
        return executor.executeOperation(readOp).getRowsAffected();

    }
    @Override
    public int removeSottoprodotto(int idProdottoComposito, IProdotto sottoprodotto) {

        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "DELETE FROM progetto_pis.prodotto_has_prodotto " +
                "WHERE prodotto_articolo_idarticolo = '" + idProdottoComposito +
                "' AND prodotto_articolo_idarticolo1 = '" + sottoprodotto.getIdArticolo() + "';";
        IDbOperation readOp = new WriteOperation(sql);
        return executor.executeOperation(readOp).getRowsAffected();

    }

    @Override
    public int removeAllSottoprodotti(int idProdottoComposito) {

        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "DELETE FROM progetto_pis.prodotto_has_prodotto " +
                "WHERE prodotto_articolo_idarticolo = '" + idProdottoComposito + "';";
        IDbOperation readOp = new WriteOperation(sql);
        return executor.executeOperation(readOp).getRowsAffected();

    }

    @Override
    public int removeById(int idProdottoComposito) {

        ArticoloDAO articoloDAO = ArticoloDAO.getInstance();
        return articoloDAO.removeById(idProdottoComposito);
    }

    @Override
    public int update(ProdottoComposito prodottoComposito) {

        ProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
        int rowCount = prodottoDAO.update(prodottoComposito);
        rowCount += removeAllSottoprodotti(prodottoComposito.getIdArticolo());

        Iterator<IProdotto> sottoprodottiIterator = prodottoComposito.getSottoprodotti().iterator();
        while (sottoprodottiIterator.hasNext()) {
            IProdotto sottoprodotto = sottoprodottiIterator.next();
            rowCount += addSottoprodotto(prodottoComposito.getIdArticolo(), sottoprodotto);
        }
        return rowCount;
    }
}
