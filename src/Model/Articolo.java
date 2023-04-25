package Model;

import Business.AbstractFactory.IArticolo;
import Business.AbstractFactory.ICategoria;

import java.util.ArrayList;
import java.util.List;

public class Articolo implements IArticolo {

    private int idArticolo;
    private Float prezzo;
    private int quantita;
    private String name;
    private String descrizione;
    private List<Feedback> commenti;
    private ICategoria categoria;
    private List<Immagine> immagini;

    public Articolo(Float prezzo, List<Feedback> commenti, String name, String descrizione, ICategoria categoria, List<Immagine> immagini, int quantita) {
        this.prezzo = prezzo;
        this.commenti = commenti;
        this.name = name;
        this.descrizione = descrizione;
        this.categoria = categoria;
        this.immagini = immagini;
        this.quantita = quantita;
    }

    public Articolo() {
        this.commenti = new ArrayList<>();
        this.immagini = new ArrayList<>();
    }

    public Articolo(String nome, String descrizione, Float prezzo, int quantita){
        this.name = nome;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
        this.quantita = quantita;
        this.commenti = new ArrayList<>();
        this.immagini = new ArrayList<>();
    }

    public Articolo(String name, String descrizione, ICategoria categoria) {
        this.name = name;
        this.descrizione = descrizione;
        this.categoria = categoria;
        this.commenti = new ArrayList<>();
        this.immagini = new ArrayList<>();
    }


    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public void addImage(Immagine immagine){
        this.immagini.add(immagine);
    }
    public void addAllImage(List<Immagine> immagini){
        this.immagini.addAll(immagini);
    }
    public void addFeedback(Feedback commento){
        this.commenti.add(commento);
    }
    public void addAllFeedback(List<Feedback> commenti){
        this.commenti.addAll(commenti);
    }

    @Override
    public String toString() {
        return "Articolo{" +
                ", prezzo=" + prezzo +
                ", nome='" + name + '\'' +
                ", descrizione='" + descrizione + '\'' +
                ", categoria=" + categoria.getNome() +
                '}';
    }



}
