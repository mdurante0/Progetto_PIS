package Test;
import DAO.*;
import Model.CategoriaProdotto;
import Model.Magazzino;
import Model.composite.Prodotto;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class MagazzinoDAOTest {
    @Before
    public void setUp() {
        IMagazzinoDAO magazzinoDAO = MagazzinoDAO.getInstance();
        magazzinoDAO.add(new Magazzino( 4, 2, null));
    }

    @After
    public void tearDown() {
        IMagazzinoDAO magazzinoDAO = MagazzinoDAO.getInstance();
        magazzinoDAO.removeById(1);
    }
    @Test
    public void findAllTest() {
        IMagazzinoDAO magazzinoDAO = MagazzinoDAO.getInstance();
        ArrayList<Magazzino> magazzini = magazzinoDAO.findAll();
        Assert.assertEquals(2, magazzini.size());
    }

    @Test
    public void findByIdTest() {
        IMagazzinoDAO magazzinoDAO = MagazzinoDAO.getInstance();
        Magazzino magazzino = magazzinoDAO.findById(1);
        Assert.assertEquals(1, magazzino.getIdMagazzino());
    }

    @Test
    public void updateTest() {
        IMagazzinoDAO magazzinoDAO = MagazzinoDAO.getInstance();
        Magazzino magazzino = new Magazzino(7, 5, null);
        magazzino.setIdMagazzino(magazzinoDAO.findById(1).getIdMagazzino());
        magazzinoDAO.update(magazzino);
        magazzino = magazzinoDAO.findById(1);
        Assert.assertEquals(7, magazzino.getQuantitaCorsie());
    }


    @Test
    public void addProdottoTest(){
        IMagazzinoDAO magazzinoDAO = MagazzinoDAO.getInstance();
        IProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
        CategoriaProdotto categoriaProdotto = new CategoriaProdotto("arredamento", null, null);
        Prodotto prodotto = new Prodotto(12.5F, null, "Sedia", "Sedia in legno massello", categoriaProdotto, null, null, 9, null );
        prodottoDAO.add(prodotto);
        int rowCount = magazzinoDAO.addProdotto(1, prodotto);
        Assert.assertEquals(1, rowCount);
    }
  /*  @Test
    public void removeProdottoTest(){
        IMagazzinoDAO magazzinoDAO = MagazzinoDAO.getInstance();
        Prodotto prodotto = new Prodotto(12.5F, null, "Sedia", "Sedia in legno massello", null, null, null, 9, null );

        int rowCount = magazzinoDAO.removeProdotto(1, prodotto);
        Assert.assertEquals(1, rowCount);
    }
*/


}