package Test;

import DAO.*;
import Model.CategoriaProdotto;
import Model.Produttore;
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
        Produttore produttore = new Produttore("PoltroneSofa","poltronesofa@gmail.com","trento","italy","0987654321","artigiani della qualita");
        IProduttoreDAO produttoreDAO = ProduttoreDAO.getInstance();
        produttoreDAO.add(produttore);
        produttore = produttoreDAO.findByName("PoltroneSofa");

        CategoriaProdotto categoriaProdotto = new CategoriaProdotto("Soggiorno");
        ICategoriaProdottoDAO categoriaProdottoDAO = CategoriaProdottoDAO.getInstance();
        categoriaProdottoDAO.add(categoriaProdotto);
        categoriaProdotto = categoriaProdottoDAO.findByName("Soggiorno");

        Prodotto p1 = new Prodotto("Poltrona", "poltrona massaggiante",30.99F,produttore,categoriaProdotto,2);
        Prodotto p2 = new Prodotto("Sofa", "divano reclinabile",55.30F, produttore, categoriaProdotto,1);

        IProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
        prodottoDAO.add(p1);
        prodottoDAO.add(p2);

        ProdottoComposito prodottoComposito = new ProdottoComposito("Poltrone e Sofa", "due poltrone massaggianti e un divano reclinabile", produttore, categoriaProdotto);
        prodottoComposito.add(p1);
        prodottoComposito.add(p2);

        IProdottoCompositoDAO prodottoCompositoDAO = ProdottoCompositoDAO.getInstance();
        prodottoCompositoDAO.add(prodottoComposito);
    }

    @After
    public void tearDown()  {
        IArticoloDAO articoloDAO = ArticoloDAO.getInstance();
        ICategoriaProdottoDAO categoriaProdottoDAO = CategoriaProdottoDAO.getInstance();
        IProduttoreDAO produttoreDAO = ProduttoreDAO.getInstance();

        articoloDAO.removeById(articoloDAO.findByName("Poltrona").getIdArticolo());
        articoloDAO.removeById(articoloDAO.findByName("Sofa").getIdArticolo());
        articoloDAO.removeById(articoloDAO.findByName("Poltrone e Sofa").getIdArticolo());

        produttoreDAO.removeById("PoltroneSofa");
        categoriaProdottoDAO.removeById("Soggiorno");
    }

    @Test
    public void findAllTest() {
        IProdottoCompositoDAO prodottoCompositoDAO = ProdottoCompositoDAO.getInstance();
        ArrayList<ProdottoComposito> prodottiCompositi = prodottoCompositoDAO.findAll();
        Assert.assertEquals(9, prodottiCompositi.size());
    }

    @Test
    public void findByIdTest() {
        IProdottoCompositoDAO prodottoCompositoDAO = ProdottoCompositoDAO.getInstance();
        ProdottoComposito prodottoComposito = prodottoCompositoDAO.findById(prodottoCompositoDAO.findByName("Poltrone e Sofa").getIdArticolo());
        Assert.assertEquals("Poltrone e Sofa", prodottoComposito.getName());
    }
    @Test
    public void findByNameTest() {
        IProdottoCompositoDAO prodottoCompositoDAO = ProdottoCompositoDAO.getInstance();
        ProdottoComposito prodottoComposito = prodottoCompositoDAO.findByName("Poltrone e Sofa");
        Assert.assertEquals("Poltrone e Sofa",prodottoComposito.getName());
    }

    @Test
    public void addSottoProdottoTest(){
        Prodotto p = new Prodotto("Tavolino", "tavolino per salotto",30F, ProduttoreDAO.getInstance().findByName("PoltroneSofa"), CategoriaProdottoDAO.getInstance().findByName("Soggiorno"), 1);
        ProdottoDAO.getInstance().add(p);
        ProdottoComposito pc = ProdottoCompositoDAO.getInstance().findByName("Poltrone e Sofa");
        ProdottoCompositoDAO.getInstance().addSottoprodotto(pc.getIdArticolo(), p);
        pc = ProdottoCompositoDAO.getInstance().findByName("Poltrone e Sofa");
        Assert.assertEquals("Tavolino", pc.getSottoprodotti().get(2).getName());
        ProdottoDAO.getInstance().removeById(p.getIdArticolo());
    }

    @Test
    public void removeSottoProdottoTest(){
        IProdotto p = ProdottoDAO.getInstance().findByName("Sofa");
        ProdottoComposito pc = ProdottoCompositoDAO.getInstance().findByName("Poltrone e Sofa");
        ProdottoCompositoDAO.getInstance().removeSottoprodotto(pc.getIdArticolo(), p);
        pc = ProdottoCompositoDAO.getInstance().findByName("Poltrone e Sofa");
        Assert.assertEquals(1, pc.getSottoprodotti().size());
        ProdottoCompositoDAO.getInstance().addSottoprodotto(pc.getIdArticolo(), p);
    }

    @Test
    public void removeAllSottoProdottiTest(){
        ProdottoComposito pc = ProdottoCompositoDAO.getInstance().findByName("Poltrone e Sofa");
        ProdottoCompositoDAO.getInstance().removeAllSottoprodotti(pc.getIdArticolo());
        pc = ProdottoCompositoDAO.getInstance().findByName("Poltrone e Sofa");
        Assert.assertEquals(0, pc.getSottoprodotti().size());
        IProdotto p = ProdottoDAO.getInstance().findByName("Poltrona");
        ProdottoCompositoDAO.getInstance().addSottoprodotto(pc.getIdArticolo(), p);
        p = ProdottoDAO.getInstance().findByName("Sofa");
        ProdottoCompositoDAO.getInstance().addSottoprodotto(pc.getIdArticolo(), p);
    }

    @Test
    public void updateTest() {
        IProdottoCompositoDAO prodottoCompositoDAO = ProdottoCompositoDAO.getInstance();
        ProdottoComposito prodottoComposito = prodottoCompositoDAO.findByName("Poltrone e Sofa");

        prodottoComposito.getSottoprodotti().get(0).setQuantita(3); //metto 3 poltrone invece di 2
        prodottoCompositoDAO.update(prodottoComposito);

        prodottoComposito = prodottoCompositoDAO.findByName("Poltrone e Sofa");
        Assert.assertEquals(3, prodottoComposito.getSottoprodotti().get(0).getQuantita());
    }

}