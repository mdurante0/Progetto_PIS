package DAO;

import DbInterface.IDbConnection;
import DbInterface.command.DbOperationExecutor;
import DbInterface.command.IDbOperation;
import DbInterface.command.ReadOperation;
import DbInterface.command.WriteOperation;
import Model.Produttore;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProduttoreDAO implements IProduttoreDAO {
    private static ProduttoreDAO instance = new ProduttoreDAO();
    private Produttore produttore;
    private static IDbConnection conn;
    private static ResultSet rs;

    private ProduttoreDAO(){
        produttore = null;
        conn = null;
        rs = null;
    }

    public static ProduttoreDAO getInstance() {
        return instance;
    }

    @Override
    public Produttore findById(String name) {
        String sql = "SELECT idproduttore, nome, email, telefono, citta, nazione, descrizione FROM progetto_pis.produttore WHERE nome = '" + name + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        try {
            rs.next();
            if (rs.getRow()==1) {
                produttore = new Produttore();
                produttore.setIdProduttore(rs.getInt("idproduttore"));
                produttore.setNome(rs.getString("nome"));
                produttore.setMail(rs.getString("email"));
                produttore.setTelefono(rs.getString("telefono"));
                produttore.setCitta(rs.getString("citta"));
                produttore.setNazione(rs.getString("nazione"));
                produttore.setDescrizione(rs.getString("descrizione"));
                return produttore;
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
    public ArrayList<Produttore> findAll() {
        String sql = "SELECT idproduttore, nome, email, telefono, citta, nazione, descrizione FROM progetto_pis.produttore ;";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        ArrayList<Produttore> produttori = new ArrayList<>();
        try {
            while (rs.next()) {
                produttore = new Produttore();
                produttore.setIdProduttore(rs.getInt("idproduttore"));
                produttore.setNome(rs.getString("nome"));
                produttore.setMail(rs.getString("email"));
                produttore.setTelefono(rs.getString("telefono"));
                produttore.setCitta(rs.getString("citta"));
                produttore.setNazione(rs.getString("nazione"));
                produttore.setDescrizione(rs.getString("descrizione"));
                produttori.add(produttore);
            }
            return produttori;
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
    public int add(Produttore produttore) {
        String sql = "INSERT INTO progetto_pis.produttore (nome, email, telefono, citta, nazione, descrizione) VALUES ('"+
                produttore.getNome() + "','" +
                produttore.getMail() + "','" +
                produttore.getTelefono()+ "','" +
                produttore.getCitta()+ "','" +
                produttore.getNazione()+ "','" +
                produttore.getDescrizione() +"');";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        return executor.executeOperation(writeOp).getRowsAffected();
    }

    @Override
    public int removeById(String nome) {

        String sql = "DELETE FROM progetto_pis.produttore WHERE nome = '" + nome + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        return executor.executeOperation(writeOp).getRowsAffected();
    }
    @Override
    public int update(Produttore produttore) {
        String sql = "UPDATE progetto_pis.produttore " +
                "SET nome = '" + produttore.getNome() +
                "', email = '" + produttore.getMail() +
                "', telefono = '" + produttore.getTelefono() +
                "', citta = '" + produttore.getCitta() +
                "', nazione = '" + produttore.getNazione() +
                "', descrizione = '" + produttore.getDescrizione() +
                "' WHERE idproduttore = '" + produttore.getIdProduttore() + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        return executor.executeOperation(writeOp).getRowsAffected();
    }
}
