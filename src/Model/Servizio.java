package Model;

import Business.AbstractFactory.ICategoria;

import java.util.List;

public class Servizio extends Articolo {
    public Servizio(Float prezzo, List<Feedback> commenti, String nome, String descrizione, ICategoria categoria) {
        super(prezzo, commenti, nome, descrizione, categoria);
    }
    public Servizio(){}
    public String toString(){return null;}
}
