package Model;

import Model.composite.IProdotto;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ListaAcquisto {

    private int idLista;
    private boolean pagata;
    private String nome;
    private Map<IProdotto,Integer> prodotti;
    private Date dataCreazione;

    public ListaAcquisto(){
        this.prodotti = new HashMap<>();
    }
    public ListaAcquisto(String nome, Map<IProdotto,Integer> prodotti, Date dataCreazione) {
        this.nome = nome;
        this.prodotti = prodotti;
        this.dataCreazione = dataCreazione;
    }

    public int getIdLista() {
        return idLista;
    }

    public void setIdLista(int idLista) {
        this.idLista = idLista;
    }

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

    public Map<IProdotto,Integer> getProdotti() {
        return prodotti;
    }

    public void setProdotti(Map<IProdotto,Integer> prodotti) {
        this.prodotti = prodotti;
    }

    public Date getDataCreazione() {
        return dataCreazione;
    }

    public void setDataCreazione(Date dataCreazione) {
        this.dataCreazione = dataCreazione;
    }

    @Override
    public String toString() {
        return "ListaAcquisto{" +
                "pagata=" + pagata +
                ", nome='" + nome + '\'' +
                ", prodotti=" + prodotti +
                ", dataCreazione=" + dataCreazione +
                '}';
    }
}
