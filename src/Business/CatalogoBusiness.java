package Business;

import Business.Results.CatalogoResult;
import DAO.MagazzinoDAO;
import DAO.ProdottoDAO;
import DAO.PuntoVenditaDAO;
import DAO.ServizioDAO;
import Model.Magazzino;
import Model.PuntoVendita;
import Model.Servizio;
import Model.composite.IProdotto;

import java.util.ArrayList;

public class CatalogoBusiness {

    private static CatalogoBusiness instance;

    public static synchronized CatalogoBusiness getInstance() {
        if (instance == null) {
            instance = new CatalogoBusiness();
        }
        return instance;
    }

    public CatalogoResult caricaCatalogo(String nomePuntoVendita){
        CatalogoResult result = new CatalogoResult();

        PuntoVenditaDAO puntoVenditaDAO = PuntoVenditaDAO.getInstance();
        MagazzinoDAO magazzinoDAO = MagazzinoDAO.getInstance();
        ServizioDAO servizioDAO = ServizioDAO.getInstance();

        //Recupero il magazzino collegato al punto vendita richiesto
        PuntoVendita puntoVendita = puntoVenditaDAO.findByName(nomePuntoVendita);
        Magazzino magazzino = magazzinoDAO.findById(puntoVendita.getMagazzino().getIdMagazzino());

        if(magazzino == null){ //Magazzino non trovato
            result.setResult(CatalogoResult.Result.ERRORE_MAGAZZINO);
            result.setMessage("Catalogo non caricato! Prodotti non trovati!");
            return result;

        }else result.setListaProdotti(magazzino.getProdotti()); //Prodotti caricati

        ArrayList<Servizio> servizi = servizioDAO.findAll();

        if(servizi.isEmpty()){ //Non ci sono servizi
            result.setResult(CatalogoResult.Result.ERRORE_SERVIZI);
            result.setMessage("Catalogo non caricato! Servizi non trovati!");
            return result;

        }else result.setListaServizi(servizi); //Servizi caricati

        result.setResult(CatalogoResult.Result.CATALOGO_CARICATO);
        result.setMessage("Catalogo caricato correttamente");
        return result;
    }

    public CatalogoResult caricaCatalogoProdotti(String nomePuntoVendita){
        CatalogoResult result = new CatalogoResult();

        PuntoVenditaDAO puntoVenditaDAO = PuntoVenditaDAO.getInstance();
        MagazzinoDAO magazzinoDAO = MagazzinoDAO.getInstance();

        //Recupero il magazzino collegato al punto vendita richiesto
        PuntoVendita puntoVendita = puntoVenditaDAO.findByName(nomePuntoVendita);
        Magazzino magazzino = magazzinoDAO.findById(puntoVendita.getMagazzino().getIdMagazzino());

        if(magazzino == null || magazzino.getProdotti().isEmpty()){ //Il magazzino è vuoto
            result.setResult(CatalogoResult.Result.ERRORE_MAGAZZINO);
            result.setMessage("Catalogo non caricato! Prodotti non trovati!");
            return result;

        }else result.setListaProdotti(magazzino.getProdotti()); //Prodotti caricati

        result.setResult(CatalogoResult.Result.CATALOGO_CARICATO);
        result.setMessage("Catalogo caricato correttamente");

        return result;
    }

    public CatalogoResult caricaCatalogoServizi(){
        CatalogoResult result = new CatalogoResult();

        ServizioDAO servizioDAO = ServizioDAO.getInstance();

        ArrayList<Servizio> servizi = servizioDAO.findAll();

        if(servizi.isEmpty()){ //Non ci sono servizi
            result.setResult(CatalogoResult.Result.ERRORE_SERVIZI);
            result.setMessage("Catalogo non caricato! Servizi non trovati!");
            return result;

        }else result.setListaServizi(servizi); //Servizi caricati

        result.setResult(CatalogoResult.Result.CATALOGO_CARICATO);
        result.setMessage("Catalogo caricato correttamente");

        return result;
    }

    public CatalogoResult caricaCatalogoProdotti() {
        CatalogoResult result = new CatalogoResult();
        ProdottoDAO prodottoDAO = ProdottoDAO.getInstance();

        ArrayList<IProdotto> prodotti = prodottoDAO.findAll();
        if(prodotti.isEmpty()){ //Non esistono prodotti
            result.setResult(CatalogoResult.Result.ERRORE_PRODOTTI);
            result.setMessage("Catalogo non caricato! Prodotti non trovati!");
            return result;

        }else result.setListaProdotti(prodotti); //Prodotti caricati

        result.setResult(CatalogoResult.Result.CATALOGO_CARICATO);
        result.setMessage("Catalogo caricato correttamente");

        return result;
    }

    public CatalogoResult caricaCatalogoProdottiByNomi(ArrayList<String> nomi) {
        CatalogoResult result = new CatalogoResult();
        ProdottoDAO prodottoDAO = ProdottoDAO.getInstance();

        ArrayList<IProdotto> prodotti = new ArrayList<>();
        for (String s : nomi) {
            IProdotto p = prodottoDAO.findByName(s);
            if (p == null) { //Prodotto non trovato
                result.setResult(CatalogoResult.Result.ERRORE_PRODOTTI);
                result.setMessage("Prodotti non trovati! Riprova!");
                return result;
            }
            prodotti.add(p);
        }
        result.setListaProdotti(prodotti); //Prodotti caricati
        result.setResult(CatalogoResult.Result.CATALOGO_CARICATO);
        result.setMessage("Prodotti caricati correttamente");

        return result;
    }
}
