package Test;

import DAO.*;
import Model.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

public class FeedbackDAOTest {
    @Before
    public void setUp() {
        IFeedbackDAO feedbackDAO = FeedbackDAO.getInstance();
        IArticoloDAO articoloDAO = ArticoloDAO.getInstance();
        IClienteDAO clienteDAO = ClienteDAO.getInstance();
        IPuntoVenditaDAO puntoVenditaDAO = PuntoVenditaDAO.getInstance();

        articoloDAO.add(new Articolo(700.55F, null, "ArmadioTest", "armadio in quercia pregiata", null, null, 8));
        puntoVenditaDAO.add(new PuntoVendita("Genova", "via palma", "1111111111", "puntoVenditaTest", new Magazzino(), new Manager()));

        clienteDAO.add(new Cliente("Valentino", "Rossi", "test", "123", "valentino@gmail.com", "CL", puntoVenditaDAO.findByName("puntoVenditaTest"), null, true, 18, "via test", "test", "1234567890"));

        feedbackDAO.add(new Feedback(Feedback.Punteggio.BUONO, "articolo molto buono", false, false, "", new Date(), articoloDAO.findByName("ArmadioTest"), clienteDAO.findByUsername("test")));
    }

    @After
    public void tearDown() {
        IFeedbackDAO feedbackDAO = FeedbackDAO.getInstance();
        IArticoloDAO articoloDAO = ArticoloDAO.getInstance();
        IClienteDAO clienteDAO = ClienteDAO.getInstance();
        IPuntoVenditaDAO puntoVenditaDAO = PuntoVenditaDAO.getInstance();

        feedbackDAO.removeByArticolo(articoloDAO.findByName("ArmadioTest").getIdArticolo());
        articoloDAO.removeById(articoloDAO.findByName("ArmadioTest").getIdArticolo());
        clienteDAO.removeById("test");
        puntoVenditaDAO.removeById(puntoVenditaDAO.findByName("puntoVenditaTest").getIdPuntoVendita());
    }

    @Test
    public void findAllTest() {
        IFeedbackDAO feedbackDAO = FeedbackDAO.getInstance();
        ArrayList<Feedback> feedbacks = feedbackDAO.findAll();
        Assert.assertFalse(feedbacks.isEmpty());
    }

    @Test
    public void findByIdTest() {
        IFeedbackDAO feedbackDAO = FeedbackDAO.getInstance();
        IArticoloDAO articoloDAO = ArticoloDAO.getInstance();

        Feedback feedback = feedbackDAO.findByArticolo(articoloDAO.findByName("ArmadioTest").getIdArticolo()).get(0);
        feedback = feedbackDAO.findById(feedback.getIdFeedback());
        Assert.assertEquals("ArmadioTest", feedback.getArticolo().getName());
    }

    @Test
    public void findByArticoloTest() {
        IFeedbackDAO feedbackDAO = FeedbackDAO.getInstance();
        IArticoloDAO articoloDAO = ArticoloDAO.getInstance();
        ArrayList<Feedback> feedbacks = feedbackDAO.findByArticolo(articoloDAO.findByName("ArmadioTest").getIdArticolo());
        Assert.assertEquals(1, feedbacks.size());
    }

    @Test
    public void findByUserTest() {
        IFeedbackDAO feedbackDAO = FeedbackDAO.getInstance();
        IClienteDAO clienteDAO = ClienteDAO.getInstance();
        ArrayList<Feedback> feedbacks = feedbackDAO.findByUser(clienteDAO.findByUsername("test").getIdUtente());
        Assert.assertEquals(1, feedbacks.size());
    }

    @Test
    public void setRispostaTest(){
        IFeedbackDAO feedbackDAO = FeedbackDAO.getInstance();
        IArticoloDAO articoloDAO = ArticoloDAO.getInstance();

        ArrayList<Feedback> feedbacks = feedbackDAO.findByArticolo(articoloDAO.findByName("ArmadioTest").getIdArticolo());
        feedbackDAO.setRisposta(feedbacks.get(0).getIdFeedback(),"La sua opinione è molto importante per noi!");
        feedbacks = feedbackDAO.findByArticolo(articoloDAO.findByName("ArmadioTest").getIdArticolo());
        Assert.assertEquals("La sua opinione è molto importante per noi!", feedbacks.get(0).getRisposta());
    }

    @Test
    public void updateTest() {
        IFeedbackDAO feedbackDAO = FeedbackDAO.getInstance();
        IArticoloDAO articoloDAO = ArticoloDAO.getInstance();

        Articolo articolo = articoloDAO.findByName("ArmadioTest");
        Feedback feedback = feedbackDAO.findByArticolo(articolo.getIdArticolo()).get(0);

        feedback.setGradimento(Feedback.Punteggio.ECCELLENTE);
        feedback.setCommento("molto soddisfatto");
        feedback.setRisposta("grazie");
        feedback.setLetto(true);
        feedback.setRisposto(true);
        feedback.setData(new Date());
        feedbackDAO.update(feedback);

        feedback = feedbackDAO.findByArticolo(articolo.getIdArticolo()).get(0);
        Assert.assertEquals("molto soddisfatto", feedback.getCommento());
        Assert.assertEquals("grazie", feedback.getRisposta());

    }
}