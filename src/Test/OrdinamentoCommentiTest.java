package Test;

import Business.Strategy.CommentiMiglioriStrategy;
import Business.Strategy.CommentiRecentiStrategy;
import Business.Strategy.IOrdinamentoCommentoStrategy;
import Business.Strategy.OrdinamentoCommenti;
import Model.Feedback;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrdinamentoCommentiTest {

    List<Feedback> commentiList;

    @Before
    public void setUp() throws Exception {

        //fixture
        commentiList = new ArrayList<Feedback>();

        Feedback c1=new Feedback();
        c1.setIdFeedback(1);
        c1.setGradimento(Feedback.Punteggio.MEDIOCRE);
        Date data1 = new SimpleDateFormat("yyyy-MM-dd").parse("2021-05-20");
        c1.setData(data1);

        Feedback c2=new Feedback();
        c2.setIdFeedback(2);
        c2.setGradimento(Feedback.Punteggio.SCARSO);
        Date data2 = new SimpleDateFormat("yyyy-MM-dd").parse("2021-05-27");
        c2.setData(data2);

        Feedback c3=new Feedback();
        c3.setIdFeedback(3);
        c3.setGradimento(Feedback.Punteggio.ECCELLENTE);
        Date data3 = new SimpleDateFormat("yyyy-MM-dd").parse("2021-05-22");
        c3.setData(data3);

        commentiList.add(c1);
        commentiList.add(c2);
        commentiList.add(c3);

    }

    @After
    public void tearDown() {
        commentiList = null;
    }

    @Test
    public void ordinaTest() {

        OrdinamentoCommenti ordinamentoCommenti = new OrdinamentoCommenti(commentiList);
        IOrdinamentoCommentoStrategy strategy = new CommentiRecentiStrategy();
        ordinamentoCommenti.setOrdinamentoCommentoStrategy(strategy);
        ordinamentoCommenti.ordina();

        //verifichiamo che ha ordinato come ci aspettavamo
        Assert.assertEquals(commentiList.get(0).getIdFeedback(), 2);
        Assert.assertEquals(commentiList.get(1).getIdFeedback(), 3);
        Assert.assertEquals(commentiList.get(2).getIdFeedback(), 1);

        strategy = new CommentiMiglioriStrategy();
        ordinamentoCommenti.setOrdinamentoCommentoStrategy(strategy);
        ordinamentoCommenti.ordina();
        Assert.assertEquals(commentiList.get(0).getIdFeedback(), 3);
        Assert.assertEquals(commentiList.get(1).getIdFeedback(), 1);
        Assert.assertEquals(commentiList.get(2).getIdFeedback(), 2);

    }
}
