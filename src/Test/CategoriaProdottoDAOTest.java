package Test;

import DAO.CategoriaProdottoDAO;
import DAO.ICategoriaProdottoDAO;
import Model.CategoriaProdotto;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class CategoriaProdottoDAOTest {
    @Before
    public void setUp() throws Exception {
        ICategoriaProdottoDAO categoriaProdottoDAO = CategoriaProdottoDAO.getInstance();

        CategoriaProdotto cpParent = new CategoriaProdotto("Arredamento");
        categoriaProdottoDAO.add(cpParent);
        cpParent = categoriaProdottoDAO.findByName("Arredamento");

        CategoriaProdotto cp = new CategoriaProdotto("Soggiorno",cpParent.getIdCategoria());
        categoriaProdottoDAO.add(cp);
    }

    @After
    public void tearDown() throws Exception {
        ICategoriaProdottoDAO categoriaProdottoDAO = CategoriaProdottoDAO.getInstance();
        categoriaProdottoDAO.removeById("Soggiorno");
        categoriaProdottoDAO.removeById("Arredamento");

    }

    @Test
    public void findAllTest() {
        ICategoriaProdottoDAO categoriaProdottoDAO = CategoriaProdottoDAO.getInstance();
        ArrayList<CategoriaProdotto> categorie = categoriaProdottoDAO.findAll();
        Assert.assertEquals(2, categorie.size());
    }

    @Test
    public void findByIdTest() {
        ICategoriaProdottoDAO categoriaProdottoDAO = CategoriaProdottoDAO.getInstance();
        CategoriaProdotto categoriaProdotto = categoriaProdottoDAO.findById(categoriaProdottoDAO.findByName("Arredamento").getIdCategoria());
        Assert.assertEquals("Arredamento", categoriaProdotto.getNome());
    }

    @Test
    public void findByNameTest() {
        ICategoriaProdottoDAO categoriaProdottoDAO = CategoriaProdottoDAO.getInstance();
        CategoriaProdotto categoriaProdotto = categoriaProdottoDAO.findByName("Arredamento");
        Assert.assertEquals("Arredamento", categoriaProdotto.getNome());
    }

    @Test
    public void findAllByParentTest() {
        ICategoriaProdottoDAO categoriaProdottoDAO = CategoriaProdottoDAO.getInstance();
        ArrayList<CategoriaProdotto> categorie = categoriaProdottoDAO.findAllByParent(categoriaProdottoDAO.findByName("Arredamento").getIdCategoria());
        Assert.assertEquals(1, categorie.size());
    }



    @Test
    public void updateTest() {
        ICategoriaProdottoDAO categoriaProdottoDAO = CategoriaProdottoDAO.getInstance();
        CategoriaProdotto categoriaProdotto = categoriaProdottoDAO.findByName("Arredamento");
        categoriaProdotto.setNome("Mobilia");
        categoriaProdottoDAO.update(categoriaProdotto);
        categoriaProdotto = categoriaProdottoDAO.findById(categoriaProdotto.getIdCategoria());
        Assert.assertEquals("Mobilia", categoriaProdotto.getNome());
        categoriaProdotto.setNome("Arredamento");
        categoriaProdottoDAO.update(categoriaProdotto);
    }
}