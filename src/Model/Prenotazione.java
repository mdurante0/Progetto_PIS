package Model;

import Model.composite.Prodotto;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Prenotazione {

    private int idPrenotazione;
    private Map<Prodotto,Integer> prodotti;
    private Date dataPrenotazione;


    public Prenotazione(){
        this.prodotti = new HashMap<>();
    }

    public Prenotazione(Map<Prodotto,Integer> prodotti, Date dataPrenotazione) {
        this.prodotti = prodotti;
        this.dataPrenotazione = dataPrenotazione;
    }

    public int getIdPrenotazione() {
        return idPrenotazione;
    }

    public void setIdPrenotazione(int idPrenotazione) {
        this.idPrenotazione = idPrenotazione;
    }

    public Map<Prodotto,Integer> getProdotti() {
        return prodotti;
    }

    public void setProdotti(Map<Prodotto,Integer> prodotti) {
        this.prodotti = prodotti;
    }

    public Date getDataPrenotazione() {
        return dataPrenotazione;
    }

    public void setDataPrenotazione(Date dataPrenotazione) {
        this.dataPrenotazione = dataPrenotazione;
    }

    @Override
    public String toString() {
        return "Prenotazione{" +
                "prodotti=" + prodotti +
                ", dataPrenotazione=" + dataPrenotazione +
                '}';
    }
}
