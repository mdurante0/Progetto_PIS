package View.ViewModel;



public class RigaListaAcquistoCliente {

    private String nomeLista;
    private String pagata;
    private Object modificaButton;
    private Object eliminaButton;

    public String getNomeLista() {
        return nomeLista;
    }

    public void setNomeLista(String nomeLista) {
        this.nomeLista = nomeLista;
    }

    public Object getModificaButton() {
        return modificaButton;
    }

    public void setModificaButton(Object modificaButton) {
        this.modificaButton = modificaButton;
    }

    public String getPagata() {
        return pagata;
    }

    public void setPagata(String pagata) {
        this.pagata = pagata;
    }

    public Object getEliminaButton() {
        return eliminaButton;
    }

    public void setEliminaButton(Object eliminaButton) {
        this.eliminaButton = eliminaButton;
    }
}
