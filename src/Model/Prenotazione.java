package Model;

import Model.composite.IProdotto;
import Model.composite.Prodotto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Prenotazione {

    private int idPrenotazione;
    private List<IProdotto> prodotti = new ArrayList<>();
    private Date dataPrenotazione = new Date();
    private int idUtente;

    public Prenotazione(){
    }

    public Prenotazione(List<IProdotto> prodotti, Date dataPrenotazione, int idUtente) {
        this.prodotti = prodotti;
        this.dataPrenotazione = dataPrenotazione;
        this.idUtente = idUtente;
    }

    public Prenotazione(IProdotto prodotto, Date dataPrenotazione, int idUtente) {
        this.prodotti.add(prodotto);
        this.dataPrenotazione = dataPrenotazione;
        this.idUtente = idUtente;
    }

    public int getIdPrenotazione() {
        return idPrenotazione;
    }

    public void setIdPrenotazione(int idPrenotazione) {
        this.idPrenotazione = idPrenotazione;
    }

    public List<Model.composite.IProdotto> getProdotti() {
        return prodotti;
    }

    public void setProdotti(List<Model.composite.IProdotto> prodotti) {
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

    public void add(IProdotto prodotto) {
        prodotti.add(prodotto);
    }

    public void remove(Prodotto prodotto){
        prodotti.remove(prodotto);
    }

    public void clear(){
        prodotti.clear();
    }
    public void setQuantita(int [] quantita){
        for (int i = 0; i < prodotti.size(); i++){
            prodotti.get(i).setQuantita(quantita[i]);
        }

    }

    @Override
    public String toString() {
        return "Prenotazione{" +
                "prodotti=" + prodotti +
                ", dataPrenotazione=" + dataPrenotazione +
                '}';
    }
}
