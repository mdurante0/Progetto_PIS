package Test;
import DAO.*;
import DAO.IArticoloDAO;
import Model.Amministratore;
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
        articoloDAO.add(new Articolo(7.5F, null, "Armadio", "è un cazzo di armadio", null, null, 8 ));
    }
    @After
    public void tearDown(){
       IArticoloDAO articoloDAO = ArticoloDAO.getInstance();
        articoloDAO.removeById("Armadio");
    }
    @Test
    public void testFindByName() {
        IArticoloDAO articoloDAO = ArticoloDAO.getInstance();
        Articolo articolo = articoloDAO.findByName("Armadio");
        Assert.assertEquals("Armadio", articolo.getName());
    }
    @Test
    public void testFindAll(){
        IArticoloDAO articoloDAO = ArticoloDAO.getInstance();
        ArrayList<Articolo> articoli = articoloDAO.findAll();
        Assert.assertEquals(1, articoli.size());
    }

    @Test
    public void testUpdate(){
        IArticoloDAO articoloDAO = ArticoloDAO.getInstance();
        float prezzo = 8.5F;
        Articolo articolo = new Articolo(prezzo, null, "Armadio", "AZZ", null, null, 8 );
        articolo.setIdArticolo(articoloDAO.findByName(articolo.getName()).getIdArticolo());
        articoloDAO.update(articolo);
        articolo = articoloDAO.findByName("Armadio");
        Assert.assertEquals("AZZ", articolo.getDescrizione());

    }
}

