package Business;

import Business.Results.CatalogoResult;
import DAO.MagazzinoDAO;
import DAO.PuntoVenditaDAO;
import DAO.ServizioDAO;
import Model.Magazzino;
import Model.PuntoVendita;
import Model.Servizio;

import java.util.ArrayList;

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

        PuntoVenditaDAO puntoVenditaDAO = PuntoVenditaDAO.getInstance();
        MagazzinoDAO magazzinoDAO = MagazzinoDAO.getInstance();
        ServizioDAO servizioDAO = ServizioDAO.getInstance();

        //Recupero il magazzino collegato al punto vendita richiesto
        PuntoVendita puntoVendita = puntoVenditaDAO.findById(idPuntoVendita);
        Magazzino magazzino = magazzinoDAO.findById(puntoVendita.getIdMagazzino());

        if(magazzino == null){ //Il magazzino è vuoto
            result.setResult(CatalogoResult.Result.ERRORE_MAGAZZINO);
            result.setMessage("Catalogo non caricato! Prodotti non trovati!");
            return result;

        }else result.setListaProdotti(magazzino.getProdotti()); //Prodotti caricati

        ArrayList<Servizio> servizi = servizioDAO.findAll();

        if(servizi == null){ //Non ci sono servizi
            result.setResult(CatalogoResult.Result.ERRORE_SERVIZI);
            result.setMessage("Catalogo non caricato! Servizi non trovati!");
            return result;

        }else result.setListaServizi(servizi); //Servizi caricati

        result.setResult(CatalogoResult.Result.CATALOGO_CARICATO);
        result.setMessage("Catalogo caricato correttamente");
        return result;
    }

    public CatalogoResult caricaCatalogoProdotti(int idPuntoVendita){
        CatalogoResult result = new CatalogoResult();

        PuntoVenditaDAO puntoVenditaDAO = PuntoVenditaDAO.getInstance();
        MagazzinoDAO magazzinoDAO = MagazzinoDAO.getInstance();

        //Recupero il magazzino collegato al punto vendita richiesto
        PuntoVendita puntoVendita = puntoVenditaDAO.findById(idPuntoVendita);
        Magazzino magazzino = magazzinoDAO.findById(puntoVendita.getIdMagazzino());

        if(magazzino.getProdotti().isEmpty()){ //Il magazzino è vuoto
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

        if(servizi == null){ //Non ci sono servizi
            result.setResult(CatalogoResult.Result.ERRORE_SERVIZI);
            result.setMessage("Catalogo non caricato! Servizi non trovati!");
            return result;

        }else result.setListaServizi(servizi); //Servizi caricati

        result.setResult(CatalogoResult.Result.CATALOGO_CARICATO);
        result.setMessage("Catalogo caricato correttamente");

        return result;
    }

}
