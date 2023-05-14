package Business.Results;

import Model.Articolo;

import java.util.ArrayList;

public class CatalogoResult {
    public enum Result { CATALOGO_CARICATO, ERRORE_CATALOGO, ERRORE_MAGAZZINO }

    private Result result;
    private String message;
    private ArrayList<Articolo> lista;

    public ArrayList<Articolo> getLista() {
        return lista;
    }

    public void setLista(ArrayList<Articolo> lista) {
        this.lista = lista;
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
