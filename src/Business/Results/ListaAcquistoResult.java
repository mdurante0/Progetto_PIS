package Business.Results;

import Model.ListaAcquisto;

import java.util.ArrayList;

public class ListaAcquistoResult {
    public enum Result { ADD_OK, ADD_ERROR, LISTE_CARICATE, USER_DOESNT_EXIST, ITEM_DOESNT_EXIST, PRENOTAZIONE_ERROR, REMOVE_OK, UPDATE_OK, UPDATE_ERROR, REMOVE_ERROR}
    private ArrayList<ListaAcquisto> listeAcquisto = new ArrayList<>();
    private Result result;
    private String message;

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

    public ArrayList<ListaAcquisto> getListeAcquisto() {
        return listeAcquisto;
    }

    public void setListeAcquisto(ArrayList<ListaAcquisto> listeAcquisto) {
        this.listeAcquisto = listeAcquisto;
    }
}
