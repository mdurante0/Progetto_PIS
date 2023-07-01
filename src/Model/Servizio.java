package Model;

import Business.AbstractFactory.ICategoria;

import java.util.List;

public class Servizio extends Articolo {
    private Fornitore fornitore = new Fornitore();

    public Servizio(Float prezzo, List<Feedback> commenti, String nome, String descrizione, CategoriaServizio categoria, Fornitore fornitore, List<Immagine> immagini) {
        super(prezzo, commenti, nome, descrizione, categoria, immagini);
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

    public Servizio(String nome, String descrizione, Float prezzo, Fornitore fornitore) {
        super(nome,descrizione,prezzo);
        this.fornitore = fornitore;
        super.setCategoria(new CategoriaServizio());
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
