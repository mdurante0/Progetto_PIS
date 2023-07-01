package Test;

import DAO.CategoriaProdottoDAO;
import DAO.ICategoriaProdottoDAO;
import DAO.IProdottoDAO;
import DAO.ProdottoDAO;
import Model.CategoriaProdotto;
import Model.composite.IProdotto;
import Model.composite.Prodotto;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class ProdottoDAOTest {
    @Before
    public void setUp() {
        IProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
        ICategoriaProdottoDAO categoriaProdottoDAO = CategoriaProdottoDAO.getInstance();

        CategoriaProdotto categoriaProdotto = new CategoriaProdotto("categoriaTest");
        categoriaProdottoDAO.add(categoriaProdotto);
        categoriaProdotto = categoriaProdottoDAO.findByName("categoriaTest"); //recupero l'id

        Prodotto prodotto = new Prodotto("cassaTest", "cassa di legno", 10F, 3);
        prodotto.setCategoria(categoriaProdotto);
        prodottoDAO.add(prodotto);
    }
    @After
    public void tearDown() {
        IProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
        ICategoriaProdottoDAO categoriaProdottoDAO = CategoriaProdottoDAO.getInstance();

        IProdotto prodotto = prodottoDAO.findByName("cassaTest");
        prodottoDAO.removeById(prodotto.getIdArticolo());
        categoriaProdottoDAO.removeById("categoriaTest");
    }
    @Test
    public void findByIdTest() {
        IProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
        IProdotto prodotto = prodottoDAO.findById(prodottoDAO.findByName("cassaTest").getIdArticolo());
        Assert.assertEquals("cassa di legno", prodotto.getDescrizione());
    }
    @Test
    public void findByNameTest() {
        IProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
        IProdotto prodotto = prodottoDAO.findByName("cassaTest");
        Assert.assertEquals("cassaTest", prodotto.getName());
    }
    @Test
    public void findAllTest() {
        IProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
        ArrayList<IProdotto> prodotti = prodottoDAO.findAll();
        Assert.assertFalse(prodotti.isEmpty());
    }
    @Test
    public void findAllByCategoriaTest() {
        IProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
        ICategoriaProdottoDAO categoriaProdottoDAO = CategoriaProdottoDAO.getInstance();

        CategoriaProdotto categoriaProdotto = categoriaProdottoDAO.findByName("categoriaTest");
        ArrayList<IProdotto> prodotti = prodottoDAO.findAllByCategoria(categoriaProdotto.getIdCategoria());
        Assert.assertEquals(1, prodotti.size());
    }
    @Test
    public void updateTest() {
        IProdottoDAO prodottoDAO = ProdottoDAO.getInstance();

        IProdotto prodotto = prodottoDAO.findByName("cassaTest");
        prodotto.setDescrizione("cassa in legno massello");
        prodottoDAO.update(prodotto);

        prodotto = prodottoDAO.findByName("cassaTest");
        Assert.assertEquals("cassa in legno massello", prodotto.getDescrizione());
    }
}

