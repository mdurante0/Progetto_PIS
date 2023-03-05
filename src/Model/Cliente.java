package Model;

import Business.FactoryMethod.NotificationFactory;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Utente {

    private List<ListaAcquisto> listeAcquisto;
    private List<Prenotazione> prenotazioni;
    private PuntoVendita puntoVenditaDiRegistrazione;
    private int idPuntoVendita;
    private NotificationFactory.TipoNotifica canalePreferito;
    private boolean abilitazione;
    private int eta;
    private String residenza;
    private String professione;
    private String telefono;

    public Cliente() {
        this.listeAcquisto = new ArrayList<>();
        this.prenotazioni = new ArrayList<>();
    }

    public Cliente(List<ListaAcquisto> listeAcquisto, List<Prenotazione> prenotazioni, PuntoVendita puntoVenditaDiRegistrazione,
                   NotificationFactory.TipoNotifica canalePreferito, boolean abilitazione, int eta, String residenza, String professione, String telefono) {

        this.listeAcquisto = listeAcquisto;
        this.prenotazioni = prenotazioni;
        this.puntoVenditaDiRegistrazione = puntoVenditaDiRegistrazione;
        this.canalePreferito = canalePreferito;
        this.abilitazione = abilitazione;
        this.eta = eta;
        this.residenza = residenza;
        this.professione = professione;
        this.telefono = telefono;
    }

    public Cliente(String name, String surname, String username, String pwd, String email, String tipo, List<ListaAcquisto> listeAcquisto, List<Prenotazione> prenotazioni,
                   PuntoVendita puntoVenditaDiRegistrazione, NotificationFactory.TipoNotifica canalePreferito, boolean abilitazione, int eta, String residenza,
                   String professione, String telefono) {

        super(name, surname, username, pwd, email, tipo);
        this.listeAcquisto = listeAcquisto;
        this.prenotazioni = prenotazioni;
        this.puntoVenditaDiRegistrazione = puntoVenditaDiRegistrazione;
        this.canalePreferito = canalePreferito;
        this.abilitazione = abilitazione;
        this.eta = eta;
        this.residenza = residenza;
        this.professione = professione;
        this.telefono = telefono;
    }
    public Cliente(String name, String surname, String username, String pwd, String email, String tipo, int puntoVenditaDiRegistrazione, NotificationFactory.TipoNotifica canalePreferito, boolean abilitazione, int eta, String residenza,
                   String professione, String telefono) {

        super(name, surname, username, pwd, email, tipo);
        idPuntoVendita = puntoVenditaDiRegistrazione;
        this.canalePreferito = canalePreferito;
        this.abilitazione = abilitazione;
        this.eta = eta;
        this.residenza = residenza;
        this.professione = professione;
        this.telefono = telefono;
    }

    public int getEta() {
        return eta;
    }

    public void setEta(int eta) {
        this.eta = eta;
    }

    public String getResidenza() {
        return residenza;
    }

    public void setResidenza(String residenza) {
        this.residenza = residenza;
    }

    public String getProfessione() {
        return professione;
    }

    public void setProfessione(String professione) {
        this.professione = professione;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public boolean isAbilitazione() {
        return abilitazione;
    }

    public int getAbilitazione() {
        if(this.abilitazione)
            return 1;
        else
            return 0;
    }

    public void setAbilitazione(boolean abilitazione) {
        this.abilitazione = abilitazione;
    }

    public List<ListaAcquisto> getListeAcquisto() {
        return listeAcquisto;
    }

    public void setListeAcquisto(List<ListaAcquisto> listeAcquisto) {
        this.listeAcquisto = listeAcquisto;
    }

    public List<Prenotazione> getPrenotazioni() {
        return prenotazioni;
    }

    public void setPrenotazioni(List<Prenotazione> prenotazioni) {
        this.prenotazioni = prenotazioni;
    }

    public PuntoVendita getPuntoVenditaDiRegistrazione() {
        return puntoVenditaDiRegistrazione;
    }


    public void setPuntoVenditaDiRegistrazione(PuntoVendita puntoVenditaDiRegistrazione) {
        this.puntoVenditaDiRegistrazione = puntoVenditaDiRegistrazione;
    }

    public int getIdPuntoVendita() {
        return idPuntoVendita;
    }

    public void setIdPuntoVendita(int idPuntoVendita) {
        this.idPuntoVendita = idPuntoVendita;
    }

    public NotificationFactory.TipoNotifica getCanalePreferito() {
        return canalePreferito;
    }

    public void setCanalePreferito(NotificationFactory.TipoNotifica canalePreferito) {
        this.canalePreferito = canalePreferito;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "nome="+super.getName()+
                ", cognome=" + super.getSurname() +
                ", username="+super.getUsername()+
                ", email=" + super.getEmail() +
                ", puntoVenditaDiRegistrazione=" + puntoVenditaDiRegistrazione +
                ", abilitazione=" + abilitazione +
                '}';
    }
}
