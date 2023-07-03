package Business;

import Business.Results.FeedbackResult;
import DAO.ArticoloDAO;
import DAO.ClienteDAO;
import DAO.FeedbackDAO;
import DAO.UtenteDAO;
import Model.Articolo;
import Model.Cliente;
import Model.Feedback;

import java.util.ArrayList;

public class FeedbackBusiness {

    private static FeedbackBusiness instance;

    public static synchronized FeedbackBusiness getInstance() {
        if (instance == null) {
            instance = new FeedbackBusiness();
        }
        return instance;
    }

    public FeedbackResult caricaFeedback(String nomeArticolo){
        FeedbackDAO fDao = FeedbackDAO.getInstance();
        ArticoloDAO aDao = ArticoloDAO.getInstance();
        FeedbackResult result = new FeedbackResult();
        Articolo a;

        //Verifico l'esistenza dell'articolo
        if(!aDao.articoloExists(nomeArticolo)){
            result.setResult(FeedbackResult.Result.ITEM_DOESNT_EXIST);
            result.setMessage("L'articolo indicato non esiste! Riprova!");
            return result;
        } else a = aDao.findByName(nomeArticolo);

        //Carico i feedback
        ArrayList<Feedback> feedbacks = fDao.findAllByArticolo(a.getIdArticolo());
        if(feedbacks.isEmpty()){ //Non ci sono feedback
            result.setResult(FeedbackResult.Result.FEEDBACK_DOESNT_EXIST);
            result.setMessage("Non ci sono feedback per questo articolo!");
            return result;
        }

        //Feedback caricati correttamente
        result.setFeedbacks(feedbacks);
        result.setResult(FeedbackResult.Result.FEEDBACK_CARICATI);
        result.setMessage("Feeback caricati correttamente!");
        return result;
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
        Cliente c = clienteDAO.findByUsername(clienteUsername);
        if(!articoloDAO.isAcquistato(a.getIdArticolo(), c)){
            result.setResult(FeedbackResult.Result.ACQUISTO_ERROR);
            result.setMessage("L'articolo che si vuole recensire non è stato acquistato!");
            return result;
        }

        //Aggiungo il feedback
        feedback.setArticolo(a);
        feedback.setCliente(c);
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
    public FeedbackResult rispondi(Feedback feedback, String risposta){
        FeedbackResult result = new FeedbackResult();
        FeedbackDAO feedbackDAO = FeedbackDAO.getInstance();

        //Verifico l'esistenza del feedback
        feedback = feedbackDAO.findById(feedback.getIdFeedback());
        if(feedback == null){
            result.setResult(FeedbackResult.Result.ITEM_DOESNT_EXIST);
            result.setMessage("Feedback non trovato! Riprova!");
            return result;
        }

        //Aggiungo la risposta
        feedback.setRisposta(risposta);
        int risposto = feedbackDAO.setRisposta(feedback.getIdFeedback(), risposta);
        if(risposto == 0){ //Risposta non inserita
            result.setResult(FeedbackResult.Result.UPDATE_ERROR);
            result.setMessage("Risposta non inserita! Riprova!");
            return result;
        }

        //Risposta inserita correttamente
        result.setResult(FeedbackResult.Result.UPDATE_OK);
        result.setMessage("Risposta inserita correttamente!");
        return result;
    }

}
