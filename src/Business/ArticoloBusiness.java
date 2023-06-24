package Business;

import Business.Results.ArticoloResult;
import DAO.*;
import Model.*;
import Model.composite.IProdotto;
import Model.composite.Prodotto;
import Model.composite.ProdottoComposito;

public class ArticoloBusiness {

    private static ArticoloBusiness instance;

    public static synchronized ArticoloBusiness getInstance() {
        if (instance == null) {
            instance = new ArticoloBusiness();
        }
        return instance;
    }
    public ArticoloResult addArticolo(Articolo a){

        ArticoloDAO aDao = ArticoloDAO.getInstance();

        ArticoloResult result = new ArticoloResult();

        //Verifica esistenza articolo
        boolean articoloExists = aDao.articoloExists(a.getName());
        if (articoloExists){
            a.setIdArticolo(aDao.findByName(a.getName()).getIdArticolo());
            result.setResult(ArticoloResult.Result.ITEM_ALREADY_EXISTS);
            result.setMessage("L'articolo da inserire è già esistente! Riprova");
            return result;
        }

        //inserimento in base al tipo di articolo
        int inserito;

        if(a instanceof Prodotto) {
            ProdottoDAO pDao = ProdottoDAO.getInstance();
            inserito = pDao.add((Prodotto) a);
        }

        else if(a instanceof Servizio){
            ServizioDAO sDao = ServizioDAO.getInstance();
            inserito = sDao.add((Servizio) a);
        }

        else if(a instanceof ProdottoComposito) {
            ProdottoCompositoDAO pcDao = ProdottoCompositoDAO.getInstance();
            inserito = pcDao.add((ProdottoComposito) a);
        }

        else { //errore nel tipo di articolo
            result.setResult(ArticoloResult.Result.WRONG_TYPE);
            result.setMessage("Errore nella specificazione dell'articolo! Riprova!");
            return result;
        }

        if(inserito == 0){ //articolo non inserito
            result.setResult(ArticoloResult.Result.ITEM_ERROR);
            result.setMessage("Errore nell'inserimento dell'articolo! Riprova!");
            return result;
        }

        //l'inserimento è andato a buon fine
        result.setResult(ArticoloResult.Result.ADD_OK);
        result.setMessage("Articolo inserito correttamente!");
        return result;
    }

    public ArticoloResult addProdottoToMagazzino(IProdotto p, int idMagazzino){

        ProdottoDAO pDao = ProdottoDAO.getInstance();
        MagazzinoDAO mDao = MagazzinoDAO.getInstance();
        ArticoloResult result = new ArticoloResult();

        //Verifica esistenza articolo
        IProdotto prodotto = pDao.findByName(p.getName());
        boolean prodottoExists = prodotto != null;
        if (!prodottoExists){
            result.setResult(ArticoloResult.Result.ITEM_DOESNT_EXIST);
            result.setMessage("L'articolo da inserire nel magazzino non esiste! Riprova");
            return result;
        }

        int inserito = mDao.addProdotto(idMagazzino,p);
        if(inserito == 0){ //articolo non inserito
            result.setResult(ArticoloResult.Result.ITEM_ERROR);
            result.setMessage("Articolo gia presente in questo punto vendita! Riprova!");
            return result;
        }

        //l'inserimento è andato a buon fine
        result.setResult(ArticoloResult.Result.ADD_OK);
        result.setMessage("Articolo inserito correttamente!");
        return result;
    }

    public ArticoloResult updateArticolo(Articolo a){

        ArticoloResult result = new ArticoloResult();

        //aggiornamento in base al tipo di articolo
        int aggiornato;

        if(a instanceof ProdottoComposito pc) {
            ProdottoCompositoDAO pcDao = ProdottoCompositoDAO.getInstance();
            aggiornato = pcDao.update(pc);
        }
        else if(a instanceof Prodotto p) {
            ProdottoDAO pDao = ProdottoDAO.getInstance();
            aggiornato = pDao.update(p);
        }

        else if(a instanceof Servizio s){
            ServizioDAO sDao = ServizioDAO.getInstance();
            aggiornato = sDao.update(s);
        }

        else { //errore nel tipo di articolo
            result.setResult(ArticoloResult.Result.WRONG_TYPE);
            result.setMessage("Errore nella specificazione dell'articolo! Riprova!");
            return result;
        }

        if(aggiornato == 0){ //articolo non aggiornato
            result.setResult(ArticoloResult.Result.ITEM_ERROR);
            result.setMessage("Errore nell'aggiornamento dell'articolo! Riprova!");
            return result;
        }

        //l'aggiornamento è andato a buon fine
        result.setResult(ArticoloResult.Result.UPDATE_OK);
        result.setMessage("Articolo aggiornato correttamente!");
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

    public ArticoloResult removeProdottoFromMagazzino(IProdotto p, int idMagazzino){

        MagazzinoDAO mDao = MagazzinoDAO.getInstance();
        ArticoloResult result = new ArticoloResult();

        int removed = mDao.removeProdotto(idMagazzino,p);
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

    public ArticoloResult addArticoloToCategoria(Articolo a, int idCategoria){

        ArticoloDAO aDao = ArticoloDAO.getInstance();
        ArticoloResult result = new ArticoloResult();

        //Verifica esistenza articolo
        boolean articoloExists = aDao.articoloExists(a.getName());
        if (!articoloExists){
            result.setResult(ArticoloResult.Result.ITEM_DOESNT_EXIST);
            result.setMessage("L'articolo a cui assegnare la categoria non esiste! Riprova");
            return result;
        }

        int assegnato;
        if(a instanceof IProdotto p) {
            CategoriaProdottoDAO cDao = CategoriaProdottoDAO.getInstance();
            ProdottoDAO pDao = ProdottoDAO.getInstance();
            p.setCategoria(cDao.findById(idCategoria));
            assegnato = pDao.update(p);
        } else  {
            CategoriaServizioDAO cDao = CategoriaServizioDAO.getInstance();
            ServizioDAO sDao = ServizioDAO.getInstance();
            Servizio s = (Servizio) a;
            s.setCategoria(cDao.findById(idCategoria));
            assegnato = sDao.update(s);
        }

        if (assegnato == 0) { //categoria non assegnata
            result.setResult(ArticoloResult.Result.ITEM_ERROR);
            result.setMessage("Errore nell'assegnamento della categoria! Riprova!");
            return result;
        }

        //l'assegnazione è andata a buon fine
        result.setResult(ArticoloResult.Result.ADD_OK);
        result.setMessage("Categoria assegnata correttamente!");
        return result;
    }
    public ArticoloResult removeArticoloFromCategoria(Articolo a){

        ArticoloDAO aDao = ArticoloDAO.getInstance();
        ArticoloResult result = new ArticoloResult();

        //Verifica esistenza articolo
        boolean articoloExists = aDao.articoloExists(a.getName());
        if (!articoloExists){
            result.setResult(ArticoloResult.Result.ITEM_DOESNT_EXIST);
            result.setMessage("L'articolo a cui assegnare la categoria non esiste! Riprova");
            return result;
        }

        int rimossa;

        if(a instanceof IProdotto p) {
            ProdottoDAO pDao = ProdottoDAO.getInstance();
            a.setCategoria(new CategoriaProdotto());
            p.setCategoria(new CategoriaProdotto());
            rimossa = pDao.update(p);
        } else  {
            ServizioDAO sDao = ServizioDAO.getInstance();
            a.setCategoria(new CategoriaServizio());
            Servizio s = (Servizio) a;
            rimossa = sDao.update(s);
        }

        if(rimossa == 0){ //categoria rimossa
            result.setResult(ArticoloResult.Result.ITEM_ERROR);
            result.setMessage("Errore nella rimozione della categoria! Riprova!");
            return result;
        }

        //la rimozione è andata a buon fine
        result.setResult(ArticoloResult.Result.DELETE_OK);
        result.setMessage("Categoria rimossa correttamente!");
        return result;
    }
    public ArticoloResult updateProdottoInMagazzino(IProdotto prodotto, Magazzino magazzino){
        MagazzinoDAO mDao = MagazzinoDAO.getInstance();
        ProdottoDAO pDao = ProdottoDAO.getInstance();
        ArticoloResult result = new ArticoloResult();


        //Verifica esistenza articolo
        if (!mDao.prodottoExists(magazzino.getIdMagazzino(), prodotto.getIdArticolo())){
            result.setResult(ArticoloResult.Result.ITEM_DOESNT_EXIST);
            result.setMessage("L'articolo indicato non è presente nel magazzino! Riprova");
            return result;
        }

        int aggiornato = mDao.updateProdotto(magazzino,prodotto);
        if(aggiornato == 0){ //articolo non aggiornato
            result.setResult(ArticoloResult.Result.ITEM_ERROR);
            result.setMessage("Errore nella modifica! Riprova!");
            return result;
        }

        //la modifica è andata a buon fine
        result.setResult(ArticoloResult.Result.UPDATE_OK);
        result.setMessage("Modifica effettuata correttamente!");
        return result;
    }

    public ArticoloResult getType(Articolo a){

        ArticoloDAO aDao = ArticoloDAO.getInstance();
        ArticoloResult result = new ArticoloResult();

        //Verifica esistenza articolo
        boolean articoloExists = aDao.articoloExists(a.getName());
        if (!articoloExists){
            result.setResult(ArticoloResult.Result.ITEM_DOESNT_EXIST);
            result.setMessage("L'articolo da cercare non esiste! Riprova");
            return result;
        }

        //verifica tipo di articolo
        if(aDao.isProdottoComposito(a.getName())) {
            ProdottoComposito pc = ProdottoCompositoDAO.getInstance().findByName(a.getName());
            result.setArticolo(pc);
            result.setResult(ArticoloResult.Result.IS_PRODOTTO_COMPOSITO);
            result.setMessage("L'articolo indicato è un prodotto composito");
            return result;

        } else if(aDao.isProdotto(a.getName())) {
            Prodotto p = (Prodotto) ProdottoDAO.getInstance().findByName(a.getName());
            result.setArticolo(p);
            result.setResult(ArticoloResult.Result.IS_PRODOTTO);
            result.setMessage("L'articolo indicato è un prodotto");
            return result;

        } else if(aDao.isServizio(a.getName())){
            Servizio s = ServizioDAO.getInstance().findByName(a.getName());
            result.setArticolo(s);
            result.setResult(ArticoloResult.Result.IS_SERVIZIO);
            result.setMessage("L'articolo indicato è un servizio");
            return result;

        } else { //errore nel tipo di articolo
            result.setResult(ArticoloResult.Result.WRONG_TYPE);
            result.setMessage("Errore nella specificazione dell'articolo! Riprova!");
            return result;
        }
    }
}
