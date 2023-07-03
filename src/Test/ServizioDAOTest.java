package Test;

import DAO.*;
import Model.CategoriaServizio;
import Model.Fornitore;
import Model.Servizio;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class ServizioDAOTest {
    @Before
    public void setUp() {
        IServizioDAO servizioDAO = ServizioDAO.getInstance();
        ICategoriaServizioDAO categoriaServizioDAO = CategoriaServizioDAO.getInstance();
        IFornitoreDAO fornitoreDAO  = FornitoreDAO.getInstance();

        Fornitore fornitore = new Fornitore("test","test@gmail.com","trento","italy","099533test","corriere espresso");
        fornitoreDAO.add(fornitore);
        fornitore = fornitoreDAO.findByName("test"); //recupero l'id

        CategoriaServizio categoriaServizio = new CategoriaServizio("categoriaTest");
        categoriaServizioDAO.add(categoriaServizio);
        categoriaServizio = categoriaServizioDAO.findByName("categoriaTest"); //recupero l'id

        servizioDAO.add(new Servizio(55.35F, null, "TrasportoTest", "Servizio sicuro e affidabile", categoriaServizio, fornitore,null));
    }

    @After
    public void tearDown() {
        IServizioDAO servizioDAO = ServizioDAO.getInstance();
        ICategoriaServizioDAO categoriaServizioDAO = CategoriaServizioDAO.getInstance();
        IFornitoreDAO fornitoreDAO = FornitoreDAO.getInstance();

        Servizio servizio = servizioDAO.findByName("TrasportoTest");
        servizioDAO.removeById(servizio.getIdArticolo());
        fornitoreDAO.removeById("test");
        categoriaServizioDAO.removeById("categoriaTest");
    }
    @Test
    public void findByIdTest() {
        IServizioDAO servizioDAO = ServizioDAO.getInstance();
        Servizio servizio = servizioDAO.findById(servizioDAO.findByName("TrasportoTest").getIdArticolo());
        Assert.assertEquals("TrasportoTest", servizio.getName());
    }
    @Test
    public void findByNameTest() {
        IServizioDAO servizioDAO = ServizioDAO.getInstance();
        Servizio servizio = servizioDAO.findByName("TrasportoTest");
        Assert.assertEquals("TrasportoTest", servizio.getName());
    }

    @Test
    public void findAllTest() {
        IServizioDAO servizioDAO = ServizioDAO.getInstance();
        ArrayList<Servizio> servizi = servizioDAO.findAll();
        Assert.assertFalse(servizi.isEmpty());
    }
    @Test
    public void findAllByCategoriaTest() {
        IServizioDAO servizioDAO = ServizioDAO.getInstance();
        ICategoriaServizioDAO categoriaServizioDAO = CategoriaServizioDAO.getInstance();

        CategoriaServizio categoriaServizio = categoriaServizioDAO.findByName("categoriaTest");
        ArrayList<Servizio> servizi = servizioDAO.findAllByCategoria(categoriaServizio.getIdCategoria());
        Assert.assertEquals(1, servizi.size());
    }


    @Test
    public void updateTest() {
        IServizioDAO servizioDAO = ServizioDAO.getInstance();

        Servizio servizio = servizioDAO.findByName("TrasportoTest");
        servizio.setDescrizione("servizio di trasporto");
        servizioDAO.update(servizio);

        servizio = servizioDAO.findByName("TrasportoTest");
        Assert.assertEquals("servizio di trasporto", servizio.getDescrizione());
    }
}

