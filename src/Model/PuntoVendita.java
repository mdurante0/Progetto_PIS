package Model;

public class PuntoVendita {

    private int idPuntoVendita;
    private Manager manager = new Manager();
    private Magazzino magazzino;
    private String citta;
    private String indirizzo;
    private String telefono;
    private String nome;

    public PuntoVendita(){
    }

    public PuntoVendita(String citta, String indirizzo, String telefono, String nome, Magazzino magazzino, Manager manager) {
        this.citta = citta;
        this.indirizzo = indirizzo;
        this.telefono = telefono;
        this.nome = nome;
        this.magazzino = magazzino;
        this.manager = manager;
    }

    public PuntoVendita(String citta, String indirizzo, String telefono, String nome, Magazzino magazzino) {
        this.citta = citta;
        this.indirizzo = indirizzo;
        this.telefono = telefono;
        this.nome = nome;
        this.magazzino = magazzino;
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

    public Magazzino getMagazzino() {
        return magazzino;
    }

    public void setMagazzino(Magazzino magazzino) {
        this.magazzino = magazzino;
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
