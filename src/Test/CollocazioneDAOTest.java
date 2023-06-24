package Test;

import DAO.CollocazioneDAO;
import DAO.ICollocazioneDAO;
import DAO.IMagazzinoDAO;
import DAO.MagazzinoDAO;
import Model.Collocazione;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class CollocazioneDAOTest {
    @Before
    public void setUp() {
        ICollocazioneDAO collocazioneDAO = CollocazioneDAO.getInstance();
        IMagazzinoDAO magazzinoDAO = MagazzinoDAO.getInstance();
        collocazioneDAO.add(new Collocazione(4,4,magazzinoDAO.findById(magazzinoDAO.findByAddress("aaa").getIdMagazzino())));
    }

    @After
    public void tearDown() {
        ICollocazioneDAO collocazioneDAO = CollocazioneDAO.getInstance();
        IMagazzinoDAO magazzinoDAO = MagazzinoDAO.getInstance();
        collocazioneDAO.removeById(5);

    }

    @Test
    public void findAllTest() {
        ICollocazioneDAO collocazioneDAO = CollocazioneDAO.getInstance();
        ArrayList<Collocazione> collocazioni = collocazioneDAO.findAll();
        Assert.assertEquals(2, collocazioni.size());
    }

    @Test
    public void findByIdTest() {
        ICollocazioneDAO collocazioneDAO = CollocazioneDAO.getInstance();
        Collocazione collocazione = collocazioneDAO.findById(2);
        Assert.assertEquals(1, collocazione.getCorsia());
    }
    @Test
    public void findAllByMagazzinoTest() {
        ICollocazioneDAO collocazioneDAO = CollocazioneDAO.getInstance();
        ArrayList<Collocazione> collocazioni = collocazioneDAO.findAllByMagazzino(1);
        Assert.assertEquals(2, collocazioni.size());
    }

    @Test
    public void findAllByProdottoTest() {
        ICollocazioneDAO collocazioneDAO = CollocazioneDAO.getInstance();
        ArrayList<Collocazione> collocazioni = collocazioneDAO.findAllByProdotto(1);
        Assert.assertEquals(2, collocazioni.size());
    }

    @Test
    public void updateTest() {
        ICollocazioneDAO collocazioneDAO = CollocazioneDAO.getInstance();
        Collocazione collocazione = collocazioneDAO.findById(2);
        collocazione.setCorsia(4);
        collocazione.setScaffale(3);
        collocazione.setMagazzino(MagazzinoDAO.getInstance().findById(1));
        collocazioneDAO.update(collocazione);
        Assert.assertEquals(4, collocazione.getCorsia());
        Assert.assertEquals(3, collocazione.getScaffale());

    }
}