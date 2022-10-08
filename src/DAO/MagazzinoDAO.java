package DAO;

import DbInterface.IDbConnection;
import DbInterface.command.DbOperationExecutor;
import DbInterface.command.IDbOperation;
import DbInterface.command.ReadOperation;
import DbInterface.command.WriteOperation;
import Model.Magazzino;
import Model.composite.IProdotto;
import Model.composite.Prodotto;
import Model.composite.ProdottoComposito;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

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
    public Magazzino findById(int idMagazzino) {

        String sql = "SELECT idmagazzino, quantita_corsie, quantita_scaffali, prodotto_articolo_idarticolo, quantita " +
                "FROM progetto_pis.magazzino AS m INNER JOIN progetto_pis_magazzino_has_prodotto AS mp " +
                "ON m.idmagazzino = mp.magazzino_idmagazzino " +
                "WHERE idmagazzino = '" + idMagazzino + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        try {
            rs.next();
            if (rs.getRow()==1) {
                magazzino = new Magazzino();
                magazzino.setIdMagazzino(rs.getInt("idmagazzino"));
                magazzino.setQuantitaCorsie(rs.getInt("quantita_corsie"));
                magazzino.setQuantitaScaffali(rs.getInt("quantita_scaffali"));

                ProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
                Prodotto prodotto;
                while (rs.next()){
                    prodotto = prodottoDAO.findById(rs.getInt("articolo_idarticolo"));
                    prodotto.setQuantita(rs.getInt("quantita"));
                    magazzino.add(prodotto);

                }

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

        String sql = "SELECT idmagazzino, quantita_corsie, quantita_scaffali, prodotto_articolo_idarticolo, quantita " +
                "FROM progetto_pis.magazzino AS m INNER JOIN progetto_pis_magazzino_has_prodotto AS mp " +
                "ON m.idmagazzino = mp.magazzino_idmagazzino;";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        ArrayList<Magazzino> magazzini = new ArrayList<>();
        try {
            while (rs.next()) {
                magazzino = new Magazzino();
                magazzino.setIdMagazzino(rs.getInt("idmagazzino"));
                magazzino.setQuantitaCorsie(rs.getInt("quantita_corsie"));
                magazzino.setQuantitaScaffali(rs.getInt("quantita_scaffali"));

                ProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
                Prodotto prodotto;
                while (rs.next()){
                    prodotto = prodottoDAO.findById(rs.getInt("articolo_idarticolo"));
                    prodotto.setQuantita(rs.getInt("quantita"));
                    magazzino.add(prodotto);

                }

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

        String sql = "INSERT INTO progetto_pis.magazzino (quantita_corsie, quantita_scaffali) VALUES ('"+
                magazzino.getQuantitaCorsie() + "','" +
                magazzino.getQuantitaScaffali() + ");";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        executor.executeOperation(writeOp);

        sql = "SELECT max(idmagazzino) FROM progetto_pis.magazzino;";
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        int rowCount = 0;
        try {
            rs.next();
            magazzino.setIdMagazzino(rs.getInt("max(idmagazzino)"));

            ProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
            Iterator<IProdotto> prodottoIterator = magazzino.getProdotti().iterator();
            Prodotto prodotto;
            while (prodottoIterator.hasNext()) {

                prodotto = prodottoDAO.findByName(prodottoIterator.next().getName());
                sql = "INSERT INTO progetto_pis.magazzino_has_prodotto " +
                        "(magazzino_idmagazzino, prodotto_articolo_idarticolo, collocazione_idcollocazione ,quantita_prodotto) " +
                        "VALUES ('" + magazzino.getIdMagazzino() + "','" +
                        prodotto.getIdArticolo() + "','" +
                        prodotto.getIdCollocazione() + "','" +
                        prodotto.getQuantita() + "');";

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

    public int addProdotto(int idMagazzino, IProdotto iProdotto){

        String sql;
        if (iProdotto instanceof Prodotto prodotto) {
            sql = "INSERT INTO progetto_pis.magazzino_has_prodotto " +
                    "(magazzino_idmagazzino, prodotto_articolo_idarticolo, collocazione_idcollocazione, quantita) VALUES ('" +
                    idMagazzino + "','" +
                    prodotto.getIdArticolo() + "','" +
                    prodotto.getIdCollocazione() + "','" +
                    prodotto.getQuantita() + "');";
        }
        else if (iProdotto instanceof ProdottoComposito prodottoComposito) {
            sql = "INSERT INTO progetto_pis.magazzino_has_prodotto " +
                    "(magazzino_idmagazzino, prodotto_articolo_idarticolo, quantita_prodotto) VALUES ('" +
                    idMagazzino + "','" +
                    prodottoComposito.getIdArticolo() + "','" +
                    prodottoComposito.getQuantita() + "');";
        }
        else return -1;

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        return executor.executeOperation(writeOp).getRowsAffected();
    }

    public int removeProdotto(int idMagazzino, IProdotto iProdotto){

        String sql = "DELETE FROM progetto_pis.magazzino_has_prodotto " +
                "WHERE magazzino_idmagazzino = '" + idMagazzino +
                "' AND prodotto_articolo_idarticolo = '" + iProdotto.getIdArticolo() + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        return executor.executeOperation(writeOp).getRowsAffected();
    }

    @Override
    public int removeById(int idMagazzino) {

        String sql = "DELETE FROM progetto_pis.magazzino " +
                "WHERE idmagazzino = '"+ idMagazzino + "';";

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