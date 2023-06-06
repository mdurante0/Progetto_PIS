package Business;

import Business.Results.FeedbackResult;
import DAO.ArticoloDAO;
import DAO.FeedbackDAO;
import Model.Articolo;
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
        ArrayList<Feedback> feedbacks = fDao.findByArticolo(a.getIdArticolo());
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
}
