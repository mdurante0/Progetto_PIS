package Model;

import Model.composite.IProdotto;

import java.util.HashMap;
import java.util.Map;

public class Magazzino {
    private int idMagazzino;
    private int quantitaCorsie;
    private int quantitaScaffali;
    private Map<IProdotto,Integer> prodotti = new HashMap<>();

    public Magazzino(){}
    public Magazzino(int quantitaCorsie, int quantitaScaffali, Map<IProdotto, Integer> prodotti) {
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

    public void add(IProdotto prodotto, int quantita){
        prodotti.put(prodotto,quantita);
    }
    public void remove(IProdotto prodotto, int quantita){
        prodotti.remove(prodotto, quantita);
    }
    public void clear(){
        prodotti.clear();
    }
    public String toString(){return null;}

}
