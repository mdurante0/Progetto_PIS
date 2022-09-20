package Model.composite;

import Business.AbstractFactory.ICategoria;
import Model.*;

import java.util.List;

public class Prodotto extends Articolo implements IProdotto {

    private Collocazione collocazione;

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

    private Produttore produttore;
    private int quantita;

    /*viene avvalorata in maniera diversa a seconda se prendiamo l'immagine dal db
      (campo BLOB), oppure da un path locale, oppure dal Cloud (es. Amazon S3)
     */

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

    public String getNome() {return super.getNome(); }

    public void setNome(String nome) { super.setNome(nome); }


    @Override
    public String toString() {
        return "Prodotto{" +
                ", nome=" + super.getNome() +
                ", prezzo=" + super.getPrezzo() +
                ", categoria=" + super.getCategoria().getNome() +
                ", produttore=" + produttore.getNome() +
                '}';
    }
}
