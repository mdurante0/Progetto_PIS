package Model;

import Business.AbstractFactory.ICategoria;

import java.util.List;

public class Servizio extends Articolo {
    private Fornitore fornitore;

    public Servizio(Float prezzo, List<Feedback> commenti, String nome, String descrizione, CategoriaServizio categoria, Fornitore fornitore, List<Immagine> immagini, int quantita) {
        super(prezzo, commenti, nome, descrizione, categoria, immagini, quantita);
        this.fornitore = fornitore;
    }
    public Servizio(){
        super();
        super.setCategoria(new CategoriaServizio());
        this.fornitore = new Fornitore();
    }

    public Servizio(String nome, String descrizione, Float prezzo, ICategoria categoria, Fornitore fornitore) {
        super(nome,descrizione,prezzo,categoria);
        this.fornitore = fornitore;
    }

    public Fornitore getFornitore() {
        return fornitore;
    }
    public void setFornitore(Fornitore fornitore) {
        this.fornitore = fornitore;
    }

    @Override
    public String toString() {
        return "Prenotazione{" +
                ", nome=" + super.getName() +
                ", prezzo=" + super.getPrezzo() +
                ", descrizione=" + super.getDescrizione() +
                ", categoria=" + super.getCategoria().getNome() +
                ", fornitore=" + fornitore.getNome() +
                '}';
    }
}
