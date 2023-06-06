package Business.Results;

import Model.Prenotazione;

import java.util.ArrayList;

public class PrenotazioneResult {
    public enum Result { ADD_OK, ADD_ERROR, USER_DOESNT_EXIST, ITEM_DOESNT_EXIST, PRENOTAZIONE_ERROR, REMOVE_OK, PRENOTAZIONI_CARICATE, REMOVE_ERROR}
    private ArrayList<Prenotazione> prenotazioni = new ArrayList<>();
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

    public ArrayList<Prenotazione> getPrenotazioni() {
        return prenotazioni;
    }

    public void setPrenotazioni(ArrayList<Prenotazione> prenotazioni) {
        this.prenotazioni = prenotazioni;
    }
}
