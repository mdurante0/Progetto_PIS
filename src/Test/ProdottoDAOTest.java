package Test;

import DAO.*;
import Model.CategoriaProdotto;
import Model.Collocazione;
import Model.Magazzino;
import Model.Produttore;
import Model.composite.Prodotto;
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

        magazzinoDAO.add (new Magazzino( 4, 5, "via Paoli 23"));

        Collocazione collocazione = new Collocazione(4,4,magazzinoDAO.findByAddress("via Paoli 23").getIdMagazzino());
        collocazioneDAO.add(collocazione);
        categoriaProdottoDAO.add(new CategoriaProdotto("aaa"));

        Prodotto prodotto = new Prodotto(55.35F, null, "cassa", "sono una cassa", categoriaProdottoDAO.findByName("aaa"), collocazione, produttoreDAO.findByName("Valentino"),magazzinoDAO.findByAddress("via Paoli 23"), null , 8);
        prodottoDAO.add(prodotto);
        magazzinoDAO.addProdotto(magazzinoDAO.findByAddress("via Paoli 23").getIdMagazzino(), prodotto);
    }

    //@After
    public void tearDown() {
        IArticoloDAO articoloDAO = ArticoloDAO.getInstance();
        IProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
        ICategoriaProdottoDAO categoriaProdottoDAO = CategoriaProdottoDAO.getInstance();
        IMagazzinoDAO magazzinoDAO = MagazzinoDAO.getInstance();
        IProduttoreDAO produttoreDAO = ProduttoreDAO.getInstance();
        ICollocazioneDAO collocazioneDAO = CollocazioneDAO.getInstance();

        collocazioneDAO.removeById(collocazioneDAO.findByMagazzinoAndProdotto(magazzinoDAO.findByAddress("via Paoli 23").getIdMagazzino(), prodottoDAO.findByName("cassa").getIdArticolo()).getIdCollocazione());
        articoloDAO.removeById(prodottoDAO.findByName("cassa").getIdArticolo());
        magazzinoDAO.removeById(magazzinoDAO.findByAddress("via Paoli 23").getIdMagazzino());
        produttoreDAO.removeById("Valentino");
        categoriaProdottoDAO.removeById("aaa");
    }
    @Test
    public void testFindById() {
        IProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
        Prodotto prodotto = prodottoDAO.findById(prodottoDAO.findByName("cassa").getIdArticolo());
        Assert.assertEquals("Valentino", prodotto.getProduttore().getNome());
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
        Assert.assertEquals(1, prodotti.size());
    }
    @Test
    public void testFindAllByCategoria() {
        IProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
        ICategoriaProdottoDAO categoriaProdottoDAO = CategoriaProdottoDAO.getInstance();
        ArrayList<Prodotto> prodotti = prodottoDAO.findAllByCategoria(categoriaProdottoDAO.findByName("aaa").getIdCategoria());
        Assert.assertEquals(1, prodotti.size());
    }


    @Test
    public void testUpdate() {
        IProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
        Prodotto prodotto = prodottoDAO.findByName("cassa");
        prodotto.setDescrizione("cassa in legno massello");
        prodottoDAO.update(prodotto);
        prodotto = prodottoDAO.findByName("cassa");
        Assert.assertEquals("cassa in legno massello", prodotto.getDescrizione());

    }


}

