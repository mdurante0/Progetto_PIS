package Business;

import Business.AbstractFactory.FactoryProvider;
import Business.AbstractFactory.ICategoria;
import Business.Results.ArticoloResult;
import Business.Results.CategoriaResult;
import Business.Results.PuntoVenditaResult;
import DAO.*;
import Model.*;
import Model.composite.Prodotto;
import Model.composite.ProdottoComposito;

public class AmministratoreBusiness {

    private static AmministratoreBusiness instance;

    public static synchronized AmministratoreBusiness getInstance() {
        if (instance == null) {
            instance = new AmministratoreBusiness();
        }
        return instance;
    }

    public ArticoloResult addArticolo(Articolo a, FactoryProvider.FactoryType type){

        ArticoloDAO aDao = ArticoloDAO.getInstance();
        ArticoloResult result = new ArticoloResult();

        //Verifica esistenza articolo
        boolean articoloExists = aDao.articoloExists(a.getName());
        if (articoloExists){
            result.setResult(ArticoloResult.Result.ITEM_ALREADY_EXISTS);
            result.setMessage("L'articolo da inserire è già esistente! Riprova");
            return result;
        }

        //inserimento in base al tipo di articolo
        int inserito;
        switch (type){
            case PRODOTTO: {
                Prodotto p = (Prodotto) a;
                ProdottoDAO pDao = ProdottoDAO.getInstance();
                inserito = pDao.add(p);
                break;
            }
            case PRODOTTO_COMPOSITO: {
                ProdottoComposito pc = (ProdottoComposito) a;
                ProdottoCompositoDAO pcDao = ProdottoCompositoDAO.getInstance();
                inserito = pcDao.add(pc);
                break;
            }
            case SERVIZIO: {
                Servizio s = (Servizio) a;
                ServizioDAO sDao = ServizioDAO.getInstance();
                inserito = sDao.add(s);
                break;
            }
            default: { //errore nel tipo di articolo
                result.setResult(ArticoloResult.Result.WRONG_TYPE);
                result.setMessage("Errore nella specificazione dell'articolo! Riprova!");
                return result;
            }
        }

        if(inserito == 0){ //articolo non inserito
            result.setResult(ArticoloResult.Result.ITEM_ERROR);
            result.setMessage("Errore nell'inseerimento dell'articolo! Riprova!");
            return result;
        }

        //l'inserimento è andato a buon fine
        result.setResult(ArticoloResult.Result.ADD_OK);
        result.setMessage("Articolo inserito correttamente!");
        return result;
    }

    public ArticoloResult updateArticolo(Articolo a, FactoryProvider.FactoryType type){

        ArticoloDAO aDao = ArticoloDAO.getInstance();
        ArticoloResult result = new ArticoloResult();

        //Verifica esistenza articolo
        if (aDao.findById(a.getIdArticolo()) == null){
            result.setResult(ArticoloResult.Result.ITEM_DOESNT_EXIST);
            result.setMessage("L'articolo da aggiornare non esiste! Riprova");
            return result;
        }

        //modifica in base al tipo di articolo
        int updated;
        switch (type){
            case PRODOTTO: {
                Prodotto p = (Prodotto) a;
                ProdottoDAO pDao = ProdottoDAO.getInstance();
                updated = pDao.update(p);
                break;
            }
            case PRODOTTO_COMPOSITO: {
                ProdottoComposito pc = (ProdottoComposito) a;
                ProdottoCompositoDAO pcDao = ProdottoCompositoDAO.getInstance();
                updated = pcDao.update(pc);
                break;
            }
            case SERVIZIO: {
                Servizio s = (Servizio) a;
                ServizioDAO sDao = ServizioDAO.getInstance();
                updated = sDao.update(s);
                break;
            }
            default: { //errore nel tipo di articolo
                result.setResult(ArticoloResult.Result.WRONG_TYPE);
                result.setMessage("Errore nella specificazione dell'articolo! Riprova!");
                return result;
            }
        }

        if(updated == 0){ //articolo non modificato
            result.setResult(ArticoloResult.Result.ITEM_ERROR);
            result.setMessage("Errore nell'aggiornamento dell'articolo! Riprova!");
            return result;
        }

        //la modifica è andata a buon fine
        result.setResult(ArticoloResult.Result.UPDATE_OK);
        result.setMessage("Articolo aggioranto correttamente!");
        return result;
    }

    public ArticoloResult removeArticolo(Articolo a){

        ArticoloDAO aDao = ArticoloDAO.getInstance();
        ArticoloResult result = new ArticoloResult();

        //Verifica esistenza articolo
        boolean articoloExists = aDao.articoloExists(a.getName());
        if (!articoloExists){
            result.setResult(ArticoloResult.Result.ITEM_DOESNT_EXIST);
            result.setMessage("L'articolo da rimuovere non esiste! Riprova");
            return result;
        } else {
            a = aDao.findByName(a.getName());
        }

        int removed = aDao.removeById(a.getIdArticolo());

        if(removed == 0){ //articolo non rimosso
            result.setResult(ArticoloResult.Result.ITEM_ERROR);
            result.setMessage("Errore nella rimozione dell'articolo! Riprova!");
            return result;
        }

        //la cancellazione è andata a buon fine
        result.setResult(ArticoloResult.Result.DELETE_OK);
        result.setMessage("Articolo rimosso correttamente!");
        return result;
    }

    public PuntoVenditaResult addSalePoint(PuntoVendita p, Magazzino m){

        PuntoVenditaResult result = new PuntoVenditaResult();

        //verifico l'esistenza del punto vendita
        PuntoVenditaDAO pDao = PuntoVenditaDAO.getInstance();
        if(pDao.findByName(p.getNome()) != null){
            result.setResult(PuntoVenditaResult.Result.SALEPOINT_ALREADY_EXISTS);
            result.setMessage("Il punto vendita inserito è già esistente! Riprova!");
            return result;
        }

        //inserisco il nuovo punto vendita e il nuovo magazzino
        MagazzinoDAO mDao = MagazzinoDAO.getInstance();
        if(mDao.add(m) == 0) { //magazzino non inserito
            result.setResult(PuntoVenditaResult.Result.DEPOSIT_ERROR);
            result.setMessage("Punto vendita non inserito! Verifica il magazzino! Riprova!");
            return result;

        } else if(pDao.add(p) == 0){ //punto vendita non inserito
            result.setResult(PuntoVenditaResult.Result.SALEPOINT_ERROR);
            result.setMessage("Punto vendita non inserito! Riprova!");
            return result;
        }

        //l'inserimento è andato a buon fine
        result.setResult(PuntoVenditaResult.Result.ADD_OK);
        result.setMessage("Punto vendita inserito correttamente!");
        return result;
    }

    public PuntoVenditaResult updateSalePoint(PuntoVendita p){

        PuntoVenditaResult result = new PuntoVenditaResult();

        //verifico l'esistenza del punto vendita
        PuntoVenditaDAO pDao = PuntoVenditaDAO.getInstance();
        if(pDao.findById(p.getIdPuntoVendita()) != null){
            result.setResult(PuntoVenditaResult.Result.SALEPOINT_DOESNT_EXIST);
            result.setMessage("Il punto vendita da aggiornare non esiste! Riprova!");
            return result;
        }

        //aggiorno il punto vendita
        if(pDao.update(p) == 0){ //punto vendita non aggiornato
            result.setResult(PuntoVenditaResult.Result.SALEPOINT_ERROR);
            result.setMessage("Punto vendita non aggiornato! Riprova!");
            return result;
        }

        //l'aggiornamento è andato a buon fine
        result.setResult(PuntoVenditaResult.Result.UPDATE_SALEPOINT_OK);
        result.setMessage("Punto vendita aggiornato correttamente!");
        return result;
    }

    public PuntoVenditaResult updateDeposit(Magazzino m){

        PuntoVenditaResult result = new PuntoVenditaResult();

        //verifico l'esistenza del magazzino
        MagazzinoDAO mDao = MagazzinoDAO.getInstance();
        if(mDao.findById(m.getIdMagazzino()) != null){
            result.setResult(PuntoVenditaResult.Result.DEPOSIT_DOESNT_EXIST);
            result.setMessage("Il magazzino da aggiornare non esiste! Riprova!");
            return result;
        }

        //aggiorno il punto vendita
        if(mDao.update(m) == 0){ //magazzino non aggiornato
            result.setResult(PuntoVenditaResult.Result.DEPOSIT_ERROR);
            result.setMessage("Magazzino non aggiornato! Riprova!");
            return result;
        }

        //l'aggiornamento è andato a buon fine
        result.setResult(PuntoVenditaResult.Result.UPDATE_DEPOSIT_OK);
        result.setMessage("Magazzino aggiornato correttamente!");
        return result;
    }

    public PuntoVenditaResult removeSalePoint(PuntoVendita p){

        PuntoVenditaDAO pDao = PuntoVenditaDAO.getInstance();
        PuntoVenditaResult result = new PuntoVenditaResult();

        //Verifica esistenza punto vendita
        p = pDao.findByName(p.getNome());
        if (p == null){
            result.setResult(PuntoVenditaResult.Result.SALEPOINT_DOESNT_EXIST);
            result.setMessage("Il punto vendita da rimuovere non esiste! Riprova");
            return result;
        }

        //rimozione del punto vendita (e del rispettivo magazzino tramite cascade)
        int removed = pDao.removeById(p.getIdPuntoVendita());
        if(removed == 0){ //punto vendita non rimosso
            result.setResult(PuntoVenditaResult.Result.SALEPOINT_ERROR);
            result.setMessage("Errore nella rimozione del punto vendita! Riprova!");
            return result;
        }

        //la cancellazione è andata a buon fine
        result.setResult(PuntoVenditaResult.Result.DELETE_OK);
        result.setMessage("Punto vendita rimosso correttamente!");
        return result;
    }

    public CategoriaResult addCategoria(ICategoria categoria){

        CategoriaResult result = new CategoriaResult();

        //verifico l'esistenza della categoria
        CategoriaProdottoDAO cpDao = CategoriaProdottoDAO.getInstance();
        CategoriaServizioDAO csDao = CategoriaServizioDAO.getInstance();
        if(cpDao.findByName(categoria.getNome()) != null  || csDao.findByName(categoria.getNome()) != null){
            result.setResult(CategoriaResult.Result.CATEGORY_ALREADY_EXISTS);
            result.setMessage("La categoria inserita è già esistente! Riprova!");
            return result;
        }

        //inserisco la nuova categoria
        if (categoria instanceof CategoriaProdotto) {
            if (cpDao.add((CategoriaProdotto) categoria) == 0) { //categoria non inserita
                result.setResult(CategoriaResult.Result.CATEGORY_ERROR);
                result.setMessage("Categoria non inserita! Riprova!");
                return result;
            }
        } else {
            if(csDao.add((CategoriaServizio) categoria) == 0) { //categoria non inserita
                result.setResult(CategoriaResult.Result.CATEGORY_ERROR);
                result.setMessage("Categoria non inserita! Riprova!");
                return result;
            }
        }

        //l'inserimento è andato a buon fine
        result.setResult(CategoriaResult.Result.ADD_OK);
        result.setMessage("Punto vendita inserito correttamente!");
        return result;
    }
}
