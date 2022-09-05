package Model;

import Business.AbstractFactory.IArticolo;

import java.util.List;

public class Articolo implements IArticolo {

    protected Float prezzo;
    private List<Commento> commenti;
    protected String nome;

/*    public Float getPrezzo() {
        return prezzo;
    }*/

    public void setPrezzo(Float prezzo) {
        this.prezzo = prezzo;
    }


}
