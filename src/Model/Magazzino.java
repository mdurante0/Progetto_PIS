package Model;

import Model.composite.IProdotto;

import java.util.ArrayList;
import java.util.List;

public class Magazzino {

    private int idMagazzino;
    private int quantitaCorsie;
    private int quantitaScaffali;
    private String indirizzo;
    private List<IProdotto> prodotti = new ArrayList<>();
    private int idPuntoVendita;

    public Magazzino(){
    }

    public Magazzino(int quantitaCorsie, int quantitaScaffali, String indirizzo, List<IProdotto> prodotti) {
        this.quantitaCorsie = quantitaCorsie;
        this.quantitaScaffali = quantitaScaffali;
        this.indirizzo = indirizzo;
        this.prodotti = prodotti;
    }

    public Magazzino(int quantitaCorsie, int quantitaScaffali, String indirizzo) {
        this.quantitaCorsie = quantitaCorsie;
        this.quantitaScaffali = quantitaScaffali;
        this.indirizzo = indirizzo;
    }

    public void setIdMagazzino(int idMagazzino) {
        this.idMagazzino = idMagazzino;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public List<IProdotto> getProdotti() {
        return prodotti;
    }

    public void setProdotti(List<IProdotto> prodotti) {
        this.prodotti = prodotti;
    }

    public int getQuantitaCorsie() {
        return quantitaCorsie;
    }

    public void setQuantitaCorsie(int quantitaCorsie) {
        this.quantitaCorsie = quantitaCorsie;
    }

    public int getQuantitaScaffali() {
        return quantitaScaffali;
    }

    public void setQuantitaScaffali(int quantitaScaffali) {
        this.quantitaScaffali = quantitaScaffali;
    }

    public int getIdMagazzino() {
        return idMagazzino;
    }

    public int getIdPuntoVendita() {
        return idPuntoVendita;
    }

    public void setIdPuntoVendita(int idPuntoVendita) {
        this.idPuntoVendita = idPuntoVendita;
    }

    public void add(IProdotto prodotto){
        prodotti.add(prodotto);
    }
    public void remove(IProdotto prodotto){
        prodotti.remove(prodotto);
    }
    public void clear(){
        prodotti.clear();
    }

    @Override
    public String toString() {
        return "Magazzino{" +
                "quantitaCorsie=" + quantitaCorsie +
                ", quantitaScaffali=" + quantitaScaffali +
                ", prodotti=" + prodotti +
                '}';
    }
}
