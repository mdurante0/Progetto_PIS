package Test;

import DAO.*;
import Model.Collocazione;
import Model.Magazzino;
import Model.composite.IProdotto;
import Model.composite.Prodotto;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class CollocazioneDAOTest {
    @Before
    public void setUp() {
        ICollocazioneDAO collocazioneDAO = CollocazioneDAO.getInstance();
        IMagazzinoDAO magazzinoDAO = MagazzinoDAO.getInstance();
        IProdottoDAO prodottoDAO = ProdottoDAO.getInstance();

        magazzinoDAO.add(new Magazzino(10,10,"magazzinoTest"));
        prodottoDAO.add(new Prodotto("prodottoTest","prodotto di test", 10F, 5));
        Magazzino magazzino = magazzinoDAO.findByAddress("magazzinoTest");
        IProdotto prodotto = prodottoDAO.findByName("prodottoTest");
        Collocazione collocazione = new Collocazione(4,4, magazzino);

        collocazioneDAO.add(collocazione);
        collocazione = collocazioneDAO.findByCorsiaScaffaleAndMagazzino(4,4,magazzino.getIdMagazzino()); //recupero l'id
        prodotto.setCollocazione(collocazione);
        magazzinoDAO.addProdotto(magazzino.getIdMagazzino(), prodotto);
    }

    @After
    public void tearDown() {
        ICollocazioneDAO collocazioneDAO = CollocazioneDAO.getInstance();
        IMagazzinoDAO magazzinoDAO = MagazzinoDAO.getInstance();
        IProdottoDAO prodottoDAO = ProdottoDAO.getInstance();

        Magazzino magazzino = magazzinoDAO.findByAddress("magazzinoTest");
        Collocazione collocazione = collocazioneDAO.findByCorsiaScaffaleAndMagazzino(4,4, magazzino.getIdMagazzino());
        IProdotto prodotto = prodottoDAO.findByName("prodottoTest");

        collocazioneDAO.removeById(collocazione.getIdCollocazione());
        magazzinoDAO.removeById(magazzino.getIdMagazzino());
        prodottoDAO.removeById(prodotto.getIdArticolo());
    }

    @Test
    public void findAllTest() {
        ICollocazioneDAO collocazioneDAO = CollocazioneDAO.getInstance();
        ArrayList<Collocazione> collocazioni = collocazioneDAO.findAll();
        Assert.assertFalse(collocazioni.isEmpty());
    }

    @Test
    public void findByIdTest() {
        ICollocazioneDAO collocazioneDAO = CollocazioneDAO.getInstance();
        IMagazzinoDAO magazzinoDAO = MagazzinoDAO.getInstance();

        Magazzino magazzino = magazzinoDAO.findByAddress("magazzinoTest");
        Collocazione collocazione = collocazioneDAO.findByCorsiaScaffaleAndMagazzino(4,4, magazzino.getIdMagazzino());
        collocazione = collocazioneDAO.findById(collocazione.getIdCollocazione());
        Assert.assertEquals(4, collocazione.getCorsia());
    }
    @Test
    public void findAllByMagazzinoTest() {
        ICollocazioneDAO collocazioneDAO = CollocazioneDAO.getInstance();
        IMagazzinoDAO magazzinoDAO = MagazzinoDAO.getInstance();

        Magazzino magazzino = magazzinoDAO.findByAddress("magazzinoTest");
        ArrayList<Collocazione> collocazioni = collocazioneDAO.findAllByMagazzino(magazzino.getIdMagazzino());
        Assert.assertEquals(1, collocazioni.size());
    }

    @Test
    public void findAllByProdottoTest() {
        ICollocazioneDAO collocazioneDAO = CollocazioneDAO.getInstance();
        IProdottoDAO prodottoDAO = ProdottoDAO.getInstance();

        IProdotto prodotto = prodottoDAO.findByName("prodottoTest");
        ArrayList<Collocazione> collocazioni = collocazioneDAO.findAllByProdotto(prodotto.getIdArticolo());
        Assert.assertEquals(1, collocazioni.size());
    }

    @Test
    public void findByMagazzinoAndProdottoTest(){
        ICollocazioneDAO collocazioneDAO = CollocazioneDAO.getInstance();
        IMagazzinoDAO magazzinoDAO = MagazzinoDAO.getInstance();
        IProdottoDAO prodottoDAO = ProdottoDAO.getInstance();

        Magazzino magazzino = magazzinoDAO.findByAddress("magazzinoTest");
        IProdotto prodotto = prodottoDAO.findByName("prodottoTest");
        Collocazione collocazione = collocazioneDAO.findByMagazzinoAndProdotto(magazzino.getIdMagazzino(), prodotto.getIdArticolo());

        Assert.assertEquals(4, collocazione.getCorsia());
    }

    @Test
    public void updateTest() {
        ICollocazioneDAO collocazioneDAO = CollocazioneDAO.getInstance();
        IMagazzinoDAO magazzinoDAO = MagazzinoDAO.getInstance();
        IProdottoDAO prodottoDAO = ProdottoDAO.getInstance();

        Magazzino magazzino = magazzinoDAO.findByAddress("magazzinoTest");
        IProdotto prodotto = prodottoDAO.findByName("prodottoTest");
        Collocazione collocazione = collocazioneDAO.findByMagazzinoAndProdotto(magazzino.getIdMagazzino(), prodotto.getIdArticolo());

        collocazione.setCorsia(3);
        collocazione.setScaffale(3);
        collocazioneDAO.update(collocazione);

        collocazione = collocazioneDAO.findByMagazzinoAndProdotto(magazzino.getIdMagazzino(), prodotto.getIdArticolo());
        Assert.assertEquals(3, collocazione.getCorsia());
        Assert.assertEquals(3, collocazione.getScaffale());

        collocazione.setCorsia(4);
        collocazione.setScaffale(4);
        collocazioneDAO.update(collocazione);
    }
}