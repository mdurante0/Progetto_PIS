package Test;

import DAO.IManagerDAO;
import DAO.ManagerDAO;
import Model.Manager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class ManagerDAOTest {
    @Before
    public void setUp() throws Exception {
        IManagerDAO managerDAO = ManagerDAO.getInstance();
        managerDAO.add(new Manager("Valentino","Rossi","vr46","123","valentino@gmail.com","MN",7500.55F, 3));
    }

    @After
    public void tearDown() throws Exception {
        IManagerDAO managerDAO = ManagerDAO.getInstance();
        managerDAO.removeById("vr46");
    }

    @Test
    public void findAllTest() {
        IManagerDAO managerDao = ManagerDAO.getInstance();
        ArrayList<Manager> managers = managerDao.findAll();
        Assert.assertEquals(1, managers.size());
    }

    @Test
    public void findByIdTest() {
        IManagerDAO managerDAO = ManagerDAO.getInstance();
        Manager manager = managerDAO.findById("vr46");
        Assert.assertEquals("Valentino", manager.getName());
    }


    @Test
    public void removeByIdTest() {
        IManagerDAO managerDAO = ManagerDAO.getInstance();
        int rowCount = managerDAO.removeById("vr46");
        Assert.assertEquals(1, rowCount);
    }

    @Test
    public void updateTest() {
        IManagerDAO managerDAO = ManagerDAO.getInstance();
        Manager manager = new Manager("Valentino", "Rossi", "vr46", "123", "valentino@vr46.com", "MN",7500.55F, 3 );
        manager.setIdUtente(managerDAO.findById(manager.getUsername()).getIdUtente());
        managerDAO.update(manager);
        manager = managerDAO.findById("vr46");
        Assert.assertEquals("valentino@vr46.com", manager.getEmail());
    }
}