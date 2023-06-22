package View.ViewModel;



public class RigaDettagliListaAcquisto {



    private String nomeProdotto;
    private int quantita;
    private float costo;
    private Object eliminaButton;



    public Object getEliminaButton() {
        return eliminaButton;
    }

    public void setEliminaButton(Object eliminaButton) {
        this.eliminaButton = eliminaButton;
    }

    public String getNomeProdotto() {
        return nomeProdotto;
    }

    public void setNomeProdotto(String nomeProdotto) {
        this.nomeProdotto = nomeProdotto;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public float getCosto() {
        return costo;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }
}
