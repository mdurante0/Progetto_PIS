package Business;

import Business.Results.ImmagineResult;
import DAO.ArticoloDAO;
import DAO.ImmagineDAO;
import Model.Articolo;
import Model.Immagine;

import java.io.File;
import java.util.ArrayList;

public class ImmagineBusiness {

    private static ImmagineBusiness instance;

    public static synchronized ImmagineBusiness getInstance() {
        if (instance == null) {
            instance = new ImmagineBusiness();
        }
        return instance;
    }

    public ImmagineResult caricaImmaginiByArticolo(String nomeArticolo){
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

    public ImmagineResult  addImmagine(File file, int idArticolo){
        ImmagineResult immagineResult = new ImmagineResult();
        ImmagineDAO immagineDAO = ImmagineDAO.getInstance();

        if(immagineDAO.add(file, idArticolo) == 0){ //immagine non inserita
            immagineResult.setResult(ImmagineResult.Result.ADD_ERROR);
            immagineResult.setMessage("Errore nell'inserimento dell'immagine! Riprova!");
            return immagineResult;
        }

        immagineResult.setResult(ImmagineResult.Result.ADD_OK);
        immagineResult.setMessage("Immagine aggiunta correttamente!");
        return immagineResult;
    }

    public ImmagineResult removeImmagine(Immagine immagine){

        ImmagineResult result = new ImmagineResult();
        ImmagineDAO immagineDAO = ImmagineDAO.getInstance();

        if(immagineDAO.removeById(immagine.getIdImmagine()) == 0) { //immagine non rimossa
            result.setResult(ImmagineResult.Result.REMOVE_ERROR);
            result.setMessage("Immagine non rimossa! Riprova!");
            return result;
        }

        //la rimozione è andata a buon fine
        result.setResult(ImmagineResult.Result.REMOVE_OK);
        result.setMessage("Immagine rimossa correttamente!");
        return result;
    }

    public ImmagineResult removeImmagineByArticolo(Articolo articolo) {

        ImmagineResult result = new ImmagineResult();
        ImmagineDAO immagineDAO = ImmagineDAO.getInstance();

        if(immagineDAO.removeByArticolo(articolo.getIdArticolo()) == 0) { //immagini non rimosse
            result.setResult(ImmagineResult.Result.REMOVE_ERROR);
            result.setMessage("Immagini non rimosse! Riprova!");
            return result;
        }

        //la rimozione è andata a buon fine
        result.setResult(ImmagineResult.Result.REMOVE_OK);
        result.setMessage("Immagini rimosse correttamente!");
        return result;
    }
}
