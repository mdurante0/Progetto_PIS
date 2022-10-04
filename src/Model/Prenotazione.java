package Model;

import java.util.*;

public class Prenotazione {

    private int idPrenotazione;
    private List<Model.composite.Prodotto> prodotti;
    private Date dataPrenotazione;
    private int idUtente;
    private int idProdotto;
    private int quantita;

    public Prenotazione(){
        this.prodotti = new ArrayList<>();
    }

    public Prenotazione(List<Model.composite.Prodotto> prodotti, Date dataPrenotazione) {
        this.prodotti = prodotti;
        this.dataPrenotazione = dataPrenotazione;
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

    public int getIdProdotto() {
        return idProdotto;
    }

    public void setIdProdotto(int idProdotto) {
        this.idProdotto = idProdotto;
    }

    @Override
    public String toString() {
        return "Prenotazione{" +
                "prodotti=" + prodotti +
                ", dataPrenotazione=" + dataPrenotazione +
                '}';
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }
}
