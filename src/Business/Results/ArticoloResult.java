package Business.Results;

import Model.Articolo;

public class ArticoloResult {
    public enum Result { ADD_OK, UPDATE_OK, DELETE_OK, ITEM_DOESNT_EXIST, ITEM_ALREADY_EXISTS, WRONG_TYPE, ITEM_ERROR, IS_PRODOTTO_COMPOSITO, IS_PRODOTTO, IS_SERVIZIO }

    private Result result;
    private String message;
    private Articolo articolo;

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

    public Articolo getArticolo() {
        return articolo;
    }

    public void setArticolo(Articolo articolo) {
        this.articolo = articolo;
    }
}
