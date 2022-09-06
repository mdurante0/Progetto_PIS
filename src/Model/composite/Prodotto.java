package Model.composite;

import Model.Articolo;
import Model.Collocazione;
import Model.Immagine;
import Model.Produttore;

import java.util.ArrayList;
import java.util.List;

public class Prodotto extends Articolo implements IProdotto {

    private Collocazione collocazione;
    private Produttore produttore;
    private int quantita;

    /*viene avvalorata in maniera diversa a seconda se prendiamo l'immagine dal db
      (campo BLOB), oppure da un path locale, oppure dal Cloud (es. Amazon S3)
     */
    private List<Immagine> immagini = new ArrayList<>();

    @Override
    public Float getPrezzo() {
        return this.prezzo;
    }

    public String getNome() {return this.nome; }

    public void setNome(String nome) { this.nome = nome; }

    public void add(Immagine immagine){
        this.immagini.add(immagine);
    }
    public void add(List<Immagine> immagini){
        this.immagini.addAll(immagini);
    }
}
