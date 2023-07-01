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
        fornitoreDAO.add(new Fornitore("FornitoreTest","vr46@gmail.com","trento","italy","0995331239","boh"));
    }

    @After
    public void tearDown() {
        IFornitoreDAO fornitoreDAO = FornitoreDAO.getInstance();
        fornitoreDAO.removeById("FornitoreTest");
    }

    @Test
    public void findAllTest() {
        IFornitoreDAO fornitoreDAO = FornitoreDAO.getInstance();
        ArrayList<Fornitore> fornitori = fornitoreDAO.findAll();
        Assert.assertFalse(fornitori.isEmpty());
    }

    @Test
    public void findByIdTest() {
        IFornitoreDAO fornitoreDAO = FornitoreDAO.getInstance();
        Fornitore fornitore = fornitoreDAO.findById(fornitoreDAO.findByName("FornitoreTest").getIdFornitore());
        Assert.assertEquals("FornitoreTest", fornitore.getNome());
    }

    @Test
    public void findByNameTest() {
        IFornitoreDAO fornitoreDAO = FornitoreDAO.getInstance();
        Fornitore fornitore = fornitoreDAO.findByName("FornitoreTest");
        Assert.assertEquals("FornitoreTest", fornitore.getNome());
    }


    @Test
    public void updateTest() {
        IFornitoreDAO fornitoreDAO = FornitoreDAO.getInstance();

        Fornitore fornitore = fornitoreDAO.findByName("FornitoreTest");
        fornitore.setMail("fornitore@gmail.it");
        fornitoreDAO.update(fornitore);
        fornitore = fornitoreDAO.findByName("FornitoreTest");
        Assert.assertEquals("fornitore@gmail.it", fornitore.getMail());
    }
}