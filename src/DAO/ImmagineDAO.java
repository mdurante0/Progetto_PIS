package DAO;

import DbInterface.IDbConnection;
import DbInterface.command.DbOperationExecutor;
import DbInterface.command.IDbOperation;
import DbInterface.command.ReadOperation;
import DbInterface.command.WriteOperation;
import Model.Immagine;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ImmagineDAO implements IImmagineDAO {
    private static ImmagineDAO instance = new ImmagineDAO();
    private Immagine immagine;
    private static IDbConnection conn;
    private static ResultSet rs;

    private ImmagineDAO() {
        immagine = null;
        conn = null;
        rs = null;
    }

    public static ImmagineDAO getInstance() {
        return instance;
    }

    @Override
    public Immagine findById(int id) {
        String sql = "SELECT idimmagine, immagine, articolo_idarticolo " +
                "FROM progetto_pis.immagine " +
                "WHERE idimmagine = '" + id + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        try {
            rs.next();
            if (rs.getRow()==1) {
                immagine = new Immagine();
                immagine.setIdImmagine(rs.getInt("idimmagine"));
                immagine.setIdArticolo(rs.getInt("articolo_idarticolo"));
                immagine.setPic(new ImageIcon(rs.getBytes("immagine")));

                byte [] array = rs.getBytes("immagine");
                File file = File.createTempFile("something-", ".binary", new File("resources/temporaryFiles"));
                FileOutputStream out = new FileOutputStream( file );
                out.write( array );
                out.close();
                immagine.setFile(file);
                file.deleteOnExit();

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
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public ArrayList<Immagine> findAll() {
        String sql = "SELECT idimmagine, immagine, articolo_idarticolo " +
                "FROM progetto_pis.immagine ;";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        ArrayList<Immagine> immagini = new ArrayList<>();
        try {
            while (rs.next()) {
                immagine = new Immagine();
                immagine.setPic(new ImageIcon(rs.getBytes("immagine")));
                immagine.setIdArticolo(rs.getInt("articolo_idarticolo"));
                immagine.setIdImmagine(rs.getInt("idimmagine"));

                byte [] array = rs.getBytes("immagine");
                File file = File.createTempFile("something-", ".binary", new File("resources/temporaryFiles"));
                FileOutputStream out = new FileOutputStream( file );
                out.write( array );
                out.close();
                immagine.setFile(file);
                file.deleteOnExit();

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
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public ArrayList<Immagine> findAllByArticolo(int idArticolo) {
        String sql = "SELECT idimmagine, immagine, articolo_idarticolo " +
                "FROM progetto_pis.immagine " +
                "WHERE articolo_idarticolo = '" + idArticolo + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        ArrayList<Immagine> immagini = new ArrayList<>();
        try {
            while (rs.next()) {
                immagine = new Immagine();
                immagine.setPic(new ImageIcon(rs.getBytes("immagine")));
                immagine.setIdArticolo(rs.getInt("articolo_idarticolo"));
                immagine.setIdImmagine(rs.getInt("idimmagine"));

                byte [] array = rs.getBytes("immagine");
                File file = File.createTempFile("something-", ".binary", new File("resources/temporaryFiles"));
                FileOutputStream out = new FileOutputStream( file );
                out.write( array );
                out.close();
                immagine.setFile(file);
                file.deleteOnExit();

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
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public int add(File file, int idArticolo) {
        String sql = "INSERT INTO progetto_pis.immagine (immagine, articolo_idarticolo) VALUES (?,'" + idArticolo + "');";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation writeOp = new WriteOperation(file, sql);
        return executor.executeOperation(writeOp).getRowsAffected();
    }

    @Override
    public int removeById(int id) {
        String sql = "DELETE FROM progetto_pis.immagine " +
                "WHERE idimmagine = '" + id + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        return executor.executeOperation(writeOp).getRowsAffected();
    }

    public int removeByArticolo(int idArticolo) {
        String sql = "DELETE FROM progetto_pis.immagine " +
                "WHERE articolo_idarticolo = '" + idArticolo + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        return executor.executeOperation(writeOp).getRowsAffected();
    }
}
