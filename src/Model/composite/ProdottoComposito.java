package Model.composite;

import Business.AbstractFactory.ICategoria;
import Model.Articolo;
import Model.Feedback;
import Model.Immagine;

import java.util.ArrayList;
import java.util.List;

public class ProdottoComposito extends Articolo implements IProdotto {

    private final List<Prodotto> sottoprodotti = new ArrayList<>();
    private int idMagazzino;

    public ProdottoComposito(Float prezzo, List<Feedback> commenti, String name, String descrizione, ICategoria categoria, List<Immagine> immagini) {
        super(prezzo, commenti, name, descrizione, categoria, immagini);
    }

    public ProdottoComposito() {
    }

    public void add(Prodotto prodotto) {
        sottoprodotti.add(prodotto);
    }

    public void add(List<Prodotto> prodotti) {
        sottoprodotti.addAll(prodotti);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    public void setName(String name) {
        super.setName(name);
    }

    public List<Prodotto> getSottoprodotti() {
        return sottoprodotti;
    }

    public int getIdMagazzino() {
        return idMagazzino;
    }

    public void setIdMagazzino(int idMagazzino) {
        this.idMagazzino = idMagazzino;
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
