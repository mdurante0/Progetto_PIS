package Test;

import DAO.CategoriaServizioDAO;
import DAO.ICategoriaServizioDAO;
import Model.CategoriaServizio;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class CategoriaServizioDAOTest {
    @Before
    public void setUp() throws Exception {
        ICategoriaServizioDAO categoriaServizioDAO = CategoriaServizioDAO.getInstance();
        categoriaServizioDAO.add(new CategoriaServizio("aaa"));
    }

    @After
    public void tearDown() throws Exception {
        ICategoriaServizioDAO categoriaServizioDAO = CategoriaServizioDAO.getInstance();
        categoriaServizioDAO.removeById("aaa");

    }

    @Test
    public void findAllTest() {
        ICategoriaServizioDAO categoriaServizioDAO = CategoriaServizioDAO.getInstance();
        ArrayList<CategoriaServizio> categorie = categoriaServizioDAO.findAll();
        Assert.assertEquals(1, categorie.size());
    }

    @Test
    public void findByIdTest() {
        ICategoriaServizioDAO categoriaServizioDAO = CategoriaServizioDAO.getInstance();
        CategoriaServizio categoriaServizio = categoriaServizioDAO.findById(categoriaServizioDAO.findByName("aaa").getIdCategoria());
        Assert.assertEquals("aaa", categoriaServizio.getNome());
    }

    @Test
    public void findByNameTest() {
        ICategoriaServizioDAO categoriaServizioDAO = CategoriaServizioDAO.getInstance();
        CategoriaServizio categoriaServizio = categoriaServizioDAO.findByName("aaa");
        Assert.assertEquals("aaa", categoriaServizio.getNome());
    }


    @Test
    public void updateTest() {
        ICategoriaServizioDAO categoriaServizioDAO = CategoriaServizioDAO.getInstance();
        CategoriaServizio categoriaServizio = categoriaServizioDAO.findByName("aaa");
        categoriaServizio.setIdCategoria(categoriaServizioDAO.findByName("aaa").getIdCategoria());
        categoriaServizioDAO.update(categoriaServizio);
        categoriaServizio = categoriaServizioDAO.findByName("aaa");
        Assert.assertEquals("aaa", categoriaServizio.getNome());
    }
}