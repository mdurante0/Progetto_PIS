package Business;

import Business.Results.PuntoVenditaResult;
import DAO.PuntoVenditaDAO;
import Model.Manager;
import Model.PuntoVendita;

import java.util.ArrayList;

public class PuntoVenditaBusiness {

    private static PuntoVenditaBusiness instance;

    public static synchronized PuntoVenditaBusiness getInstance() {
        if (instance == null) {
            instance = new PuntoVenditaBusiness();
        }
        return instance;
    }

    public PuntoVenditaResult caricaPuntiVendita(){
        PuntoVenditaResult result = new PuntoVenditaResult();

        PuntoVenditaDAO puntoVenditaDAO = PuntoVenditaDAO.getInstance();

        ArrayList<PuntoVendita> puntiVendita = puntoVenditaDAO.findAll();

        if(puntiVendita.isEmpty()){ //Non ci sono punti vendita
            result.setResult(PuntoVenditaResult.Result.SALEPOINT_ERROR);
            result.setMessage("Nessun punto vendita trovato!");
            return result;

        }else result.setPuntiVendita(puntiVendita); //Punti vendita caricati

        result.setResult(PuntoVenditaResult.Result.SALEPOINT_CARICATI);
        result.setMessage("Punti vendita caricati correttamente");

        return result;
    }

    public PuntoVenditaResult caricaPuntoVenditaByManager(Manager manager){
        PuntoVenditaResult result = new PuntoVenditaResult();

        PuntoVenditaDAO puntoVenditaDAO = PuntoVenditaDAO.getInstance();

        PuntoVendita puntoVendita = puntoVenditaDAO.findByManager(manager.getIdUtente());

        if(puntoVendita == null){ //Il manager indicato non è stato assegnato ad alcun punto vendita
            result.setResult(PuntoVenditaResult.Result.SALEPOINT_ERROR);
            result.setMessage("Punto vendita non trovato!");
            return result;

        }else result.getPuntiVendita().add(puntoVendita); //Punto vendita caricato

        result.setResult(PuntoVenditaResult.Result.SALEPOINT_CARICATI);
        result.setMessage("Punti vendita caricati correttamente");

        return result;
    }
}
