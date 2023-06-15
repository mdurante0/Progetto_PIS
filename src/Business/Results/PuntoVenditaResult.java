package Business.Results;

import Model.PuntoVendita;

import java.util.ArrayList;
import java.util.List;

public class PuntoVenditaResult {

    public enum Result { ADD_OK, SALEPOINT_ALREADY_EXISTS, SALEPOINT_ERROR, SALEPOINT_DOESNT_EXIST, DELETE_OK, UPDATE_SALEPOINT_OK, SALEPOINT_CARICATI }

    private Result result;
    private String message;
    private List<PuntoVendita> puntiVendita = new ArrayList<>();

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

    public List<PuntoVendita> getPuntiVendita() {
        return puntiVendita;
    }

    public void setPuntiVendita(List<PuntoVendita> puntiVendita) {
        this.puntiVendita = puntiVendita;
    }
}
