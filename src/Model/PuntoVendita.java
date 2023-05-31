package Model;

public class PuntoVendita {

    private int idPuntoVendita;
    private Manager manager = new Manager();
    private int idMagazzino;
    private String citta;
    private String indirizzo;
    private String telefono;
    private String nome;

    public PuntoVendita(){
    }

    public PuntoVendita(String citta, String indirizzo, String telefono, String nome, int idMagazzino, Manager manager) {
        this.citta = citta;
        this.indirizzo = indirizzo;
        this.telefono = telefono;
        this.nome = nome;
        this.idMagazzino = idMagazzino;
        this.manager = manager;
    }

    public PuntoVendita(String citta, String indirizzo, String telefono, String nome, int idMagazzino) {
        this.citta = citta;
        this.indirizzo = indirizzo;
        this.telefono = telefono;
        this.nome = nome;
        this.idMagazzino = idMagazzino;
    }

    public int getIdPuntoVendita() {
        return idPuntoVendita;
    }

    public void setIdPuntoVendita(int idPuntoVendita) {
        this.idPuntoVendita = idPuntoVendita;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
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
