package Business;

import Business.Results.FeedbackResult;
import DAO.ArticoloDAO;
import DAO.ClienteDAO;
import DAO.FeedbackDAO;
import DAO.UtenteDAO;
import Model.Articolo;
import Model.Cliente;
import Model.Feedback;

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
}
