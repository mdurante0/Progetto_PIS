package Business;

import Business.Results.PuntoVenditaResult;
import DAO.MagazzinoDAO;
import DAO.PuntoVenditaDAO;
import Model.Magazzino;
import Model.Manager;
import Model.PuntoVendita;

import java.util.ArrayList;

public class PuntoVenditaBusiness {

    private static PuntoVenditaBusiness instance;

    public static synchronized PuntoVenditaBusiness getInstance() {
        if (instance == null) {
            instance = new PuntoVenditaBusiness();
        }
        return instance;
    }

    public PuntoVenditaResult caricaPuntiVendita(){
        PuntoVenditaResult result = new PuntoVenditaResult();

        PuntoVenditaDAO puntoVenditaDAO = PuntoVenditaDAO.getInstance();

        ArrayList<PuntoVendita> puntiVendita = puntoVenditaDAO.findAll();

        if(puntiVendita.isEmpty()){ //Non ci sono punti vendita
            result.setResult(PuntoVenditaResult.Result.SALEPOINT_ERROR);
            result.setMessage("Nessun punto vendita trovato!");
            return result;

        }else result.setPuntiVendita(puntiVendita); //Punti vendita caricati

        result.setResult(PuntoVenditaResult.Result.SALEPOINT_CARICATI);
        result.setMessage("Punti vendita caricati correttamente");

        return result;
    }

    public PuntoVenditaResult caricaPuntoVenditaByManager(Manager manager){
        PuntoVenditaResult result = new PuntoVenditaResult();

        PuntoVenditaDAO puntoVenditaDAO = PuntoVenditaDAO.getInstance();

        PuntoVendita puntoVendita = puntoVenditaDAO.findByManager(manager.getIdUtente());

        if(puntoVendita == null){ //Il manager indicato non è stato assegnato ad alcun punto vendita
            result.setResult(PuntoVenditaResult.Result.SALEPOINT_ERROR);
            result.setMessage("Punto vendita non trovato!");
            return result;

        }else result.getPuntiVendita().add(puntoVendita); //Punto vendita caricato

        result.setResult(PuntoVenditaResult.Result.SALEPOINT_CARICATI);
        result.setMessage("Punti vendita caricati correttamente");

        return result;
    }

    public PuntoVenditaResult addSalePoint(PuntoVendita p, Magazzino m){

        PuntoVenditaResult result = new PuntoVenditaResult();

        //verifico l'esistenza del punto vendita
        PuntoVenditaDAO pDao = PuntoVenditaDAO.getInstance();
        if(pDao.findByName(p.getNome()) != null){
            result.setResult(PuntoVenditaResult.Result.SALEPOINT_ALREADY_EXISTS);
            result.setMessage("Il punto vendita inserito è già esistente! Riprova!");
            return result;
        }

        //inserisco il nuovo punto vendita e il nuovo magazzino
        MagazzinoDAO mDao = MagazzinoDAO.getInstance();
        if(mDao.add(m) == 0) { //magazzino non inserito
            result.setResult(PuntoVenditaResult.Result.DEPOSIT_ERROR);
            result.setMessage("Punto vendita non inserito! Verifica il magazzino! Riprova!");
            return result;

        } else if(pDao.add(p) == 0){ //punto vendita non inserito
            result.setResult(PuntoVenditaResult.Result.SALEPOINT_ERROR);
            result.setMessage("Punto vendita non inserito! Riprova!");
            return result;
        }

        //l'inserimento è andato a buon fine
        result.setResult(PuntoVenditaResult.Result.ADD_OK);
        result.setMessage("Punto vendita inserito correttamente!");
        return result;
    }

    public PuntoVenditaResult updateSalePoint(PuntoVendita p){

        PuntoVenditaResult result = new PuntoVenditaResult();

        //verifico l'esistenza del punto vendita
        PuntoVenditaDAO pDao = PuntoVenditaDAO.getInstance();
        if(pDao.findById(p.getIdPuntoVendita()) != null){
            result.setResult(PuntoVenditaResult.Result.SALEPOINT_DOESNT_EXIST);
            result.setMessage("Il punto vendita da aggiornare non esiste! Riprova!");
            return result;
        }

        //aggiorno il punto vendita
        if(pDao.update(p) == 0){ //punto vendita non aggiornato
            result.setResult(PuntoVenditaResult.Result.SALEPOINT_ERROR);
            result.setMessage("Punto vendita non aggiornato! Riprova!");
            return result;
        }

        //l'aggiornamento è andato a buon fine
        result.setResult(PuntoVenditaResult.Result.UPDATE_SALEPOINT_OK);
        result.setMessage("Punto vendita aggiornato correttamente!");
        return result;
    }

    public PuntoVenditaResult updateDeposit(Magazzino m){

        PuntoVenditaResult result = new PuntoVenditaResult();

        //verifico l'esistenza del magazzino
        MagazzinoDAO mDao = MagazzinoDAO.getInstance();
        if(mDao.findById(m.getIdMagazzino()) != null){
            result.setResult(PuntoVenditaResult.Result.DEPOSIT_DOESNT_EXIST);
            result.setMessage("Il magazzino da aggiornare non esiste! Riprova!");
            return result;
        }

        //aggiorno il magazzino
        if(mDao.update(m) == 0){ //magazzino non aggiornato
            result.setResult(PuntoVenditaResult.Result.DEPOSIT_ERROR);
            result.setMessage("Magazzino non aggiornato! Riprova!");
            return result;
        }

        //l'aggiornamento è andato a buon fine
        result.setResult(PuntoVenditaResult.Result.UPDATE_DEPOSIT_OK);
        result.setMessage("Magazzino aggiornato correttamente!");
        return result;
    }

    public PuntoVenditaResult removeSalePoint(PuntoVendita p){

        PuntoVenditaDAO pDao = PuntoVenditaDAO.getInstance();
        PuntoVenditaResult result = new PuntoVenditaResult();

        //Verifica esistenza punto vendita
        p = pDao.findByName(p.getNome());
        if (p == null){
            result.setResult(PuntoVenditaResult.Result.SALEPOINT_DOESNT_EXIST);
            result.setMessage("Il punto vendita da rimuovere non esiste! Riprova");
            return result;
        }

        //rimozione del punto vendita (e del rispettivo magazzino tramite cascade)
        int removed = pDao.removeById(p.getIdPuntoVendita());
        if(removed == 0){ //punto vendita non rimosso
            result.setResult(PuntoVenditaResult.Result.SALEPOINT_ERROR);
            result.setMessage("Errore nella rimozione del punto vendita! Riprova!");
            return result;
        }

        //la cancellazione è andata a buon fine
        result.setResult(PuntoVenditaResult.Result.DELETE_OK);
        result.setMessage("Punto vendita rimosso correttamente!");
        return result;
    }

}
