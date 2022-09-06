package Business.Strategy;

import Model.Feedback;

import java.util.Comparator;
import java.util.List;

public class CommentiRecentiStrategy implements IOrdinamentoCommentoStrategy {

    @Override
    public void ordina(List<Feedback> listaCommenti) {

        listaCommenti.sort(new Comparator<Feedback>() {
            @Override
            public int compare(Feedback o1, Feedback o2) {

                if(o2 == null || o1 == null) return 0;

                return o2.getData().compareTo(o1.getData());
            }
        });

    }
}
