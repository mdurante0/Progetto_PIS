package Business;

import Business.Results.ManagerResult;
import DAO.ManagerDAO;
import Model.Manager;

import java.util.ArrayList;

public class ManagerBusiness {

    private static ManagerBusiness instance;

    public static synchronized ManagerBusiness getInstance() {
        if (instance == null) {
            instance = new ManagerBusiness();
        }
        return instance;
    }

    public ManagerResult caricaManagers(){
        ManagerResult result = new ManagerResult();

        ManagerDAO managerDAO = ManagerDAO.getInstance();

        ArrayList<Manager> managers = managerDAO.findAll();

        if(managers.isEmpty()){ //Non ci sono manager
            result.setResult(ManagerResult.Result.MANAGER_ERROR);
            result.setMessage("Nessun manager trovato!");
            return result;

        }else result.setManagers(managers); //Manager caricati

        result.setResult(ManagerResult.Result.MANAGER_CARICATI);
        result.setMessage("Manager caricati correttamente");

        return result;
    }
}
