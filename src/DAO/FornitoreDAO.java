package DAO;

import DbInterface.IDbConnection;
import DbInterface.command.DbOperationExecutor;
import DbInterface.command.IDbOperation;
import DbInterface.command.ReadOperation;
import DbInterface.command.WriteOperation;
import Model.Fornitore;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FornitoreDAO implements IFornitoreDAO {
    private static FornitoreDAO instance = new FornitoreDAO();
    private Fornitore fornitore;
    private static IDbConnection conn;
    private static ResultSet rs;

    private FornitoreDAO(){
        fornitore = null;
        conn = null;
        rs = null;
    }

    public static FornitoreDAO getInstance() {
        return instance;
    }

    @Override
    public Fornitore findById(int id) {
        String sql = "SELECT * FROM progetto_pis.fornitore WHERE idfornitore = '" + id + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        try {
            rs.next();
            if (rs.getRow()==1) {
                fornitore = new Fornitore();
                fornitore.setIdFornitore(rs.getInt("idfornitore"));
                fornitore.setNome(rs.getString("nome"));
                fornitore.setMail(rs.getString("email"));
                fornitore.setTelefono(rs.getString("telefono"));
                fornitore.setCitta(rs.getString("citta"));
                fornitore.setNazione(rs.getString("nazione"));
                fornitore.setDescrizione(rs.getString("descrizione"));
                fornitore.setSito(rs.getString("sito"));
                return fornitore;
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
    public Fornitore findByName(String name) {
        String sql = "SELECT * FROM progetto_pis.fornitore WHERE nome = '" + name + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        try {
            rs.next();
            if (rs.getRow()==1) {
                fornitore = new Fornitore();
                fornitore.setIdFornitore(rs.getInt("idfornitore"));
                fornitore.setNome(rs.getString("nome"));
                fornitore.setMail(rs.getString("email"));
                fornitore.setTelefono(rs.getString("telefono"));
                fornitore.setCitta(rs.getString("citta"));
                fornitore.setNazione(rs.getString("nazione"));
                fornitore.setDescrizione(rs.getString("descrizione"));
                fornitore.setSito(rs.getString("sito"));

                return fornitore;
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
    public ArrayList<Fornitore> findAll() {
        String sql = "SELECT * FROM progetto_pis.fornitore ;";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        ArrayList<Fornitore> fornitori = new ArrayList<>();
        try {
            while (rs.next()) {
                fornitore = new Fornitore();
                fornitore.setIdFornitore(rs.getInt("idfornitore"));
                fornitore.setNome(rs.getString("nome"));
                fornitore.setMail(rs.getString("email"));
                fornitore.setTelefono(rs.getString("telefono"));
                fornitore.setCitta(rs.getString("citta"));
                fornitore.setNazione(rs.getString("nazione"));
                fornitore.setDescrizione(rs.getString("descrizione"));
                fornitore.setSito(rs.getString("sito"));

                fornitori.add(fornitore);
            }
            return fornitori;
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
    public int add(Fornitore fornitore) {
        String sql = "INSERT INTO progetto_pis.fornitore (nome, email, telefono, citta, nazione, descrizione, sito) VALUES ('"+
                fornitore.getNome() + "','" +
                fornitore.getMail() + "','" +
                fornitore.getTelefono()+ "','" +
                fornitore.getCitta()+ "','" +
                fornitore.getNazione()+ "','" +
                fornitore.getDescrizione()+ "','" +
                fornitore.getSito() + "');";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        return executor.executeOperation(writeOp).getRowsAffected();
    }

    @Override
    public int removeById(String nome) {

        String sql = "DELETE FROM progetto_pis.fornitore WHERE nome = '" + nome + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        return executor.executeOperation(writeOp).getRowsAffected();
    }
    @Override
    public int update(Fornitore fornitore) {
        String sql = "UPDATE progetto_pis.fornitore " +
                "SET nome = '" + fornitore.getNome() +
                "', email = '" + fornitore.getMail() +
                "', telefono = '" + fornitore.getTelefono() +
                "', citta = '" + fornitore.getCitta() +
                "', nazione = '" + fornitore.getNazione() +
                "', descrizione = '" + fornitore.getDescrizione() +
                "', sito = '" + fornitore.getSito() +
                "' WHERE idfornitore = '" + fornitore.getIdFornitore() + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        return executor.executeOperation(writeOp).getRowsAffected();
    }
}
