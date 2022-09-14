package Model;

import Business.AbstractFactory.ICategoria;

import java.util.List;

public class Servizio extends Articolo {

    private Fornitore fornitore;

    public Servizio(Float prezzo, List<Feedback> commenti, String nome, String descrizione, ICategoria categoria, Fornitore fornitore) {
        super(prezzo, commenti, nome, descrizione, categoria);
        this.fornitore = fornitore;
    }
    public Servizio(){}

    @Override
    public String toString() {
        return "Prodotto{" +
                ", nome=" + super.getNome() +
                ", prezzo=" + super.getPrezzo() +
                ", descrizione=" + super.getDescrizione() +
                ", categoria=" + super.getCategoria().getNome() +
                ", fornitore=" + fornitore.getNome() +
                '}';
    }
}
