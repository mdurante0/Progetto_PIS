package Business.Results;

import Model.Cliente;

import java.util.ArrayList;

public class ClienteResult {
    public enum Result { ABILITAZIONE_OK, USER_DOESNT_EXIST, ABILITAZIONE_ERROR, RIMOZIONE_OK, RIMOZIONE_ERROR, CLIENTE_ERROR, CLIENTI_CARICATI }

    private Result result;
    private String message;
    private ArrayList<Cliente> clienti = new ArrayList<>();

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<Cliente> getClienti() {
        return clienti;
    }

    public void setClienti(ArrayList<Cliente> clienti) {
        this.clienti = clienti;
    }
}
