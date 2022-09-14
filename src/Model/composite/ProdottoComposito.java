package Model.composite;

import Model.Articolo;
import Model.Collocazione;

import java.util.ArrayList;
import java.util.Iterator;
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

    public String getNome() {
        return super.getNome();
    }

    public void setNome(String nome) {
        super.setNome(nome);
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
                "nome=" + super.getNome() +
                ", sottoprodotti=" + sottoprodotti +
                '}';
    }
}
