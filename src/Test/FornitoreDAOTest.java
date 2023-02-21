package Test;

import Business.LoginResult;
import Business.SessionManager;
import Business.UtenteBusiness;
import DAO.FornitoreDAO;
import DAO.IFornitoreDAO;
import DAO.ProduttoreDAO;
import Model.Cliente;
import Model.Fornitore;
import Model.Produttore;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class FornitoreDAOTest {
    @Before
    public void setUp() throws Exception {
        IFornitoreDAO fornitoreDAO = FornitoreDAO.getInstance();
        fornitoreDAO.add(new Fornitore("Valentino","vr46@gmail.com","trento","italy","0995331239","boh"));
    }

    @After
    public void tearDown() throws Exception {
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
        Fornitore fornitore = fornitoreDAO.findById("Valentino");
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
        fornitore.setIdFornitore(fornitoreDAO.findById(fornitore.getNome()).getIdFornitore());
        fornitoreDAO.update(fornitore);
        fornitore = fornitoreDAO.findById("Valentino");
        Assert.assertEquals("vr46@gmail.it", fornitore.getMail());
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