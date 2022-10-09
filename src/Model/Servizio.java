package Model;

import Business.AbstractFactory.ICategoria;

import java.util.List;

public class Servizio extends Articolo {

    private Fornitore fornitore;
    private int idFornitore;
    private int idCategoria;

    public Servizio(Float prezzo, List<Feedback> commenti, String nome, String descrizione, ICategoria categoria, Fornitore fornitore, List<Immagine> immagini, int quantita) {
        super(prezzo, commenti, nome, descrizione, categoria, immagini, quantita);
        this.fornitore = fornitore;
    }
    public Servizio(){
        super();
    }

    public Fornitore getFornitore() {
        return fornitore;
    }

    public int getIdFornitore() {
        return idFornitore;
    }

    public void setIdFornitore(int idFornitore) {
        this.idFornitore = idFornitore;
    }

    public void setFornitore(Fornitore fornitore) {
        this.fornitore = fornitore;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
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
