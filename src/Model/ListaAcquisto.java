package Model;

import Model.composite.IProdotto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ListaAcquisto {

    public boolean pagata;
    private String nome;
    private List<IProdotto> prodotti = new ArrayList<>();
    private Date dataCreazione;

    public boolean isPagata() {
        return pagata;
    }

    public void setPagata(boolean pagata) {
        this.pagata = pagata;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<IProdotto> getProdotti() {
        return prodotti;
    }

    public void setProdotti(List<IProdotto> prodotti) {
        this.prodotti = prodotti;
    }

    public Date getDataCreazione() {
        return dataCreazione;
    }

    public void setDataCreazione(Date dataCreazione) {
        this.dataCreazione = dataCreazione;
    }


}
