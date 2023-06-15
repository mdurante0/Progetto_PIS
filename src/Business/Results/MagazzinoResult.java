package Business.Results;

import Model.Magazzino;

import java.util.ArrayList;

public class MagazzinoResult {

    public enum Result { ADD_OK, MAGAZZINO_ALREADY_EXISTS, MAGAZZINO_DOESNT_EXIST, UPDATE_OK, DELETE_OK, MAGAZZINO_ERROR, MAGAZZINI_CARICATI }

    private Result result;
    private String message;

    private ArrayList<Magazzino> magazzini = new ArrayList<>();

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

    public ArrayList<Magazzino> getMagazzini() {
        return magazzini;
    }

    public void setMagazzini(ArrayList<Magazzino> magazzini) {
        this.magazzini = magazzini;
    }
}
