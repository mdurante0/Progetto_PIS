package Model;

import Model.composite.Prodotto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Prenotazione {

    private int idPrenotazione;
    private List<Model.composite.Prodotto> prodotti;
    private Date dataPrenotazione;
    private int idUtente;

    public Prenotazione(){
        this.prodotti = new ArrayList<>();
    }

    public Prenotazione(List<Model.composite.Prodotto> prodotti, Date dataPrenotazione, int idUtente) {
        this.prodotti = prodotti;
        this.dataPrenotazione = dataPrenotazione;
        this.idUtente = idUtente;
    }

    public int getIdPrenotazione() {
        return idPrenotazione;
    }

    public void setIdPrenotazione(int idPrenotazione) {
        this.idPrenotazione = idPrenotazione;
    }

    public List<Model.composite.Prodotto> getProdotti() {
        return prodotti;
    }

    public void setProdotti(List<Model.composite.Prodotto> prodotti) {
        this.prodotti = prodotti;
    }

    public Date getDataPrenotazione() {
        return dataPrenotazione;
    }

    public void setDataPrenotazione(Date dataPrenotazione) {
        this.dataPrenotazione = dataPrenotazione;
    }

    public int getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
    }

    public void add(Prodotto prodotto) {
        prodotti.add(prodotto);
    }

    public void remove(Prodotto prodotto){
        prodotti.remove(prodotto);
    }

    public void clear(){
        prodotti.clear();
    }

    @Override
    public String toString() {
        return "Prenotazione{" +
                "prodotti=" + prodotti +
                ", dataPrenotazione=" + dataPrenotazione +
                '}';
    }
}
