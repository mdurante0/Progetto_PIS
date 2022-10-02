package DAO;

import DbInterface.IDbConnection;
import DbInterface.command.DbOperationExecutor;
import DbInterface.command.IDbOperation;
import DbInterface.command.ReadOperation;
import DbInterface.command.WriteOperation;
import Model.Immagine;
import Model.Produttore;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ImmagineDAO implements IImmagineDAO {
    private static IImmagineDAO instance = new ImmagineDAO();
    private Immagine immagine;
    private static IDbConnection conn;
    private static ResultSet rs;
    @Override
    public Immagine findById(int id) {
        String sql = "SELECT immagine" +
                "FROM progetto_pis.immagine " +
                "WHERE articolo_idarticolo = '" + id + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        try {
            rs.next();
            if (rs.getRow()==1) {
                immagine = new Immagine();
                immagine.setPic((ImageIcon) rs.getBlob("immagine"));

                return immagine;
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
    public ArrayList<Immagine> findAll() {
        String sql = "SELECT immagine, articolo_idarticolo" +
                "FROM progetto_pis.immagine ;";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        ArrayList<Immagine> immagini = new ArrayList<>();
        try {
            while (rs.next()) {
                immagine = new Immagine();
                immagine.setPic((ImageIcon) rs.getBlob("immagine"));
                immagine.setIdArticolo(rs.getInt("articolo_idarticolo"));

                immagini.add(immagine);
            }
            return immagini;
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
    public int add(Immagine immagine) {

        String sql = "INSERT INTO progetto_pis.immagine (immagine, articolo_idarticolo) VALUES ('"+
                immagine.getPic() + "','" +
                immagine.getIdArticolo() +"');";
        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        return executor.executeOperation(writeOp).getRowsAffected();
    }

    @Override
    public int removeById(int id) {
        String sql = "DELETE FROM progetto_pis.immagine " +
                "WHERE articolo_idarticolo = '" + id + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        return executor.executeOperation(writeOp).getRowsAffected();
    }

    @Override
    public int update(Immagine immagine) {
        String sql = "UPDATE progetto_pis.immagine " +
                "SET immagine = '" + immagine.getPic() +
                "' WHERE articolo_idarticolo = '" + immagine.getIdArticolo() + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        return executor.executeOperation(writeOp).getRowsAffected();
    }
}
