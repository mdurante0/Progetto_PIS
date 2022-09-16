package Model;

import Model.composite.IProdotto;

import java.util.*;

public class ListaAcquisto {

    private int idLista;
    private boolean pagata;
    private String nome;
    private List<IProdotto> prodotti;
    private Date dataCreazione;

    public ListaAcquisto(){
        this.prodotti = new ArrayList<>();
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
