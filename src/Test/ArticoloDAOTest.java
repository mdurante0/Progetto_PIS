package Test;

import DAO.*;
import Model.*;
import Model.composite.Prodotto;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class ArticoloDAOTest {
    @Before
    public void setUp() {
        IArticoloDAO articoloDAO = ArticoloDAO.getInstance();
        articoloDAO.add(new Articolo(7.5F, null, "Armadio", "Armadio spazioso", null, null, 8));
    }

    @After
    public void tearDown() {
        IArticoloDAO articoloDAO = ArticoloDAO.getInstance();
        articoloDAO.removeById(articoloDAO.findByName("Armadio").getIdArticolo());
    }

    @Test
    public void testFindByName() {
        IArticoloDAO articoloDAO = ArticoloDAO.getInstance();
        Articolo articolo = articoloDAO.findByName("Armadio");
        Assert.assertEquals("Armadio", articolo.getName());
    }

    @Test
    public void testFindAll() {
        IArticoloDAO articoloDAO = ArticoloDAO.getInstance();
        ArrayList<Articolo> articoli = articoloDAO.findAll();
        Assert.assertEquals(1, articoli.size());
    }

    @Test
    public void testUpdate() {
        IArticoloDAO articoloDAO = ArticoloDAO.getInstance();
        float prezzo = 8.5F;
        Articolo articolo = new Articolo(prezzo, null, "Armadio", "Armadio grande", null, null, 8);
        articolo.setIdArticolo(articoloDAO.findByName(articolo.getName()).getIdArticolo());
        articoloDAO.update(articolo);
        articolo = articoloDAO.findByName("Armadio");
        Assert.assertEquals("Armadio grande", articolo.getDescrizione());

    }

    @Test
    public void articoloExistsTest() {
        IArticoloDAO articoloDAO = ArticoloDAO.getInstance();
        boolean exist = articoloDAO.articoloExists("Armadio");
        Assert.assertTrue(exist);
        exist = articoloDAO.articoloExists("Comodino");
        Assert.assertFalse(exist);
    }

    @Test
    public void isProdottoTest() {
        IArticoloDAO articoloDAO = ArticoloDAO.getInstance();

        boolean check = articoloDAO.isProdotto("Armadio");
        Assert.assertTrue(check);

        check = articoloDAO.isProdotto("Trasporto");
        Assert.assertFalse(check);

        check = articoloDAO.isProdotto("Tavolo con sedie");
        Assert.assertFalse(check);

    }

    @Test
    public void isProdottoCompositoTest() {
        IArticoloDAO articoloDAO = ArticoloDAO.getInstance();

        boolean check = articoloDAO.isProdottoComposito("Armadio");
        Assert.assertFalse(check);

        check = articoloDAO.isProdottoComposito("Trasporto");
        Assert.assertFalse(check);

        check = articoloDAO.isProdottoComposito("Tavolo con sedie");
        Assert.assertTrue(check);

    }

    @Test
    public void isServizioTest() {
        IArticoloDAO articoloDAO = ArticoloDAO.getInstance();

        boolean check = articoloDAO.isServizio("Armadio");
        Assert.assertFalse(check);

        check = articoloDAO.isServizio("Trasporto");
        Assert.assertTrue(check);

        check = articoloDAO.isServizio("Tavolo con sedie");
        Assert.assertFalse(check);
    }

    @Test
    public void isAcquistatoTest(){
        IListaAcquistoDAO listaAcquistoDAO = ListaAcquistoDAO.getInstance();
        IProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
        IClienteDAO clienteDAO = ClienteDAO.getInstance();
        IPuntoVenditaDAO puntoVenditaDAO = PuntoVenditaDAO.getInstance();
        IMagazzinoDAO magazzinoDAO = MagazzinoDAO.getInstance();

        Magazzino magazzino = new Magazzino(7,5,"via mozart 25");
        magazzinoDAO.add(magazzino);

        PuntoVendita puntoVendita = new PuntoVendita("Lecce", "via mozart 23", "1234567890", "MyPuntoVendita", magazzino.getIdMagazzino(), null);
        puntoVenditaDAO.add(puntoVendita);
        puntoVendita = puntoVenditaDAO.findByName("MyPuntoVendita");

        Cliente c = new Cliente("Valentino","Rossi","vr46","123","valentino@gmail.com","CL", puntoVendita.getIdPuntoVendita(), true, 18, "via mozart 21", "avvocato", "0231561237" );
        clienteDAO.add(c);

        Prodotto p = new Prodotto("Comodino", "Comodino con due cassetti", 10F, 1);

        prodottoDAO.add(p);

        ArrayList<Articolo> articoli = new ArrayList<>();
        articoli.add(p);

        ListaAcquisto lista = new ListaAcquisto(c.getIdUtente(), true, "mylist", articoli);
        listaAcquistoDAO.add(lista);

        IArticoloDAO articoloDAO = ArticoloDAO.getInstance();
        Assert.assertTrue(articoloDAO.isAcquistato(p.getIdArticolo(),c));

        clienteDAO.removeById("vr46");
        prodottoDAO.removeById(prodottoDAO.findByName("Comodino").getIdArticolo());
        puntoVenditaDAO.removeById(puntoVenditaDAO.findByName("MyPuntoVendita").getIdPuntoVendita());
        magazzinoDAO.removeById(magazzinoDAO.findByAddress("via mozart 25").getIdMagazzino());
    }
}

