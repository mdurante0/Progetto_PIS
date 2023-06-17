package View.ViewModel;



public class RigaListaAcquisto {

    private String usernameCliente;
    private String nomeLista;
    private Object pagata;
    private Object visualizzaButton;
    private Object eliminaButton;

    public String getNomeLista() {
        return nomeLista;
    }

    public void setNomeLista(String nomeLista) {
        this.nomeLista = nomeLista;
    }

    public String getUsernameCliente() {
        return usernameCliente;
    }

    public void setUsernameCliente(String usernameCliente) {
        this.usernameCliente = usernameCliente;
    }

    public Object getPagata() {
        return pagata;
    }

    public void setPagata(Object pagata) {
        this.pagata = pagata;
    }

    public Object getVisualizzaButton() {
        return visualizzaButton;
    }

    public void setVisualizzaButton(Object visualizzaButton) {
        this.visualizzaButton = visualizzaButton;
    }

    public Object getEliminaButton() {
        return eliminaButton;
    }

    public void setEliminaButton(Object eliminaButton) {
        this.eliminaButton = eliminaButton;
    }
}
