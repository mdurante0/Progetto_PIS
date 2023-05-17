package Business.Results;

import Model.Servizio;
import Model.composite.IProdotto;

import java.util.ArrayList;
import java.util.List;

public class CatalogoResult {
    public enum Result { CATALOGO_CARICATO, ERRORE_MAGAZZINO, ERRORE_SERVIZI }

    private Result result;
    private String message;
    private List<IProdotto> listaProdotti = new ArrayList<>();
    private List<Servizio> listaServizi = new ArrayList<>();

    public List<IProdotto> getListaProdotti() {
        return listaProdotti;
    }

    public void setListaProdotti(List<IProdotto> listaProdotti) {
        this.listaProdotti = listaProdotti;
    }

    public List<Servizio> getListaServizi() {
        return listaServizi;
    }

    public void setListaServizi(List<Servizio> listaServizi) {
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
