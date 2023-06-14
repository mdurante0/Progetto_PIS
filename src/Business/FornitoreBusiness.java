package Business;

import Business.Results.FornitoreResult;
import DAO.FornitoreDAO;
import Model.Fornitore;

import java.util.ArrayList;

public class FornitoreBusiness {

    private static FornitoreBusiness instance;

    public static synchronized FornitoreBusiness getInstance() {
        if (instance == null) {
            instance = new FornitoreBusiness();
        }
        return instance;
    }

    public FornitoreResult caricaFornitori(){
        FornitoreResult result = new FornitoreResult();

        FornitoreDAO fornitoreDAO = FornitoreDAO.getInstance();

        ArrayList<Fornitore> fornitori = fornitoreDAO.findAll();

        if(fornitori.isEmpty()){ //Non ci sono fornitori
            result.setResult(FornitoreResult.Result.SUPPLIER_ERROR);
            result.setMessage("Nessun fornitore trovato!");
            return result;

        }else result.setFornitori(fornitori); //Fornitori caricati

        result.setResult(FornitoreResult.Result.FORNITORI_CARICATI);
        result.setMessage("Fornitori caricati correttamente");

        return result;
    }
    public FornitoreResult addFornitore(Fornitore fornitore){

        FornitoreResult result = new FornitoreResult();

        //verifico l'esistenza del fornitore
        FornitoreDAO fDao = FornitoreDAO.getInstance();
        if(fDao.findByName(fornitore.getNome()) != null){
            result.setResult(FornitoreResult.Result.SUPPLIER_ALREADY_EXISTS);
            result.setMessage("Il fornitore da inserire è già esistente! Riprova!");
            return result;
        }

        //Aggiungo il nuovo fornitore
        if(fDao.add(fornitore) == 0) { //fornitore non inserito
            result.setResult(FornitoreResult.Result.SUPPLIER_ERROR);
            result.setMessage("Fornitore non inserito! Riprova!");
            return result;
        }

        //l'inserimento è andato a buon fine
        result.setResult(FornitoreResult.Result.ADD_OK);
        result.setMessage("Fornitore inserito correttamente!");
        return result;
    }

    public FornitoreResult updateFornitore(Fornitore fornitore){

        FornitoreResult result = new FornitoreResult();

        //verifico l'esistenza del fornitore
        FornitoreDAO fDao = FornitoreDAO.getInstance();
        if(fDao.findById(fornitore.getIdFornitore()) == null){
            result.setResult(FornitoreResult.Result.SUPPLIER_DOESNT_EXIST);
            result.setMessage("Il fornitore da aggiornare non esiste! Riprova!");
            return result;
        }

        //Aggiorno il fornitore
        if(fDao.update(fornitore) == 0) { //fornitore non aggiornato
            result.setResult(FornitoreResult.Result.SUPPLIER_ERROR);
            result.setMessage("Fornitore non aggiornato! Riprova!");
            return result;
        }

        //l'aggiornamento è andato a buon fine
        result.setResult(FornitoreResult.Result.UPDATE_OK);
        result.setMessage("Fornitore aggiornato correttamente!");
        return result;
    }

    public FornitoreResult removeFornitore(Fornitore fornitore){

        FornitoreResult result = new FornitoreResult();

        //verifico l'esistenza del fornitore
        FornitoreDAO fDao = FornitoreDAO.getInstance();
        if(fDao.findByName(fornitore.getNome()) == null){
            result.setResult(FornitoreResult.Result.SUPPLIER_DOESNT_EXIST);
            result.setMessage("Il fornitore da rimuovere non esiste! Riprova!");
            return result;
        }

        //Rimuovo il fornitore
        if(fDao.removeById(fornitore.getNome()) == 0) { //fornitore non rimosso
            result.setResult(FornitoreResult.Result.SUPPLIER_ERROR);
            result.setMessage("Fornitore non rimosso! Riprova!");
            return result;
        }

        //la rimozione è andata a buon fine
        result.setResult(FornitoreResult.Result.DELETE_OK);
        result.setMessage("Fornitore rimosso correttamente!");
        return result;
    }

}
