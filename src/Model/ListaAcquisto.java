package Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ListaAcquisto {

    private int idLista;
    private boolean pagata;
    private String nome;
    private List<Articolo> articoli;
    private Date dataCreazione;
    private int idUtente;
    private float costoFinale;

    public ListaAcquisto(){
        this.articoli = new ArrayList<>();
    }

    public ListaAcquisto(boolean pagata, String nome, List<Articolo> articoli, Date dataCreazione) {
        this.pagata = pagata;
        this.nome = nome;
        this.articoli = articoli;
        this.dataCreazione = dataCreazione;
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

    public int getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
    }

    public float getCostoFinale() {
        float c=0F;
        for (Articolo a : articoli)
            c+= a.getPrezzo();
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
                ", prodotti=" + articoli +
                ", dataCreazione=" + dataCreazione +
                '}';
    }
}
