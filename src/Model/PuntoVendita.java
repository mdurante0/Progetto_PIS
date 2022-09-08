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
    public String toString(){return null;}
}
