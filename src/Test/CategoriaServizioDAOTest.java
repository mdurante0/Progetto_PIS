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
    public void setUp() {
        ICategoriaServizioDAO categoriaServizioDAO = CategoriaServizioDAO.getInstance();
        categoriaServizioDAO.add(new CategoriaServizio("TrasportiTest"));
    }

    @After
    public void tearDown() {
        ICategoriaServizioDAO categoriaServizioDAO = CategoriaServizioDAO.getInstance();
        categoriaServizioDAO.removeById("TrasportiTest");

    }

    @Test
    public void findAllTest() {
        ICategoriaServizioDAO categoriaServizioDAO = CategoriaServizioDAO.getInstance();
        ArrayList<CategoriaServizio> categorie = categoriaServizioDAO.findAll();
        Assert.assertFalse(categorie.isEmpty());
    }

    @Test
    public void findByIdTest() {
        ICategoriaServizioDAO categoriaServizioDAO = CategoriaServizioDAO.getInstance();
        CategoriaServizio categoriaServizio = categoriaServizioDAO.findById(categoriaServizioDAO.findByName("TrasportiTest").getIdCategoria());
        Assert.assertEquals("TrasportiTest", categoriaServizio.getNome());
    }

    @Test
    public void findByNameTest() {
        ICategoriaServizioDAO categoriaServizioDAO = CategoriaServizioDAO.getInstance();
        CategoriaServizio categoriaServizio = categoriaServizioDAO.findByName("TrasportiTest");
        Assert.assertEquals("TrasportiTest", categoriaServizio.getNome());
    }


    @Test
    public void updateTest() {
        ICategoriaServizioDAO categoriaServizioDAO = CategoriaServizioDAO.getInstance();
        CategoriaServizio categoriaServizio = categoriaServizioDAO.findByName("TrasportiTest");
        categoriaServizio.setNome("MontaggiTest");
        categoriaServizioDAO.update(categoriaServizio);
        categoriaServizio = categoriaServizioDAO.findByName("MontaggiTest");
        Assert.assertEquals("MontaggiTest", categoriaServizio.getNome());
        categoriaServizio.setNome("TrasportiTest");
        categoriaServizioDAO.update(categoriaServizio);
    }
}