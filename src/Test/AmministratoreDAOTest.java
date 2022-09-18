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
    public void setUp() throws Exception {
        IAmministratoreDAO amministratoreDAO = AmministratoreDAO.getInstance();
        amministratoreDAO.add(new Amministratore("Valentino", "Rossi", "vr46", "123", "valentino@gmail.com", "am"));
    }

    @After
    public void tearDown() throws Exception {
        //IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        //utenteDAO.removeById("vr46");
    }

    @Test
    public void findAllTest() {
        IAmministratoreDAO amministratoreDAO = AmministratoreDAO.getInstance();
        ArrayList<Amministratore> amministratori = amministratoreDAO.findAll();
        Assert.assertEquals(3, amministratori.size());
    }
/*
    @Test
    public void findByIdTest() {
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        Utente utente = utenteDAO.findById("vr46");
        Assert.assertEquals("Valentino", utente.getName());
    }

    @Test
    public void removeByIdTest() {
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        int rowCount = utenteDAO.removeById("vr46");
        Assert.assertEquals(1, rowCount);
    }

    @Test
    public void updateTest() {
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        Utente utente = new Utente("Valentino", "Rossi", "vr46", "123", "valentino@vr46.com", "MN");
        utenteDAO.update(utente);
        utente = utenteDAO.findById("vr46");
        Assert.assertEquals("valentino@vr46.com", utente.getEmail());
    }

    @Test
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

 */
}