package Model.composite;

import Business.AbstractFactory.ICategoria;
import Model.*;

import java.util.List;

public class Prodotto extends Articolo implements IProdotto {

    private Collocazione collocazione;
    private Produttore produttore;
    private int idProduttore;



    private int quantita;
    private int idMagazzino;
    public Collocazione getCollocazione() {
        return collocazione;
    }

    public void setCollocazione(Collocazione collocazione) {
        this.collocazione = collocazione;
    }

    public Produttore getProduttore() {
        return produttore;
    }

    public void setProduttore(Produttore produttore) {
        this.produttore = produttore;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public int getIdMagazzino() {
        return idMagazzino;
    }

    public void setIdMagazzino(int idMagazzino) {
        this.idMagazzino = idMagazzino;
    }

    public int getIdProduttore() {
        return idProduttore;
    }

    public void setIdProduttore(int idProduttore) {
        this.idProduttore = idProduttore;
    }

    public Prodotto(){
        super();
    }
    public Prodotto(Float prezzo, List<Feedback> commenti, String nome, String descrizione, ICategoria categoria, Collocazione collocazione, Produttore produttore, int quantita, List<Immagine> immagini) {
        super(prezzo, commenti, nome, descrizione, categoria, immagini);
        this.collocazione = collocazione;
        this.produttore = produttore;
        this.quantita = quantita;
    }

    @Override
    public Float getPrezzo() {
        return super.getPrezzo();
    }
    @Override
    public String getName() {return super.getName(); }

    public void setName(String name) { super.setName(name); }

    @Override
    public String toString() {
        return "Prenotazione{" +
                ", nome=" + super.getName() +
                ", prezzo=" + super.getPrezzo() +
                ", categoria=" + super.getCategoria().getNome() +
                ", produttore=" + produttore.getNome() +
                '}';
    }
}
