package Business;

import Business.AbstractFactory.ICategoria;
import Business.Results.CategoriaResult;
import DAO.CategoriaProdottoDAO;
import DAO.CategoriaServizioDAO;
import Model.CategoriaProdotto;
import Model.CategoriaServizio;

import java.util.ArrayList;

public class CategoriaBusiness {

    private static CategoriaBusiness instance;

    public static synchronized CategoriaBusiness getInstance() {
        if (instance == null) {
            instance = new CategoriaBusiness();
        }
        return instance;
    }

    public CategoriaResult caricaCategorieProdotto(){
        CategoriaResult result = new CategoriaResult();

        CategoriaProdottoDAO categoriaProdottoDAO = CategoriaProdottoDAO.getInstance();

        ArrayList<CategoriaProdotto> categorie = categoriaProdottoDAO.findAll();

        if(categorie.isEmpty()){ //Non ci sono categorie
            result.setResult(CategoriaResult.Result.CATEGORY_ERROR);
            result.setMessage("Nessuna categoria prodotto trovata!");
            return result;

        }else result.setCategorieProdotto(categorie); //Categorie caricate

        result.setResult(CategoriaResult.Result.CATEGORIE_CARICATE);
        result.setMessage("Categorie caricate correttamente");

        return result;
    }

    public CategoriaResult caricaCategorieServizio(){
        CategoriaResult result = new CategoriaResult();

        CategoriaServizioDAO categoriaServizioDAO = CategoriaServizioDAO.getInstance();

        ArrayList<CategoriaServizio> categorie = categoriaServizioDAO.findAll();

        if(categorie.isEmpty()){ //Non ci sono categorie
            result.setResult(CategoriaResult.Result.CATEGORY_ERROR);
            result.setMessage("Nessuna categoria servizio trovata!");
            return result;

        }else result.setCategorieServizio(categorie); //Categorie caricate

        result.setResult(CategoriaResult.Result.CATEGORIE_CARICATE);
        result.setMessage("Categorie caricate correttamente");

        return result;
    }

    public CategoriaResult caricaCategoriaProdottoByName(String nomeCategoriaProdotto){
        CategoriaResult result = new CategoriaResult();

        CategoriaProdottoDAO categoriaProdottoDAO = CategoriaProdottoDAO.getInstance();

        CategoriaProdotto categoria = categoriaProdottoDAO.findByName(nomeCategoriaProdotto);

        if(categoria == null){ //Non ci sono categorie con questo nome
            result.setResult(CategoriaResult.Result.CATEGORY_ERROR);
            result.setMessage("Categoria prodotto non trovata!");
            return result;

        }else result.getCategorieProdotto().add(categoria); //Categoria caricata

        result.setResult(CategoriaResult.Result.CATEGORIE_CARICATE);
        result.setMessage("Categoria caricata correttamente");

        return result;
    }

    public CategoriaResult caricaCategoriaServizioByName(String nomeCategoriaServizio){
        CategoriaResult result = new CategoriaResult();

        CategoriaServizioDAO categoriaServizioDAO = CategoriaServizioDAO.getInstance();

        CategoriaServizio categoria = categoriaServizioDAO.findByName(nomeCategoriaServizio);

        if(categoria == null){ //Non ci sono categorie con questo nome
            result.setResult(CategoriaResult.Result.CATEGORY_ERROR);
            result.setMessage("Categoria servizio non trovata!");
            return result;

        }else result.getCategorieServizio().add(categoria); //Categoria caricata

        result.setResult(CategoriaResult.Result.CATEGORIE_CARICATE);
        result.setMessage("Categoria caricata correttamente");

        return result;
    }

    public CategoriaResult addCategoria(ICategoria categoria){

        CategoriaResult result = new CategoriaResult();

        //verifico l'esistenza della categoria
        CategoriaProdottoDAO cpDao = CategoriaProdottoDAO.getInstance();
        CategoriaServizioDAO csDao = CategoriaServizioDAO.getInstance();
        if(cpDao.findByName(categoria.getNome()) != null  || csDao.findByName(categoria.getNome()) != null){
            result.setResult(CategoriaResult.Result.CATEGORY_ALREADY_EXISTS);
            result.setMessage("La categoria inserita è già esistente! Riprova!");
            return result;
        }

        //inserisco la nuova categoria
        if (categoria instanceof CategoriaProdotto) {
            if (cpDao.add((CategoriaProdotto) categoria) == 0) { //categoria non inserita
                result.setResult(CategoriaResult.Result.CATEGORY_ERROR);
                result.setMessage("Categoria non inserita! Riprova!");
                return result;
            }
        } else {
            if(csDao.add((CategoriaServizio) categoria) == 0) { //categoria non inserita
                result.setResult(CategoriaResult.Result.CATEGORY_ERROR);
                result.setMessage("Categoria non inserita! Riprova!");
                return result;
            }
        }

        //l'inserimento è andato a buon fine
        result.setResult(CategoriaResult.Result.ADD_OK);
        result.setMessage("Categoria inserita correttamente!");
        return result;
    }


    public CategoriaResult updateCategoria(ICategoria categoria){

        CategoriaResult result = new CategoriaResult();

        //verifico l'esistenza della categoria
        CategoriaProdottoDAO cpDao = CategoriaProdottoDAO.getInstance();
        CategoriaServizioDAO csDao = CategoriaServizioDAO.getInstance();
        if(cpDao.findById(categoria.getIdCategoria()) == null  && csDao.findById(categoria.getIdCategoria()) == null){
            result.setResult(CategoriaResult.Result.CATEGORY_DOESNT_EXIST);
            result.setMessage("La categoria inserita è già esistente! Riprova!");
            return result;
        }

        //aggiorno la categoria
        if (categoria instanceof CategoriaProdotto) {
            if (cpDao.update((CategoriaProdotto) categoria) == 0) { //categoria non aggiornata
                result.setResult(CategoriaResult.Result.CATEGORY_ERROR);
                result.setMessage("Categoria non aggiornata! Riprova!");
                return result;
            }
        } else {
            if(csDao.update((CategoriaServizio) categoria) == 0) { //categoria non aggiornata
                result.setResult(CategoriaResult.Result.CATEGORY_ERROR);
                result.setMessage("Categoria non aggiornata! Riprova!");
                return result;
            }
        }

        //l'aggiornamento è andato a buon fine
        result.setResult(CategoriaResult.Result.UPDATE_OK);
        result.setMessage("Categoria aggiornata correttamente!");
        return result;
    }

    public CategoriaResult removeCategoria(ICategoria categoria){

        CategoriaResult result = new CategoriaResult();

        //verifico l'esistenza della categoria
        CategoriaProdottoDAO cpDao = CategoriaProdottoDAO.getInstance();
        CategoriaServizioDAO csDao = CategoriaServizioDAO.getInstance();
        if(cpDao.findById(categoria.getIdCategoria()) == null  && csDao.findById(categoria.getIdCategoria()) == null){
            result.setResult(CategoriaResult.Result.CATEGORY_DOESNT_EXIST);
            result.setMessage("La categoria inserita è già esistente! Riprova!");
            return result;
        }

        //rimozione della categoria
        if (categoria instanceof CategoriaProdotto) {
            if (cpDao.removeById(categoria.getNome()) == 0) { //categoria non rimossa
                result.setResult(CategoriaResult.Result.CATEGORY_ERROR);
                result.setMessage("Categoria non rimossa! Riprova!");
                return result;
            }
        } else {
            if(csDao.removeById(categoria.getNome()) == 0) { //categoria non rimossa
                result.setResult(CategoriaResult.Result.CATEGORY_ERROR);
                result.setMessage("Categoria non rimossa! Riprova!");
                return result;
            }
        }

        //la cancellazione è andata a buon fine
        result.setResult(CategoriaResult.Result.DELETE_OK);
        result.setMessage("Categoria rimossa correttamente!");
        return result;
    }

}
