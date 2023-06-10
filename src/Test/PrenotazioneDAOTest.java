package Test;

import DAO.*;
import Model.*;
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
        ICategoriaProdottoDAO categoriaProdottoDAO = CategoriaProdottoDAO.getInstance();
        ICollocazioneDAO collocazioneDAO = CollocazioneDAO.getInstance();
        IMagazzinoDAO magazzinoDAO = MagazzinoDAO.getInstance();
        IProduttoreDAO produttoreDAO = ProduttoreDAO.getInstance();
        IPrenotazioneDAO prenotazioneDAO = PrenotazioneDAO.getInstance();
        IClienteDAO clienteDAO = ClienteDAO.getInstance();
        IPuntoVenditaDAO puntoVenditaDAO = PuntoVenditaDAO.getInstance();
        IManagerDAO managerDAO = ManagerDAO.getInstance();

        Magazzino m = new Magazzino( 4, 5, "via Paoli 23");
        magazzinoDAO.add(m);
        Manager manager = new Manager("Giovanni", "Paoli", "gpaoli", "456", "gpaoli@myshop.com","MN",10);
        managerDAO.add(manager);

        PuntoVendita puntoVendita = new PuntoVendita("Lecce", "via mozart 23", "1234567890", "MyPuntoVendita", m.getIdMagazzino(), manager);
        puntoVenditaDAO.add(puntoVendita);
        puntoVendita = puntoVenditaDAO.findByName("MyPuntoVendita");

        Cliente c = new Cliente("Valentino","Rossi","vr46","123","valentino@gmail.com","CL", puntoVendita, true, 18, "via mozart 21", "avvocato", "0231561237" );
        clienteDAO.add(c);
        c = clienteDAO.findById("vr46");

        produttoreDAO.add(new Produttore("Valentino","vr46@gmail.com","trento","italy","0995331239","boh"));


        Collocazione collocazione = new Collocazione(4,4,magazzinoDAO.findById(magazzinoDAO.findByAddress("via Paoli 23").getIdMagazzino()).getIdMagazzino());
        collocazioneDAO.add(collocazione);
        categoriaProdottoDAO.add(new CategoriaProdotto("aaa"));

        Prodotto p1 = new Prodotto(55.35F, null, "cassa", "sono una cassa", categoriaProdottoDAO.findByName("aaa"), collocazione, produttoreDAO.findByName("Valentino"),m, null , 8);
        prodottoDAO.add(p1);
        Prodotto p2 = new Prodotto(12.5F, null, "mattonelle", "mattonelle in marmo", categoriaProdottoDAO.findByName("aaa"), collocazione, produttoreDAO.findByName("Valentino"), m, null,12);
        prodottoDAO.add(p2);

        Date d = new Date();
        ArrayList<Prodotto> prodotti = new ArrayList<>();
        prodotti.add(p1);
        prodotti.add(p2);

        Prenotazione prenotazione = new Prenotazione(prodotti, d, clienteDAO.findById("vr46").getIdUtente());
        prenotazioneDAO.add(prenotazione);


    }

    @After
    public void tearDown()  {
        IArticoloDAO articoloDAO = ArticoloDAO.getInstance();
        IProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
        ICategoriaProdottoDAO categoriaProdottoDAO = CategoriaProdottoDAO.getInstance();
        ICollocazioneDAO collocazioneDAO = CollocazioneDAO.getInstance();
        IMagazzinoDAO magazzinoDAO = MagazzinoDAO.getInstance();
        IProduttoreDAO produttoreDAO = ProduttoreDAO.getInstance();
        IPrenotazioneDAO prenotazioneDAO = PrenotazioneDAO.getInstance();
        IClienteDAO clienteDAO = ClienteDAO.getInstance();
        IPuntoVenditaDAO puntoVenditaDAO = PuntoVenditaDAO.getInstance();
        IManagerDAO managerDAO = ManagerDAO.getInstance();

        prenotazioneDAO.removeByUser(clienteDAO.findById("vr46").getIdUtente());
        clienteDAO.removeById("vr46");
        articoloDAO.removeById(prodottoDAO.findByName("cassa").getIdArticolo());
        articoloDAO.removeById(prodottoDAO.findByName("mattonelle").getIdArticolo());
        produttoreDAO.removeById("Valentino");
        magazzinoDAO.removeById(magazzinoDAO.findByAddress("via Paoli 23").getIdMagazzino());
        puntoVenditaDAO.removeByManager(managerDAO.findById("gpaoli").getIdUtente());
        managerDAO.removeById("gpaoli");
        categoriaProdottoDAO.removeById("aaa");



    }
    @Test
    public void findAllTest() {
        IPrenotazioneDAO prenotazioneDAO = PrenotazioneDAO.getInstance();
        ArrayList<Prenotazione> prenotazioni = prenotazioneDAO.findAll();
        Assert.assertEquals(1, prenotazioni.size());
    }
    @Test
    public void findByUserTest() {
        IPrenotazioneDAO prenotazioneDAO = PrenotazioneDAO.getInstance();
        IClienteDAO clienteDAO = ClienteDAO.getInstance();
        ArrayList<Prenotazione> prenotazioni = prenotazioneDAO.findByUser(clienteDAO.findById("vr46").getIdUtente());
        Assert.assertEquals(1, prenotazioni.size());
        Assert.assertEquals(2, prenotazioni.get(0).getProdotti().size());
    }
    @Test
    public void findByIdTest() {
        IPrenotazioneDAO prenotazioneDAO = PrenotazioneDAO.getInstance();
        IClienteDAO clienteDAO = ClienteDAO.getInstance();
        Prenotazione prenotazione = prenotazioneDAO.findById(prenotazioneDAO.findByUser(clienteDAO.findById("vr46").getIdUtente()).get(0).getIdPrenotazione());
        Assert.assertEquals(2, prenotazione.getProdotti().size());
    }
@Test
    public void add_removeProdottoTest(){
        IPrenotazioneDAO prenotazioneDAO = PrenotazioneDAO.getInstance();
        IProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
        IArticoloDAO articoloDAO = ArticoloDAO.getInstance();
        ICategoriaProdottoDAO categoriaProdottoDAO = CategoriaProdottoDAO.getInstance();
        IMagazzinoDAO magazzinoDAO = MagazzinoDAO.getInstance();
        IProduttoreDAO produttoreDAO = ProduttoreDAO.getInstance();
        IClienteDAO clienteDAO = ClienteDAO.getInstance();

        Collocazione collocazione = new Collocazione(4,4, magazzinoDAO.findByAddress("via Paoli 23").getIdMagazzino());

        Prodotto prodotto = new Prodotto(55.35F, null, "dondolo", "sono una cassa", categoriaProdottoDAO.findByName("aaa"), collocazione, produttoreDAO.findByName("Valentino"),magazzinoDAO.findByAddress("via Paoli 23"), null , 9);

        prodottoDAO.add(prodotto);

        prodotto = prodottoDAO.findByName("dondolo");

        prenotazioneDAO.addProdotto(prenotazioneDAO.findByUser(clienteDAO.findById("vr46").getIdUtente()).get(0).getIdPrenotazione(), prodotto);

        Prenotazione prenotazione = prenotazioneDAO.findById(prenotazioneDAO.findByUser(clienteDAO.findById("vr46").getIdUtente()).get(0).getIdPrenotazione());

        Assert.assertEquals(3, prenotazione.getProdotti().size());

        prenotazioneDAO.removeProdotto(prenotazioneDAO.findByUser(clienteDAO.findById("vr46").getIdUtente()).get(0).getIdPrenotazione(), prodotto);

        prenotazione = prenotazioneDAO.findById(prenotazioneDAO.findByUser(clienteDAO.findById("vr46").getIdUtente()).get(0).getIdPrenotazione());

        Assert.assertEquals(2, prenotazione.getProdotti().size());

      articoloDAO.removeById(prodottoDAO.findByName("dondolo").getIdArticolo());


    }
    @Test
    public void updateTest() {
        IListaAcquistoDAO listaAcquistoDAO = ListaAcquistoDAO.getInstance();
        IPrenotazioneDAO prenotazioneDAO = PrenotazioneDAO.getInstance();
        IProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
        IArticoloDAO articoloDAO = ArticoloDAO.getInstance();
        ICategoriaProdottoDAO categoriaProdottoDAO = CategoriaProdottoDAO.getInstance();
        IMagazzinoDAO magazzinoDAO = MagazzinoDAO.getInstance();
        IProduttoreDAO produttoreDAO = ProduttoreDAO.getInstance();
        IClienteDAO clienteDAO = ClienteDAO.getInstance();

        Collocazione collocazione = new Collocazione(4,4, magazzinoDAO.findByAddress("via Paoli 23").getIdMagazzino());

        Prenotazione prenotazione = prenotazioneDAO.findById(prenotazioneDAO.findByUser(clienteDAO.findById("vr46").getIdUtente()).get(0).getIdPrenotazione());

        Date d = new Date();

        prenotazione.setDataPrenotazione(d);

        Prodotto prodotto2 = prodottoDAO.findByName("mattonelle");
        Prodotto prodotto1 = prodottoDAO.findByName("cassa");

        prodotto1.setQuantita(20);
        prodotto2.setQuantita(30);

        ArrayList<Prodotto> prodotti = new ArrayList<>();

        prodotti.add(prodotto1);
        prodotti.add(prodotto2);

        prenotazione.setProdotti(prodotti);


        prenotazioneDAO.update(prenotazione);

        prenotazione = prenotazioneDAO.findById(prenotazioneDAO.findByUser(clienteDAO.findById("vr46").getIdUtente()).get(0).getIdPrenotazione());

        Assert.assertEquals(20, prenotazione.getProdotti().get(0).getQuantita());
        Assert.assertEquals(30, prenotazione.getProdotti().get(1).getQuantita());



    }
}