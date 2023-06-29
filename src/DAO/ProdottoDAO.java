package DAO;

import DbInterface.IDbConnection;
import DbInterface.command.DbOperationExecutor;
import DbInterface.command.IDbOperation;
import DbInterface.command.ReadOperation;
import DbInterface.command.WriteOperation;
import Model.Articolo;
import Model.CategoriaProdotto;
import Model.Produttore;
import Model.composite.IProdotto;
import Model.composite.Prodotto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdottoDAO implements IProdottoDAO {
    private static ProdottoDAO instance = new ProdottoDAO();
    private Prodotto prodotto;
    private static IDbConnection conn;
    private static ResultSet rs;

    private ProdottoDAO() {
        prodotto = null;
        conn = null;
        rs = null;
    }

    public static ProdottoDAO getInstance() {
        return instance;
    }
    @Override
    public IProdotto findById(int idProdotto) {

        String sql = "SELECT articolo_idarticolo, produttore_idproduttore, categoria_prodotto_idcategoria_prodotto, nome, descrizione, costo " +
                "FROM progetto_pis.prodotto AS p INNER JOIN progetto_pis.articolo AS a " +
                "ON a.idarticolo = p.articolo_idarticolo " +
                "WHERE articolo_idarticolo = '" + idProdotto + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        try {
            rs.next();
            if (rs.getRow()==1) {
                prodotto = new Prodotto();
                prodotto.setIdArticolo(rs.getInt("articolo_idarticolo"));
                prodotto.setName(rs.getString("nome"));
                prodotto.setDescrizione(rs.getString("descrizione"));
                prodotto.setPrezzo(rs.getFloat("costo"));

                int idProduttore = rs.getInt("produttore_idproduttore");
                Produttore produttore = ProduttoreDAO.getInstance().findById(idProduttore);
                if(produttore != null){
                    prodotto.setProduttore(produttore);
                }
                int idCategoria = rs.getInt("categoria_prodotto_idcategoria_prodotto");
                CategoriaProdotto categoriaProdotto = CategoriaProdottoDAO.getInstance().findById(idCategoria);
                if(categoriaProdotto != null){
                    prodotto.setCategoria(categoriaProdotto);
                }

                return prodotto;
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

    public IProdotto findByName(String name) {

        String sql = "SELECT articolo_idarticolo, produttore_idproduttore, nome, descrizione, costo, categoria_prodotto_idcategoria_prodotto FROM progetto_pis.prodotto AS p INNER JOIN progetto_pis.articolo AS a ON a.idarticolo = p.articolo_idarticolo WHERE nome = '"+ name +"';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        try {
            rs.next();
            if (rs.getRow()==1) {
                prodotto = new Prodotto();
                prodotto.setIdArticolo(rs.getInt("articolo_idarticolo"));
                prodotto.setName(rs.getString("nome"));
                prodotto.setDescrizione(rs.getString("descrizione"));
                prodotto.setPrezzo(rs.getFloat("costo"));

                int idProduttore = rs.getInt("produttore_idproduttore");
                Produttore produttore = ProduttoreDAO.getInstance().findById(idProduttore);
                if(produttore != null){
                    prodotto.setProduttore(produttore);
                }
                int idCategoria = rs.getInt("categoria_prodotto_idcategoria_prodotto");
                CategoriaProdotto categoriaProdotto = CategoriaProdottoDAO.getInstance().findById(idCategoria);
                if(categoriaProdotto != null){
                    prodotto.setCategoria(categoriaProdotto);
                }
                return prodotto;
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
    public ArrayList<IProdotto> findAll() {

        String sql = "SELECT * FROM progetto_pis.prodotto AS p INNER JOIN progetto_pis.articolo AS a " +
                "ON a.idarticolo = p.articolo_idarticolo;";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        ArrayList<IProdotto> prodotti = new ArrayList<>();
        try {
            while (rs.next()) {
                prodotto = new Prodotto();
                prodotto.setIdArticolo(rs.getInt("articolo_idarticolo"));
                prodotto.setName(rs.getString("nome"));
                prodotto.setDescrizione(rs.getString("descrizione"));
                prodotto.setPrezzo(rs.getFloat("costo"));

                int idProduttore = rs.getInt("produttore_idproduttore");
                Produttore produttore = ProduttoreDAO.getInstance().findById(idProduttore);
                if(produttore != null){
                    prodotto.setProduttore(produttore);
                }
                int idCategoria = rs.getInt("categoria_prodotto_idcategoria_prodotto");
                CategoriaProdotto categoriaProdotto = CategoriaProdottoDAO.getInstance().findById(idCategoria);
                if(categoriaProdotto != null){
                    prodotto.setCategoria(categoriaProdotto);
                }
                prodotti.add(prodotto);
            }
            return prodotti;
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

    public ArrayList<IProdotto> findAllByCategoria(int idCategoria) {

        String sql = "SELECT * FROM progetto_pis.prodotto AS p INNER JOIN progetto_pis.articolo AS a " +
                "ON a.idarticolo = p.articolo_idarticolo " +
                 "WHERE categoria_prodotto_idcategoria_prodotto = '" + idCategoria + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        ArrayList<IProdotto> prodotti = new ArrayList<>();
        try {
            while (rs.next()) {
                prodotto = new Prodotto();
                prodotto.setIdArticolo(rs.getInt("articolo_idarticolo"));
                prodotto.setName(rs.getString("nome"));
                prodotto.setDescrizione(rs.getString("descrizione"));
                prodotto.setPrezzo(rs.getFloat("costo"));

                int idProduttore = rs.getInt("produttore_idproduttore");
                Produttore produttore = ProduttoreDAO.getInstance().findById(idProduttore);
                if(produttore != null){
                    prodotto.setProduttore(produttore);
                }

                CategoriaProdotto categoriaProdotto = CategoriaProdottoDAO.getInstance().findById(idCategoria);
                if(categoriaProdotto != null){
                    prodotto.setCategoria(categoriaProdotto);
                }
                prodotti.add(prodotto);
            }
            return prodotti;
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
    public int add(IProdotto prodotto) {

        ArticoloDAO articoloDAO = ArticoloDAO.getInstance();
        articoloDAO.add((Articolo) prodotto);

        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "SELECT max(idarticolo) FROM progetto_pis.articolo;";
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        int rowCount = 0;
        try {
            rs.next();
            prodotto.setIdArticolo(rs.getInt("max(idarticolo)"));

            //ne categoria ne produttore sono null
            if (prodotto.getCategoria().getNome() != null && prodotto.getProduttore().getNome() != null)
                sql = "INSERT INTO progetto_pis.prodotto (articolo_idarticolo, categoria_prodotto_idcategoria_prodotto, produttore_idproduttore) " +
                    "VALUES ('" +
                    prodotto.getIdArticolo() + "','" +
                    prodotto.getCategoria().getIdCategoria() + "','" +
                    prodotto.getProduttore().getIdProduttore() + "');";

            //produttore è null
            else if (prodotto.getCategoria().getNome() != null && prodotto.getProduttore().getNome() == null)
                sql = "INSERT INTO progetto_pis.prodotto (articolo_idarticolo, categoria_prodotto_idcategoria_prodotto) " +
                        "VALUES ('" +
                        prodotto.getIdArticolo() + "','" +
                        prodotto.getCategoria().getIdCategoria() + "');";

            //categoria è null
            else if(prodotto.getCategoria().getNome() == null && prodotto.getProduttore().getNome() != null)
                sql = "INSERT INTO progetto_pis.prodotto (articolo_idarticolo, produttore_idproduttore) " +
                        "VALUES ('" +
                        prodotto.getIdArticolo() + "','" +
                        prodotto.getProduttore().getIdProduttore() + "');";

            //sia categoria che produttore sono null
            else
                sql = "INSERT INTO progetto_pis.prodotto (articolo_idarticolo) " +
                        "VALUES ('" +
                        prodotto.getIdArticolo() + "');";


            IDbOperation writeOp = new WriteOperation(sql);
            rowCount = executor.executeOperation(writeOp).getRowsAffected();

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
    public int removeById(int idProdotto) {

        ArticoloDAO articoloDAO = ArticoloDAO.getInstance();
        return articoloDAO.removeById(idProdotto);
    }


    @Override
    public int update(IProdotto prodotto) {

        ArticoloDAO articoloDAO = ArticoloDAO.getInstance();
        int rowCount = articoloDAO.update((Articolo) prodotto);

        String sql;

        //ne categoria ne produttore sono null
        if (prodotto.getCategoria().getNome() != null && prodotto.getProduttore().getNome() != null)
            sql = "UPDATE progetto_pis.prodotto " +
                    "SET produttore_idproduttore = '" + prodotto.getProduttore().getIdProduttore() +
                    "', categoria_prodotto_idcategoria_prodotto = '" + prodotto.getCategoria().getIdCategoria() +
                    "' WHERE articolo_idarticolo = '" + prodotto.getIdArticolo() + "';";

            //produttore è null
        else if (prodotto.getCategoria().getNome() != null && prodotto.getProduttore().getNome() == null)
            sql = "UPDATE progetto_pis.prodotto " +
                    "SET categoria_prodotto_idcategoria_prodotto = '" + prodotto.getCategoria().getIdCategoria() +
                    "' WHERE articolo_idarticolo = '" + prodotto.getIdArticolo() + "';";

            //categoria è null
        else if(prodotto.getCategoria().getNome() == null && prodotto.getProduttore().getNome() != null)
            sql = "UPDATE progetto_pis.prodotto " +
                    "SET produttore_idproduttore = '" + prodotto.getProduttore().getIdProduttore() +
                    "', categoria_prodotto_idcategoria_prodotto = null" +
                    " WHERE articolo_idarticolo = '" + prodotto.getIdArticolo() + "';";

            //sia categoria che produttore sono null
        else
            sql = "UPDATE progetto_pis.prodotto " +
                    "SET produttore_idproduttore = null" +
                    ", categoria_prodotto_idcategoria_prodotto = null" +
                    " WHERE articolo_idarticolo = '" + prodotto.getIdArticolo() + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        return executor.executeOperation(writeOp).getRowsAffected();
    }
}
