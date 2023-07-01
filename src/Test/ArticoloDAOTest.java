package Test;

import DAO.*;
import Model.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class ArticoloDAOTest {
    @Before
    public void setUp() {
        IArticoloDAO articoloDAO = ArticoloDAO.getInstance();
        articoloDAO.add(new Articolo(7.5F, null, "ArmadioTest", "Armadio spazioso", null, null, 8));
    }

    @After
    public void tearDown() {
        IArticoloDAO articoloDAO = ArticoloDAO.getInstance();
        articoloDAO.removeById(articoloDAO.findByName("ArmadioTest").getIdArticolo());
    }

    @Test
    public void findByNameTest() {
        IArticoloDAO articoloDAO = ArticoloDAO.getInstance();
        Articolo articolo = articoloDAO.findByName("ArmadioTest");
        Assert.assertEquals("ArmadioTest", articolo.getName());
    }

    @Test
    public void findAllTest() {
        IArticoloDAO articoloDAO = ArticoloDAO.getInstance();
        ArrayList<Articolo> articoli = articoloDAO.findAll();
        Assert.assertFalse(articoli.isEmpty());
    }

    @Test
    public void updateTest() {
        IArticoloDAO articoloDAO = ArticoloDAO.getInstance();
        Articolo articolo = articoloDAO.findByName("ArmadioTest");
        articolo.setDescrizione("Armadio grande");
        articoloDAO.update(articolo);
        articolo = articoloDAO.findByName("ArmadioTest");
        Assert.assertEquals("Armadio grande", articolo.getDescrizione());

    }

    @Test
    public void articoloExistsTest() {
        IArticoloDAO articoloDAO = ArticoloDAO.getInstance();
        boolean exist = articoloDAO.articoloExists("ArmadioTest");
        Assert.assertTrue(exist);
    }

    @Test
    public void isProdottoTest() {
        IArticoloDAO articoloDAO = ArticoloDAO.getInstance();

        boolean check = articoloDAO.isProdotto("ArmadioTest");
        Assert.assertFalse(check);
    }

    @Test
    public void isProdottoCompositoTest() {
        IArticoloDAO articoloDAO = ArticoloDAO.getInstance();

        boolean check = articoloDAO.isProdottoComposito("ArmadioTest");
        Assert.assertFalse(check);
    }

    @Test
    public void isServizioTest() {
        IArticoloDAO articoloDAO = ArticoloDAO.getInstance();

        boolean check = articoloDAO.isServizio("ArmadioTest");
        Assert.assertFalse(check);
    }

    @Test
    public void isAcquistatoTest(){
        IListaAcquistoDAO listaAcquistoDAO = ListaAcquistoDAO.getInstance();
        IArticoloDAO articoloDAO = ArticoloDAO.getInstance();
        IClienteDAO clienteDAO = ClienteDAO.getInstance();
        IPuntoVenditaDAO puntoVenditaDAO = PuntoVenditaDAO.getInstance();

        PuntoVendita puntoVendita = new PuntoVendita("test", "test", "1234567899", "puntoVenditaTest", new Magazzino(), new Manager());
        puntoVenditaDAO.add(puntoVendita);
        puntoVendita = puntoVenditaDAO.findByName("puntoVenditaTest");

        Cliente c = new Cliente("Valentino","Rossi","test","123","valentino@gmail.com","CL", puntoVendita, true, 18, "via mozart 21", "avvocato", "0231561237" );
        clienteDAO.add(c);

        Articolo articolo = articoloDAO.findByName("ArmadioTest");

        ArrayList<Articolo> articoli = new ArrayList<>();
        articoli.add(articolo);

        ListaAcquisto lista = new ListaAcquisto(c, true, "listaTest", articoli);
        listaAcquistoDAO.add(lista);

        Assert.assertTrue(articoloDAO.isAcquistato(articolo.getIdArticolo(),c));

        clienteDAO.removeById("vr46");
        puntoVenditaDAO.removeById(puntoVenditaDAO.findByName("puntoVenditaTest").getIdPuntoVendita());
    }
}

