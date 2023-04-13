package Model;

public class PuntoVendita {

    private int idPuntoVendita;
    private int idManager;
    private int idMagazzino;
    private String citta;
    private String indirizzo;
    private String telefono;
    private String nome;

    public PuntoVendita(){}

    public PuntoVendita(String citta, String indirizzo, String telefono, String nome, int idMagazzino, int idManager) {
        this.citta = citta;
        this.indirizzo = indirizzo;
        this.telefono = telefono;
        this.nome = nome;
        this.idMagazzino = idMagazzino;
        this.idManager = idManager;
    }

    public int getIdPuntoVendita() {
        return idPuntoVendita;
    }

    public void setIdPuntoVendita(int idPuntoVendita) {
        this.idPuntoVendita = idPuntoVendita;
    }

    public int getIdManager() {
        return idManager;
    }

    public void setIdManager(int idManager) {
        this.idManager = idManager;
    }

    public int getIdMagazzino() {
        return idMagazzino;
    }

    public void setIdMagazzino(int idMagazzino) {
        this.idMagazzino = idMagazzino;
    }

    public String getCitta() {
        return citta;
    }


    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    @Override
    public String toString() {
        return "PuntoVendita{" +
                "citta='" + citta + '\'' +
                ", indirizzo='" + indirizzo + '\'' +
                ", telefono=" + telefono +
                '}';
    }


}
