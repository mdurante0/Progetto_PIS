package Model.composite;

import Model.Articolo;
import Model.Collocazione;

import java.util.ArrayList;
import java.util.List;

public class ProdottoComposito extends Articolo implements IProdotto {

    private Collocazione collocazione;
    private final List<IProdotto> sottoprodotti = new ArrayList<>();

    public void add(IProdotto prodotto) {
        // if ...
        sottoprodotti.add(prodotto);
    }

    public void add(List<IProdotto> prodotti) {
        sottoprodotti.addAll(prodotti);
    }

    public String getName() {
        return super.getName();
    }

    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public Float getPrezzo() {
        Float p = 0F;
        for(IProdotto prodotto : sottoprodotti)
            p+=prodotto.getPrezzo();
        return p;
    }

    @Override
    public String toString() {
        return "ProdottoComposito{" +
                "nome=" + super.getName() +
                ", sottoprodotti=" + sottoprodotti +
                '}';
    }
}
