package Business.Strategy;

import Model.Feedback;

import java.util.Comparator;
import java.util.List;

public class CommentiMiglioriStrategy implements IOrdinamentoCommentoStrategy{

    @Override
    public void ordina(List<Feedback> listaCommenti) {

        listaCommenti.sort(new Comparator<Feedback>() {
            @Override
            public int compare(Feedback o1, Feedback o2) {

                // restituisce 0 se o1.punteggio = o2.punteggio
                // restituisce n>0 se o1.punteggio < o2.punteggio
                // restituisce n<0 se o1.punteggio > o2.punteggio

                if(o1.getGradimento() == o2.getGradimento()) return 0;

                int punteggio1=0, punteggio2=0;

                switch(o1.getGradimento()) {
                    case SCARSO: punteggio1 = 1; break;
                    case MEDIOCRE: punteggio1 = 2; break;
                    case BUONO: punteggio1 = 3; break;
                    case OTTIMO: punteggio1 = 4; break;
                    case ECCELLENTE: punteggio1 = 5;
                }

                switch(o2.getGradimento()) {
                    case SCARSO: punteggio2 = 1; break;
                    case MEDIOCRE: punteggio2 = 2; break;
                    case BUONO: punteggio2 = 3; break;
                    case OTTIMO: punteggio2 = 4; break;
                    case ECCELLENTE: punteggio2 = 5;
                }

                return punteggio2 - punteggio1;

            }
        });
    }
}
