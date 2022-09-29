package Model;

public class PuntoVendita {

    private int idPuntoVendita;
    private String citta;
    private String indirizzo;
    private int telefono;

    public PuntoVendita(){}

    public PuntoVendita(String citta, String indirizzo, int telefono) {
        this.citta = citta;
        this.indirizzo = indirizzo;
        this.telefono = telefono;
    }

    public int getIdPuntoVendita() {
        return idPuntoVendita;
    }

    public void setIdPuntoVendita(int idPuntoVendita) {
        this.idPuntoVendita = idPuntoVendita;
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

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
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
