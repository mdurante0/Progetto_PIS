package DAO;

import DbInterface.IDbConnection;
import DbInterface.command.DbOperationExecutor;
import DbInterface.command.IDbOperation;
import DbInterface.command.ReadOperation;
import DbInterface.command.WriteOperation;
import Model.Collocazione;
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

        String sql = "SELECT * FROM progetto_pis.magazzino WHERE idmagazzino = '" + idMagazzino + "';";

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
                magazzino.setIndirizzo(rs.getString("indirizzo"));

                sql = "SELECT * FROM progetto_pis.magazzino_has_prodotto WHERE magazzino_idmagazzino = '" + idMagazzino + "';";
                executor = new DbOperationExecutor();
                readOp = new ReadOperation(sql);
                rs = executor.executeOperation(readOp).getResultSet();

                ProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
                CollocazioneDAO collocazioneDAO = CollocazioneDAO.getInstance();
                while(rs.next()){
                    Prodotto prodotto = prodottoDAO.findById(rs.getInt("prodotto_articolo_idarticolo"));
                    prodotto.setQuantita(rs.getInt("quantita_prodotto"));
                    //prodotto.setCollocazione(collocazioneDAO.findById(rs.getInt("collocazione_idcollocazione")));
                    prodotto.setCollocazione(new Collocazione());
                    prodotto.getCollocazione().setIdCollocazione(rs.getInt("collocazione_idcollocazione"));
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

    public Magazzino findByAddress(String indirizzo) {

        String sql = "SELECT * FROM progetto_pis.magazzino WHERE indirizzo = '" + indirizzo + "';";

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
                magazzino.setIndirizzo(rs.getString("indirizzo"));

                sql = "SELECT * FROM progetto_pis.magazzino_has_prodotto WHERE magazzino_idmagazzino = '" + magazzino.getIdMagazzino() + "';";
                executor = new DbOperationExecutor();
                readOp = new ReadOperation(sql);
                rs = executor.executeOperation(readOp).getResultSet();

                ProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
                CollocazioneDAO collocazioneDAO = CollocazioneDAO.getInstance();
                while(rs.next()){
                    Prodotto prodotto = prodottoDAO.findById(rs.getInt("prodotto_articolo_idarticolo"));
                    prodotto.setQuantita(rs.getInt("quantita_prodotto"));
                    prodotto.setCollocazione(collocazioneDAO.findById(rs.getInt("collocazione_idcollocazione")));
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

        String sql = "SELECT * FROM progetto_pis.magazzino ;";

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
                magazzino.setIndirizzo(rs.getString("indirizzo"));

                sql = "SELECT * FROM progetto_pis.magazzino_has_prodotto WHERE magazzino_idmagazzino = '" + magazzino.getIdMagazzino() + "';";
                DbOperationExecutor executor2 = new DbOperationExecutor();
                IDbOperation readOp2 = new ReadOperation(sql);
                ResultSet rs2 = executor2.executeOperation(readOp2).getResultSet();

                ProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
                CollocazioneDAO collocazioneDAO = CollocazioneDAO.getInstance();
                while(rs2.next()){
                    Prodotto prodotto = prodottoDAO.findById(rs2.getInt("prodotto_articolo_idarticolo"));
                    prodotto.setQuantita(rs2.getInt("quantita_prodotto"));
                    prodotto.setCollocazione(collocazioneDAO.findById(rs2.getInt("collocazione_idcollocazione")));
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

        String sql = "INSERT INTO progetto_pis.magazzino (quantita_corsie, quantita_scaffali, indirizzo) VALUES ('"+
                magazzino.getQuantitaCorsie() + "','" +
                magazzino.getQuantitaScaffali() + "','" +
                magazzino.getIndirizzo() + "');";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        int rowCount = executor.executeOperation(writeOp).getRowsAffected();

        sql = "SELECT max(idmagazzino) FROM progetto_pis.magazzino;";
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        try {
            rs.next();
            magazzino.setIdMagazzino(rs.getInt("max(idmagazzino)"));

            Iterator<IProdotto> prodottoIterator = magazzino.getProdotti().iterator();
            IProdotto iProdotto;
            while (prodottoIterator.hasNext()) {

                iProdotto = prodottoIterator.next();
                if (iProdotto instanceof Prodotto prodotto) {
                    sql = "INSERT INTO progetto_pis.magazzino_has_prodotto " +
                            "(magazzino_idmagazzino, prodotto_articolo_idarticolo, collocazione_idcollocazione, quantita_prodotto) VALUES ('" +
                            magazzino.getIdMagazzino() + "','" +
                            prodotto.getIdArticolo() + "','" +
                            prodotto.getCollocazione().getIdCollocazione() + "','" +
                            prodotto.getQuantita() + "');";
                }
                else if (iProdotto instanceof ProdottoComposito prodottoComposito) {
                    sql = "INSERT INTO progetto_pis.magazzino_has_prodotto " +
                            "(magazzino_idmagazzino, prodotto_articolo_idarticolo, quantita_prodotto) VALUES ('" +
                            magazzino.getIdMagazzino() + "','" +
                            prodottoComposito.getIdArticolo() + "','" +
                            prodottoComposito.getQuantita() + "');";
                }
                else return -1;

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
                    "(magazzino_idmagazzino, prodotto_articolo_idarticolo, collocazione_idcollocazione, quantita_prodotto) VALUES ('" +
                    idMagazzino + "','" +
                    prodotto.getIdArticolo() + "','" +
                    prodotto.getCollocazione().getIdCollocazione() + "','" +
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

    public boolean prodottoExists(int idMagazzino, int idProdotto) {

        String sql = "SELECT count(*) FROM progetto_pis.magazzino_has_prodotto WHERE magazzino_idmagazzino = '" + idMagazzino +
                "' AND prodotto_articolo_idarticolo = '" + idProdotto + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        try {
            rs.next();
            if (rs.getRow() == 1) {
                int count = rs.getInt("count(*)");
                return count == 1;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public int updateProdotto(Magazzino magazzino, IProdotto iProdotto) {

        String sql;
        if (iProdotto instanceof Prodotto prodotto) {
            sql = "UPDATE progetto_pis.magazzino_has_prodotto " +
                    "SET collocazione_idcollocazione = '" + prodotto.getCollocazione().getIdCollocazione() +
                    "', quantita_prodotto = '"+ prodotto.getQuantita() +
                    "' WHERE magazzino_idmagazzino = '" + magazzino.getIdMagazzino() +
                    "' AND prodotto_articolo_idarticolo = '" + prodotto.getIdArticolo() + "';";
        }
        else if (iProdotto instanceof ProdottoComposito prodottoComposito) {
            sql = "UPDATE progetto_pis.magazzino_has_prodotto " +
                    "SET quantita_prodotto = '" + prodottoComposito.getQuantita() +
                    "' WHERE magazzino_idmagazzino = '" + magazzino.getIdMagazzino() +
                    "' AND prodotto_articolo_idarticolo = '" + prodottoComposito.getIdArticolo() + "';";
        }
        else return -1;

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        return executor.executeOperation(writeOp).getRowsAffected();
    }

    @Override
    public int update(Magazzino magazzino) {

        String sql = "UPDATE progetto_pis.magazzino " +
                "SET quantita_corsie = '" + magazzino.getQuantitaCorsie() +
                "', quantita_scaffali = '"+ magazzino.getQuantitaScaffali() +
                "', indirizzo = '"+ magazzino.getIndirizzo() +
                "' WHERE idmagazzino = '" + magazzino.getIdMagazzino() + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        int rowCount = executor.executeOperation(writeOp).getRowsAffected();


        Iterator<IProdotto> prodottoIterator = magazzino.getProdotti().iterator();
        IProdotto iProdotto;
        while (prodottoIterator.hasNext()) {

            iProdotto = prodottoIterator.next();
            if (iProdotto instanceof Prodotto prodotto) {
                sql = "UPDATE progetto_pis.magazzino_has_prodotto " +
                        "SET collocazione_idcollocazione = '" + prodotto.getCollocazione().getIdCollocazione() +
                        "', quantita_prodotto = '"+ prodotto.getQuantita() +
                        "' WHERE magazzino_idmagazzino = '" + magazzino.getIdMagazzino() +
                        "' AND prodotto_articolo_idarticolo = '" + prodotto.getIdArticolo() + "';";
            }
            else if (iProdotto instanceof ProdottoComposito prodottoComposito) {
                sql = "UPDATE progetto_pis.magazzino_has_prodotto " +
                        "SET quantita_prodotto = '" + prodottoComposito.getQuantita() +
                        "' WHERE magazzino_idmagazzino = '" + magazzino.getIdMagazzino() +
                        "' AND prodotto_articolo_idarticolo = '" + prodottoComposito.getIdArticolo() + "';";
            }
            else return -1;

            writeOp = new WriteOperation(sql);
            rowCount += executor.executeOperation(writeOp).getRowsAffected();

        }
        return rowCount;
    }
}