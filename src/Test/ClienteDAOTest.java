package Test;

import Business.FactoryMethod.NotificationFactory;
import DAO.ClienteDAO;
import DAO.IClienteDAO;
import DAO.IPuntoVenditaDAO;
import DAO.PuntoVenditaDAO;
import Model.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAOTest {
    @Before
    public void setUp() {
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
    }

    @After
    public void tearDown() {
        IClienteDAO clienteDAO = ClienteDAO.getInstance();
        clienteDAO.removeById("vr46");
    }

    @Test
    public void findAllTest() {
        IClienteDAO clienteDao = ClienteDAO.getInstance();
        ArrayList<Cliente> clienti = clienteDao.findAll();
        Assert.assertEquals(2, clienti.size());
    }

    @Test
    public void findByIdTest() {
        IClienteDAO clienteDAO = ClienteDAO.getInstance();
        Cliente cliente = clienteDAO.findById("vr46");
        Assert.assertEquals("Valentino", cliente.getName());
    }


    @Test
    public void updateTest() {
        IClienteDAO clienteDao = ClienteDAO.getInstance();

        Articolo articolo = new Articolo();
        ListaAcquisto list = new ListaAcquisto();
        list.add(articolo);
        List<ListaAcquisto> listaAcquisto = new ArrayList<>();
        listaAcquisto.add(list);

        Prenotazione p = new Prenotazione();
        List<Prenotazione> prenotazione = new ArrayList<>();
        prenotazione.add(p);

        PuntoVendita puntoVendita = new PuntoVendita();

        NotificationFactory.TipoNotifica canalePreferito = NotificationFactory.TipoNotifica.EMAIL;

        boolean abilitazione = true;

        int eta = 18;
        String residenza = "viale delle rose 17";
        String professione = "avvocato";
        String telefono = "0231561237";


        Cliente cliente = new Cliente("Valentino","Rossi","vr46","123","valentino@gmail.com","Cl",  puntoVendita.getIdPuntoVendita(), canalePreferito, abilitazione, eta, residenza, professione, telefono );
        cliente.setIdUtente(clienteDao.findById(cliente.getUsername()).getIdUtente());
        clienteDao.update(cliente);
        cliente = clienteDao.findById("vr46");
        Assert.assertEquals("valentino@gmail.com", cliente.getEmail());
    }


}