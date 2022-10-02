package DAO;

import DbInterface.IDbConnection;
import DbInterface.command.DbOperationExecutor;
import DbInterface.command.IDbOperation;
import DbInterface.command.ReadOperation;
import DbInterface.command.WriteOperation;
import Model.ListaAcquisto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ListaAcquistoDAO implements IListaAcquistoDAO {
    private static ListaAcquistoDAO instance = new ListaAcquistoDAO();
    private ListaAcquisto listaAcquisto;
    private static IDbConnection conn;
    private static ResultSet rs;
    @Override
    public ListaAcquisto findById(int idLista) {
        String sql = "SELECT idlista_acquisto, utente_acquirente_utente_idutente, pagata, costo_finale " +
                "FROM progetto_pis.lista_acquisto " +
                "WHERE idlista_acquisto = '" + idLista + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        try {
            rs.next();
            if (rs.getRow()==1) {
                listaAcquisto = new ListaAcquisto();
                listaAcquisto.setIdLista(rs.getInt("idlista_acquisto"));
                listaAcquisto.setIdUtente(rs.getInt("utente_acquirente_utente_idutente"));
                listaAcquisto.setPagata(rs.getBoolean("pagata"));
                listaAcquisto.setCostoFinale(rs.getFloat("costo_finale"));
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

    public ArrayList<ListaAcquisto> findByUser(int idUtenteAcquirente){

        String sql = "SELECT idlista_acquisto, utente_acquirente_utente_idutente, pagata, costo_finale " +
                "FROM progetto_pis.lista_acquisto " +
                "WHERE utente_acquirente_utente_idutente = '" + idUtenteAcquirente + "';";
        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        ArrayList<ListaAcquisto> listeAcquisto = new ArrayList<>();
        try {
            while (rs.next()) {
                listaAcquisto = new ListaAcquisto();
                listaAcquisto.setIdLista(rs.getInt("idlista_acquisto"));
                listaAcquisto.setIdUtente(rs.getInt("utente_acquirente_utente_idutente"));
                listaAcquisto.setPagata(rs.getBoolean("pagata"));
                listaAcquisto.setCostoFinale(rs.getFloat("costo_finale"));

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
    public ArrayList<ListaAcquisto> findAll() {
        String sql = "SELECT idlista_acquisto, utente_acquirente_utente_idutente, pagata, costo_finale " +
                "FROM progetto_pis.lista_acquisto ";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        ArrayList<ListaAcquisto> listeAcquisto = new ArrayList<>();
        try {
            while (rs.next()) {
                listaAcquisto = new ListaAcquisto();
                listaAcquisto.setIdLista(rs.getInt("idlista_acquisto"));
                listaAcquisto.setIdUtente(rs.getInt("utente_acquirente_utente_idutente"));
                listaAcquisto.setPagata(rs.getBoolean("pagata"));
                listaAcquisto.setCostoFinale(rs.getFloat("costo_finale"));

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
        String sql = "INSERT INTO progetto_pis.lista_acquisto (utente_acquirente_utente_idutente, pagata, costo_finale) VALUES ('"+
                listaAcquisto.getIdUtente() + "','" +
                listaAcquisto.isPagata() + "','" +
                listaAcquisto.getCostoFinale()+"');";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        return executor.executeOperation(writeOp).getRowsAffected();
    }

    @Override
    public int removeById(int id) {

        String sql = "DELETE FROM progetto_pis.lista_acquisto " +
                "WHERE idlista_acquisto = '" + id + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        return executor.executeOperation(writeOp).getRowsAffected();
    }

    public int removeByUser(int idUtenteAcquirente) {

        String sql = "DELETE FROM progetto_pis.lista_acquisto " +
                "WHERE utente_acquirente_utente_idutente = '" + idUtenteAcquirente + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        return executor.executeOperation(writeOp).getRowsAffected();
    }

    @Override
    public int update(ListaAcquisto listaAcquisto) {
        String sql = "UPDATE progetto_pis.lista_acquisto " +
                "SET utente_acquirente_utente_idutente = '" + listaAcquisto.getIdUtente() +
                "', pagata = '" + listaAcquisto.isPagata() +
                "', costo_finale = '" + listaAcquisto.getCostoFinale() +
                "' WHERE idlista_acquisto = '" + listaAcquisto.getIdLista() + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        return executor.executeOperation(writeOp).getRowsAffected();
    }
}
