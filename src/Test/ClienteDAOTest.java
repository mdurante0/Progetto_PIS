package Test;

import Business.FactoryMethod.NotificationFactory;
import DAO.ClienteDAO;
import DAO.IClienteDAO;
import DAO.IPuntoVenditaDAO;
import DAO.PuntoVenditaDAO;
import Model.Cliente;
import Model.Magazzino;
import Model.Manager;
import Model.PuntoVendita;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class ClienteDAOTest {
    @Before
    public void setUp() {
        IClienteDAO clienteDAO = ClienteDAO.getInstance();
        IPuntoVenditaDAO puntoVenditaDAO = PuntoVenditaDAO.getInstance();

        PuntoVendita puntoVendita = new PuntoVendita("Genova", "via palma", "1111111111", "puntoVenditaTest", new Magazzino(), new Manager());
        puntoVenditaDAO.add(puntoVendita);
        puntoVendita = puntoVenditaDAO.findByName("puntoVenditaTest");

        clienteDAO.add(new Cliente("Valentino","Rossi","clienteTest","123","valentino@gmail.com","CL", puntoVendita, NotificationFactory.TipoNotifica.EMAIL, true, 18, "via mozart 21", "avvocato", "0231561237" ));
    }

    @After
    public void tearDown() {
        IClienteDAO clienteDAO = ClienteDAO.getInstance();
        IPuntoVenditaDAO  puntoVenditaDAO = PuntoVenditaDAO.getInstance();

        clienteDAO.removeById("clienteTest");
        puntoVenditaDAO.removeById(puntoVenditaDAO.findByName("puntoVenditaTest").getIdPuntoVendita());
    }

    @Test
    public void findAllTest() {
        IClienteDAO clienteDao = ClienteDAO.getInstance();
        ArrayList<Cliente> clienti = clienteDao.findAll();
        Assert.assertFalse(clienti.isEmpty());
    }

    @Test
    public void findAllByPuntoVenditaTest(){
        IClienteDAO clienteDao = ClienteDAO.getInstance();
        IPuntoVenditaDAO puntoVenditaDAO = PuntoVenditaDAO.getInstance();
        PuntoVendita puntoVendita = puntoVenditaDAO.findByName("puntoVenditaTest");

        ArrayList<Cliente> clienti = clienteDao.findAllByPuntoVendita(puntoVendita.getIdPuntoVendita());
        Assert.assertEquals(1, clienti.size());
    }

    @Test
    public void findByIdTest() {
        IClienteDAO clienteDAO = ClienteDAO.getInstance();
        Cliente cliente = clienteDAO.findById(clienteDAO.findByUsername("clienteTest").getIdUtente());
        Assert.assertEquals("Valentino", cliente.getName());
    }

    @Test
    public void findByUsernameTest() {
        IClienteDAO clienteDAO = ClienteDAO.getInstance();
        Cliente cliente = clienteDAO.findByUsername("clienteTest");
        Assert.assertEquals("Valentino", cliente.getName());
    }

    @Test
    public void updateTest() {
        IClienteDAO clienteDao = ClienteDAO.getInstance();
        Cliente cliente = clienteDao.findByUsername("clienteTest");
        cliente.setResidenza("Viale delle rose 17");
        clienteDao.update(cliente);
        cliente = clienteDao.findByUsername("clienteTest");
        Assert.assertEquals("Viale delle rose 17", cliente.getResidenza());
    }


}