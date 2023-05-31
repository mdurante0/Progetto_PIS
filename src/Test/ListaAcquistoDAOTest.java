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
        IArticoloDAO articoloDAO = ArticoloDAO.getInstance();
        IListaAcquistoDAO listaAcquistoDAO = ListaAcquistoDAO.getInstance();
        IClienteDAO clienteDAO = ClienteDAO.getInstance();
        IPuntoVenditaDAO puntoVenditaDAO = PuntoVenditaDAO.getInstance();
        IMagazzinoDAO magazzinoDAO = MagazzinoDAO.getInstance();

        Magazzino magazzino = new Magazzino(7,5,"via mozart 25");
        magazzinoDAO.add(magazzino);

        PuntoVendita puntoVendita = new PuntoVendita("Lecce", "via mozart 23", "1234567890", "MyPuntoVendita", magazzino.getIdMagazzino(), null);
        puntoVenditaDAO.add(puntoVendita);
        puntoVendita = puntoVenditaDAO.findByName("MyPuntoVendita");

        Fornitore fornitore = new Fornitore("FacciamoMontaggi","facciamomontaggi@gmail.com","Milano","Italia","1234567890","Montiamo mobili, armadi e comodini","www.facciamomontaggi.com");
        fornitoreDAO.add(fornitore);
        fornitore = fornitoreDAO.findByName("FacciamoMontaggi");
/*
        Manager manager = new Manager("Giovanni", "Paoli", "gpaoli", "456", "gpaoli@myshop.com","MN",10);
        managerDAO.add(manager);

        CategoriaServizio categoriaServizio = new CategoriaServizio("Montaggi");
        categoriaServizioDAO.add(categoriaServizio);
        categoriaServizio = categoriaServizioDAO.findByName("Montaggi");

        CategoriaProdotto categoriaProdotto = new CategoriaProdotto("Camera da letto");
        categoriaProdottoDAO.add(categoriaProdotto);
        categoriaProdotto = categoriaProdottoDAO.findByName("Camera da letto");

        Produttore produttore = new Produttore("FacciamoArmadi", "facciamoarmadi@gmail.com","Milano","Italia","1234567890", "Facciamo armadi in legno", "www.facciamoarmadi.com");
        produttoreDAO.add(produttore);
        produttore = produttoreDAO.findByName("FacciamoArmadi");
        */

        Cliente c = new Cliente("Valentino","Rossi","vr46","123","valentino@gmail.com","CL", puntoVendita.getIdPuntoVendita(), true, 18, "via mozart 21", "avvocato", "0231561237" );
        clienteDAO.add(c);

        Prodotto p = new Prodotto("Armadio", "armadio a due ante", 50F, 5);
        Servizio s = new Servizio("Montaggio", "Montaggio di un armadio a due ante", 10F, fornitore);

        prodottoDAO.add(p);
        servizioDAO.add(s);

        ArrayList<Articolo> articoli = new ArrayList<>();
        articoli.add(p);
        articoli.add(s);

        ListaAcquisto lista = new ListaAcquisto(c.getIdUtente(), false, "mylist", articoli);
        listaAcquistoDAO.add(lista);

    }

    @After
    public void tearDown()  {
/*
        IProduttoreDAO produttoreDAO = ProduttoreDAO.getInstance();
        IManagerDAO managerDAO = ManagerDAO.getInstance();
        */
        IMagazzinoDAO magazzinoDAO = MagazzinoDAO.getInstance();
        IFornitoreDAO fornitoreDAO = FornitoreDAO.getInstance();
        IClienteDAO clienteDAO = ClienteDAO.getInstance();
        IListaAcquistoDAO listaAcquistoDAO = ListaAcquistoDAO.getInstance();
        IPuntoVenditaDAO puntoVenditaDAO = PuntoVenditaDAO.getInstance();
        IProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
        IServizioDAO servizioDAO = ServizioDAO.getInstance();

        listaAcquistoDAO.removeByUser(clienteDAO.findById("vr46").getIdUtente());
        clienteDAO.removeById("vr46");
        prodottoDAO.removeById(prodottoDAO.findByName("Armadio").getIdArticolo());
        servizioDAO.removeById(servizioDAO.findByName("Montaggio").getIdArticolo());
        fornitoreDAO.removeById("FacciamoMontaggi");
        puntoVenditaDAO.removeById(puntoVenditaDAO.findByName("MyPuntoVendita").getIdPuntoVendita());
        magazzinoDAO.removeById(magazzinoDAO.findByAddress("via mozart 25").getIdMagazzino());

/*
        managerDAO.removeById("gpaoli");
        produttoreDAO.removeById("FacciamoArmadi");
        */

    }
    @Test
    public void findAllTest() {
        IListaAcquistoDAO listaAcquistoDAO = ListaAcquistoDAO.getInstance();
        ArrayList<ListaAcquisto> listeAcquisto = listaAcquistoDAO.findAll();
        Assert.assertEquals(1, listeAcquisto.size());
    }
    @Test
    public void findByUserTest() {
        IListaAcquistoDAO listaAcquistoDAO = ListaAcquistoDAO.getInstance();
        IClienteDAO clienteDAO = ClienteDAO.getInstance();
        ArrayList<ListaAcquisto> listeAcquisto = listaAcquistoDAO.findByUser(clienteDAO.findById("vr46").getIdUtente());
        Assert.assertEquals(1, listeAcquisto.size());
    }

    @Test
    public void findByIdTest() {
        IListaAcquistoDAO listaAcquistoDAO = ListaAcquistoDAO.getInstance();
        ListaAcquisto listaAcquisto = listaAcquistoDAO.findById(listaAcquistoDAO.findByName("mylist").getIdLista());
        Assert.assertEquals("mylist", listaAcquisto.getNome());
    }

    @Test
    public void findByNameTest() {
        IListaAcquistoDAO listaAcquistoDAO = ListaAcquistoDAO.getInstance();
        ListaAcquisto listaAcquisto = listaAcquistoDAO.findByName("mylist");
        Assert.assertEquals("mylist", listaAcquisto.getNome());
    }

   /* @Test
    public void updateTest() {
        IListaAcquistoDAO listaAcquistoDAO = ListaAcquistoDAO.getInstance();


    }*/
}