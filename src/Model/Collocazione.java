package Model;

public class Collocazione {

    private int idCollocazione;
    private int corsia;
    private int scaffale;
    private int idProdotto;
    private int idMagazzino;

    public Collocazione(int corsia, int scaffale) {
        this.corsia = corsia;
        this.scaffale = scaffale;
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

    public int getIdMagazzino() {
        return idMagazzino;
    }

    public void setIdMagazzino(int idMagazzino) {
        this.idMagazzino = idMagazzino;
    }

    @Override
    public String toString() {
        return "Collocazione{" +
                "corsia=" + corsia +
                ", scaffale=" + scaffale +
                '}';
    }
}
