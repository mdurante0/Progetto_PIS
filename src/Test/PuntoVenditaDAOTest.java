package Test;

import DAO.*;
import Model.Magazzino;
import Model.Manager;
import Model.PuntoVendita;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class PuntoVenditaDAOTest {
    @Before
    public void setUp() throws Exception {
        IPuntoVenditaDAO  puntoVenditaDAO = PuntoVenditaDAO.getInstance();
        IManagerDAO managerDAO = ManagerDAO.getInstance();
        IMagazzinoDAO magazzinoDAO = MagazzinoDAO.getInstance();

        magazzinoDAO.add(new Magazzino( 4, 2, "via Paoli 23", null));

        managerDAO.add(new Manager("Antonio","Bianchi","ab77","123","ab77@gmail.com","MN", (float) 7500.55, 3));

        puntoVenditaDAO.add(new PuntoVendita("Genova", "via palma", "1111111111", "aaa", magazzinoDAO.findByAddress("via Paoli 23").getIdMagazzino(), managerDAO.findByUsername("ab77")));
    }

    //@After
    public void tearDown() throws Exception {
        IPuntoVenditaDAO puntoVenditaDAO = PuntoVenditaDAO.getInstance();
        IManagerDAO managerDAO = ManagerDAO.getInstance();
        IMagazzinoDAO magazzinoDAO = MagazzinoDAO.getInstance();

        //puntoVenditaDAO.removeById(puntoVenditaDAO.findByName("aaa").getIdPuntoVendita());
        puntoVenditaDAO.removeByManager(managerDAO.findByUsername("ab77").getIdUtente());
        magazzinoDAO.removeById(magazzinoDAO.findByAddress("via Paoli 23").getIdMagazzino());
        managerDAO.removeById("ab77");

    }

    @Test
    public void findAllTest() {
        IPuntoVenditaDAO puntoVenditaDAO = PuntoVenditaDAO.getInstance();
        ArrayList<PuntoVendita> puntiVendita = puntoVenditaDAO.findAll();
        Assert.assertEquals(1, puntiVendita.size());
    }

    @Test
    public void findByIdTest() {
        IPuntoVenditaDAO puntoVenditaDAO = PuntoVenditaDAO.getInstance();
        PuntoVendita puntoVendita = puntoVenditaDAO.findById(puntoVenditaDAO.findByName("aaa").getIdPuntoVendita());
        Assert.assertEquals("aaa", puntoVendita.getNome());
    }
    @Test
    public void findByNameTest() {
        IPuntoVenditaDAO puntoVenditaDAO = PuntoVenditaDAO.getInstance();
        PuntoVendita puntoVendita = puntoVenditaDAO.findByName("aaa");
        Assert.assertEquals("aaa", puntoVendita.getNome());
    }
    @Test
    public void findByManagerTest() {
        IPuntoVenditaDAO puntoVenditaDAO = PuntoVenditaDAO.getInstance();
        IManagerDAO managerDAO = ManagerDAO.getInstance();
        PuntoVendita puntoVendita = puntoVenditaDAO.findByManager(managerDAO.findByUsername("ab77").getIdUtente());
        Assert.assertEquals("ab77", puntoVendita.getManager().getUsername());
    }

    @Test
    public void updateTest() {
        IPuntoVenditaDAO puntoVenditaDAO = PuntoVenditaDAO.getInstance();
        PuntoVendita puntoVendita = puntoVenditaDAO.findByName("aaa");
        puntoVendita.setCitta("Amalfi");
        puntoVendita.setTelefono("0952741894");
        puntoVenditaDAO.update(puntoVendita);
        puntoVendita = puntoVenditaDAO.findByName("aaa");
        Assert.assertEquals("aaa", puntoVendita.getNome());
        Assert.assertEquals("Amalfi", puntoVendita.getCitta());
    }
}