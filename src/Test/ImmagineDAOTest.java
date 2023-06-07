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

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;

public class ImmagineDAOTest {
    @Before
    public void setUp() throws Exception {
        IImmagineDAO immagineDAO = ImmagineDAO.getInstance();
        IArticoloDAO articoloDAO = ArticoloDAO.getInstance();

        File file = new File("C:\\Users\\lenovo\\Documents\\ING_INF_III\\Tesi\\LOGO.PNG");
        ImageIcon pic = new ImageIcon();

        articoloDAO.add(new Articolo(700.55F, null, "Armadio", "Armadio in quercia pregiata", null, null, 8));
        immagineDAO.add( file , new Immagine(pic, articoloDAO.findByName("Armadio").getIdArticolo()));
    }

    @After
    public void tearDown() {
        IImmagineDAO immagineDAO = ImmagineDAO.getInstance();
        IArticoloDAO articoloDAO = ArticoloDAO.getInstance();

        immagineDAO.removeByArticolo(articoloDAO.findByName("Armadio").getIdArticolo());
        articoloDAO.removeById(articoloDAO.findByName("Armadio").getIdArticolo());
    }

    @Test
    public void findAllTest() {
        IImmagineDAO immagineDAO = ImmagineDAO.getInstance();
        ArrayList<Immagine> immagini = immagineDAO.findAll();
        Assert.assertEquals(2, immagini.size());
    }

    @Test
    public void findByIdTest() {
        IImmagineDAO immagineDAO = ImmagineDAO.getInstance();
        Immagine immagine = immagineDAO.findById(23);
        Assert.assertEquals(270, immagine.getIdArticolo());
    }
    @Test
    public void findByArticoloTest() {
        IImmagineDAO immagineDAO = ImmagineDAO.getInstance();
        IArticoloDAO articoloDAO = ArticoloDAO.getInstance();
        ArrayList<Immagine> immagini = immagineDAO.findByArticolo(articoloDAO.findByName("Armadio").getIdArticolo());
        Assert.assertEquals(1, immagini.size());
    }

    @Test
    public void updateTest() {
        IImmagineDAO immagineDAO = ImmagineDAO.getInstance();
        Immagine immagine = immagineDAO.findById(23);

        File file = new File("C:\\Users\\lenovo\\Documents\\ING_INF_III\\Tesi\\LOGO.PNG");
            immagine.setIdArticolo(1);
            immagineDAO.update(file, immagine);
            Assert.assertEquals(1, immagine.getIdArticolo());


    }
}