package Business.Results;

import Model.Fornitore;

import java.util.ArrayList;

public class FornitoreResult {

    public enum Result { ADD_OK, SUPPLIER_ALREADY_EXISTS, SUPPLIER_DOESNT_EXIST, UPDATE_OK, DELETE_OK, FORNITORI_CARICATI, SUPPLIER_ERROR }

    private Result result;
    private String message;
    private ArrayList<Fornitore> fornitori = new ArrayList<>();
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

    public ArrayList<Fornitore> getFornitori() {
        return fornitori;
    }

    public void setFornitori(ArrayList<Fornitore> fornitori) {
        this.fornitori = fornitori;
    }
}
