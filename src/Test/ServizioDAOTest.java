package Test;

import DAO.*;
import Model.CategoriaServizio;
import Model.Collocazione;
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

        fornitoreDAO.add(new Fornitore("Valentino","vr46@gmail.com","trento","italy","0995331239","boh"));

        categoriaServizioDAO.add(new CategoriaServizio("aaa"));

        servizioDAO.add(new Servizio(55.35F, null, "Trasporto", "Servizio sicuro e affidabile", categoriaServizioDAO.findByName("aaa"), fornitoreDAO.findByName("Valentino"),null, 8));
    }

    @After
    public void tearDown() {
        IArticoloDAO articoloDAO = ArticoloDAO.getInstance();
        IServizioDAO servizioDAO = ServizioDAO.getInstance();
        ICategoriaServizioDAO categoriaServizioDAO = CategoriaServizioDAO.getInstance();

        IFornitoreDAO fornitoreDAO = FornitoreDAO.getInstance();


        articoloDAO.removeById(servizioDAO.findByName("Trasporto").getIdArticolo());
        fornitoreDAO.removeById("Valentino");
        categoriaServizioDAO.removeById("aaa");
    }
    @Test
    public void testFindById() {
        IServizioDAO servizioDAO = ServizioDAO.getInstance();
        Servizio servizio = servizioDAO.findById(servizioDAO.findByName("Trasporto").getIdArticolo());
        Assert.assertEquals("Trasporto", servizio.getName());
    }
    @Test
    public void testFindByName() {
        IServizioDAO servizioDAO = ServizioDAO.getInstance();
        Servizio servizio = servizioDAO.findByName("Trasporto");
        Assert.assertEquals("Trasporto", servizio.getName());
    }

    @Test
    public void testFindAllTest() {
        IServizioDAO servizioDAO = ServizioDAO.getInstance();
        ArrayList<Servizio> servizi = servizioDAO.findAll();
        Assert.assertEquals(1, servizi.size());
    }
    @Test
    public void testFindAllByCategoria() {
        IServizioDAO servizioDAO = ServizioDAO.getInstance();
        ICategoriaServizioDAO categoriaServizioDAO = CategoriaServizioDAO.getInstance();
        ArrayList<Servizio> servizi = servizioDAO.findAllByCategoria(categoriaServizioDAO.findByName("aaa").getIdCategoria());
        Assert.assertEquals(1, servizi.size());
    }


    @Test
    public void testUpdate() {
        IServizioDAO servizioDAO = ServizioDAO.getInstance();
        IFornitoreDAO fornitoreDAO = FornitoreDAO.getInstance();
        ICategoriaServizioDAO categoriaServizioDAO = CategoriaServizioDAO.getInstance();

        Fornitore fornitore = new Fornitore("Gigi","gigi@gmail.com","trento","italy","0995331429","boh");
        CategoriaServizio categoriaServizio = new CategoriaServizio("bbb");

        fornitoreDAO.add(fornitore);
        categoriaServizioDAO.add(categoriaServizio);

        Servizio servizio = servizioDAO.findByName("Trasporto");

        servizio.setFornitore(fornitore);
        servizio.setCategoria(categoriaServizio);
        servizioDAO.update(servizio);

        Servizio servizio1 = servizioDAO.findByName("Trasporto");

        Assert.assertEquals("Trasporto", servizio1.getName());

fornitoreDAO.removeById("Gigi");
categoriaServizioDAO.removeById("bbb");


    }


}

