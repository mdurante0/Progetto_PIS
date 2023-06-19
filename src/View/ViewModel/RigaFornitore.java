package View.ViewModel;

public class RigaFornitore {

  private String nomeFornitore;
  private String emailFornitore;
  private String sitoFornitore;
  private String descrizione;
  private Object modificaButton;
  private Object eliminaButton;

    public String getNomeFornitore() {
        return nomeFornitore;
    }

    public void setNomeFornitore(String nomeFornitore) {
        this.nomeFornitore = nomeFornitore;
    }

    public String getEmailFornitore() {
        return emailFornitore;
    }

    public void setEmailFornitore(String emailFornitore) {
        this.emailFornitore = emailFornitore;
    }

    public String getSitoFornitore() {
        return sitoFornitore;
    }

    public void setSitoFornitore(String sitoFornitore) {
        this.sitoFornitore = sitoFornitore;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

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
}
