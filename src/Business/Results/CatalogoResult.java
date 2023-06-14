package Business.Results;

import Model.Servizio;
import Model.composite.IProdotto;

import java.util.ArrayList;

public class CatalogoResult {
    public enum Result { CATALOGO_CARICATO, ERRORE_MAGAZZINO, ERRORE_SERVIZI }

    private Result result;
    private String message;
    private ArrayList<IProdotto> listaProdotti = new ArrayList<>();
    private ArrayList<Servizio> listaServizi = new ArrayList<>();

    public ArrayList<IProdotto> getListaProdotti() {
        return listaProdotti;
    }

    public void setListaProdotti(ArrayList<IProdotto> listaProdotti) {
        this.listaProdotti = listaProdotti;
    }

    public ArrayList<Servizio> getListaServizi() {
        return listaServizi;
    }

    public void setListaServizi(ArrayList<Servizio> listaServizi) {
        this.listaServizi = listaServizi;
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
