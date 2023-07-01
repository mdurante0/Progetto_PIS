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
        managerDAO.add(new Manager("Valentino","Rossi","test","123","valentino@gmail.com","MN",7500.55F, 3));
    }

    @After
    public void tearDown() throws Exception {
        IManagerDAO managerDAO = ManagerDAO.getInstance();
        managerDAO.removeById("test");
    }

    @Test
    public void findByIdTest() {
        IManagerDAO managerDAO = ManagerDAO.getInstance();
        Manager manager = managerDAO.findById(managerDAO.findByUsername("test").getIdUtente());
        Assert.assertEquals("Valentino", manager.getName());
    }

    @Test
    public void findAllTest() {
        IManagerDAO managerDao = ManagerDAO.getInstance();
        ArrayList<Manager> managers = managerDao.findAll();
        Assert.assertFalse(managers.isEmpty());
    }

    @Test
    public void findByUsernameTest() {
        IManagerDAO managerDAO = ManagerDAO.getInstance();
        Manager manager = managerDAO.findByUsername("test");
        Assert.assertEquals("Valentino", manager.getName());
    }


    @Test
    public void removeByIdTest() {
        IManagerDAO managerDAO = ManagerDAO.getInstance();
        int rowCount = managerDAO.removeById("test");
        Assert.assertEquals(1, rowCount);
    }

    @Test
    public void updateTest() {
        IManagerDAO managerDAO = ManagerDAO.getInstance();
        Manager manager = managerDAO.findByUsername("test");
        manager.setEmail("valentino@vr46.com");
        managerDAO.update(manager);
        manager = managerDAO.findByUsername("test");
        Assert.assertEquals("valentino@vr46.com", manager.getEmail());
    }
}