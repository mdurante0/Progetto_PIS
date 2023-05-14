package Business;

import Business.Results.CatalogoResult;

public class CatalogoBusiness {

    private static CatalogoBusiness instance;

    public static synchronized CatalogoBusiness getInstance() {
        if (instance == null) {
            instance = new CatalogoBusiness();
        }
        return instance;
    }

    public CatalogoResult caricaCatalogo(int idPuntoVendita){
        CatalogoResult result = new CatalogoResult();



        return result;
    }

}
