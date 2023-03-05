package Test;

import Business.Results.LoginResult;
import Business.SessionManager;
import Business.UtenteBusiness;
import DAO.IUtenteDAO;
import DAO.UtenteDAO;
import Model.Cliente;
import Model.Utente;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class UtenteDAOTest {
    @Before
    public void setUp() throws Exception {
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        utenteDAO.add(new Utente("Valentino", "Rossi", "vr46", "123", "valentino@gmail.com", "MN"));
    }

    @After
    public void tearDown() throws Exception {
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        utenteDAO.removeById("vr46");
    }

    @Test
    public void findAllTest() {
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        ArrayList<Utente> utenti = utenteDAO.findAll();
        Assert.assertEquals(1, utenti.size());
    }

    @Test
    public void findByIdTest() {
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        Utente utente = utenteDAO.findById("vr46");
        Assert.assertEquals("Valentino", utente.getName());
    }

    @Test
    public void updateTest() {
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        Utente utente = new Utente("Valentino", "Rossi", "vr46", "123", "valentino@vr46.com", "MN");
        utente.setIdUtente(utenteDAO.findById(utente.getUsername()).getIdUtente());
        utenteDAO.update(utente);
        utente = utenteDAO.findById("vr46");
        Assert.assertEquals("valentino@vr46.com", utente.getEmail());
    }

    @Test
    public void removeByIdTest() {
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        int rowCount = utenteDAO.removeById("vr46");
        Assert.assertEquals(1, rowCount);
    }
    @Test
    public void userExistsTest(){
    IUtenteDAO utenteDAO = UtenteDAO.getInstance();
    boolean exist = utenteDAO.userExists("vr46");
    Assert.assertEquals(true, exist);
    exist = utenteDAO.userExists("robby");
    Assert.assertEquals(false, exist);
    }
    @Test
    public void checkCredentialsTest(){
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        boolean check = utenteDAO.checkCredentials("vr46", "123");
        Assert.assertEquals(true, check);
        check = utenteDAO.checkCredentials("vr46", "125");
        Assert.assertEquals(false, check);
    }
    @Test
    public void isClienteTest(){
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        boolean check = utenteDAO.isCliente("vr46");
        Assert.assertEquals(false, check);
    }
    @Test
    public void isManagerTest(){
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        boolean check = utenteDAO.isManager("vr46");
        Assert.assertEquals(true, check);
    }
    @Test
    public void isAmministratoreTest(){
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        boolean check = utenteDAO.isAmministratore("vr46");
        Assert.assertEquals(false, check);
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