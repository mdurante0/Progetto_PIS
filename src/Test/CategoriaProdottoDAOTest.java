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
        categoriaProdottoDAO.add(new CategoriaProdotto("aaa"));
    }

    @After
    public void tearDown() throws Exception {
        ICategoriaProdottoDAO categoriaProdottoDAO = CategoriaProdottoDAO.getInstance();
        categoriaProdottoDAO.removeById("aaa");

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
        CategoriaProdotto categoriaProdotto = categoriaProdottoDAO.findById(categoriaProdottoDAO.findByName("aaa").getIdCategoria());
        Assert.assertEquals("aaa", categoriaProdotto.getNome());
    }

    @Test
    public void findByNameTest() {
        ICategoriaProdottoDAO categoriaProdottoDAO = CategoriaProdottoDAO.getInstance();
        CategoriaProdotto categoriaProdotto = categoriaProdottoDAO.findByName("aaa");
        Assert.assertEquals("aaa", categoriaProdotto.getNome());
    }

    @Test
    public void findAllByParentTest() {
        ICategoriaProdottoDAO categoriaProdottoDAO = CategoriaProdottoDAO.getInstance();
        CategoriaProdotto categoriaProdotto = categoriaProdottoDAO.findByName("aaa");
        categoriaProdotto.setIdCategoriaProdottoParent(categoriaProdottoDAO.findByName("arredamento").getIdCategoria());
        categoriaProdottoDAO.update(categoriaProdotto);
        ArrayList<CategoriaProdotto> categorie = categoriaProdottoDAO.findAllByParent(categoriaProdottoDAO.findByName("arredamento").getIdCategoria());
        Assert.assertEquals(2, categorie.size());
    }



    @Test
    public void updateTest() {
        ICategoriaProdottoDAO categoriaProdottoDAO = CategoriaProdottoDAO.getInstance();
        CategoriaProdotto categoriaProdotto = categoriaProdottoDAO.findByName("aaa");
        categoriaProdotto.setIdCategoriaProdottoParent(categoriaProdottoDAO.findByName("arredamento").getIdCategoria());
        categoriaProdottoDAO.update(categoriaProdotto);
        categoriaProdotto = categoriaProdottoDAO.findByName("aaa");
        Assert.assertEquals("aaa", categoriaProdotto.getNome());
    }
}