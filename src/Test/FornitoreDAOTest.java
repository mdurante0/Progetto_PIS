package Test;

import DAO.FornitoreDAO;
import DAO.IFornitoreDAO;
import Model.Fornitore;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class FornitoreDAOTest {
    @Before
    public void setUp() {
        IFornitoreDAO fornitoreDAO = FornitoreDAO.getInstance();
        fornitoreDAO.add(new Fornitore("Valentino","vr46@gmail.com","trento","italy","0995331239","boh"));
    }

    @After
    public void tearDown() {
        IFornitoreDAO fornitoreDAO = FornitoreDAO.getInstance();
        fornitoreDAO.removeById("Valentino");
    }

    @Test
    public void findAllTest() {
        IFornitoreDAO fornitoreDAO = FornitoreDAO.getInstance();
        ArrayList<Fornitore> produttori = fornitoreDAO.findAll();
        Assert.assertEquals(1, produttori.size());
    }

    @Test
    public void findByIdTest() {
        IFornitoreDAO fornitoreDAO = FornitoreDAO.getInstance();
        Fornitore fornitore = fornitoreDAO.findById(fornitoreDAO.findByName("Valentino").getIdFornitore());
        Assert.assertEquals("Valentino", fornitore.getNome());
    }

    @Test
    public void findByNameTest() {
        IFornitoreDAO fornitoreDAO = FornitoreDAO.getInstance();
        Fornitore fornitore = fornitoreDAO.findByName("Valentino");
        Assert.assertEquals("Valentino", fornitore.getNome());
    }


    @Test
    public void removeByIdTest() {
        IFornitoreDAO fornitoreDAO = FornitoreDAO.getInstance();
        int rowCount = fornitoreDAO.removeById("Valentino");
        Assert.assertEquals(1, rowCount);
    }

    @Test
    public void updateTest() {
        IFornitoreDAO fornitoreDAO = FornitoreDAO.getInstance();
        Fornitore fornitore = new Fornitore("Valentino","vr46@gmail.it","trento","italy","0995331239","boh");
        fornitore.setIdFornitore(fornitoreDAO.findByName(fornitore.getNome()).getIdFornitore());
        fornitoreDAO.update(fornitore);
        fornitore = fornitoreDAO.findByName("Valentino");
        Assert.assertEquals("vr46@gmail.it", fornitore.getMail());
    }
}