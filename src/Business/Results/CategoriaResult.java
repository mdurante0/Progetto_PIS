package Business.Results;

import Business.AbstractFactory.ICategoria;
import Model.CategoriaProdotto;
import Model.CategoriaServizio;

import java.util.ArrayList;

public class CategoriaResult {

    public enum Result { ADD_OK, CATEGORY_ALREADY_EXISTS, CATEGORY_DOESNT_EXIST, UPDATE_OK, DELETE_OK, CATEGORIE_CARICATE, CATEGORY_ERROR }

    private Result result;
    private String message;
    private ArrayList<CategoriaProdotto> categorieProdotto = new ArrayList<>();
    private ArrayList<CategoriaServizio> categorieServizio = new ArrayList<>();
    private ArrayList<ICategoria> categorie = new ArrayList<>();
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

    public ArrayList<ICategoria> getCategorie() {
        return categorie;
    }

    public void setCategorie(ArrayList<ICategoria> categorie) {
        this.categorie = categorie;
    }

    public ArrayList<CategoriaProdotto> getCategorieProdotto() {
        return categorieProdotto;
    }

    public void setCategorieProdotto(ArrayList<CategoriaProdotto> categorieProdotto) {
        this.categorieProdotto = categorieProdotto;
    }

    public ArrayList<CategoriaServizio> getCategorieServizio() {
        return categorieServizio;
    }

    public void setCategorieServizio(ArrayList<CategoriaServizio> categorieServizio) {
        this.categorieServizio = categorieServizio;
    }
}
