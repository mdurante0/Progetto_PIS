package Test;

import DAO.ArticoloDAO;
import DAO.IArticoloDAO;
import Model.Articolo;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class ArticoloDAOTest {
    @Before
    public void setUp() {
        IArticoloDAO articoloDAO = ArticoloDAO.getInstance();
        articoloDAO.add(new Articolo(7.5F, null, "Armadio", "è un cazzo di armadio", null, null, 8));
    }

    @After
    public void tearDown() {
        IArticoloDAO articoloDAO = ArticoloDAO.getInstance();
        articoloDAO.removeById(articoloDAO.findByName("Armadio").getIdArticolo());
    }

    @Test
    public void testFindByName() {
        IArticoloDAO articoloDAO = ArticoloDAO.getInstance();
        Articolo articolo = articoloDAO.findByName("Armadio");
        Assert.assertEquals("Armadio", articolo.getName());
    }

    @Test
    public void testFindAll() {
        IArticoloDAO articoloDAO = ArticoloDAO.getInstance();
        ArrayList<Articolo> articoli = articoloDAO.findAll();
        Assert.assertEquals(1, articoli.size());
    }

    @Test
    public void testUpdate() {
        IArticoloDAO articoloDAO = ArticoloDAO.getInstance();
        float prezzo = 8.5F;
        Articolo articolo = new Articolo(prezzo, null, "Armadio", "AZZ", null, null, 8);
        articolo.setIdArticolo(articoloDAO.findByName(articolo.getName()).getIdArticolo());
        articoloDAO.update(articolo);
        articolo = articoloDAO.findByName("Armadio");
        Assert.assertEquals("AZZ", articolo.getDescrizione());

    }

    @Test
    public void articoloExistsTest() {
        IArticoloDAO articoloDAO = ArticoloDAO.getInstance();
        boolean exist = articoloDAO.articoloExists("Armadio");
        Assert.assertEquals(true, exist);
        exist = articoloDAO.articoloExists("Comodino");
        Assert.assertEquals(false, exist);
    }

    @Test
    public void isProdottoTest() {
        IArticoloDAO articoloDAO = ArticoloDAO.getInstance();
        IArticoloDAO articoloDAO1 = ArticoloDAO.getInstance();
        IArticoloDAO articoloDAO2 = ArticoloDAO.getInstance();

        boolean check = articoloDAO.isProdotto("Armadio");
        Assert.assertEquals(true, check);

        check = articoloDAO1.isProdotto("Trasporto");
        Assert.assertEquals(false, check);

        check = articoloDAO2.isProdotto("Tavolo con sedie");
        Assert.assertEquals(false, check);

    }

    @Test
    public void isProdottoCompositoTest() {
        IArticoloDAO articoloDAO = ArticoloDAO.getInstance();
        IArticoloDAO articoloDAO1 = ArticoloDAO.getInstance();
        IArticoloDAO articoloDAO2 = ArticoloDAO.getInstance();

        boolean check = articoloDAO.isProdottoComposito("Armadio");
        Assert.assertEquals(false, check);

        check = articoloDAO1.isProdottoComposito("Trasporto");
        Assert.assertEquals(false, check);

        check = articoloDAO2.isProdottoComposito("Tavolo con sedie");
        Assert.assertEquals(true, check);

    }

    @Test
    public void isServizioTest() {
        IArticoloDAO articoloDAO = ArticoloDAO.getInstance();
        IArticoloDAO articoloDAO1 = ArticoloDAO.getInstance();
        IArticoloDAO articoloDAO2 = ArticoloDAO.getInstance();

        boolean check = articoloDAO.isServizio("Armadio");
        Assert.assertEquals(false, check);

        check = articoloDAO1.isServizio("Trasporto");
        Assert.assertEquals(true, check);

        check = articoloDAO2.isServizio("Tavolo con sedie");
        Assert.assertEquals(false, check);
    }
}

