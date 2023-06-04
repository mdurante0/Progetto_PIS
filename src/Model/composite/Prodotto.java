package Model.composite;

import Business.AbstractFactory.ICategoria;
import Model.*;

import java.util.List;

public class Prodotto extends Articolo implements IProdotto {

    private Collocazione collocazione;
    private Produttore produttore;
    private Magazzino magazzino;

    public Prodotto(Float prezzo, List<Feedback> commenti, String nome, String descrizione, ICategoria categoria, Collocazione collocazione, Produttore produttore, Magazzino magazzino, List<Immagine> immagini, int quantita) {
        super(prezzo, commenti, nome, descrizione, categoria, immagini, quantita);
        this.collocazione = collocazione;
        this.produttore = produttore;
        this.magazzino = magazzino;
    }

    public Prodotto(String nome,String descrizione, Float prezzo, Produttore produttore, CategoriaProdotto categoriaProdotto, int quantita){
        super(nome, descrizione, prezzo, quantita, categoriaProdotto);
        this.produttore = produttore;
    }

    public Prodotto(String nome,String descrizione, Float prezzo, int quantita){
        super(nome, descrizione, prezzo, quantita);

    }

    public Prodotto(){
        super();
        super.setCategoria(new CategoriaProdotto());
        this.collocazione = new Collocazione();
        this.produttore = new Produttore();
        this.magazzino = new Magazzino();
    }

    public Prodotto(String nome, String descrizione, Float prezzo, int quantita, ICategoria categoria) {
        super(nome,descrizione,prezzo,quantita,categoria);
    }

    @Override
    public Float getPrezzo() {
        return super.getPrezzo();
    }
    @Override
    public String getName() {return super.getName(); }
    public Collocazione getCollocazione() {
        return collocazione;
    }
    public void setCollocazione(Collocazione collocazione) {
        this.collocazione = collocazione;
    }

    @Override
    public Produttore getProduttore() {
        return produttore;
    }

    public void setProduttore(Produttore produttore) {
        this.produttore = produttore;
    }

    public Magazzino getMagazzino() {
        return this.magazzino;
    }

    public void setMagazzino(Magazzino magazzino) {
        this.magazzino = magazzino;
    }

    public void setName(String name) { super.setName(name); }
    public ICategoria getCategoria(){return super.getCategoria();}
    public void setCategoria(CategoriaProdotto categoria){super.setCategoria(categoria);}

    @Override
    public String toString() {
        return "Prodotto{" +
                ", nome=" + super.getName() +
                ", prezzo=" + super.getPrezzo() +
                ", categoria=" + super.getCategoria().getNome() +
                ", produttore=" + produttore.getNome() +
                '}';
    }



}
