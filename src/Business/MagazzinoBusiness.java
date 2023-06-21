package Business;

import Business.Results.MagazzinoResult;
import DAO.MagazzinoDAO;
import Model.Magazzino;
import Model.PuntoVendita;

import java.util.ArrayList;

public class MagazzinoBusiness {

    private static MagazzinoBusiness instance;

    public static synchronized MagazzinoBusiness getInstance() {
        if (instance == null) {
            instance = new MagazzinoBusiness();
        }
        return instance;
    }

    public MagazzinoResult caricaMagazzini(){
        MagazzinoResult result = new MagazzinoResult();

        MagazzinoDAO magazzinoDAO = MagazzinoDAO.getInstance();

        ArrayList<Magazzino> magazzini = magazzinoDAO.findAll();

        if(magazzini.isEmpty()){ //Non ci sono magazzini
            result.setResult(MagazzinoResult.Result.MAGAZZINO_ERROR);
            result.setMessage("Nessun magazzino trovato!");
            return result;

        }else result.setMagazzini(magazzini); //Magazzini caricati

        result.setResult(MagazzinoResult.Result.MAGAZZINI_CARICATI);
        result.setMessage("Magazzini caricati correttamente");

        return result;
    }
    public MagazzinoResult addMagazzino(Magazzino magazzino){

        MagazzinoResult result = new MagazzinoResult();

        //verifico l'esistenza del magazzino
        MagazzinoDAO magazzinoDAO = MagazzinoDAO.getInstance();
        if(magazzinoDAO.findByAddress(magazzino.getIndirizzo()) != null){
            result.setResult(MagazzinoResult.Result.MAGAZZINO_ALREADY_EXISTS);
            result.setMessage("Il magazzino da inserire è già esistente! Riprova!");
            return result;
        }

        //Aggiungo il nuovo magazzino
        if(magazzinoDAO.add(magazzino) == 0) { //magazzino non inserito
            result.setResult(MagazzinoResult.Result.MAGAZZINO_ERROR);
            result.setMessage("Magazzino non inserito! Riprova!");
            return result;
        }

        //l'inserimento è andato a buon fine
        result.setResult(MagazzinoResult.Result.ADD_OK);
        result.setMessage("Magazzino inserito correttamente!");
        return result;
    }

    public MagazzinoResult updateMagazzino(Magazzino m){

        MagazzinoResult result = new MagazzinoResult();

        //verifico l'esistenza del magazzino
        MagazzinoDAO mDao = MagazzinoDAO.getInstance();
        if(mDao.findById(m.getIdMagazzino()) == null){
            result.setResult(MagazzinoResult.Result.MAGAZZINO_DOESNT_EXIST);
            result.setMessage("Il magazzino da aggiornare non esiste! Riprova!");
            return result;
        }

        //aggiorno il magazzino
        if(mDao.update(m) == 0){ //magazzino non aggiornato
            result.setResult(MagazzinoResult.Result.MAGAZZINO_ERROR);
            result.setMessage("Magazzino non aggiornato! Riprova!");
            return result;
        }

        //l'aggiornamento è andato a buon fine
        result.setResult(MagazzinoResult.Result.UPDATE_OK);
        result.setMessage("Magazzino aggiornato correttamente!");
        return result;
    }

    public MagazzinoResult removeMagazzino(Magazzino magazzino){

        MagazzinoDAO magazzinoDAO = MagazzinoDAO.getInstance();
        MagazzinoResult result = new MagazzinoResult();

        //Verifica esistenza magazzino
        magazzino = magazzinoDAO.findByAddress(magazzino.getIndirizzo());
        if (magazzino == null){
            result.setResult(MagazzinoResult.Result.MAGAZZINO_DOESNT_EXIST);
            result.setMessage("Il magazzino da rimuovere non esiste! Riprova");
            return result;
        }

        //rimozione del magazzino
        int removed = magazzinoDAO.removeById(magazzino.getIdMagazzino());
        if(removed == 0){ //magazzino non rimosso
            result.setResult(MagazzinoResult.Result.MAGAZZINO_ERROR);
            result.setMessage("Errore nella rimozione del magazzino! Riprova!");
            return result;
        }

        //la cancellazione è andata a buon fine
        result.setResult(MagazzinoResult.Result.DELETE_OK);
        result.setMessage("Magazzino rimosso correttamente!");
        return result;
    }
    public MagazzinoResult caricaMagazzinoByPuntoVendita(PuntoVendita puntoVendita){
        MagazzinoResult result = new MagazzinoResult();

        MagazzinoDAO magazzinoDAO = MagazzinoDAO.getInstance();

        Magazzino magazzino = magazzinoDAO.findById(puntoVendita.getMagazzino().getIdMagazzino());

        if(magazzino == null){ //Non ci sono magazzini
            result.setResult(MagazzinoResult.Result.MAGAZZINO_ERROR);
            result.setMessage("Nessun magazzino trovato!");
            return result;

        }else result.getMagazzini().add(magazzino); //Magazzino caricato

        result.setResult(MagazzinoResult.Result.MAGAZZINI_CARICATI);
        result.setMessage("Magazzino caricato correttamente");

        return result;
    }
    public MagazzinoResult caricaMagazzinoByIndirizzo(String indirizzo){
        MagazzinoResult result = new MagazzinoResult();

        MagazzinoDAO magazzinoDAO = MagazzinoDAO.getInstance();

        Magazzino magazzino = magazzinoDAO.findByAddress(indirizzo);

        if(magazzino == null){ //Non ci sono magazzini
            result.setResult(MagazzinoResult.Result.MAGAZZINO_ERROR);
            result.setMessage("Nessun magazzino trovato!");
            return result;

        }else result.getMagazzini().add(magazzino); //Magazzino caricato

        result.setResult(MagazzinoResult.Result.MAGAZZINI_CARICATI);
        result.setMessage("Magazzino caricati correttamente");

        return result;
    }

}
