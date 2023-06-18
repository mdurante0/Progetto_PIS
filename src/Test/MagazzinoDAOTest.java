package Test;

import DAO.*;
import Model.*;
import Model.composite.IProdotto;
import Model.composite.Prodotto;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class MagazzinoDAOTest {

    @Before
    public void setUp() {

        Produttore produttore = new Produttore("PoltroneSofa","poltronesofa@gmail.com","trento","italy","0995331239","artigiani della qualita");
        IProduttoreDAO produttoreDAO = ProduttoreDAO.getInstance();
        produttoreDAO.add(produttore);
        produttore = produttoreDAO.findByName("PoltroneSofa");

        CategoriaProdotto categoriaProdotto = new CategoriaProdotto("Soggiorno");
        ICategoriaProdottoDAO categoriaProdottoDAO = CategoriaProdottoDAO.getInstance();
        categoriaProdottoDAO.add(categoriaProdotto);
        categoriaProdotto = categoriaProdottoDAO.findByName("Soggiorno");

        IProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
        Prodotto p1 = new Prodotto("Poltrona", "poltrona massaggiante",30.99F,produttore,categoriaProdotto,2);
        Prodotto p2 = new Prodotto("Sofa", "divano reclinabile",55.30F, produttore, categoriaProdotto,1);
        prodottoDAO.add(p1);
        prodottoDAO.add(p2);

        IMagazzinoDAO magazzinoDAO = MagazzinoDAO.getInstance();
        Magazzino m = new Magazzino( 4, 5, "via Paoli 23");
        magazzinoDAO.add(m);

        ICollocazioneDAO collocazioneDAO = CollocazioneDAO.getInstance();
        Collocazione c1 = new Collocazione(1,1, m);
        Collocazione c2 = new Collocazione(1,2, m);
        collocazioneDAO.add(c1);
        collocazioneDAO.add(c2);

        p1.setCollocazione(c1);
        p2.setCollocazione(c2);

        magazzinoDAO.addProdotto(m.getIdMagazzino(), p1);
        magazzinoDAO.addProdotto(m.getIdMagazzino(), p2);

    }

    @After
    public void tearDown() {
        IMagazzinoDAO magazzinoDAO = MagazzinoDAO.getInstance();
        IArticoloDAO articoloDAO = ArticoloDAO.getInstance();
        IProduttoreDAO produttoreDAO = ProduttoreDAO.getInstance();
        ICollocazioneDAO collocazioneDAO = CollocazioneDAO.getInstance();
        ICategoriaProdottoDAO categoriaProdottoDAO = CategoriaProdottoDAO.getInstance();

        Magazzino m = magazzinoDAO.findByAddress("via Paoli 23");
        Articolo a1 = articoloDAO.findByName("Poltrona");
        Articolo a2 = articoloDAO.findByName("Sofa");
        Produttore p = produttoreDAO.findByName("PoltroneSofa");
        Collocazione c1 = collocazioneDAO.findByMagazzinoAndProdotto(m.getIdMagazzino(), a1.getIdArticolo());
        Collocazione c2 = collocazioneDAO.findByMagazzinoAndProdotto(m.getIdMagazzino(), a2.getIdArticolo());
        CategoriaProdotto cp = categoriaProdottoDAO.findByName("Soggiorno");

        produttoreDAO.removeById(p.getNome());
        categoriaProdottoDAO.removeById(cp.getNome());
        collocazioneDAO.removeById(c1.getIdCollocazione());
        collocazioneDAO.removeById(c2.getIdCollocazione());
        articoloDAO.removeById(a1.getIdArticolo());
        articoloDAO.removeById(a2.getIdArticolo());
        magazzinoDAO.removeById(m.getIdMagazzino());
    }

    @Test
    public void findAllTest() {
        IMagazzinoDAO magazzinoDAO = MagazzinoDAO.getInstance();
        ArrayList<Magazzino> magazzini = magazzinoDAO.findAll();
        Assert.assertEquals(4, magazzini.size());
    }

    @Test
    public void findByIdTest() {
        IMagazzinoDAO magazzinoDAO = MagazzinoDAO.getInstance();
        Magazzino magazzino = magazzinoDAO.findById(magazzinoDAO.findByAddress("via Paoli 23").getIdMagazzino());
        Assert.assertEquals("via Paoli 23", magazzino.getIndirizzo());
        Assert.assertEquals("Poltrona", magazzino.getProdotti().get(0).getName());
        Assert.assertEquals("Sofa", magazzino.getProdotti().get(1).getName());
    }

    @Test
    public void findByAddressTest() {
        IMagazzinoDAO magazzinoDAO = MagazzinoDAO.getInstance();
        Magazzino magazzino = magazzinoDAO.findByAddress("via Paoli 23");
        Assert.assertEquals(4, magazzino.getQuantitaCorsie());
        Assert.assertEquals("Poltrona", magazzino.getProdotti().get(0).getName());
        Assert.assertEquals("Sofa", magazzino.getProdotti().get(1).getName());
    }

    @Test
    public void prodottoExistsTest(){
        IMagazzinoDAO magazzinoDAO = MagazzinoDAO.getInstance();
        IProdottoDAO prodottoDAO = ProdottoDAO.getInstance();

        Magazzino magazzino = magazzinoDAO.findByAddress("via Paoli 23");
        IProdotto prodotto = prodottoDAO.findByName("Poltrona");

        Assert.assertTrue(magazzinoDAO.prodottoExists(magazzino.getIdMagazzino(),prodotto.getIdArticolo()));
    }

    @Test
    public void updateTest() {
        IMagazzinoDAO magazzinoDAO = MagazzinoDAO.getInstance();

        Magazzino magazzino = magazzinoDAO.findByAddress("via Paoli 23");
        magazzino.setQuantitaCorsie(7);
        magazzino.getProdotti().get(0).setQuantita(10);

        magazzinoDAO.update(magazzino);
        magazzino = magazzinoDAO.findById(magazzino.getIdMagazzino());
        Assert.assertEquals(7, magazzino.getQuantitaCorsie());
        Assert.assertEquals(10, magazzino.getProdotti().get(0).getQuantita());
    }

    @Test
    public void addProdottoTest(){
        IMagazzinoDAO magazzinoDAO = MagazzinoDAO.getInstance();
        IProdottoDAO pDao = ProdottoDAO.getInstance();
        ICollocazioneDAO collocazioneDAO = CollocazioneDAO.getInstance();
        IProduttoreDAO produttoreDAO = ProduttoreDAO.getInstance();
        ICategoriaProdottoDAO categoriaProdottoDAO = CategoriaProdottoDAO.getInstance();
        IArticoloDAO articoloDAO = ArticoloDAO.getInstance();

        CategoriaProdotto categoriaProdotto = categoriaProdottoDAO.findByName("Soggiorno");
        Produttore produttore = produttoreDAO.findByName("PoltroneSofa");

        Prodotto p = new Prodotto("Tavolo", "Tavolo in legno",15.99F,produttore,categoriaProdotto,5);
        pDao.add(p);

        Magazzino m = magazzinoDAO.findByAddress("via Paoli 23");
        Collocazione c = new Collocazione(1,3, m);
        collocazioneDAO.add(c);
        p.setCollocazione(c);

        int rowCount = magazzinoDAO.addProdotto(m.getIdMagazzino(), p);
        articoloDAO.removeById(p.getIdArticolo());

        Assert.assertEquals(1, rowCount);
    }

    @Test
    public void updateProdottoTest(){
        IMagazzinoDAO magazzinoDAO = MagazzinoDAO.getInstance();

        Magazzino magazzino = magazzinoDAO.findByAddress("via Paoli 23");
        IProdotto prodotto = magazzino.getProdotti().get(0);
        prodotto.setQuantita(15);

        magazzinoDAO.updateProdotto(magazzino, prodotto);
        magazzino = magazzinoDAO.findById(magazzino.getIdMagazzino());
        Assert.assertEquals(15, magazzino.getProdotti().get(0).getQuantita());
    }

   @Test
    public void removeProdottoTest(){
       IMagazzinoDAO magazzinoDAO = MagazzinoDAO.getInstance();
       IProdottoDAO pDao = ProdottoDAO.getInstance();
       ICollocazioneDAO collocazioneDAO = CollocazioneDAO.getInstance();
       IProduttoreDAO produttoreDAO = ProduttoreDAO.getInstance();
       ICategoriaProdottoDAO categoriaProdottoDAO = CategoriaProdottoDAO.getInstance();
       IArticoloDAO articoloDAO = ArticoloDAO.getInstance();

       CategoriaProdotto categoriaProdotto = categoriaProdottoDAO.findByName("Soggiorno");
       Produttore produttore = produttoreDAO.findByName("PoltroneSofa");

       Prodotto p = new Prodotto("Tavolo", "Tavolo in legno",15.99F,produttore,categoriaProdotto,5);
       pDao.add(p);

       Magazzino m = magazzinoDAO.findByAddress("via Paoli 23");
       Collocazione c = new Collocazione(1,3, m);
       collocazioneDAO.add(c);
       p.setCollocazione(c);

       magazzinoDAO.addProdotto(m.getIdMagazzino(), p);
       int rowCount = magazzinoDAO.removeProdotto(m.getIdMagazzino(), p);
       articoloDAO.removeById(p.getIdArticolo());

       Assert.assertEquals(1, rowCount);
    }


}