package Model;

import Business.FactoryMethod.NotificationFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Cliente extends Utente {

    private List<ListaAcquisto> listeAcquisto;
    private List<Prodotto> prenotazioni;
    private PuntoVendita puntoVenditaDiRegistrazione;
    private int idPuntoVendita;
    private NotificationFactory.TipoNotifica canalePreferito;
    private Date registrazione;
    private boolean abilitazione;

    public Cliente() {
        this.listeAcquisto = new ArrayList<>();
        this.prenotazioni = new ArrayList<>();
    }

    public Cliente(List<ListaAcquisto> listeAcquisto, List<Prodotto> prenotazioni, PuntoVendita puntoVenditaDiRegistrazione, NotificationFactory.TipoNotifica canalePreferito, Date registrazione, boolean abilitazione) {
        this.listeAcquisto = listeAcquisto;
        this.prenotazioni = prenotazioni;
        this.puntoVenditaDiRegistrazione = puntoVenditaDiRegistrazione;
        this.canalePreferito = canalePreferito;
        this.registrazione = registrazione;
        this.abilitazione = abilitazione;
    }

    public Cliente(String name, String surname, String username, String pwd, String email, String tipo, List<ListaAcquisto> listeAcquisto, List<Prodotto> prenotazioni, PuntoVendita puntoVenditaDiRegistrazione, NotificationFactory.TipoNotifica canalePreferito, Date registrazione, boolean abilitazione) {
        super(name, surname, username, pwd, email, tipo);
        this.listeAcquisto = listeAcquisto;
        this.prenotazioni = prenotazioni;
        this.puntoVenditaDiRegistrazione = puntoVenditaDiRegistrazione;
        this.canalePreferito = canalePreferito;
        this.registrazione = registrazione;
        this.abilitazione = abilitazione;
    }

    public Date getRegistrazione() {
        return registrazione;
    }

    public void setRegistrazione(Date registrazione) {
        this.registrazione = registrazione;
    }

    public boolean isAbilitazione() {
        return abilitazione;
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

    public List<Prodotto> getPrenotazioni() {
        return prenotazioni;
    }

    public void setPrenotazioni(List<Prodotto> prenotazioni) {
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
                ", registrazione=" + registrazione +
                ", abilitazione=" + abilitazione +
                '}';
    }
}
