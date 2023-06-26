package View.ViewModel;

import javax.swing.*;

public class RigaPuntoVendita {

   private String manager;
   private String magazzino;
   private String citta;
   private String indirizzo;
   private String telefono;
   private String nomePuntoVendita;
   private Object modificaButton;
   private Object eliminaButton;

    public Object getModificaButton() {
        return modificaButton;
    }

    public void setModificaButton(Object modificaButton) {
        this.modificaButton = modificaButton;
    }

    public Object getEliminaButton() {
        return eliminaButton;
    }

    public void setEliminaButton(Object eliminaButton) {
        this.eliminaButton = eliminaButton;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getMagazzino() {
        return magazzino;
    }

    public void setMagazzino(String magazzino) {
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

    public String getNomePuntoVendita() {
        return nomePuntoVendita;
    }

    public void setNomePuntoVendita(String nomePuntoVendita) {
        this.nomePuntoVendita = nomePuntoVendita;
    }
}
