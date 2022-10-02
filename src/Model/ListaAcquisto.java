package Model;

import Model.composite.IProdotto;

import java.util.*;

public class ListaAcquisto {

    private int idLista;
    private boolean pagata;
    private String nome;
    private List<IProdotto> prodotti;
    private Date dataCreazione;
    private int idUtente;
    private float costoFinale;

    public ListaAcquisto(){
        this.prodotti = new ArrayList<>();
    }

    public ListaAcquisto(boolean pagata, String nome, List<IProdotto> prodotti, Date dataCreazione) {
        this.pagata = pagata;
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

    public int getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
    }

    public float getCostoFinale() {
        float c=0F;
        for (IProdotto p : prodotti)
            c+= p.getPrezzo();
        return c;
    }

    public void setCostoFinale(float costoFinale) {
        this.costoFinale = costoFinale;
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
