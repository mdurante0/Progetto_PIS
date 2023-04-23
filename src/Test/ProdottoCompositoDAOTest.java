package Test;

import DAO.*;
import Model.CategoriaProdotto;
import Model.composite.ProdottoComposito;
import Model.Collocazione;
import Model.Produttore;
import Model.composite.Prodotto;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class ProdottoCompositoDAOTest {
    @Before
    public void setUp() {
        IProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
        ICategoriaProdottoDAO categoriaProdottoDAO = CategoriaProdottoDAO.getInstance();
        ICollocazioneDAO collocazioneDAO = CollocazioneDAO.getInstance();
        IMagazzinoDAO magazzinoDAO = MagazzinoDAO.getInstance();
        IProduttoreDAO produttoreDAO = ProduttoreDAO.getInstance();
        int quantita = 8;

        produttoreDAO.add(new Produttore("Valentino","vr46@gmail.com","trento","italy","0995331239","boh"));

        Collocazione collocazione = new Collocazione(4,4,magazzinoDAO.findById(magazzinoDAO.findByAddress("aaa").getIdMagazzino()).getIdMagazzino());
        collocazioneDAO.add(collocazione);
        categoriaProdottoDAO.add(new CategoriaProdotto("aaa"));

        prodottoDAO.add(new Prodotto(55.35F, "cassa", "sono una cassa", categoriaProdottoDAO.findByName("aaa").getIdCategoria(), collocazione.getIdCollocazione() , produttoreDAO.findByName("Valentino").getIdProduttore(), quantita));

        IProdottoCompositoDAO prodottoCompositoDAO = ProdottoCompositoDAO.getInstance();

        prodottoCompositoDAO.add(new ProdottoComposito(prodottoDAO.findByName("sedia").getIdArticolo(), prodottoDAO.findByName("cassa").getIdArticolo(), quantita));
    }
    @After
    public void tearDown()  {
        IArticoloDAO articoloDAO = ArticoloDAO.getInstance();
        IProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
        ICategoriaProdottoDAO categoriaProdottoDAO = CategoriaProdottoDAO.getInstance();

        IProduttoreDAO produttoreDAO = ProduttoreDAO.getInstance();



        articoloDAO.removeById(prodottoDAO.findByName("cassa").getIdArticolo());
        produttoreDAO.removeById("Valentino");
        categoriaProdottoDAO.removeById("aaa");

        IProdottoCompositoDAO prodottoCompositoDAO = ProdottoCompositoDAO.getInstance();
        prodottoCompositoDAO.removeById(prodottoCompositoDAO.findByName("cassa").getIdArticolo());

    }

    @Test
    public void findAllTest() {
        IProdottoCompositoDAO prodottoCompositoDAO = ProdottoCompositoDAO.getInstance();
        ArrayList<ProdottoComposito> prodottiCompositi = prodottoCompositoDAO.findAll();
        Assert.assertEquals(1, prodottiCompositi.size());
    }

    @Test
    public void findByIdTest() {
        IProdottoCompositoDAO prodottoCompositoDAO = ProdottoCompositoDAO.getInstance();
        ProdottoComposito prodottoComposito = prodottoCompositoDAO.findById(prodottoCompositoDAO.findByName("cassa").getIdArticolo1());
        Assert.assertEquals("sedia", prodottoComposito.getName());
    }
    @Test
    public void findByNameTest() {
        IProdottoCompositoDAO prodottoCompositoDAO = ProdottoCompositoDAO.getInstance();
        ProdottoComposito prodottoComposito = prodottoCompositoDAO.findByName("cassa");
        Assert.assertEquals(2,prodottoComposito.getIdArticolo1() );
    }





    @Test
    public void updateTest() {
        IProdottoCompositoDAO prodottoCompositoDAO = ProdottoCompositoDAO.getInstance();
        ProdottoComposito prodottoComposito = prodottoCompositoDAO.findByName("cassa");
       prodottoComposito.setQuantita(4);
        prodottoCompositoDAO.update(prodottoComposito);
        prodottoComposito = prodottoCompositoDAO.findByName("cassa");
        Assert.assertEquals("cassa", prodottoComposito.getName());
    }
}