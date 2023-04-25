package DAO;

import DbInterface.IDbConnection;
import DbInterface.command.DbOperationExecutor;
import DbInterface.command.IDbOperation;
import DbInterface.command.ReadOperation;
import DbInterface.command.WriteOperation;
import Model.composite.IProdotto;
import Model.composite.Prodotto;
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

        String sql = "SELECT * FROM progetto_pis.prodotto_has_prodotto INNER JOIN progetto_pis.articolo ON prodotto_articolo_idarticolo = idarticolo INNER JOIN progetto_pis.prodotto  ON idarticolo = articolo_idarticolo WHERE prodotto_articolo_idarticolo = '" + idProdottoComposito + "';";


        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        try {
            rs.next();
            if (rs.getRow()==1) {
                prodottoComposito = new ProdottoComposito();
                prodottoComposito.setIdArticolo(rs.getInt("prodotto_articolo_idarticolo"));
                prodottoComposito.setName(rs.getString("nome"));
                prodottoComposito.setDescrizione(rs.getString("descrizione"));
                prodottoComposito.setPrezzo(rs.getFloat("costo"));
                prodottoComposito.getCategoria().setIdCategoria(rs.getInt("categoria_prodotto_idcategoria_prodotto"));
                prodottoComposito.getProduttore().setIdProduttore(rs.getInt("produttore_idproduttore"));
            }

            //aggiungo i componenti del prodotto composito
            ProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
            int idSottoprodotto;
            int quantita;
            do {
                idSottoprodotto = rs.getInt("prodotto_articolo_idarticolo1");
                quantita = rs.getInt("quantita");
                Prodotto sottoprodotto = prodottoDAO.findById(idSottoprodotto);
                sottoprodotto.setQuantita(quantita);
                sottoprodotto.getProduttore().setIdProduttore(rs.getInt("produttore_idproduttore"));
                sottoprodotto.getCategoria().setIdCategoria(rs.getInt("categoria_prodotto_idcategoria_prodotto"));
                prodottoComposito.add(sottoprodotto);

            } while (rs.next());

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

        String sql = "SELECT * FROM progetto_pis.prodotto_has_prodotto INNER JOIN progetto_pis.articolo ON prodotto_articolo_idarticolo = idarticolo INNER JOIN progetto_pis.prodotto  ON idarticolo = articolo_idarticolo WHERE nome = '" + name + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        try {
            rs.next();
            if (rs.getRow()==1) {
                prodottoComposito = new ProdottoComposito();
                prodottoComposito.setIdArticolo(rs.getInt("prodotto_articolo_idarticolo"));
                prodottoComposito.setName(rs.getString("nome"));
                prodottoComposito.setDescrizione(rs.getString("descrizione"));
                prodottoComposito.setPrezzo(rs.getFloat("costo"));
                prodottoComposito.getCategoria().setIdCategoria(rs.getInt("categoria_prodotto_idcategoria_prodotto"));
                prodottoComposito.getProduttore().setIdProduttore(rs.getInt("produttore_idproduttore"));
            }

            //aggiungo i componenti del prodotto composito
            ProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
            int idSottoprodotto;
            int quantita;
            do {

                idSottoprodotto = rs.getInt("prodotto_articolo_idarticolo1");
                quantita = rs.getInt("quantita");
                Prodotto sottoprodotto = prodottoDAO.findById(idSottoprodotto);
                sottoprodotto.setQuantita(quantita);
                sottoprodotto.getProduttore().setIdProduttore(rs.getInt("produttore_idproduttore"));
                sottoprodotto.getCategoria().setIdCategoria(rs.getInt("categoria_prodotto_idcategoria_prodotto"));
                prodottoComposito.add(sottoprodotto);

            } while (rs.next());

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

        String sql = "SELECT * FROM progetto_pis.prodotto_has_prodotto INNER JOIN progetto_pis.articolo ON prodotto_articolo_idarticolo = idarticolo INNER JOIN progetto_pis.prodotto  ON idarticolo = articolo_idarticolo;";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        ArrayList<ProdottoComposito> prodottiCompositi = new ArrayList<>();
        try {
            while (rs.next()) {
                prodottoComposito = new ProdottoComposito();
                prodottoComposito.setIdArticolo(rs.getInt("prodotto_articolo_idarticolo"));
                prodottoComposito.setName(rs.getString("nome"));
                prodottoComposito.setDescrizione(rs.getString("descrizione"));
                prodottoComposito.setPrezzo(rs.getFloat("costo"));
                prodottoComposito.getCategoria().setIdCategoria(rs.getInt("categoria_prodotto_idcategoria_prodotto"));
                prodottoComposito.getProduttore().setIdProduttore(rs.getInt("produttore_idproduttore"));

                //aggiungo i componenti del prodotto composito
                ProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
                int idSottoprodotto;
                int quantita;

                int idAttuale = prodottoComposito.getIdArticolo();
                while (idAttuale == rs.getInt("prodotto_articolo_idarticolo")) {

                    idSottoprodotto = rs.getInt("prodotto_articolo_idarticolo1");
                    quantita = rs.getInt("quantita");
                    Prodotto sottoprodotto = prodottoDAO.findById(idSottoprodotto);
                    sottoprodotto.setQuantita(quantita);
                    sottoprodotto.getProduttore().setIdProduttore(rs.getInt("produttore_idproduttore"));
                    sottoprodotto.getCategoria().setIdCategoria(rs.getInt("categoria_prodotto_idcategoria_prodotto"));
                    prodottoComposito.add(sottoprodotto);

                    if(!rs.next())
                        break;
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

            sql = "INSERT INTO progetto_pis.prodotto (articolo_idarticolo, produttore_idproduttore, categoria_prodotto_idcategoria_prodotto) VALUES ('" +
                    prodottoComposito.getIdArticolo() + "','" +
                    prodottoComposito.getProduttore().getIdProduttore() + "','" +
                    prodottoComposito.getCategoria().getIdCategoria() + ");";

            IDbOperation writeOp = new WriteOperation(sql);
            executor.executeOperation(writeOp);

            Iterator<IProdotto> sottoprodottiIterator = prodottoComposito.getSottoprodotti().iterator();
            while (sottoprodottiIterator.hasNext()) {

                IProdotto sottoprodotto = sottoprodottiIterator.next();
                int idSottoprodotto = prodottoDAO.findByName(sottoprodotto.getName()).getIdArticolo();

                sql = "INSERT INTO progetto_pis.prodotto_has_prodotto " +
                        "(prodotto_articolo_idarticolo, prodotto_articolo_idarticolo1, quantita) " +
                        "VALUES ('" + prodottoComposito.getIdArticolo() + "','" + idSottoprodotto + "','" + sottoprodotto.getQuantita() + "');";

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

    public int addSottoprodotto(int idProdottoComposito, Prodotto sottoprodotto) {

        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "INSERT INTO progetto_pis.prodotto_has_prodotto (prodotto_articolo_idarticolo, prodotto_articolo_idarticolo1, quantita) VALUES ('"+
                idProdottoComposito + "','" + sottoprodotto.getIdArticolo() + "','" + sottoprodotto.getQuantita() + "');";
        IDbOperation readOp = new WriteOperation(sql);
        return executor.executeOperation(readOp).getRowsAffected();

    }

    public int removeSottoprodotto(int idProdottoComposito, Prodotto sottoprodotto) {

        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "DELETE FROM progetto_pis.prodotto_has_prodotto " +
                "WHERE prodotto_articolo_idarticolo = '" + idProdottoComposito +
                "' AND prodotto_articolo_idarticolo1 = '" + sottoprodotto.getIdArticolo() + "');";
        IDbOperation readOp = new WriteOperation(sql);
        return executor.executeOperation(readOp).getRowsAffected();

    }

    @Override
    public int removeById(int idProdottoComposito) {

        ArticoloDAO articoloDAO = ArticoloDAO.getInstance();
        return articoloDAO.removeById(idProdottoComposito);
    }

    @Override
    public int update(ProdottoComposito prodottoComposito) { //da rivedere

        ProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
        prodottoDAO.update(prodottoComposito);

        String sql = "";
        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation writeOp;
        int rowCount = 0;

        Iterator<IProdotto> sottoprodottiIterator = prodottoComposito.getSottoprodotti().iterator();
        while (sottoprodottiIterator.hasNext()) {

            IProdotto sottoprodotto = sottoprodottiIterator.next();

            sql = "UPDATE progetto_pis.prodotto_has_prodotto " +
                    "SET  quantita = '" + sottoprodotto.getQuantita() +
                    "' WHERE prodotto_articolo_idarticolo = '" + prodottoComposito.getIdArticolo() + "';";

            writeOp = new WriteOperation(sql);
            rowCount += executor.executeOperation(writeOp).getRowsAffected();
        }

        writeOp = new WriteOperation(sql);
        return executor.executeOperation(writeOp).getRowsAffected();
    }
}
