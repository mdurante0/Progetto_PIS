package Model;

public class Collocazione {

    private int corsia;
    private int scaffale;


    public Collocazione(int corsia, int scaffale) {
        this.corsia = corsia;
        this.scaffale = scaffale;
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
}
