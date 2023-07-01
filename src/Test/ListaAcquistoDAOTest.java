package Test;

import DAO.*;
import Model.*;
import Model.composite.Prodotto;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class ListaAcquistoDAOTest {
    @Before
    public void setUp() {
        IFornitoreDAO fornitoreDAO = FornitoreDAO.getInstance();
        IListaAcquistoDAO listaAcquistoDAO = ListaAcquistoDAO.getInstance();
        IServizioDAO servizioDAO = ServizioDAO.getInstance();
        IPuntoVenditaDAO puntoVenditaDAO = PuntoVenditaDAO.getInstance();
        IClienteDAO clienteDAO = ClienteDAO.getInstance();

        PuntoVendita puntoVendita = new PuntoVendita("Lecce", "via mozart 23", "1234567890", "puntoVenditaTest", new Magazzino(), new Manager());
        puntoVenditaDAO.add(puntoVendita);
        puntoVendita = puntoVenditaDAO.findByName("puntoVenditaTest");

        Fornitore fornitore = new Fornitore("Test","test@gmail.com","Milano","Italia","7234567890","Montiamo mobili, armadi e comodini","www.facciamomontaggi.com");
        fornitoreDAO.add(fornitore);
        fornitore = fornitoreDAO.findByName("Test");

        Cliente c = new Cliente("Valentino","Rossi","test","123","valentino@gmail.com","CL", puntoVendita, true, 18, "via mozart 21", "avvocato", "0231561237" );
        clienteDAO.add(c);

        Servizio s = new Servizio("MontaggioTest", "Montaggio di un armadio a due ante", 10F, fornitore);

        servizioDAO.add(s);

        ArrayList<Articolo> articoli = new ArrayList<>();
        articoli.add(s);

        ListaAcquisto lista = new ListaAcquisto(c, false, "testList", articoli);
        listaAcquistoDAO.add(lista);
    }

    @After
    public void tearDown()  {
        IFornitoreDAO fornitoreDAO = FornitoreDAO.getInstance();
        IClienteDAO clienteDAO = ClienteDAO.getInstance();
        IListaAcquistoDAO listaAcquistoDAO = ListaAcquistoDAO.getInstance();
        IPuntoVenditaDAO puntoVenditaDAO = PuntoVenditaDAO.getInstance();
        IServizioDAO servizioDAO = ServizioDAO.getInstance();

        ListaAcquisto listaAcquisto = listaAcquistoDAO.findByName("testLista");
        if(listaAcquisto != null)
            listaAcquistoDAO.removeById(listaAcquisto.getIdLista());
        clienteDAO.removeById("test");
        servizioDAO.removeById(servizioDAO.findByName("MontaggioTest").getIdArticolo());
        fornitoreDAO.removeById("Test");
        puntoVenditaDAO.removeById(puntoVenditaDAO.findByName("puntoVenditaTest").getIdPuntoVendita());
    }
    @Test
    public void findAllTest() {
        IListaAcquistoDAO listaAcquistoDAO = ListaAcquistoDAO.getInstance();
        ArrayList<ListaAcquisto> listeAcquisto = listaAcquistoDAO.findAll();
        Assert.assertFalse(listeAcquisto.isEmpty());
    }
    @Test
    public void findByUserTest() {
        IListaAcquistoDAO listaAcquistoDAO = ListaAcquistoDAO.getInstance();
        IClienteDAO clienteDAO = ClienteDAO.getInstance();
        ArrayList<ListaAcquisto> listeAcquisto = listaAcquistoDAO.findByUser(clienteDAO.findByUsername("test").getIdUtente());
        Assert.assertEquals(1, listeAcquisto.size());
    }

    @Test
    public void findByIdTest() {
        IListaAcquistoDAO listaAcquistoDAO = ListaAcquistoDAO.getInstance();
        ListaAcquisto listaAcquisto = listaAcquistoDAO.findById(listaAcquistoDAO.findByName("testList").getIdLista());
        Assert.assertEquals("testList", listaAcquisto.getNome());
    }

    @Test
    public void findByNameTest() {
        IListaAcquistoDAO listaAcquistoDAO = ListaAcquistoDAO.getInstance();
        ListaAcquisto listaAcquisto = listaAcquistoDAO.findByName("testList");
        Assert.assertEquals("testList", listaAcquisto.getNome());
    }

    @Test
    public void updateTest() {
        IListaAcquistoDAO listaAcquistoDAO = ListaAcquistoDAO.getInstance();
        ListaAcquisto listaAcquisto = listaAcquistoDAO.findByName("testList");
        listaAcquisto.setNome("MyFirstListTest");
        listaAcquistoDAO.update(listaAcquisto);
        Assert.assertEquals("MyFirstListTest", listaAcquisto.getNome());
        listaAcquisto.setNome("testList");
        listaAcquistoDAO.update(listaAcquisto);
    }

    @Test
    public void findNotPaidByPuntoVenditaTest(){
        IListaAcquistoDAO listaAcquistoDAO = ListaAcquistoDAO.getInstance();
        IPuntoVenditaDAO puntoVenditaDAO = PuntoVenditaDAO.getInstance();
        PuntoVendita puntoVendita = puntoVenditaDAO.findByName("puntoVenditaTest");
        ArrayList<ListaAcquisto> listeAcquisto = listaAcquistoDAO.findAllByPuntoVendita(puntoVendita.getIdPuntoVendita());
        Assert.assertEquals(1, listeAcquisto.size());
    }

    @Test
    public void setPagataTest(){
        IListaAcquistoDAO listaAcquistoDAO = ListaAcquistoDAO.getInstance();
        ListaAcquisto listaAcquisto = listaAcquistoDAO.findByName("testList");
        listaAcquistoDAO.setPagata(listaAcquisto.getIdLista());
        listaAcquisto = listaAcquistoDAO.findByName("testList");
        Assert.assertTrue(listaAcquisto.isPagata());
    }

    @Test
    public void addArticoloTest(){
        IListaAcquistoDAO listaAcquistoDAO = ListaAcquistoDAO.getInstance();
        IProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
        Prodotto p = new Prodotto("TavoloTest", "Tavolo in legno", 20F, 1);
        prodottoDAO.add(p);
        ListaAcquisto listaAcquisto = listaAcquistoDAO.findByName("testList");
        listaAcquistoDAO.addArticolo(listaAcquisto.getIdLista(),p);
        listaAcquisto = listaAcquistoDAO.findByName("testList");
        Assert.assertEquals(2,listaAcquisto.getArticoli().size());
        prodottoDAO.removeById(p.getIdArticolo());
    }

    @Test
    public void removeArticoloTest(){
        IListaAcquistoDAO listaAcquistoDAO = ListaAcquistoDAO.getInstance();
        IServizioDAO servizioDAO = ServizioDAO.getInstance();
        ListaAcquisto listaAcquisto = listaAcquistoDAO.findByName("testList");
        Servizio servizio = servizioDAO.findByName("MontaggioTest");
        listaAcquistoDAO.removeArticolo(listaAcquisto.getIdLista(), servizio);
        listaAcquisto = listaAcquistoDAO.findByName("testList");
        Assert.assertTrue(listaAcquisto.getArticoli().isEmpty());
    }

    @Test
    public void removeByUserTest(){
        IListaAcquistoDAO listaAcquistoDAO = ListaAcquistoDAO.getInstance();
        IClienteDAO clienteDAO = ClienteDAO.getInstance();
        Cliente cliente = clienteDAO.findByUsername("test");
        listaAcquistoDAO.removeByUser(cliente.getIdUtente());
        Assert.assertTrue(listaAcquistoDAO.findByUser(cliente.getIdUtente()).isEmpty());
    }
}