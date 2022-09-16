package Model;

import Model.composite.IProdotto;

import java.util.ArrayList;
import java.util.List;

public class Magazzino {

    private int idMagazzino;
    private int quantitaCorsie;
    private int quantitaScaffali;
    private List<IProdotto> prodotti;

    public Magazzino(){
        this.prodotti = new ArrayList<>();
    }
    public Magazzino(int quantitaCorsie, int quantitaScaffali, List<IProdotto> prodotti) {
        this.quantitaCorsie = quantitaCorsie;
        this.quantitaScaffali = quantitaScaffali;
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
