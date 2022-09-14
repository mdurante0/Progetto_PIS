package Model;

import Model.composite.Prodotto;

import java.util.Date;
import java.util.List;

public class Prenotazione {

    private int idPrenotazione;
    private List<Prodotto> prodotti;
    private Date dataPrenotazione;

    public Prenotazione(){}

    public Prenotazione(List<Prodotto> prodotti, Date dataPrenotazione) {
        this.prodotti = prodotti;
        this.dataPrenotazione = dataPrenotazione;
    }

    public int getIdPrenotazione() {
        return idPrenotazione;
    }

    public void setIdPrenotazione(int idPrenotazione) {
        this.idPrenotazione = idPrenotazione;
    }

    public List<Prodotto> getProdotti() {
        return prodotti;
    }

    public void setProdotti(List<Prodotto> prodotti) {
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
