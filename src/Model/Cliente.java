package Model;

import Business.FactoryMethod.NotificationFactory;

import java.util.Date;
import java.util.List;

public class Cliente extends Utente {

    private List<ListaAcquisto> listeAcquisto;
    private List<Prenotazione> prenotazioni;
    private PuntoVendita puntoVenditaDiRegistrazione;
    private NotificationFactory.TipoNotifica canalePreferito;
    private Date registrazione;
    private boolean abilitazione;

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
