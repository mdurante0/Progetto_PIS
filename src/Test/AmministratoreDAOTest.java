package Test;

import Business.LoginResult;
import Business.SessionManager;
import Business.UtenteBusiness;
import DAO.AmministratoreDAO;
import DAO.IAmministratoreDAO;
import Model.Amministratore;
import Model.Cliente;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class AmministratoreDAOTest {
    @Before
    public void setUp() throws Exception {
        IAmministratoreDAO amministratoreDAO = AmministratoreDAO.getInstance();
        amministratoreDAO.add(new Amministratore("Valentino", "Rossi", "vr46", "123", "valentino@gmail.com", "am"));
    }

    @After
    public void tearDown() throws Exception {
        IAmministratoreDAO amministratoreDAO = AmministratoreDAO.getInstance();
        amministratoreDAO.removeById("vr46");
    }

    @Test
    public void findAllTest() {
        IAmministratoreDAO amministratoreDAO = AmministratoreDAO.getInstance();
        ArrayList<Amministratore> amministratori = amministratoreDAO.findAll();
        Assert.assertEquals(1, amministratori.size());
    }

    @Test
    public void findByIdTest() {
        IAmministratoreDAO amministratoreDAO = AmministratoreDAO.getInstance();
        Amministratore amministratore = amministratoreDAO.findById("vr46");
        Assert.assertEquals("Valentino", amministratore.getName());
    }


    @Test
    public void removeByIdTest() {
        IAmministratoreDAO amministratoreDAO = AmministratoreDAO.getInstance();
        int rowCount = amministratoreDAO.removeById("vr46");
        Assert.assertEquals(1, rowCount);
    }

    @Test
    public void updateTest() {
        IAmministratoreDAO amministratoreDAO = AmministratoreDAO.getInstance();
        Amministratore amministratore = new Amministratore("Valentino", "Rossi", "vr46", "123", "valentino@vr46.com", "MN");
        amministratore.setIdUtente(amministratoreDAO.findById(amministratore.getUsername()).getIdUtente());
        amministratoreDAO.update(amministratore);
        amministratore = amministratoreDAO.findById("vr46");
        Assert.assertEquals("valentino@vr46.com", amministratore.getEmail());
    }

    //@Test
    public void loginTest() {

        UtenteBusiness ub = UtenteBusiness.getInstance();
        String username = "roberto";
        String password = "12345";
        LoginResult result = ub.login(username, password);

        Assert.assertNotNull(result);
        Assert.assertTrue(result.getResult() == LoginResult.Result.LOGIN_OK);
        Assert.assertNotNull(SessionManager.getSession().get(SessionManager.LOGGED_USER));
        Assert.assertTrue(SessionManager.getSession().get(SessionManager.LOGGED_USER) instanceof Cliente);

        Cliente c = (Cliente) SessionManager.getSession().get(SessionManager.LOGGED_USER);
        Assert.assertTrue(c.getIdUtente() == 1);
    }

}