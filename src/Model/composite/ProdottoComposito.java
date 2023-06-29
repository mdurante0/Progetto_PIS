package Model.composite;

import Business.AbstractFactory.ICategoria;
import Model.*;

import java.util.ArrayList;
import java.util.List;

public class ProdottoComposito extends Articolo implements IProdotto {

    private ArrayList<IProdotto> sottoprodotti = new ArrayList<>();
    private Magazzino magazzino;

    private Produttore produttore;
    private Collocazione collocazione;

    public ProdottoComposito() {
        super();
        super.setCategoria(new CategoriaProdotto());
        this.collocazione = new Collocazione();
        this.produttore = new Produttore();
        this.magazzino = new Magazzino();
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
    public Magazzino getMagazzino() {
        return magazzino;
    }
    public void setMagazzino(Magazzino magazzino) {
        this.magazzino = magazzino;
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
    public void setImmagini(List<Immagine> listaImmagini) {
        super.setImmagini(listaImmagini);
    }
    @Override
    public List<Immagine> getImmagini(){
        return super.getImmagini();
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
