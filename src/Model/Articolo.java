package Model;

import Business.AbstractFactory.IArticolo;
import Business.AbstractFactory.ICategoria;

import java.util.ArrayList;
import java.util.List;

public class Articolo implements IArticolo {

    private int idArticolo;
    private Float prezzo;
    private List<Feedback> commenti;
    private String nome;
    private String descrizione;
    private ICategoria categoria;
    private List<Immagine> immagini;



    public Articolo(Float prezzo, List<Feedback> commenti, String nome, String descrizione, ICategoria categoria, List<Immagine> immagini) {
        this.prezzo = prezzo;
        this.commenti = commenti;
        this.nome = nome;
        this.descrizione = descrizione;
        this.categoria = categoria;
        this.immagini = immagini;
    }

    public Articolo() {
        this.commenti = new ArrayList<>();
        this.immagini = new ArrayList<>();
    }

    public Float getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(Float prezzo) {
        this.prezzo = prezzo;
    }

    public int getIdArticolo() {
        return idArticolo;
    }

    public void setIdArticolo(int idArticolo) {
        this.idArticolo = idArticolo;
    }

    public List<Feedback> getCommenti() {
        return commenti;
    }

    public void setCommenti(List<Feedback> commenti) {
        this.commenti = commenti;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public ICategoria getCategoria() {
        return categoria;
    }

    public void setCategoria(ICategoria categoria) {
        this.categoria = categoria;
    }

    public List<Immagine> getImmagini() {
        return immagini;
    }

    public void setImmagini(List<Immagine> immagini) {
        this.immagini = immagini;
    }
    @Override
    public String toString() {
        return "Articolo{" +
                ", prezzo=" + prezzo +
                ", nome='" + nome + '\'' +
                ", descrizione='" + descrizione + '\'' +
                ", categoria=" + categoria.getNome() +
                '}';
    }
}
