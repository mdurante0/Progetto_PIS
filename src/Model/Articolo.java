package Model;

import Business.AbstractFactory.IArticolo;

import java.util.List;

public class Articolo implements IArticolo {

    protected Float prezzo;
    private List<Feedback> commenti;
    protected String nome;

    public Float getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(Float prezzo) {
        this.prezzo = prezzo;
    }

    public int getIdArticolo() {
        return idArticolo;
    }

    public void setIdArticolo(int idArticolo) {
        this.idArticolo = idArticolo;
    }

    public List<Feedback> getCommenti() {
        return commenti;
    }

    public void setCommenti(List<Feedback> commenti) {
        this.commenti = commenti;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public ICategoria getCategoria() {
        return categoria;
    }

    public void setCategoria(ICategoria categoria) {
        this.categoria = categoria;
    }
}
