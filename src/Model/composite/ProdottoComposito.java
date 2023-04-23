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

    private int idArticolo1;
    private int idArticolo2;
    private int quantita;

    public ProdottoComposito(Float prezzo, List<Feedback> commenti, String name, String descrizione, ICategoria categoria, List<Immagine> immagini, int quantita) {
        super(prezzo, commenti, name, descrizione, categoria, immagini, quantita);
    }

    public ProdottoComposito() {
    }

    public int getIdArticolo1() {
        return idArticolo1;
    }

    public void setIdArticolo1(int idArticolo1) {
        this.idArticolo1 = idArticolo1;
    }

    public int getIdArticolo2() {
        return idArticolo2;
    }

    public void setIdArticolo2(int idArticolo2) {
        this.idArticolo2 = idArticolo2;
    }

    @Override
    public int getQuantita() {
        return quantita;
    }

    @Override
    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public ProdottoComposito(int idArticolo1, int idArticolo2, int quantita) {
    this.idArticolo1 = idArticolo1;
    this.idArticolo2 = idArticolo2;
    this.quantita = quantita;

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
