package View.ViewModel;

public class RigaMagazzino {

    private int quantitaCorsie;
    private int quantitaScaffali;
    private String indirizzo;
    private Object modificaButton;
    private Object eliminaButton;

    public int getQuantitaCorsie() {
        return quantitaCorsie;
    }

    public void setQuantitaCorsie(int quantitaCorsie) {
        this.quantitaCorsie = quantitaCorsie;
    }

    public int getQuantitaScaffali() {
        return quantitaScaffali;
    }

    public void setQuantitaScaffali(int quantitaScaffali) {
        this.quantitaScaffali = quantitaScaffali;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
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
