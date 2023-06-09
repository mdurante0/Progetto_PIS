package Business;

import Business.Results.ImmagineResult;
import DAO.ArticoloDAO;
import DAO.ImmagineDAO;
import Model.Articolo;
import Model.Immagine;

import java.util.ArrayList;

public class ImmagineBusiness {

    private static ImmagineBusiness instance;

    public static synchronized ImmagineBusiness getInstance() {
        if (instance == null) {
            instance = new ImmagineBusiness();
        }
        return instance;
    }

    public ImmagineResult caricaImmaginiProdotto(String nomeArticolo){
        ImmagineResult result = new ImmagineResult();
        ImmagineDAO immagineDAO = ImmagineDAO.getInstance();
        ArticoloDAO articoloDAO = ArticoloDAO.getInstance();

        //Verifico l'esistenza dell'articolo
        if(!articoloDAO.articoloExists(nomeArticolo)){
            result.setResult(ImmagineResult.Result.ITEM_DOESNT_EXIST);
            result.setMessage("Immagini non caricate! Articolo non trovato!");
            return result;
        }
        Articolo articolo = articoloDAO.findByName(nomeArticolo);

        ArrayList<Immagine> listaImmagini = immagineDAO.findByArticolo(articolo.getIdArticolo());
        if(listaImmagini.isEmpty()){ //L'articolo non ha immagini
            result.setResult(ImmagineResult.Result.IMAGE_DOESNT_EXIST);
            result.setMessage("Immagini non caricate! L'articolo non ha immagini!");
            return result;

        }else result.setListaImmagini(listaImmagini); //Immagini caricate

        result.setResult(ImmagineResult.Result.IMMAGINI_CARICATE);
        result.setMessage("Immagini caricate correttamente");
        return result;
    }
}
