package View.ViewModel;

public class RigaProduttore {

  private String nomeProduttore;
  private String emailProduttore;
  private String sitoProduttore;
  private String descrizione;
  private Object modificaButton;
  private Object eliminaButton;

    public String getNomeProduttore() {
        return nomeProduttore;
    }

    public void setNomeProduttore(String nomeProduttore) {
        this.nomeProduttore = nomeProduttore;
    }

    public String getEmailProduttore() {
        return emailProduttore;
    }

    public void setEmailProduttore(String emailProduttore) {
        this.emailProduttore = emailProduttore;
    }

    public String getSitoProduttore() {
        return sitoProduttore;
    }

    public void setSitoProduttore(String sitoProduttore) {
        this.sitoProduttore = sitoProduttore;
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
