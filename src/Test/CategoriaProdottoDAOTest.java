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
    public void setUp() {
        ICategoriaProdottoDAO categoriaProdottoDAO = CategoriaProdottoDAO.getInstance();

        CategoriaProdotto cpParent = new CategoriaProdotto("ArredamentoTest");
        categoriaProdottoDAO.add(cpParent);
        cpParent = categoriaProdottoDAO.findByName("ArredamentoTest"); //recupero l'id

        CategoriaProdotto cp = new CategoriaProdotto("SoggiornoTest",cpParent.getIdCategoria());
        categoriaProdottoDAO.add(cp);
    }

    @After
    public void tearDown() throws Exception {
        ICategoriaProdottoDAO categoriaProdottoDAO = CategoriaProdottoDAO.getInstance();
        categoriaProdottoDAO.removeById("SoggiornoTest");
        categoriaProdottoDAO.removeById("ArredamentoTest");

    }

    @Test
    public void findAllTest() {
        ICategoriaProdottoDAO categoriaProdottoDAO = CategoriaProdottoDAO.getInstance();
        ArrayList<CategoriaProdotto> categorie = categoriaProdottoDAO.findAll();
        Assert.assertFalse(categorie.isEmpty());
    }

    @Test
    public void findByIdTest() {
        ICategoriaProdottoDAO categoriaProdottoDAO = CategoriaProdottoDAO.getInstance();
        CategoriaProdotto categoriaProdotto = categoriaProdottoDAO.findById(categoriaProdottoDAO.findByName("ArredamentoTest").getIdCategoria());
        Assert.assertEquals("ArredamentoTest", categoriaProdotto.getNome());
    }

    @Test
    public void findByNameTest() {
        ICategoriaProdottoDAO categoriaProdottoDAO = CategoriaProdottoDAO.getInstance();
        CategoriaProdotto categoriaProdotto = categoriaProdottoDAO.findByName("ArredamentoTest");
        Assert.assertEquals("ArredamentoTest", categoriaProdotto.getNome());
    }

    @Test
    public void findAllByParentTest() {
        ICategoriaProdottoDAO categoriaProdottoDAO = CategoriaProdottoDAO.getInstance();
        CategoriaProdotto categoriaProdottoParent = categoriaProdottoDAO.findByName("ArredamentoTest");
        ArrayList<CategoriaProdotto> categorie = categoriaProdottoDAO.findAllByParent(categoriaProdottoParent.getIdCategoria());
        CategoriaProdotto categoriaProdotto = categorie.get(categorie.size() - 1);
        Assert.assertEquals("SoggiornoTest", categoriaProdotto.getNome());
    }



    @Test
    public void updateTest() {
        ICategoriaProdottoDAO categoriaProdottoDAO = CategoriaProdottoDAO.getInstance();
        CategoriaProdotto categoriaProdotto = categoriaProdottoDAO.findByName("ArredamentoTest");
        categoriaProdotto.setNome("MobiliaTest");
        categoriaProdottoDAO.update(categoriaProdotto);
        categoriaProdotto = categoriaProdottoDAO.findById(categoriaProdotto.getIdCategoria());
        Assert.assertEquals("MobiliaTest", categoriaProdotto.getNome());
        categoriaProdotto.setNome("ArredamentoTest");
        categoriaProdottoDAO.update(categoriaProdotto);
    }
}