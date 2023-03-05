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
        produttoreDAO.add(new Produttore("Valentino","vr46@gmail.com","trento","italy","0995331239","boh"));
    }

    @After
    public void tearDown() throws Exception {
        IProduttoreDAO produttoreDAO = ProduttoreDAO.getInstance();
        produttoreDAO.removeById("Valentino");
    }

    @Test
    public void findAllTest() {
        IProduttoreDAO produttoreDAO = ProduttoreDAO.getInstance();
        ArrayList<Produttore> produttori = produttoreDAO.findAll();
        Assert.assertEquals(1, produttori.size());
    }

    @Test
    public void findByIdTest() {
        IProduttoreDAO managerDAO = ProduttoreDAO.getInstance();
        Produttore produttore = managerDAO.findById("Valentino");
        Assert.assertEquals("Valentino", produttore.getNome());
    }


    @Test
    public void removeByIdTest() {
        IProduttoreDAO managerDAO = ProduttoreDAO.getInstance();
        int rowCount = managerDAO.removeById("Valentino");
        Assert.assertEquals(1, rowCount);
    }

    @Test
    public void updateTest() {
        IProduttoreDAO produttoreDAO = ProduttoreDAO.getInstance();
        Produttore produttore = new Produttore("Valentino","vr46@gmail.it","trento","italy","0995331239","boh");
        produttore.setIdProduttore(produttoreDAO.findById(produttore.getNome()).getIdProduttore());
        produttoreDAO.update(produttore);
        produttore = produttoreDAO.findById("Valentino");
        Assert.assertEquals("vr46@gmail.it", produttore.getMail());
    }
}