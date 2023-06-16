package Business.Results;

import Model.Collocazione;

import java.util.ArrayList;

public class CollocazioneResult {

    public enum Result { ADD_OK, COLLOCAZIONE_OCCUPATA, COLLOCAZIONE_LIBERA, UPDATE_OK, DELETE_OK, COLLOCAZIONE_ERROR, MAGAZZINO_DOESNT_EXIST, COLLOCAZIONI_CARICATE }

    private Result result;
    private String message;

    private ArrayList<Collocazione> collocazioni = new ArrayList<>();

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

    public ArrayList<Collocazione> getCollocazioni() {
        return collocazioni;
    }

    public void setCollocazioni(ArrayList<Collocazione> collocazioni) {
        this.collocazioni = collocazioni;
    }
}
