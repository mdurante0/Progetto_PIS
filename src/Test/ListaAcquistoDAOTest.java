package Test;

import Business.FactoryMethod.NotificationFactory;
import DAO.*;
import Model.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

public class ListaAcquistoDAOTest {
    @Test
    public void setUp() {
        IArticoloDAO articoloDAO = ArticoloDAO.getInstance();
        IListaAcquistoDAO listaAcquistoDAO = ListaAcquistoDAO.getInstance();
        IClienteDAO clienteDAO = ClienteDAO.getInstance();
        IPuntoVenditaDAO puntoVenditaDAO = PuntoVenditaDAO.getInstance();

        PuntoVendita puntoVendita = puntoVenditaDAO.findById(1);
        NotificationFactory.TipoNotifica canalePreferito = NotificationFactory.TipoNotifica.EMAIL;

        boolean abilitazione = true;
        int eta = 18;
        String residenza = "via mozart 21";
        String professione = "avvocato";
        String telefono = "0231561237";

        clienteDAO.add(new Cliente("Valentino","Rossi","vr46","123","valentino@gmail.com","CL", puntoVendita.getIdPuntoVendita(), canalePreferito, abilitazione, eta, residenza, professione, telefono ));



        articoloDAO.add(new Articolo(7.5F, null, "Armadio", "armadio in legno massello", null, null, 8));
        articoloDAO.add(new Articolo(80.7F, null, "mensola", "mensola in acciaio inox", null, null, 8));
        Articolo articolo1 = articoloDAO.findByName("Armadio");
        Articolo articolo2 = articoloDAO.findByName("mensola");
        ArrayList<Articolo> articoli = new ArrayList<>();
        articoli.add(articolo1);
        articoli.add(articolo2);
        boolean pagata = false;
        listaAcquistoDAO.add(new ListaAcquisto(clienteDAO.findById("vr46").getIdUtente(), pagata, "mylist", articoli, new Date()));


    }

/*    @After
    public void tearDown()  {
        IArticoloDAO articoloDAO = ArticoloDAO.getInstance();
        IClienteDAO clienteDAO = ClienteDAO.getInstance();
        IListaAcquistoDAO listaAcquistoDAO = ListaAcquistoDAO.getInstance();

        listaAcquistoDAO.removeByUser(clienteDAO.findById("vr46").getIdUtente());
        clienteDAO.removeById("vr46");
        articoloDAO.removeById(articoloDAO.findByName("Armadio").getIdArticolo());
        articoloDAO.removeById(articoloDAO.findByName("mensola").getIdArticolo());
    }*/
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
        IClienteDAO clienteDAO = ClienteDAO.getInstance();
        ListaAcquisto listaAcquisto = listaAcquistoDAO.findById(listaAcquistoDAO.findByNome("mylist").getIdLista());
        Assert.assertEquals("mylist", listaAcquisto.getNome());
    }

  /*  @Test
    public void updateTest() {
        IListaAcquistoDAO listaAcquistoDAO = ListaAcquistoDAO.getInstance();


    }*/
}