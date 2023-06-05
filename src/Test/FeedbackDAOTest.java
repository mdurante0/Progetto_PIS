package Test;

import Business.FactoryMethod.NotificationFactory;
import DAO.*;
import Model.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

public class FeedbackDAOTest {
    @Before
    public void setUp() {
        IFeedbackDAO feedbackDAO = FeedbackDAO.getInstance();
        IArticoloDAO articoloDAO = ArticoloDAO.getInstance();
        IClienteDAO clienteDAO = ClienteDAO.getInstance();
        IManagerDAO managerDAO = ManagerDAO.getInstance();
        IPuntoVenditaDAO puntoVenditaDAO = PuntoVenditaDAO.getInstance();
        IMagazzinoDAO magazzinoDAO = MagazzinoDAO.getInstance();

        articoloDAO.add(new Articolo(700.55F, null, "Armadio", "armadio in quercia pregiata", null, null, 8));

        managerDAO.add(new Manager("Antonio", "Bianchi", "ab77", "123", "ab77@gmail.com", "MN", (float) 7500.55, 3));

        Magazzino m = new Magazzino(4, 2, "via Paoli 23", new ArrayList<>());
        magazzinoDAO.add(m);

        puntoVenditaDAO.add(new PuntoVendita("Genova", "via palma", "1111111111", "aaa", magazzinoDAO.findByAddress("via Paoli 23").getIdMagazzino(), managerDAO.findById("ab77")));

        NotificationFactory.TipoNotifica canalePreferito = NotificationFactory.TipoNotifica.EMAIL;
        boolean abilitazione = true;
        int eta = 18;
        String residenza = "via mozart 21";
        String professione = "avvocato";
        String telefono = "0231561237";
        clienteDAO.add(new Cliente("Valentino", "Rossi", "vr46", "123", "valentino@gmail.com", "CL", puntoVenditaDAO.findByName("aaa").getIdPuntoVendita(), canalePreferito, abilitazione, eta, residenza, professione, telefono));
        Date date = Date.from(Instant.now());

        feedbackDAO.add(new Feedback(Feedback.Punteggio.BUONO, "articolo molto buono", false, false, "", date, articoloDAO.findByName("Armadio").getIdArticolo(), clienteDAO.findById("vr46").getIdUtente()));
    }

    @After
    public void tearDown() {
        IFeedbackDAO feedbackDAO = FeedbackDAO.getInstance();
        IArticoloDAO articoloDAO = ArticoloDAO.getInstance();
        IClienteDAO clienteDAO = ClienteDAO.getInstance();
        IManagerDAO managerDAO = ManagerDAO.getInstance();
        IPuntoVenditaDAO puntoVenditaDAO = PuntoVenditaDAO.getInstance();
        IMagazzinoDAO magazzinoDAO = MagazzinoDAO.getInstance();

        feedbackDAO.removeByArticolo(articoloDAO.findByName("Armadio").getIdArticolo());
        articoloDAO.removeById(articoloDAO.findByName("Armadio").getIdArticolo());
        clienteDAO.removeById("vr46");
        puntoVenditaDAO.removeById(puntoVenditaDAO.findByName("aaa").getIdPuntoVendita());
        managerDAO.removeById("ab77");
        magazzinoDAO.removeById(magazzinoDAO.findByAddress("via Paoli 23").getIdMagazzino());

    }

    @Test
    public void findAllTest() {
        IFeedbackDAO feedbackDAO = FeedbackDAO.getInstance();
        ArrayList<Feedback> feedbacks = feedbackDAO.findAll();
        Assert.assertEquals(1, feedbacks.size());
    }

    @Test
    public void findByIdTest() {
        IFeedbackDAO feedbackDAO = FeedbackDAO.getInstance();
        Feedback feedback = feedbackDAO.findById(1);
        Assert.assertEquals(1, feedback.getIdArticolo());
    }

    @Test
    public void findByArticoloTest() {
        IFeedbackDAO feedbackDAO = FeedbackDAO.getInstance();
        IArticoloDAO articoloDAO = ArticoloDAO.getInstance();
        ArrayList<Feedback> feedbacks = feedbackDAO.findByArticolo(articoloDAO.findByName("Armadio").getIdArticolo());
        Assert.assertEquals(1, feedbacks.size());
    }

    @Test
    public void findByUserTest() {
        IFeedbackDAO feedbackDAO = FeedbackDAO.getInstance();
        IClienteDAO clienteDAO = ClienteDAO.getInstance();
        ArrayList<Feedback> feedbacks = feedbackDAO.findByUser(clienteDAO.findById("vr46").getIdUtente());
        Assert.assertEquals(1, feedbacks.size());
    }

    @Test
    public void setRispostaTest(){
        IFeedbackDAO feedbackDAO = FeedbackDAO.getInstance();
        IArticoloDAO articoloDAO = ArticoloDAO.getInstance();
        ArrayList<Feedback> feedbacks = feedbackDAO.findByArticolo(articoloDAO.findByName("Armadio").getIdArticolo());
        feedbackDAO.setRisposta(feedbacks.get(0).getIdFeedback(),"La sua opinione è molto importante per noi!");
        feedbacks = feedbackDAO.findByArticolo(articoloDAO.findByName("Armadio").getIdArticolo());
        Assert.assertEquals("La sua opinione è molto importante per noi!", feedbacks.get(0).getRisposta());
    }

    @Test
    public void updateTest() {
        IFeedbackDAO feedbackDAO = FeedbackDAO.getInstance();
        Feedback feedback = feedbackDAO.findById(1);

        Date date = new Date();

            feedback.setGradimento(Feedback.Punteggio.ECCELLENTE);
            feedback.setCommento("molto soddisfatto");
            feedback.setRisposta("grazie");
            feedback.setLetto(true);
            feedback.setRisposto(true);
            feedback.setData(date);
            feedbackDAO.update(feedback);
            Assert.assertEquals("molto soddisfatto", feedback.getCommento());
            Assert.assertEquals("grazie", feedback.getRisposta());

    }
}