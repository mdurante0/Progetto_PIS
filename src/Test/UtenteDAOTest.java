package Test;

import DAO.IUtenteDAO;
import DAO.UtenteDAO;
import Model.Utente;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class UtenteDAOTest {
    @Before
    public void setUp() {
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        utenteDAO.add(new Utente("Valentino", "Rossi", "test", "123", "valentino@gmail.com", "MN"));
    }

    @After
    public void tearDown() {
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        utenteDAO.removeById("test");
    }

    @Test
    public void findAllTest() {
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        ArrayList<Utente> utenti = utenteDAO.findAll();
        Assert.assertFalse(utenti.isEmpty());
    }

    @Test
    public void findByIdTest() {
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        Utente utente = utenteDAO.findById("test");
        Assert.assertEquals("Valentino", utente.getName());
    }

    @Test
    public void updateTest() {
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        Utente utente = utenteDAO.findById("test");
        utente.setEmail("test@vr46.com");
        utenteDAO.update(utente);
        utente = utenteDAO.findById("test");
        Assert.assertEquals("test@vr46.com", utente.getEmail());
    }
    @Test
    public void userExistsTest(){
    IUtenteDAO utenteDAO = UtenteDAO.getInstance();
    boolean exist = utenteDAO.userExists("test");
    Assert.assertEquals(true, exist);
    exist = utenteDAO.userExists("test2");
    Assert.assertEquals(false, exist);
    }
    @Test
    public void checkCredentialsTest(){
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        boolean check = utenteDAO.checkCredentials("test", "123");
        Assert.assertEquals(true, check);
        check = utenteDAO.checkCredentials("test", "125");
        Assert.assertEquals(false, check);
    }
    @Test
    public void isClienteTest(){
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        boolean check = utenteDAO.isCliente("test");
        Assert.assertEquals(false, check);
    }
    @Test
    public void isManagerTest(){
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        boolean check = utenteDAO.isManager("test");
        Assert.assertEquals(true, check);
    }
    @Test
    public void isAmministratoreTest(){
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        boolean check = utenteDAO.isAmministratore("test");
        Assert.assertEquals(false, check);
    }
}