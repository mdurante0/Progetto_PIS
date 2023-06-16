package Model;

public class Collocazione {

    private int idCollocazione;
    private int corsia;
    private int scaffale;
    private int idProdotto;
    private Magazzino magazzino;

    public Collocazione(int corsia, int scaffale) {
        this.corsia = corsia;
        this.scaffale = scaffale;
    }
    public Collocazione(int corsia, int scaffale, Magazzino magazzino) {
        this.corsia = corsia;
        this.scaffale = scaffale;
        this.magazzino = magazzino;
    }
    public Collocazione(){}

    public int getIdCollocazione() {
        return idCollocazione;
    }

    public void setIdCollocazione(int idCollocazione) {
        this.idCollocazione = idCollocazione;
    }

    public int getCorsia() {
        return corsia;
    }

    public void setCorsia(int corsia) {
        this.corsia = corsia;
    }

    public int getScaffale() {
        return scaffale;
    }

    public void setScaffale(int scaffale) {
        this.scaffale = scaffale;
    }

    public int getIdProdotto() {
        return idProdotto;
    }

    public void setIdProdotto(int idProdotto) {
        this.idProdotto = idProdotto;
    }

    public Magazzino getMagazzino() {
        return magazzino;
    }

    public void setMagazzino(Magazzino magazzino) {
        this.magazzino = magazzino;
    }

    @Override
    public String toString() {
        return "Collocazione{" +
                "corsia=" + corsia +
                ", scaffale=" + scaffale +
                '}';
    }
}
