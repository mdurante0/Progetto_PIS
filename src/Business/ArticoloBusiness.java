package Business;

import Business.Results.ArticoloResult;
import DAO.*;
import Model.Articolo;
import Model.Magazzino;
import Model.Servizio;
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

        if(a instanceof Prodotto p) {
            ProdottoDAO pDao = ProdottoDAO.getInstance();
            inserito = pDao.add(p);
        }

        else if(a instanceof Servizio s){
            ServizioDAO sDao = ServizioDAO.getInstance();
            inserito = sDao.add(s);
        }

        else if(a instanceof ProdottoComposito pc) {
            ProdottoCompositoDAO pcDao = ProdottoCompositoDAO.getInstance();
            inserito = pcDao.add(pc);
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

        ArticoloDAO aDao = ArticoloDAO.getInstance();
        ArticoloResult result = new ArticoloResult();

        //Verifica esistenza articolo
        boolean articoloExists = aDao.articoloExists(a.getName());
        if (!articoloExists){
            result.setResult(ArticoloResult.Result.ITEM_DOESNT_EXIST);
            result.setMessage("L'articolo da aggiornare non esiste! Riprova");
            return result;
        }

        //aggiornamento in base al tipo di articolo
        int aggiornato;

        if(aDao.isProdotto(a.getName())) {
            Prodotto p = (Prodotto) a;
            ProdottoDAO pDao = ProdottoDAO.getInstance();
            aggiornato = pDao.update(p);
        }

        else if(aDao.isServizio(a.getName())){
            Servizio s = (Servizio) a;
            ServizioDAO sDao = ServizioDAO.getInstance();
            aggiornato = sDao.update(s);
        }

        else if(aDao.isProdottoComposito(a.getName())) {
            ProdottoComposito pc = (ProdottoComposito) a;
            ProdottoCompositoDAO pcDao = ProdottoCompositoDAO.getInstance();
            aggiornato = pcDao.update(pc);
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

        ProdottoDAO pDao = ProdottoDAO.getInstance();
        MagazzinoDAO mDao = MagazzinoDAO.getInstance();
        ArticoloResult result = new ArticoloResult();

        //Verifica esistenza articolo
        p = pDao.findByName(p.getName());
        boolean prodottoExists = p != null;
        if (!prodottoExists){
            result.setResult(ArticoloResult.Result.ITEM_DOESNT_EXIST);
            result.setMessage("L'articolo da rimuovere non esiste! Riprova");
            return result;
        }

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
        a.setCategoria(null);
        if(a instanceof IProdotto p) {
            ProdottoDAO pDao = ProdottoDAO.getInstance();
            rimossa = pDao.update(p);
        } else  {
            ServizioDAO sDao = ServizioDAO.getInstance();
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
    public ArticoloResult updateProdottoInMagazzino(String prodottoName, int idMagazzino){
        MagazzinoDAO mDao = MagazzinoDAO.getInstance();
        ProdottoDAO pDao = ProdottoDAO.getInstance();
        ArticoloResult result = new ArticoloResult();

        IProdotto p = pDao.findByName(prodottoName);
        //Verifica esistenza articolo
        if (p == null || !mDao.prodottoExists(idMagazzino, p.getIdArticolo())){
            result.setResult(ArticoloResult.Result.ITEM_DOESNT_EXIST);
            result.setMessage("L'articolo indicato non è presente nel magazzino! Riprova");
            return result;
        }

        //Verifica esistenza magazzino
        Magazzino m = mDao.findById(idMagazzino);
        boolean magazzinoExists = m != null;
        if (!magazzinoExists){
            result.setResult(ArticoloResult.Result.ITEM_DOESNT_EXIST);
            result.setMessage("Il magazzino indicato non esiste! Riprova");
            return result;
        }

        int aggiornato = mDao.updateProdotto(m,p);
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

}
