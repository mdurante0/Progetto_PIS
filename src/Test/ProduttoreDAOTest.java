package Test;

import DAO.IProduttoreDAO;
import DAO.ProduttoreDAO;
import Model.Produttore;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class ProduttoreDAOTest {
    @Before
    public void setUp() throws Exception {
        IProduttoreDAO produttoreDAO = ProduttoreDAO.getInstance();
        produttoreDAO.add(new Produttore("test","produttore@gmail.com","trento","italy","099533test","produciamo prodotti"));
    }

    @After
    public void tearDown() throws Exception {
        IProduttoreDAO produttoreDAO = ProduttoreDAO.getInstance();
        produttoreDAO.removeById("test");
    }

    @Test
    public void findAllTest() {
        IProduttoreDAO produttoreDAO = ProduttoreDAO.getInstance();
        ArrayList<Produttore> produttori = produttoreDAO.findAll();
        Assert.assertFalse(produttori.isEmpty());
    }

    @Test
    public void findByIdTest() {
        IProduttoreDAO produttoreDAO = ProduttoreDAO.getInstance();
        Produttore produttore = produttoreDAO.findById(produttoreDAO.findByName("test").getIdProduttore());
        Assert.assertEquals("test", produttore.getNome());
    }

    @Test
    public void findByNameTest() {
        IProduttoreDAO produttoreDAO = ProduttoreDAO.getInstance();
        Produttore produttore = produttoreDAO.findByName("test");
        Assert.assertEquals("test", produttore.getNome());
    }

    @Test
    public void updateTest() {
        IProduttoreDAO produttoreDAO = ProduttoreDAO.getInstance();
        Produttore produttore = produttoreDAO.findByName("test");
        produttore.setDescrizione("prodotti in produzione");
        produttoreDAO.update(produttore);
        produttore = produttoreDAO.findByName("test");
        Assert.assertEquals("prodotti in produzione", produttore.getDescrizione());
    }
}