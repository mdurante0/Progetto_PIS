package Model.composite;

import Business.AbstractFactory.ICategoria;
import Model.Articolo;
import Model.CategoriaProdotto;
import Model.Collocazione;
import Model.Produttore;

import java.util.ArrayList;
import java.util.List;

public class ProdottoComposito extends Articolo implements IProdotto {

    private ArrayList<IProdotto> sottoprodotti = new ArrayList<>();
    private int idMagazzino;

    private Produttore produttore = new Produttore();
    private Collocazione collocazione;

    public ProdottoComposito() {
        super();
        super.setCategoria(new CategoriaProdotto());
    }

    public ProdottoComposito(String name, String descrizione, Produttore produttore, CategoriaProdotto categoriaProdotto) {
        super(name, descrizione, categoriaProdotto);
        this.produttore = produttore;
    }
    @Override
    public String getName() {
        return super.getName();
    }
    @Override
    public Produttore getProduttore() {
        return produttore;
    }
    public void setProduttore(Produttore produttore) {
        this.produttore = produttore;
    }
    public void add(IProdotto prodotto) {
        sottoprodotti.add(prodotto);
    }
    public void addAll(List<IProdotto> prodotti) {
        sottoprodotti.addAll(prodotti);
    }
    public void remove(IProdotto prodotto) {
        sottoprodotti.remove(prodotto);
    }
    public void removeAll(List<IProdotto> prodotti) {
        sottoprodotti.removeAll(prodotti);
    }
    @Override
    public void setName(String name) {
        super.setName(name);
    }
    public ArrayList<IProdotto> getSottoprodotti() {
        return sottoprodotti;
    }
    public void setSottoprodotti(ArrayList<IProdotto> sottoprodotti) {
        this.sottoprodotti = sottoprodotti;
    }
    public int getIdMagazzino() {
        return idMagazzino;
    }
    public void setIdMagazzino(int idMagazzino) {
        this.idMagazzino = idMagazzino;
    }
    public ICategoria getCategoria(){return super.getCategoria();}
    public void setCategoria(CategoriaProdotto categoria){super.setCategoria(categoria);}

    @Override
    public Collocazione getCollocazione() {
        return collocazione;
    }

    @Override
    public void setCollocazione(Collocazione collocazione) {
        this.collocazione = collocazione;
    }

    @Override
    public Float getPrezzo() {
        float p = 0F;
        for(IProdotto prodotto : sottoprodotti)
            p += (prodotto.getPrezzo() * prodotto.getQuantita());
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
