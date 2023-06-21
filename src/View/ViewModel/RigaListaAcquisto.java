package View.ViewModel;



public class RigaListaAcquisto {

    private String usernameCliente;
    private String nomeLista;
    private float costoTotale;
    private Object pagata;
    private Object DettagliButton;
    private Object eliminaButton;

    public Object getDettagliButton() {
        return DettagliButton;
    }

    public void setDettagliButton(Object dettagliButton) {
        DettagliButton = dettagliButton;
    }
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


    public Object getEliminaButton() {
        return eliminaButton;
    }

    public void setEliminaButton(Object eliminaButton) {
        this.eliminaButton = eliminaButton;
    }

    public float getCostoTotale() {
        return costoTotale;
    }

    public void setCostoTotale(float costoTotale) {
        this.costoTotale = costoTotale;
    }
}
