package Test;

import DAO.IMagazzinoDAO;
import DAO.IProdottoDAO;
import DAO.MagazzinoDAO;
import DAO.ProdottoDAO;
import Model.Magazzino;
import Model.composite.Prodotto;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class MagazzinoDAOTest {

    @Before
    public void setUp() {
        IMagazzinoDAO magazzinoDAO = MagazzinoDAO.getInstance();
        Magazzino m = new Magazzino( 4, 2, "via Paoli 23", null);
        magazzinoDAO.add(m);
    }

    @After
    public void tearDown() {
        IMagazzinoDAO magazzinoDAO = MagazzinoDAO.getInstance();
        Magazzino m = magazzinoDAO.findByAddress("via Paoli 23");
        // m viene settato a null perchè se il magazzino non contiene alcun prodotto l'inner join non funziona
        // problema: non si possono eliminare magazzini vuoti
        // possibile soluzione: rimuovere l'inner join da tutti i find e creare un metodo getProdotti in MagazzinoDAO

        magazzinoDAO.removeById(m.getIdMagazzino());
    }

    @Test
    public void findAllTest() {
        IMagazzinoDAO magazzinoDAO = MagazzinoDAO.getInstance();
        ArrayList<Magazzino> magazzini = magazzinoDAO.findAll();
        Assert.assertEquals(1, magazzini.size());
    }

    @Test
    public void findByIdTest() {
        IMagazzinoDAO magazzinoDAO = MagazzinoDAO.getInstance();
        Magazzino magazzino = magazzinoDAO.findById(0);
        Assert.assertEquals(0, magazzino.getIdMagazzino());
    }

    @Test
    public void findByAddressTest() {
        IMagazzinoDAO magazzinoDAO = MagazzinoDAO.getInstance();
        Magazzino magazzino = magazzinoDAO.findByAddress("via Beethoven 23");
        Assert.assertEquals(4, magazzino.getQuantitaCorsie());
    }

    @Test
    public void updateTest() {
        IMagazzinoDAO magazzinoDAO = MagazzinoDAO.getInstance();
        Magazzino magazzino = new Magazzino(7, 5, "via Mozart 23", null);
        magazzino.setIdMagazzino(magazzinoDAO.findByAddress(magazzino.getIndirizzo()).getIdMagazzino());
        magazzinoDAO.update(magazzino);
        magazzino = magazzinoDAO.findById(magazzino.getIdMagazzino());
        Assert.assertEquals("via Mozart 23", magazzino.getIndirizzo());
    }


    @Test
    public void addProdottoTest(){
        IMagazzinoDAO magazzinoDAO = MagazzinoDAO.getInstance();
        IProdottoDAO pDao = ProdottoDAO.getInstance();
        Prodotto p = pDao.findByName("tavolo");
        Magazzino m = magazzinoDAO.findByAddress("via Beethoven 23");
        int rowCount = magazzinoDAO.addProdotto(m.getIdMagazzino(), p);
        Assert.assertEquals(1, rowCount);
    }

   @Test
    public void removeProdottoTest(){
        IMagazzinoDAO magazzinoDAO = MagazzinoDAO.getInstance();
        ProdottoDAO pDao = ProdottoDAO.getInstance();
        Prodotto p = pDao.findByName("tavolo");
        Magazzino m = magazzinoDAO.findByAddress("via Beethoven 23");
        int rowCount = magazzinoDAO.removeProdotto(m.getIdMagazzino(), p);
        Assert.assertEquals(1, rowCount);
    }



}