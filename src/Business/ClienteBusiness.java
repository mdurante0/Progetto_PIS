package Business;

import Business.Results.FeedbackResult;
import Business.Results.ListaAcquistoResult;
import Business.Results.PrenotazioneResult;
import DAO.*;
import Model.*;

import java.util.ArrayList;

public class ClienteBusiness {

    private static ClienteBusiness instance;

    public static synchronized ClienteBusiness getInstance() {
        if (instance == null) {
            instance = new ClienteBusiness();
        }
        return instance;
    }

    public FeedbackResult creaFeedback(Feedback feedback, String clienteUsername, String articoloName){
        FeedbackResult result = new FeedbackResult();
        FeedbackDAO feedbackDAO = FeedbackDAO.getInstance();
        UtenteDAO utenteDAO = UtenteDAO.getInstance();
        ClienteDAO clienteDAO = ClienteDAO.getInstance();
        ArticoloDAO articoloDAO = ArticoloDAO.getInstance();

        //Verifico l'esistenza del cliente
        if(!utenteDAO.userExists(clienteUsername) || !utenteDAO.isCliente(clienteUsername)){
            result.setResult(FeedbackResult.Result.USER_DOESNT_EXIST);
            result.setMessage("Cliente non trovato! Riprova!");
            return result;
        }

        //Verifico l'esistenza dell'articolo
        if(!articoloDAO.articoloExists(articoloName)){
            result.setResult(FeedbackResult.Result.ITEM_DOESNT_EXIST);
            result.setMessage("Articolo non trovato! Riprova!");
            return result;
        }

        //Verifico che il cliente abbia precedentemente acquistato l'articolo da recensire
        Articolo a = articoloDAO.findByName(articoloName);
        Cliente c = clienteDAO.findById(clienteUsername);
        if(!articoloDAO.isAcquistato(a.getIdArticolo(), c)){
            result.setResult(FeedbackResult.Result.ACQUISTO_ERROR);
            result.setMessage("L'articolo che si vuole recensire non è stato acquistato!");
            return result;
        }

        //Aggiungo il feedback
        feedback.setIdArticolo(a.getIdArticolo());
        feedback.setIdUtente(c.getIdUtente());
        int inserito = feedbackDAO.add(feedback);
        if(inserito == 0){ //Feedback non inserito
            result.setResult(FeedbackResult.Result.ADD_ERROR);
            result.setMessage("Feedback non inserito! Riprova!");
            return result;
        }

        //Feedback inserito correttamente
        result.setResult(FeedbackResult.Result.ADD_OK);
        result.setMessage("Articolo recensito correttamente! La sua opinione è importante per noi!");
        return result;
    }

    public PrenotazioneResult addPrenotazione(Prenotazione prenotazione, String clienteUsername){
        PrenotazioneResult result = new PrenotazioneResult();
        PrenotazioneDAO prenotazioneDAO = PrenotazioneDAO.getInstance();
        UtenteDAO utenteDAO = UtenteDAO.getInstance();

        //Verifico l'esistenza dell'utente
        if(!utenteDAO.userExists(clienteUsername) || !utenteDAO.isCliente(clienteUsername)){
            result.setResult(PrenotazioneResult.Result.USER_DOESNT_EXIST);
            result.setMessage("Il cliente indicato non esiste! Riprova!");
            return result;
        }

        ClienteDAO clienteDAO = ClienteDAO.getInstance();
        Cliente c = clienteDAO.findById(clienteUsername);
        prenotazione.setIdUtente(c.getIdUtente());
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

    public PrenotazioneResult removePrenotazione(Prenotazione prenotazione){
        PrenotazioneResult result = new PrenotazioneResult();
        PrenotazioneDAO prenotazioneDAO = PrenotazioneDAO.getInstance();


        int rimossa = prenotazioneDAO.removeById(prenotazione.getIdPrenotazione());
        if(rimossa == 0){ //Prenotazione non rimossa
            result.setResult(PrenotazioneResult.Result.REMOVE_ERROR);
            result.setMessage("Errore nella rimozione della rimozione! Riprova!");
            return result;
        }

        //Prenotazione rimossa correttamente
        result.setResult(PrenotazioneResult.Result.REMOVE_OK);
        result.setMessage("Prenotazione rimossa con successo!");
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

        Cliente c = clienteDAO.findById(clienteUsername);
        ArrayList<Prenotazione> prenotazioni = prenotazioneDAO.findByUser(c.getIdUtente());
        if(prenotazioni.isEmpty()){ //Non ci sono prenotazioni
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

    public ListaAcquistoResult addListaAcquisto(ListaAcquisto listaAcquisto, String clienteUsername){
        ListaAcquistoResult result = new ListaAcquistoResult();
        ListaAcquistoDAO listaAcquistoDAO = ListaAcquistoDAO.getInstance();
        UtenteDAO utenteDAO = UtenteDAO.getInstance();

        //Verifico l'esistenza del cliente
        if(!utenteDAO.userExists(clienteUsername) || !utenteDAO.isCliente(clienteUsername)){
            result.setResult(ListaAcquistoResult.Result.USER_DOESNT_EXIST);
            result.setMessage("Il cliente indicato non esiste! Riprova!");
            return result;
        }

        ClienteDAO clienteDAO = ClienteDAO.getInstance();
        Cliente c = clienteDAO.findById(clienteUsername);
        listaAcquisto.setIdUtente(c.getIdUtente());
        int inserita = listaAcquistoDAO.add(listaAcquisto);
        if(inserita == 0){ //Lista d'acquisto non inserita
            result.setResult(ListaAcquistoResult.Result.ADD_ERROR);
            result.setMessage("Errore nella creazione della lista d'acquisto! Riprova!");
            return result;
        }

        //Lista d'acquisto aggiunta correttamente
        result.setResult(ListaAcquistoResult.Result.ADD_OK);
        result.setMessage("Lista d'acquisto aggiunta con successo!");
        return result;
    }

    public ListaAcquistoResult removeListaAcquisto(ListaAcquisto listaAcquisto){
        ListaAcquistoResult result = new ListaAcquistoResult();
        ListaAcquistoDAO listaAcquistoDAO = ListaAcquistoDAO.getInstance();

        int rimossa = listaAcquistoDAO.removeById(listaAcquisto.getIdLista());
        if(rimossa == 0){ //Lista d'acquisto non rimossa
            result.setResult(ListaAcquistoResult.Result.REMOVE_ERROR);
            result.setMessage("Errore nella cancellazione della lista d'acquisto! Riprova!");
            return result;
        }

        //Lista d'acquisto rimossa correttamente
        result.setResult(ListaAcquistoResult.Result.ADD_OK);
        result.setMessage("Lista d'acquisto rimossa con successo!");
        return result;
    }

    public ListaAcquistoResult caricaListeAcquisto(String clienteUsername){
        ListaAcquistoResult result = new ListaAcquistoResult();
        UtenteDAO utenteDAO = UtenteDAO.getInstance();
        ClienteDAO clienteDAO = ClienteDAO.getInstance();
        ListaAcquistoDAO listaAcquistoDAO = ListaAcquistoDAO.getInstance();

        //Verifico l'esistenza del cliente
        if(!utenteDAO.userExists(clienteUsername) || !utenteDAO.isCliente(clienteUsername)){
            result.setResult(ListaAcquistoResult.Result.USER_DOESNT_EXIST);
            result.setMessage("Il cliente indicato non esiste! Riprova!");
            return result;
        }

        Cliente c = clienteDAO.findById(clienteUsername);
        ArrayList<ListaAcquisto> liste = listaAcquistoDAO.findByUser(c.getIdUtente());
        if(liste.isEmpty()){ //Non ci sono liste d'acquisto
            result.setResult(ListaAcquistoResult.Result.ITEM_DOESNT_EXIST);
            result.setMessage("Nessuna lista d'acquisto trovate! Può crearne di nuove per effettuare nuovi acquisti!");
            return result;
        }

        //Liste d'acquisto caricate correttamente
        result.setListeAcquisto(liste);
        result.setResult(ListaAcquistoResult.Result.LISTE_CARICATE);
        result.setMessage("Liste d'acquisto caricate correttamente!");
        return result;
    }

    public ListaAcquistoResult addArticoloInListaAcquisto(ListaAcquisto listaAcquisto, String nomeArticolo){
        ListaAcquistoResult result = new ListaAcquistoResult();
        ListaAcquistoDAO listaAcquistoDAO = ListaAcquistoDAO.getInstance();
        ArticoloDAO articoloDAO = ArticoloDAO.getInstance();

        //Verifico l'esistenza dell'articolo
        if(!articoloDAO.articoloExists(nomeArticolo)){
            result.setResult(ListaAcquistoResult.Result.ITEM_DOESNT_EXIST);
            result.setMessage("Articolo non trovato! Riprova!");
            return result;
        }

        Articolo articolo = articoloDAO.findByName(nomeArticolo);
        int aggiunto = listaAcquistoDAO.addArticolo(listaAcquisto.getIdLista(), articolo);
        if(aggiunto == 0){ //Articolo non inserito
            result.setResult(ListaAcquistoResult.Result.ADD_ERROR);
            result.setMessage("Articolo non inserito! Riprova!");
            return result;
        }

        //Articolo inserito correttamente
        result.setResult(ListaAcquistoResult.Result.ADD_OK);
        result.setMessage("Articolo inserito correttamente!");
        return result;
    }

    public ListaAcquistoResult removeArticoloFromListaAcquisto(ListaAcquisto listaAcquisto, String nomeArticolo){
        ListaAcquistoResult result = new ListaAcquistoResult();
        ListaAcquistoDAO listaAcquistoDAO = ListaAcquistoDAO.getInstance();
        ArticoloDAO articoloDAO = ArticoloDAO.getInstance();

        //Verifico l'esistenza dell'articolo
        if(!articoloDAO.articoloExists(nomeArticolo)){
            result.setResult(ListaAcquistoResult.Result.ITEM_DOESNT_EXIST);
            result.setMessage("Articolo non trovato! Riprova!");
            return result;
        }

        Articolo articolo = articoloDAO.findByName(nomeArticolo);
        int rimosso = listaAcquistoDAO.removeArticolo(listaAcquisto.getIdLista(), articolo);
        if(rimosso == 0){ //Articolo non rimosso
            result.setResult(ListaAcquistoResult.Result.REMOVE_ERROR);
            result.setMessage("Articolo non rimosso! Riprova!");
            return result;
        }

        //Articolo rimosso correttamente
        result.setResult(ListaAcquistoResult.Result.REMOVE_OK);
        result.setMessage("Articolo rimosso correttamente!");
        return result;
    }
}
