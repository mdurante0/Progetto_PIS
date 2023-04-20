package Test;

import DAO.*;
import Model.CategoriaProdotto;
import Model.Collocazione;
import Model.Produttore;
import Model.composite.Prodotto;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class ProdottoDAOTest {
    @Before
    public void setUp() {
        IProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
        ICategoriaProdottoDAO categoriaProdottoDAO = CategoriaProdottoDAO.getInstance();
        ICollocazioneDAO collocazioneDAO = CollocazioneDAO.getInstance();
        IMagazzinoDAO magazzinoDAO = MagazzinoDAO.getInstance();
        IProduttoreDAO produttoreDAO = ProduttoreDAO.getInstance();

        produttoreDAO.add(new Produttore("Valentino","vr46@gmail.com","trento","italy","0995331239","boh"));

        Collocazione collocazione = new Collocazione(4,4,magazzinoDAO.findById(magazzinoDAO.findByAddress("aaa").getIdMagazzino()).getIdMagazzino());
        collocazioneDAO.add(collocazione);
        categoriaProdottoDAO.add(new CategoriaProdotto("aaa"));

        prodottoDAO.add(new Prodotto(55.35F, "cassa", "sono una cassa", categoriaProdottoDAO.findByName("aaa").getIdCategoria(), collocazione.getIdCollocazione() , produttoreDAO.findByName("Valentino").getIdProduttore(), 8));
    }

    @After
    public void tearDown() {
        IArticoloDAO articoloDAO = ArticoloDAO.getInstance();
        IProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
        ICategoriaProdottoDAO categoriaProdottoDAO = CategoriaProdottoDAO.getInstance();

        IProduttoreDAO produttoreDAO = ProduttoreDAO.getInstance();


        articoloDAO.removeById(prodottoDAO.findByName("cassa").getIdArticolo());
        produttoreDAO.removeById("Valentino");
        categoriaProdottoDAO.removeById("aaa");
    }
    @Test
    public void testFindById() {
        IProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
        Prodotto prodotto = prodottoDAO.findById(1);
        Assert.assertEquals(1, prodotto.getIdCategoria());
    }
    @Test
    public void testFindByName() {
        IProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
        Prodotto articolo = prodottoDAO.findByName("cassa");
        Assert.assertEquals("cassa", articolo.getName());
    }

    @Test
    public void testFindAllTest() {
        IProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
        ArrayList<Prodotto> prodotti = prodottoDAO.findAll();
        Assert.assertEquals(3, prodotti.size());
    }
    @Test
    public void testFindAllByCategoria() {
        IProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
        ArrayList<Prodotto> prodotti = prodottoDAO.findAllByCategoria(1);
        Assert.assertEquals(2, prodotti.size());
    }


    @Test
    public void testUpdate() {
        IProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
        ICategoriaProdottoDAO categoriaProdottoDAO = CategoriaProdottoDAO.getInstance();
        IMagazzinoDAO magazzinoDAO = MagazzinoDAO.getInstance();
        IProduttoreDAO produttoreDAO = ProduttoreDAO.getInstance();

        Collocazione collocazione = new Collocazione(4,4,magazzinoDAO.findById(magazzinoDAO.findByAddress("aaa").getIdMagazzino()).getIdMagazzino());
        collocazione.setIdCollocazione(1);


        float prezzo = 8.5F;
        Prodotto prodotto = new Prodotto(prezzo, null, "cassa", "cassa in legno massello", categoriaProdottoDAO.findByName("aaa"), collocazione , produttoreDAO.findByName("Valentino"), 9,null);
        prodotto.setIdArticolo(prodottoDAO.findByName(prodotto.getName()).getIdArticolo());
        prodottoDAO.update(prodotto);
        prodotto = prodottoDAO.findByName("cassa");
        Assert.assertEquals("cassa in legno massello", prodotto.getDescrizione());

    }


}

