package Business;

import DAO.ArticoloDAO;
import Model.Articolo;

public class AmministratoreBusiness {

    private static AmministratoreBusiness instance;

    public static synchronized AmministratoreBusiness getInstance() {
        if (instance == null) {
            instance = new AmministratoreBusiness();
        }
        return instance;
    }

    public ArticoloResult addArticolo(Articolo a){

        ArticoloDAO aDao = ArticoloDAO.getInstance();
        ArticoloResult result = new ArticoloResult();

        //controlli vari

        //inserimento in base al tipo di articolo

        result.setResult(ArticoloResult.Result.ADD_OK);
        return result;
    }
}
