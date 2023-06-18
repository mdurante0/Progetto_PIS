package Business;

import Business.Results.CollocazioneResult;
import DAO.CollocazioneDAO;
import DAO.MagazzinoDAO;
import Model.Collocazione;
import Model.Magazzino;

import java.util.ArrayList;

public class CollocazioneBusiness {

    private static CollocazioneBusiness instance;

    public static synchronized CollocazioneBusiness getInstance() {
        if (instance == null) {
            instance = new CollocazioneBusiness();
        }
        return instance;
    }

    public CollocazioneResult caricaCollocaioni(){
        CollocazioneResult result = new CollocazioneResult();

        CollocazioneDAO collocazioneDAO = CollocazioneDAO.getInstance();

        ArrayList<Collocazione> collocazioni = collocazioneDAO.findAll();

        if(collocazioni.isEmpty()){ //Non ci sono collocazioni occupate o non ci sono magazzini
            result.setResult(CollocazioneResult.Result.COLLOCAZIONE_ERROR);
            result.setMessage("Nessuna collocazione occupata trovata!");
            return result;

        }else result.setCollocazioni(collocazioni); //Collocazioni caricate

        result.setResult(CollocazioneResult.Result.COLLOCAZIONI_CARICATE);
        result.setMessage("Magazzini caricati correttamente");

        return result;
    }
    public CollocazioneResult addCollocazione(Collocazione collocazione){

        CollocazioneResult result = new CollocazioneResult();

        //verifico l'esistenza della collocazione
        CollocazioneDAO collocazioneDAO = CollocazioneDAO.getInstance();
        if(!collocazioneDAO.isFree(collocazione)){
            result.setResult(CollocazioneResult.Result.COLLOCAZIONE_OCCUPATA);
            result.setMessage("La collocazione inserita è già occupata o inesistente! Riprova!");
            return result;
        }

        //Aggiungo la nuova collocazione
        if(collocazioneDAO.add(collocazione) == 0) { //collocazione non inserita
            result.setResult(CollocazioneResult.Result.COLLOCAZIONE_ERROR);
            result.setMessage("Collocazione non inserita! Riprova!");
            return result;
        }

        //l'inserimento è andato a buon fine
        result.setResult(CollocazioneResult.Result.ADD_OK);
        result.setMessage("Collocazione inserita correttamente!");
        return result;
    }

    public CollocazioneResult updateCollocazione(Collocazione collocazione){

        CollocazioneResult result = new CollocazioneResult();

        //verifico l'esistenza della collocazione
        CollocazioneDAO collocazioneDAO = CollocazioneDAO.getInstance();
        if(collocazioneDAO.isFree(collocazione)){
            result.setResult(CollocazioneResult.Result.COLLOCAZIONE_LIBERA);
            result.setMessage("La collocazione da aggiornare non esiste! Riprova!");
            return result;
        }

        //aggiorno la collocazione
        if(collocazioneDAO.update(collocazione) == 0){ //collocazione non aggiornata
            result.setResult(CollocazioneResult.Result.COLLOCAZIONE_ERROR);
            result.setMessage("Collocazione non aggiornata! Riprova!");
            return result;
        }

        //l'aggiornamento è andato a buon fine
        result.setResult(CollocazioneResult.Result.UPDATE_OK);
        result.setMessage("Collocazione aggiornata correttamente!");
        return result;
    }

    public CollocazioneResult removeCollocazione(Collocazione collocazione){

        CollocazioneDAO collocazioneDAO = CollocazioneDAO.getInstance();
        CollocazioneResult result = new CollocazioneResult();

        //Verifica esistenza collocazione
        if (collocazioneDAO.isFree(collocazione)){
            result.setResult(CollocazioneResult.Result.COLLOCAZIONE_LIBERA);
            result.setMessage("La collocazione da rimuovere non esiste! Riprova");
            return result;
        }

        //rimozione della collocazione
        int removed = collocazioneDAO.removeById(collocazione.getIdCollocazione());
        if(removed == 0){ //collocazione non rimossa
            result.setResult(CollocazioneResult.Result.COLLOCAZIONE_ERROR);
            result.setMessage("Errore nella rimozione della collocazione! Riprova!");
            return result;
        }

        //la cancellazione è andata a buon fine
        result.setResult(CollocazioneResult.Result.DELETE_OK);
        result.setMessage("Collocazione rimossa correttamente!");
        return result;
    }

    public CollocazioneResult caricaCollocazioniByMagazzino(Magazzino magazzino){
        CollocazioneResult result = new CollocazioneResult();
        CollocazioneDAO collocazioneDAO = CollocazioneDAO.getInstance();

        //verifico l'esistenza del magazzino
        MagazzinoDAO magazzinoDAO = MagazzinoDAO.getInstance();
        if(magazzinoDAO.findByAddress(magazzino.getIndirizzo()) == null){
            result.setResult(CollocazioneResult.Result.MAGAZZINO_DOESNT_EXIST);
            result.setMessage("Il magazzino non esiste! Riprova!");
            return result;
        }

        ArrayList<Collocazione> collocazioni = collocazioneDAO.findAllByMagazzino(magazzino.getIdMagazzino());
        if(collocazioni.isEmpty()){ //Non ci sono collocazioni occupate
            result.setResult(CollocazioneResult.Result.COLLOCAZIONE_ERROR);
            result.setMessage("Nessuna collocazione occupata trovata!");
            return result;

        }else result.setCollocazioni(collocazioni); //Collocazioni caricate

        result.setResult(CollocazioneResult.Result.COLLOCAZIONI_CARICATE);
        result.setMessage("Magazzini caricati correttamente");

        return result;
    }

}
