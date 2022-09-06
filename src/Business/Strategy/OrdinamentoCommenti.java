package Business.Strategy;

import Model.Feedback;

import java.util.List;

public class OrdinamentoCommenti {

    private List<Feedback> listaCommenti;
    private IOrdinamentoCommentoStrategy ordinamentoCommentoStrategy;

    public OrdinamentoCommenti(List<Feedback> listaCommenti) {
        this.listaCommenti = listaCommenti;
    }

    public void setOrdinamentoCommentoStrategy(IOrdinamentoCommentoStrategy ordinamentoCommentoStrategy) {
        this.ordinamentoCommentoStrategy = ordinamentoCommentoStrategy;
    }

    public void ordina() {
        ordinamentoCommentoStrategy.ordina(listaCommenti);
    }
}
