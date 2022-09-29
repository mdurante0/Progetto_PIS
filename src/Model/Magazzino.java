package Model;

import Model.composite.IProdotto;

import java.util.ArrayList;
import java.util.List;

public class Magazzino {

    private int idMagazzino;
    private int quantitaCorsie;
    private int quantitaScaffali;
    private List<IProdotto> prodotti;
    private int idPuntoVendita;

    public Magazzino(){
        this.prodotti = new ArrayList<>();
    }

    public Magazzino(int quantitaCorsie, int quantitaScaffali, List<IProdotto> prodotti) {
        this.quantitaCorsie = quantitaCorsie;
        this.quantitaScaffali = quantitaScaffali;
        this.prodotti = prodotti;
    }

    public void setIdMagazzino(int idMagazzino) {
        this.idMagazzino = idMagazzino;
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
