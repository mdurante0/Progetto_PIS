package Model.composite;

import Model.Articolo;
import Model.Collocazione;
import Model.Composite.IProdotto;
import Model.Produttore;

public class Prodotto extends Articolo implements IProdotto {

    private Collocazione collocazione;
    private Produttore produttore;
    private int quantita;

    /*viene avvalorata in maniera diversa a seconda se prendiamo l'immagine dal db
      (campo BLOB), oppure da un path locale, oppure dal Cloud (esempio Amazon ESSE3)
    */

    private byte[] immagine;

    @Override
    public Float getPrezzo() {
        return this.prezzo;
    }

    public String getNome() {return this.nome; }

    public void setNome(String nome) { this.nome = nome; }
}
