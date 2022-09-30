package DAO;

import DbInterface.IDbConnection;
import DbInterface.command.DbOperationExecutor;
import DbInterface.command.IDbOperation;
import DbInterface.command.ReadOperation;
import DbInterface.command.WriteOperation;
import Model.Magazzino;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MagazzinoDAO implements IMagazzinoDAO {
    private static MagazzinoDAO instance = new MagazzinoDAO();
    private Magazzino magazzino;
    private static IDbConnection conn;
    private static ResultSet rs;

    private MagazzinoDAO() {
        magazzino = null;
        conn = null;
        rs = null;
    }

    public static MagazzinoDAO getInstance() {
        return instance;
    }

    @Override
    public Magazzino findById(int id) {

        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "SELECT idmagazzino, quantita_corsie, quantita_scaffali FROM progetto_pis.magazzino " +
                "WHERE idmagazzino = '" + id + "';";
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        try {
            rs.next();
            if (rs.getRow()==1) {
                magazzino = new Magazzino();
                magazzino.setIdMagazzino(rs.getInt("idmagazzino"));
                magazzino.setQuantitaCorsie(rs.getInt("quantita_corsie"));
                magazzino.setQuantitaScaffali(rs.getInt("quantita_scaffali"));

                return magazzino;
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
    public ArrayList<Magazzino> findAll() {
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "SELECT idmagazzino, quantita_corsie, quantita_scaffali FROM progetto_pis.magazzino ;";
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        ArrayList<Magazzino> magazzini = new ArrayList<>();
        try {
            while (rs.next()) {
                magazzino = new Magazzino();
                magazzino.setIdMagazzino(rs.getInt("idmagazzino"));
                magazzino.setQuantitaCorsie(rs.getInt("quantita_corsie"));
                magazzino.setQuantitaScaffali(rs.getInt("quantita_scaffali"));

                magazzini.add(magazzino);
            }
            return magazzini;
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
    public int add(Magazzino magazzino) {

        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "INSERT INTO progetto_pis.magazzino (quantita_corsie, quantita_scaffali) VALUES ('"+
                magazzino.getQuantitaCorsie() + "','" +
                magazzino.getQuantitaScaffali() + ");";
        IDbOperation writeOp = new WriteOperation(sql);

        return  executor.executeOperation(writeOp).getRowsAffected();
    }

    @Override
    public int removeById(int id) {
        String sql = "DELETE FROM progetto_pis.magazzino " +
                "WHERE idmagazzino = '"+ id + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        return executor.executeOperation(writeOp).getRowsAffected();
    }


    @Override
    public int update(Magazzino magazzino) {

        String sql = "UPDATE progetto_pis.magazzino " +
                "SET quantita_corsie = '" + magazzino.getQuantitaCorsie() +
                "', quantita_scaffali = '"+ magazzino.getQuantitaScaffali() +
                "' WHERE idmagazzino = '" + magazzino.getIdMagazzino() + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        return executor.executeOperation(writeOp).getRowsAffected();
    }

}