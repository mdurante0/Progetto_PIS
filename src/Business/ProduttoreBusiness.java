package Business;

import Business.Results.ProduttoreResult;
import DAO.ProduttoreDAO;
import Model.Produttore;

import java.util.ArrayList;

public class ProduttoreBusiness {

    private static ProduttoreBusiness instance;

    public static synchronized ProduttoreBusiness getInstance() {
        if (instance == null) {
            instance = new ProduttoreBusiness();
        }
        return instance;
    }

    public ProduttoreResult caricaProduttori(){
        ProduttoreResult result = new ProduttoreResult();

        ProduttoreDAO produttoreDAO = ProduttoreDAO.getInstance();

        ArrayList<Produttore> produttori = produttoreDAO.findAll();

        if(produttori.isEmpty()){ //Non ci sono produttori
            result.setResult(ProduttoreResult.Result.PRODUCER_ERROR);
            result.setMessage("Nessun produttore trovato!");
            return result;

        }else result.setProduttori(produttori); //Produttori caricati

        result.setResult(ProduttoreResult.Result.PRODUTTORI_CARICATI);
        result.setMessage("Produttori caricati correttamente");

        return result;
    }
    public ProduttoreResult addProduttore(Produttore produttore){

        ProduttoreResult result = new ProduttoreResult();

        //verifico l'esistenza del produttore
        ProduttoreDAO pDao = ProduttoreDAO.getInstance();
        if(pDao.findByName(produttore.getNome()) != null){
            result.setResult(ProduttoreResult.Result.PRODUCER_ALREADY_EXISTS);
            result.setMessage("Il produttore da inserire è già esistente! Riprova!");
            return result;
        }

        //Aggiungo il nuovo produttore
        if(pDao.add(produttore) == 0) { //produttore non inserito
            result.setResult(ProduttoreResult.Result.PRODUCER_ERROR);
            result.setMessage("Produttore non inserito! Riprova!");
            return result;
        }

        //l'inserimento è andato a buon fine
        result.setResult(ProduttoreResult.Result.ADD_OK);
        result.setMessage("Produttore inserito correttamente!");
        return result;
    }

    public ProduttoreResult updateProduttore(Produttore produttore){

        ProduttoreResult result = new ProduttoreResult();

        //verifico l'esistenza del produttore
        ProduttoreDAO pDao = ProduttoreDAO.getInstance();
        if(pDao.findById(produttore.getIdProduttore()) == null){
            result.setResult(ProduttoreResult.Result.PRODUCER_DOESNT_EXIST);
            result.setMessage("Il produttore da aggiornare non esiste! Riprova!");
            return result;
        }

        //Aggiorno il produttore
        if(pDao.update(produttore) == 0) { //produttore non aggiornato
            result.setResult(ProduttoreResult.Result.PRODUCER_ERROR);
            result.setMessage("Produttore non aggiornato! Riprova!");
            return result;
        }

        //l'aggiornamento è andato a buon fine
        result.setResult(ProduttoreResult.Result.UPDATE_OK);
        result.setMessage("Produttore aggiornato correttamente!");
        return result;
    }

    public ProduttoreResult removeProduttore(Produttore produttore){

        ProduttoreResult result = new ProduttoreResult();

        //verifico l'esistenza del produttore
        ProduttoreDAO pDao = ProduttoreDAO.getInstance();
        if(pDao.findByName(produttore.getNome()) == null){
            result.setResult(ProduttoreResult.Result.PRODUCER_DOESNT_EXIST);
            result.setMessage("Il produttore da rimuovere non esiste! Riprova!");
            return result;
        }

        //Rimuovo il produttore
        if(pDao.removeById(produttore.getNome()) == 0) { //produttore non rimosso
            result.setResult(ProduttoreResult.Result.PRODUCER_ERROR);
            result.setMessage("Produttore non rimosso! Riprova!");
            return result;
        }

        //la rimozione è andata a buon fine
        result.setResult(ProduttoreResult.Result.DELETE_OK);
        result.setMessage("Produttore rimosso correttamente!");
        return result;
    }

}
