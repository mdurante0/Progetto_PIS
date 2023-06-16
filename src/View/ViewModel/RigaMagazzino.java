package View.ViewModel;

public class RigaMagazzino {

    private int quantitaCorsie;
    private int quantitaScaffali;
    private String indirizzo;
    private Object ModificaButton;
    private Object EliminaButton;

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
        return ModificaButton;
    }

    public void setModificaButton(Object modificaButton) {
        ModificaButton = modificaButton;
    }

    public Object getEliminaButton() {
        return EliminaButton;
    }

    public void setEliminaButton(Object eliminaButton) {
        EliminaButton = eliminaButton;
    }
}
