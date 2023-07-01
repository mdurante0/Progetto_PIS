package Test;

import DAO.*;
import Model.Magazzino;
import Model.Manager;
import Model.PuntoVendita;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class PuntoVenditaDAOTest {
    @Before
    public void setUp() {
        IPuntoVenditaDAO  puntoVenditaDAO = PuntoVenditaDAO.getInstance();
        IManagerDAO managerDAO = ManagerDAO.getInstance();

        managerDAO.add(new Manager("Antonio","Bianchi","test","123","test@gmail.com","MN", 7500.55F, 3));
        puntoVenditaDAO.add(new PuntoVendita("Genova", "via palma", "111111test", "puntoVenditaTest", new Magazzino(), managerDAO.findByUsername("test")));
    }

    @After
    public void tearDown() {
        IPuntoVenditaDAO puntoVenditaDAO = PuntoVenditaDAO.getInstance();
        IManagerDAO managerDAO = ManagerDAO.getInstance();

        puntoVenditaDAO.removeById(puntoVenditaDAO.findByName("puntoVenditaTest").getIdPuntoVendita());
        managerDAO.removeById("test");
    }

    @Test
    public void findAllTest() {
        IPuntoVenditaDAO puntoVenditaDAO = PuntoVenditaDAO.getInstance();
        ArrayList<PuntoVendita> puntiVendita = puntoVenditaDAO.findAll();
        Assert.assertFalse(puntiVendita.isEmpty());
    }

    @Test
    public void findByIdTest() {
        IPuntoVenditaDAO puntoVenditaDAO = PuntoVenditaDAO.getInstance();
        PuntoVendita puntoVendita = puntoVenditaDAO.findById(puntoVenditaDAO.findByName("puntoVenditaTest").getIdPuntoVendita());
        Assert.assertEquals("puntoVenditaTest", puntoVendita.getNome());
    }
    @Test
    public void findByNameTest() {
        IPuntoVenditaDAO puntoVenditaDAO = PuntoVenditaDAO.getInstance();
        PuntoVendita puntoVendita = puntoVenditaDAO.findByName("puntoVenditaTest");
        Assert.assertEquals("puntoVenditaTest", puntoVendita.getNome());
    }
    @Test
    public void findByManagerTest() {
        IPuntoVenditaDAO puntoVenditaDAO = PuntoVenditaDAO.getInstance();
        IManagerDAO managerDAO = ManagerDAO.getInstance();

        Manager manager = managerDAO.findByUsername("test");
        PuntoVendita puntoVendita = puntoVenditaDAO.findByManager(manager.getIdUtente());
        Assert.assertEquals("test", puntoVendita.getManager().getUsername());
    }

    @Test
    public void updateTest() {
        IPuntoVenditaDAO puntoVenditaDAO = PuntoVenditaDAO.getInstance();
        PuntoVendita puntoVendita = puntoVenditaDAO.findByName("puntoVenditaTest");
        puntoVendita.setCitta("Amalfi");
        puntoVendita.setTelefono("095274test");
        puntoVenditaDAO.update(puntoVendita);
        puntoVendita = puntoVenditaDAO.findByName("puntoVenditaTest");
        Assert.assertEquals("Amalfi", puntoVendita.getCitta());
    }
}