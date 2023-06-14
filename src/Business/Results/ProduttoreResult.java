package Business.Results;

import Model.Produttore;

import java.util.ArrayList;

public class ProduttoreResult {

    public enum Result { ADD_OK, PRODUCER_ALREADY_EXISTS, PRODUCER_DOESNT_EXIST, UPDATE_OK, DELETE_OK, PRODUCER_ERROR , PRODUTTORI_CARICATI }

    private Result result;
    private String message;

    private ArrayList<Produttore> produttori = new ArrayList<>();

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

    public ArrayList<Produttore> getProduttori() {
        return produttori;
    }

    public void setProduttori(ArrayList<Produttore> produttori) {
        this.produttori = produttori;
    }
}
