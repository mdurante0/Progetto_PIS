package Test;

import DAO.*;
import Model.composite.IProdotto;
import Model.composite.Prodotto;
import Model.composite.ProdottoComposito;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class ProdottoCompositoDAOTest {
    @Before
    public void setUp() {
        Prodotto p1 = new Prodotto("PoltronaTest", "poltrona massaggiante",30.99F ,2);
        Prodotto p2 = new Prodotto("SofaTest", "divano reclinabile",55.30F, 1);

        IProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
        prodottoDAO.add(p1);
        prodottoDAO.add(p2);

        ProdottoComposito prodottoComposito = new ProdottoComposito("Poltrone e Sofa Test", "due poltrone massaggianti e un divano reclinabile");
        prodottoComposito.add(p1);
        prodottoComposito.add(p2);

        IProdottoCompositoDAO prodottoCompositoDAO = ProdottoCompositoDAO.getInstance();
        prodottoCompositoDAO.add(prodottoComposito);
    }

    @After
    public void tearDown()  {
        IProdottoCompositoDAO prodottoCompositoDAO = ProdottoCompositoDAO.getInstance();
        IProdottoDAO prodottoDAO = ProdottoDAO.getInstance();

        prodottoCompositoDAO.removeById(prodottoCompositoDAO.findByName("Poltrone e Sofa test").getIdArticolo());
        prodottoDAO.removeById(prodottoDAO.findByName("SofaTest").getIdArticolo());
        prodottoDAO.removeById(prodottoDAO.findByName("PoltronaTest").getIdArticolo());
    }

    @Test
    public void findAllTest() {
        IProdottoCompositoDAO prodottoCompositoDAO = ProdottoCompositoDAO.getInstance();
        ArrayList<ProdottoComposito> prodottiCompositi = prodottoCompositoDAO.findAll();
        Assert.assertFalse(prodottiCompositi.isEmpty());
    }

    @Test
    public void findByIdTest() {
        IProdottoCompositoDAO prodottoCompositoDAO = ProdottoCompositoDAO.getInstance();
        ProdottoComposito prodottoComposito = prodottoCompositoDAO.findById(prodottoCompositoDAO.findByName("Poltrone e Sofa Test").getIdArticolo());
        Assert.assertEquals("Poltrone e Sofa Test", prodottoComposito.getName());
    }
    @Test
    public void findByNameTest() {
        IProdottoCompositoDAO prodottoCompositoDAO = ProdottoCompositoDAO.getInstance();
        ProdottoComposito prodottoComposito = prodottoCompositoDAO.findByName("Poltrone e Sofa Test");
        Assert.assertEquals("Poltrone e Sofa Test",prodottoComposito.getName());
    }

    @Test
    public void addSottoProdottoTest(){
        IProdottoCompositoDAO prodottoCompositoDAO = ProdottoCompositoDAO.getInstance();
        IProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
        Prodotto p = new Prodotto("TavolinoTest", "tavolino per salotto",30F, 1);
        prodottoDAO.add(p);

        ProdottoComposito pc = prodottoCompositoDAO.findByName("Poltrone e Sofa Test");
        ProdottoCompositoDAO.getInstance().addSottoprodotto(pc.getIdArticolo(), p);

        pc = ProdottoCompositoDAO.getInstance().findByName("Poltrone e Sofa Test");
        Assert.assertEquals("TavolinoTest", pc.getSottoprodotti().get(2).getName());

        prodottoDAO.removeById(p.getIdArticolo());
    }

    @Test
    public void removeSottoProdottoTest(){
        IProdottoCompositoDAO prodottoCompositoDAO = ProdottoCompositoDAO.getInstance();
        IProdottoDAO prodottoDAO = ProdottoDAO.getInstance();

        IProdotto p = prodottoDAO.findByName("SofaTest");

        ProdottoComposito pc = prodottoCompositoDAO.findByName("Poltrone e Sofa Test");
        prodottoCompositoDAO.removeSottoprodotto(pc.getIdArticolo(), p);

        pc = prodottoCompositoDAO.findByName("Poltrone e Sofa Test");
        Assert.assertEquals(1, pc.getSottoprodotti().size());

        prodottoCompositoDAO.addSottoprodotto(pc.getIdArticolo(), p);
    }

    @Test
    public void removeAllSottoProdottiTest(){
        IProdottoCompositoDAO prodottoCompositoDAO = ProdottoCompositoDAO.getInstance();
        IProdottoDAO prodottoDAO = ProdottoDAO.getInstance();

        ProdottoComposito pc = prodottoCompositoDAO.findByName("Poltrone e Sofa Test");
        prodottoCompositoDAO.removeAllSottoprodotti(pc.getIdArticolo());

        pc = prodottoCompositoDAO.findByName("Poltrone e Sofa Test");
        Assert.assertTrue(pc.getSottoprodotti().isEmpty());

        IProdotto p = prodottoDAO.findByName("PoltronaTest");
        prodottoCompositoDAO.addSottoprodotto(pc.getIdArticolo(), p);

        p = prodottoDAO.findByName("SofaTest");
        prodottoCompositoDAO.addSottoprodotto(pc.getIdArticolo(), p);
    }

    @Test
    public void updateTest() {
        IProdottoCompositoDAO prodottoCompositoDAO = ProdottoCompositoDAO.getInstance();
        ProdottoComposito prodottoComposito = prodottoCompositoDAO.findByName("Poltrone e Sofa Test");

        prodottoComposito.getSottoprodotti().get(0).setQuantita(3); //metto 3 poltrone invece di 2
        prodottoCompositoDAO.update(prodottoComposito);

        prodottoComposito = prodottoCompositoDAO.findByName("Poltrone e Sofa Test");
        Assert.assertEquals(3, prodottoComposito.getSottoprodotti().get(0).getQuantita());
    }

}