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
import java.util.Date;

public class PrenotazioneDAOTest {
    @Before
    public void setUp() {
        IProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
        IPrenotazioneDAO prenotazioneDAO = PrenotazioneDAO.getInstance();
        IClienteDAO clienteDAO = ClienteDAO.getInstance();
        IPuntoVenditaDAO puntoVenditaDAO = PuntoVenditaDAO.getInstance();

        PuntoVendita puntoVendita = new PuntoVendita("Lecce", "via mozart 23", "123456test", "puntoVenditaTest", new Magazzino(), new Manager());
        puntoVenditaDAO.add(puntoVendita);
        puntoVendita = puntoVenditaDAO.findByName("puntoVenditaTest");

        Cliente c = new Cliente("Valentino","Rossi","test","123","valentino@gmail.com","CL", puntoVendita, true, 18, "via mozart 21", "avvocato", "0231561237" );
        clienteDAO.add(c);
        c = clienteDAO.findByUsername("test");

        Prodotto prodotto = new Prodotto("cassaTest", "cassa in legno",10f, 8);
        prodottoDAO.add(prodotto);

        ArrayList<IProdotto> prodotti = new ArrayList<>();
        prodotti.add(prodotto);

        Prenotazione prenotazione = new Prenotazione(prodotti, new Date(), c.getIdUtente());
        prenotazioneDAO.add(prenotazione);
    }

    @After
    public void tearDown()  {
        IProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
        IPrenotazioneDAO prenotazioneDAO = PrenotazioneDAO.getInstance();
        IClienteDAO clienteDAO = ClienteDAO.getInstance();
        IPuntoVenditaDAO puntoVenditaDAO = PuntoVenditaDAO.getInstance();

        prenotazioneDAO.removeByUser(clienteDAO.findByUsername("test").getIdUtente());
        clienteDAO.removeById("test");
        prodottoDAO.removeById(prodottoDAO.findByName("cassaTest").getIdArticolo());
        puntoVenditaDAO.removeById(puntoVenditaDAO.findByName("puntoVenditaTest").getIdPuntoVendita());
    }
    @Test
    public void findAllTest() {
        IPrenotazioneDAO prenotazioneDAO = PrenotazioneDAO.getInstance();
        ArrayList<Prenotazione> prenotazioni = prenotazioneDAO.findAll();
        Assert.assertFalse(prenotazioni.isEmpty());
    }
    @Test
    public void findByUserTest() {
        IPrenotazioneDAO prenotazioneDAO = PrenotazioneDAO.getInstance();
        IClienteDAO clienteDAO = ClienteDAO.getInstance();
        ArrayList<Prenotazione> prenotazioni = prenotazioneDAO.findByUser(clienteDAO.findByUsername("test").getIdUtente());
        Assert.assertEquals(1, prenotazioni.size());
        Assert.assertEquals(1, prenotazioni.get(0).getProdotti().size());
    }
    @Test
    public void findByIdTest() {
        IPrenotazioneDAO prenotazioneDAO = PrenotazioneDAO.getInstance();
        IClienteDAO clienteDAO = ClienteDAO.getInstance();
        Prenotazione prenotazione = prenotazioneDAO.findById(prenotazioneDAO.findByUser(clienteDAO.findByUsername("test").getIdUtente()).get(0).getIdPrenotazione());
        Assert.assertEquals(1, prenotazione.getProdotti().size());
    }
    @Test
    public void addProdottoTest(){
        IPrenotazioneDAO prenotazioneDAO = PrenotazioneDAO.getInstance();
        IProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
        IClienteDAO clienteDAO = ClienteDAO.getInstance();

        Prodotto prodotto = new Prodotto("armadioTest", "Armadio in legno",10f, 8);
        prodottoDAO.add(prodotto);

        Prenotazione prenotazione = prenotazioneDAO.findByUser(clienteDAO.findByUsername("test").getIdUtente()).get(0);
        prenotazioneDAO.addProdotto(prenotazione.getIdPrenotazione(), prodotto);

        prenotazione = prenotazioneDAO.findById(prenotazione.getIdPrenotazione());
        Assert.assertEquals(2, prenotazione.getProdotti().size());

        prodottoDAO.removeById(prodotto.getIdArticolo());
    }

    @Test
    public void removeProdottoTest(){
        IPrenotazioneDAO prenotazioneDAO = PrenotazioneDAO.getInstance();
        IProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
        IClienteDAO clienteDAO = ClienteDAO.getInstance();

        IProdotto prodotto = prodottoDAO.findByName("cassaTest");

        Prenotazione prenotazione = prenotazioneDAO.findByUser(clienteDAO.findByUsername("test").getIdUtente()).get(0);
        prenotazioneDAO.removeProdotto(prenotazione.getIdPrenotazione(), prodotto);

        prenotazione = prenotazioneDAO.findById(prenotazione.getIdPrenotazione());
        Assert.assertEquals(0, prenotazione.getProdotti().size());

        prenotazioneDAO.addProdotto(prenotazione.getIdPrenotazione(), prodotto);
    }

    @Test
    public void updateTest() {
        IPrenotazioneDAO prenotazioneDAO = PrenotazioneDAO.getInstance();
        IProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
        IClienteDAO clienteDAO = ClienteDAO.getInstance();

        Prenotazione prenotazione = prenotazioneDAO.findByUser(clienteDAO.findByUsername("test").getIdUtente()).get(0);
        IProdotto prodotto = prodottoDAO.findByName("cassaTest");

        prodotto.setQuantita(20);
        ArrayList<IProdotto> prodotti = new ArrayList<>();
        prodotti.add(prodotto);
        prenotazione.setProdotti(prodotti);

        prenotazioneDAO.update(prenotazione);
        prenotazione = prenotazioneDAO.findById(prenotazione.getIdPrenotazione());
        Assert.assertEquals(20, prenotazione.getProdotti().get(0).getQuantita());
    }
}