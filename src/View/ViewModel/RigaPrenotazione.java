package View.ViewModel;

import Model.Feedback;

import java.util.Date;

public class RigaPrenotazione {

    private String usernameCliente;

    private String nomeProdotto;

    private int quantitaProdotto;
    private Date data;
    private Object modificaButton;
    private Object eliminaButton;

    public String getUsernameCliente() {
        return usernameCliente;
    }

    public void setUsernameCliente(String usernameCliente) {
        this.usernameCliente = usernameCliente;
    }

    public String getNomeProdotto() {
        return nomeProdotto;
    }

    public void setNomeProdotto(String nomeProdotto) {
        this.nomeProdotto = nomeProdotto;
    }

    public int getQuantitaProdotto() {
        return quantitaProdotto;
    }

    public void setQuantitaProdotto(int quantitaProdotto) {
        this.quantitaProdotto = quantitaProdotto;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
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
