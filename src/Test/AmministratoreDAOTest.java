package Test;

import DAO.AmministratoreDAO;
import DAO.IAmministratoreDAO;
import Model.Amministratore;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class AmministratoreDAOTest {
    @Before
    public void setUp() {
        IAmministratoreDAO amministratoreDAO = AmministratoreDAO.getInstance();
        amministratoreDAO.add(new Amministratore("Valentino", "Rossi", "test", "123", "valentino@gmail.com", "AM"));
    }

    @After
    public void tearDown() {
        IAmministratoreDAO amministratoreDAO = AmministratoreDAO.getInstance();
        amministratoreDAO.removeById("test");
    }

    @Test
    public void findAllTest() {
        IAmministratoreDAO amministratoreDAO = AmministratoreDAO.getInstance();
        ArrayList<Amministratore> amministratori = amministratoreDAO.findAll();
        Assert.assertEquals(2, amministratori.size());
    }

    @Test
    public void findByUsernameTest() {
        IAmministratoreDAO amministratoreDAO = AmministratoreDAO.getInstance();
        Amministratore amministratore = amministratoreDAO.findByUsername("test");
        Assert.assertEquals("Valentino", amministratore.getName());
    }

    @Test
    public void updateTest() {
        IAmministratoreDAO amministratoreDAO = AmministratoreDAO.getInstance();
        Amministratore amministratore = amministratoreDAO.findByUsername("test");
        amministratore.setEmail("valentino@vr46.com");
        amministratoreDAO.update(amministratore);
        amministratore = amministratoreDAO.findByUsername("test");
        Assert.assertEquals("valentino@vr46.com", amministratore.getEmail());
    }
}