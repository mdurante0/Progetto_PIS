package Business.Results;

import Model.Immagine;

import java.util.ArrayList;

public class ImmagineResult {
    public enum Result { IMMAGINI_CARICATE, ERRORE_IMMAGINE, ITEM_DOESNT_EXIST, IMAGE_DOESNT_EXIST }

    private Result result;
    private String message;
    private ArrayList<Immagine> listaImmagini = new ArrayList<>();

    public ArrayList<Immagine> getListaImmagini() {
        return listaImmagini;
    }

    public void setListaImmagini(ArrayList<Immagine> listaImmagini) {
        this.listaImmagini = listaImmagini;
    }

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
}
