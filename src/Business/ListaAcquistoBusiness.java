package Business;

import Business.Results.ListaAcquistoResult;
import DAO.ArticoloDAO;
import DAO.ClienteDAO;
import DAO.ListaAcquistoDAO;
import DAO.UtenteDAO;
import Model.Articolo;
import Model.Cliente;
import Model.ListaAcquisto;

import java.util.ArrayList;

public class ListaAcquistoBusiness {

    private static ListaAcquistoBusiness instance;

    public static synchronized ListaAcquistoBusiness getInstance() {
        if (instance == null) {
            instance = new ListaAcquistoBusiness();
        }
        return instance;
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
        Cliente c = clienteDAO.findByUsername(clienteUsername);
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

        Cliente c = clienteDAO.findByUsername(clienteUsername);
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
