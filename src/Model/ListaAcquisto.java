package Model;

import Model.composite.IProdotto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ListaAcquisto {

    private int idLista;
    private boolean pagata;
    private String nome;
    private List<Articolo> articoli = new ArrayList<>();
    private Date dataCreazione = new Date();
    private Cliente cliente = new Cliente();
    private float costoFinale;

    public ListaAcquisto(){

    }

    public ListaAcquisto(Cliente cliente, boolean pagata, String nome, List<Articolo> articoli, Date dataCreazione) {
        this.cliente = cliente;
        this.pagata = pagata;
        this.nome = nome;
        this.articoli = articoli;
        this.dataCreazione = dataCreazione;
    }

    public ListaAcquisto(Cliente cliente, boolean pagata, String nome, Date dataCreazione) {
        this.cliente = cliente;
        this.pagata = pagata;
        this.nome = nome;
        this.dataCreazione = dataCreazione;
    }

    public ListaAcquisto(Cliente cliente, boolean pagata, String nome, ArrayList<Articolo> articoli) {
        this.cliente = cliente;
        this.pagata = pagata;
        this.nome = nome;
        this.articoli = articoli;
    }

    public boolean add(Articolo articolo) {
        return articoli.add(articolo);
    }

    public boolean remove(Articolo articolo) {
        return articoli.remove(articolo);
    }

    public void clear() {
        articoli.clear();
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
    public int getPagata() {
        if(this.pagata)
            return 1;
        else
            return 0;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Articolo> getArticoli() {
        return articoli;
    }

    public void setArticoli(List<Articolo> articoli) {
        this.articoli = articoli;
    }

    public Date getDataCreazione() {
        return dataCreazione;
    }

    public void setDataCreazione(Date dataCreazione) {
        this.dataCreazione = dataCreazione;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public float getCostoFinale() {
        this.costoFinale=0F;
        for (Articolo a : articoli) {
            if (a instanceof IProdotto)
                costoFinale += a.getPrezzo() * a.getQuantita();
            else costoFinale += a.getPrezzo();
        }
        return costoFinale;
    }

    public void setCostoFinale(float costoFinale) {
        this.costoFinale = costoFinale;
    }

   @Override
    public String toString() {
        return "ListaAcquisto{" +
                "pagata=" + pagata +
                ", nome='" + nome +
                ", prodotti=" + articoli +
                ", dataCreazione=" + dataCreazione +
                '}';
    }
}
