package Model;

import Model.composite.Prodotto;

import java.util.Date;

public class Prenotazione {

    private Prodotto prodotto;
    private Date dataPrenotazione;

    public Prenotazione(){}
    public Prenotazione(Prodotto prodotto, Date dataPrenotazione) {
        this.prodotto = prodotto;
        this.dataPrenotazione = dataPrenotazione;
    }
    public String toString(){return null;}
}
