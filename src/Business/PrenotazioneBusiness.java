package Business;

import Business.Results.PrenotazioneResult;
import DAO.ClienteDAO;
import DAO.PrenotazioneDAO;
import DAO.UtenteDAO;
import Model.Cliente;
import Model.Prenotazione;
import Model.composite.IProdotto;

import java.util.ArrayList;

public class PrenotazioneBusiness {

    private static PrenotazioneBusiness instance;

    public static synchronized PrenotazioneBusiness getInstance() {
        if (instance == null) {
            instance = new PrenotazioneBusiness();
        }
        return instance;
    }
    public PrenotazioneResult addPrenotazione(Prenotazione prenotazione){
        PrenotazioneResult result = new PrenotazioneResult();
        PrenotazioneDAO prenotazioneDAO = PrenotazioneDAO.getInstance();

        int inserita = prenotazioneDAO.add(prenotazione);
        if(inserita == 0){ //Prenotazione non inserita
            result.setResult(PrenotazioneResult.Result.ADD_ERROR);
            result.setMessage("Errore nella prenotazione del prodotto! Riprova!");
            return result;
        }

        //Prenotazione aggiunta correttamente
        result.setResult(PrenotazioneResult.Result.ADD_OK);
        result.setMessage("Prenotazione aggiunta con successo! Le forniremo il prodotto richiesto al più presto!");
        return result;
    }
    public PrenotazioneResult addProdottoInPrenotazione(Prenotazione prenotazione, IProdotto prodotto){
        PrenotazioneResult result = new PrenotazioneResult();
        PrenotazioneDAO prenotazioneDAO = PrenotazioneDAO.getInstance();

        int inserita = prenotazioneDAO.addProdotto(prenotazione.getIdPrenotazione(),prodotto);
        if(inserita == 0){ //Prenotazione non inserita
            result.setResult(PrenotazioneResult.Result.ADD_ERROR);
            result.setMessage("Errore nella prenotazione del prodotto! Riprova!");
            return result;
        }

        //Prenotazione aggiunta correttamente
        result.setResult(PrenotazioneResult.Result.ADD_OK);
        result.setMessage("Prenotazione aggiunta con successo! Le forniremo il prodotto richiesto al più presto!");
        return result;
    }


    public PrenotazioneResult removePrenotazione(Prenotazione prenotazione){
        PrenotazioneResult result = new PrenotazioneResult();
        PrenotazioneDAO prenotazioneDAO = PrenotazioneDAO.getInstance();


        int rimossa = prenotazioneDAO.removeById(prenotazione.getIdPrenotazione());
        if(rimossa == 0){ //Prenotazione non rimossa
            result.setResult(PrenotazioneResult.Result.REMOVE_ERROR);
            result.setMessage("Errore nella rimozione della prenotazione! Riprova!");
            return result;
        }

            //Prenotazione rimossa correttamente
        result.setResult(PrenotazioneResult.Result.REMOVE_OK);
        result.setMessage("Prenotazione rimossa con successo!");
        return result;
    }
    public PrenotazioneResult removeProdottoInPrenotazione(Prenotazione prenotazione, IProdotto prodotto) {
        PrenotazioneResult result = new PrenotazioneResult();
        PrenotazioneDAO prenotazioneDAO = PrenotazioneDAO.getInstance();


        int rimossa = prenotazioneDAO.removeProdotto(prenotazione.getIdPrenotazione(), prodotto);
        if (rimossa == 0) { //Prenotazione non rimossa
            result.setResult(PrenotazioneResult.Result.REMOVE_ERROR);
            result.setMessage("Errore nella rimozione del prodotto! Riprova!");
            return result;
        }
        //Prenotazione rimossa correttamente
        result.setResult(PrenotazioneResult.Result.REMOVE_OK);
        result.setMessage("Prodotto rimosso con successo!");
        return result;
    }

    public PrenotazioneResult caricaPrenotazioniByUser(String clienteUsername){
        PrenotazioneResult result = new PrenotazioneResult();
        UtenteDAO utenteDAO = UtenteDAO.getInstance();
        ClienteDAO clienteDAO = ClienteDAO.getInstance();
        PrenotazioneDAO prenotazioneDAO = PrenotazioneDAO.getInstance();

        //Verifico l'esistenza del cliente
        if(!utenteDAO.userExists(clienteUsername) || !utenteDAO.isCliente(clienteUsername)){
            result.setResult(PrenotazioneResult.Result.USER_DOESNT_EXIST);
            result.setMessage("Il cliente indicato non esiste! Riprova!");
            return result;
        }

        Cliente c = clienteDAO.findByUsername(clienteUsername);
        ArrayList<Prenotazione> prenotazioni = prenotazioneDAO.findByUser(c.getIdUtente());
        if(prenotazioni == null){ //Non ci sono prenotazioni
            result.setResult(PrenotazioneResult.Result.ITEM_DOESNT_EXIST);
            result.setMessage("Nessuna prenotazione trovata! Può crearne di nuove per richiedere prodotti non attualmente disponibili!");
            return result;
        }

        //prenotazioni caricate correttamente
        result.setPrenotazioni(prenotazioni);
        result.setResult(PrenotazioneResult.Result.PRENOTAZIONI_CARICATE);
        result.setMessage("Prenotazioni caricate correttamente!");
        return result;
    }
    public PrenotazioneResult caricaPrenotazioni(){
        PrenotazioneResult result = new PrenotazioneResult();
        PrenotazioneDAO prenotazioneDAO = PrenotazioneDAO.getInstance();

        ArrayList<Prenotazione> prenotazioni = prenotazioneDAO.findAll();
        if(prenotazioni.isEmpty()){ //Non ci sono prenotazioni
            result.setResult(PrenotazioneResult.Result.ITEM_DOESNT_EXIST);
            result.setMessage("Nessuna prenotazione trovata!");
            return result;
        }

        //prenotazioni caricate correttamente
        result.setPrenotazioni(prenotazioni);
        result.setResult(PrenotazioneResult.Result.PRENOTAZIONI_CARICATE);
        result.setMessage("Prenotazioni caricate correttamente!");
        return result;
    }

}
