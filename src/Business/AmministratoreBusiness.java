package Business;

import Business.AbstractFactory.FactoryProvider;
import Business.Results.ArticoloResult;
import Business.Results.PuntoVenditaResult;
import DAO.*;
import Model.Articolo;
import Model.Magazzino;
import Model.PuntoVendita;
import Model.Servizio;
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
        boolean articoloExists = aDao.articoloExists(a.getName());
        if (!articoloExists){
            result.setResult(ArticoloResult.Result.ITEM_DOESNT_EXIST);
            result.setMessage("L'articolo da modificare non esiste! Riprova");
            return result;
        } else {
            a = aDao.findByName(a.getName());
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
            result.setMessage("Errore nella modifica dell'articolo! Riprova!");
            return result;
        }

        //la modifica è andata a buon fine
        result.setResult(ArticoloResult.Result.UPDATE_OK);
        result.setMessage("Articolo modificato correttamente!");
        return result;
    }

    public ArticoloResult removeArticolo(Articolo a){

        ArticoloDAO aDao = ArticoloDAO.getInstance();
        ArticoloResult result = new ArticoloResult();

        //Verifica esistenza articolo
        boolean articoloExists = aDao.articoloExists(a.getName());
        if (!articoloExists){
            result.setResult(ArticoloResult.Result.ITEM_DOESNT_EXIST);
            result.setMessage("L'articolo da eliminare non esiste! Riprova");
            return result;
        } else {
            a = aDao.findByName(a.getName());
        }

        int removed = aDao.removeById(a.getIdArticolo());

        if(removed == 0){ //articolo non rimosso
            result.setResult(ArticoloResult.Result.ITEM_ERROR);
            result.setMessage("Errore nella cancellazione dell'articolo! Riprova!");
            return result;
        }

        //la cancellazione è andata a buon fine
        result.setResult(ArticoloResult.Result.DELETE_OK);
        result.setMessage("Articolo inserito correttamente!");
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
}
