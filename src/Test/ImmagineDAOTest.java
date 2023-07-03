package Test;

import DAO.ArticoloDAO;
import DAO.IArticoloDAO;
import DAO.IImmagineDAO;
import DAO.ImmagineDAO;
import Model.Articolo;
import Model.Immagine;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

public class ImmagineDAOTest {
    @Before
    public void setUp() throws Exception {
        IImmagineDAO immagineDAO = ImmagineDAO.getInstance();
        IArticoloDAO articoloDAO = ArticoloDAO.getInstance();

        File file = new File("resources\\LOGO.PNG");
        articoloDAO.add(new Articolo(700.55F, null, "ArmadioTest", "Armadio in quercia pregiata", null, null, 8));
        immagineDAO.add(file, articoloDAO.findByName("ArmadioTest").getIdArticolo());
    }

    @After
    public void tearDown() {
        IImmagineDAO immagineDAO = ImmagineDAO.getInstance();
        IArticoloDAO articoloDAO = ArticoloDAO.getInstance();

        immagineDAO.removeByArticolo(articoloDAO.findByName("ArmadioTest").getIdArticolo());
        articoloDAO.removeById(articoloDAO.findByName("ArmadioTest").getIdArticolo());
    }

    @Test
    public void findAllTest() {
        IImmagineDAO immagineDAO = ImmagineDAO.getInstance();
        ArrayList<Immagine> immagini = immagineDAO.findAll();
        Assert.assertFalse(immagini.isEmpty());
    }

    @Test
    public void findByIdTest() {
        IImmagineDAO immagineDAO = ImmagineDAO.getInstance();
        IArticoloDAO articoloDAO = ArticoloDAO.getInstance();

        Articolo articolo = articoloDAO.findByName("ArmadioTest");
        Immagine immagine = immagineDAO.findAllByArticolo(articolo.getIdArticolo()).get(0);
        immagine = immagineDAO.findById(immagine.getIdImmagine());
        Assert.assertNotNull(immagine);
    }
    @Test
    public void findAllByArticoloTest() {
        IImmagineDAO immagineDAO = ImmagineDAO.getInstance();
        IArticoloDAO articoloDAO = ArticoloDAO.getInstance();
        ArrayList<Immagine> immagini = immagineDAO.findAllByArticolo(articoloDAO.findByName("ArmadioTest").getIdArticolo());
        Assert.assertEquals(1, immagini.size());
    }
}