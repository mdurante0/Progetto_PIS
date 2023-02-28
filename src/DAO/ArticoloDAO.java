package DAO;

import DbInterface.IDbConnection;
import DbInterface.command.DbOperationExecutor;
import DbInterface.command.IDbOperation;
import DbInterface.command.ReadOperation;
import DbInterface.command.WriteOperation;
import Model.Articolo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ArticoloDAO implements IArticoloDAO {
    private static ArticoloDAO instance = new ArticoloDAO();
    private Articolo articolo;
    private static IDbConnection conn;
    private static ResultSet rs;

    private ArticoloDAO() {
        articolo = null;
        conn = null;
        rs = null;
    }

    public static ArticoloDAO getInstance() {
        return instance;
    }

    @Override
    public Articolo findById(int idArticolo) {

        String sql = "SELECT idarticolo, nome, descrizione, costo " +
                "FROM progetto_pis.articolo " +
                "WHERE idarticolo = '" + idArticolo + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        try {
            rs.next();
            if (rs.getRow()==1) {
                articolo = new Articolo();
                articolo.setIdArticolo(rs.getInt("idarticolo"));
                articolo.setName(rs.getString("nome"));
                articolo.setDescrizione(rs.getString("descrizione"));
                articolo.setPrezzo(rs.getFloat("costo"));
                return articolo;
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

    public Articolo findByName(String name) {

        String sql = "SELECT idarticolo, nome, descrizione, costo " +
                "FROM progetto_pis.articolo " +
                "WHERE nome = '" + name + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        try {
            rs.next();
            if (rs.getRow()==1) {
                articolo = new Articolo();
                articolo.setIdArticolo(rs.getInt("idarticolo"));
                articolo.setName(rs.getString("nome"));
                articolo.setDescrizione(rs.getString("descrizione"));
                articolo.setPrezzo(rs.getFloat("costo"));
                return articolo;
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
    public ArrayList<Articolo> findAll() {

        String sql = "SELECT nome, descrizione, costo " +
                "FROM progetto_pis.articolo;";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        ArrayList<Articolo> articoli = new ArrayList<>();
        try {
            while (rs.next()) {
                articolo = new Articolo();
                articolo.setName(rs.getString("nome"));
                articolo.setDescrizione(rs.getString("descrizione"));
                articolo.setPrezzo(rs.getFloat("costo"));
                articoli.add(articolo);
            }
            return articoli;
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
    public int add(Articolo articolo) {

        String sql = "INSERT INTO progetto_pis.articolo (nome, descrizione, costo) VALUES ('"+
                articolo.getName() + "','" +
                articolo.getDescrizione() + "','" +
                articolo.getPrezzo()+ "');";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        return executor.executeOperation(writeOp).getRowsAffected();
    }

    @Override
    public int removeById(String nome) {

        String sql = "DELETE FROM progetto_pis.articolo " +
                "WHERE nome = '"+ nome + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        return executor.executeOperation(writeOp).getRowsAffected();
    }

    @Override
    public int update(Articolo articolo) {

        String sql = "UPDATE progetto_pis.articolo " +
                "SET nome = '" + articolo.getName() +
                "', costo = '" + articolo.getPrezzo() +
                "', descrizione = '" + articolo.getDescrizione() +
                "' WHERE idarticolo = '" + articolo.getIdArticolo() + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        return executor.executeOperation(writeOp).getRowsAffected();
    }

    public boolean articoloExists(String nome) {

        String sql = "SELECT count(*) AS count " +
                "FROM progetto_pis.articolo AS a " +
                "WHERE a.nome= '"+ nome +"';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        try {
            rs.next();
            if (rs.getRow() == 1) {
                int count = rs.getInt("count");
                return count == 1;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isProdotto(String nome) {

        String sql = "SELECT count(*) AS count " +
                "FROM progetto_pis.articolo AS a " +
                "INNER JOIN progetto_pis.prodotto AS p " +
                "ON a.idarticolo = p.articolo_idarticolo " +
                "WHERE a.nome='"+ nome +"';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        try {
            rs.next();
            if (rs.getRow() == 1) {
                int count = rs.getInt("count");
                return count == 1;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isProdottoComposito(String nome) {

        String sql = "SELECT count(*) AS count " +
        "FROM progetto_pis.articolo AS a " +
        "INNER JOIN progetto_pis.prodotto AS p ON a.idarticolo = p.articolo_idarticolo " +
        "INNER JOIN progetto_pis.prodotto_has_prodotto AS pc " +
        "ON p.articolo_idarticolo = pc.prodotto_articolo_idarticolo " +
        "WHERE a.nome = '" + nome + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        try {
            rs.next();
            if (rs.getRow() == 1) {
                int count = rs.getInt("count");
                return count > 1;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isServizio(String nome) {

        String sql = "SELECT count(*) AS count " +
                "FROM progetto_pis.articolo AS a " +
                "INNER JOIN progetto_pis.servizio AS s " +
                "ON a.idarticolo = s.articolo_idarticolo " +
                "WHERE a.nome='"+ nome +"';";
        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        try {
            rs.next();
            if (rs.getRow() == 1) {
                int count = rs.getInt("count");
                return count == 1;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}