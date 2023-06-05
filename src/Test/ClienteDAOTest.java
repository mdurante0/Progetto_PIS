package Test;

import Business.FactoryMethod.NotificationFactory;
import DAO.*;
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
        IPuntoVenditaDAO  puntoVenditaDAO = PuntoVenditaDAO.getInstance();
        IManagerDAO managerDAO = ManagerDAO.getInstance();
        IMagazzinoDAO magazzinoDAO = MagazzinoDAO.getInstance();

        Magazzino magazzino = new Magazzino( 4, 2, "via Paoli 23", new ArrayList<>());
        magazzinoDAO.add(magazzino);
        magazzino = magazzinoDAO.findByAddress("via Paoli 23");

        Manager manager = new Manager("Antonio","Bianchi","ab77","123","ab77@gmail.com","MN",7500.55F, 3);
        managerDAO.add(manager);

        PuntoVendita puntoVendita = new PuntoVendita("Genova", "via palma", "1111111111", "aaa", magazzino.getIdMagazzino(), manager);
        puntoVenditaDAO.add(puntoVendita);
        puntoVendita = puntoVenditaDAO.findByName("aaa");

        clienteDAO.add(new Cliente("Valentino","Rossi","vr46","123","valentino@gmail.com","CL", puntoVendita.getIdPuntoVendita(), NotificationFactory.TipoNotifica.EMAIL, true, 18, "via mozart 21", "avvocato", "0231561237" ));
    }

    @After
    public void tearDown() {
        IClienteDAO clienteDAO = ClienteDAO.getInstance();
        IPuntoVenditaDAO  puntoVenditaDAO = PuntoVenditaDAO.getInstance();
        IManagerDAO managerDAO = ManagerDAO.getInstance();
        IMagazzinoDAO magazzinoDAO = MagazzinoDAO.getInstance();

        clienteDAO.removeById("vr46");
        managerDAO.removeById("ab77");
        puntoVenditaDAO.removeById(puntoVenditaDAO.findByName("aaa").getIdPuntoVendita());
        magazzinoDAO.removeById(magazzinoDAO.findByAddress("via Paoli 23").getIdMagazzino());
    }

    @Test
    public void findAllTest() {
        IClienteDAO clienteDao = ClienteDAO.getInstance();
        ArrayList<Cliente> clienti = clienteDao.findAll();
        Assert.assertEquals(1, clienti.size());
    }

    @Test
    public void findByIdTest() {
        IClienteDAO clienteDAO = ClienteDAO.getInstance();
        Cliente cliente = clienteDAO.findById("vr46");
        Assert.assertEquals("Valentino", cliente.getName());
    }

    @Test
    public void isGestibileTest(){
        IManagerDAO managerDAO = ManagerDAO.getInstance();
        IClienteDAO clienteDAO = ClienteDAO.getInstance();

        Manager manager = managerDAO.findById("ab77");
        Cliente cliente = clienteDAO.findById("vr46");
        Assert.assertTrue(clienteDAO.isGestibile(cliente, manager.getIdUtente()));
    }

    @Test
    public void updateTest() {
        IClienteDAO clienteDao = ClienteDAO.getInstance();
        Cliente cliente = clienteDao.findById("vr46");
        cliente.setResidenza("Viale delle rose 17");
        clienteDao.update(cliente);
        cliente = clienteDao.findById("vr46");
        Assert.assertEquals("Viale delle rose 17", cliente.getResidenza());
    }


}