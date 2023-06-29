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

        if(managers == null){ //Non ci sono manager
            result.setResult(ManagerResult.Result.MANAGER_ERROR);
            result.setMessage("Nessun manager trovato!");
            return result;

        }else result.setManagers(managers); //Manager caricati

        result.setResult(ManagerResult.Result.MANAGER_CARICATI);
        result.setMessage("Manager caricati correttamente");

        return result;
    }

    public ManagerResult addManager(Manager manager){

        ManagerResult result = new ManagerResult();

        //verifico l'esistenza del manager
        ManagerDAO managerDAO = ManagerDAO.getInstance();
        if(managerDAO.findByUsername(manager.getUsername()) != null){
            result.setResult(ManagerResult.Result.USER_ALREADY_EXISTS);
            result.setMessage("Il manager da inserire è già esistente! Riprova!");
            return result;
        }

        //Aggiungo il nuovo manager
        if(managerDAO.add(manager) == 0) { //manager non inserito
            result.setResult(ManagerResult.Result.MANAGER_ERROR);
            result.setMessage("Manager non inserito! Riprova!");
            return result;
        }

        //l'inserimento è andato a buon fine
        result.setResult(ManagerResult.Result.ADD_OK);
        result.setMessage("Manager inserito correttamente!");
        return result;
    }

    public ManagerResult updateManager(Manager manager){

        ManagerResult result = new ManagerResult();

        //verifico l'esistenza del manager
        ManagerDAO managerDAO = ManagerDAO.getInstance();
        if(managerDAO.findById(manager.getIdUtente()) == null){
            result.setResult(ManagerResult.Result.USER_DOESNT_EXIST);
            result.setMessage("Il manager da aggiornare non esiste! Riprova!");
            return result;
        }

        //Aggiorno il manager
        String pwd = manager.getPwd();
        final int MINIMUM_LENGTH = 8;
        boolean length = pwd.length() >= MINIMUM_LENGTH;
        if(!length){
            result.setMessage("La password inserita deve contenere almeno 8 caratteri! Riprova");
            return result;
        }
        boolean containsUpper = pwd.matches(".*\\p{Upper}.*");
        if (!containsUpper) {
            result.setMessage("La password deve contenere almento una lettera maiuscola! Riprova");
            return result;
        }
        boolean containsDigits = pwd.matches(".*\\d.*");
        if (!containsDigits) {
            result.setMessage("La password deve contenere almeno un numero! Riprova");
            return result;
        }

        String pwdEncrypted = Encrypt.encrypt(manager.getPwd());
        manager.setPwd(pwdEncrypted);
        if(managerDAO.update(manager) == 0) { //manager non aggiornato
            result.setResult(ManagerResult.Result.MANAGER_ERROR);
            result.setMessage("Manager non aggiornato! Riprova!");
            return result;
        }

        //l'aggiornamento è andato a buon fine
        result.setResult(ManagerResult.Result.UPDATE_OK);
        result.setMessage("Manager aggiornato correttamente!");
        return result;
    }

    public ManagerResult removeManager(Manager manager){

        ManagerResult result = new ManagerResult();

        //verifico l'esistenza del manager
        ManagerDAO managerDAO = ManagerDAO.getInstance();
        if(managerDAO.findByUsername(manager.getUsername()) == null){
            result.setResult(ManagerResult.Result.USER_DOESNT_EXIST);
            result.setMessage("Il manager da rimuovere non esiste! Riprova!");
            return result;
        }

        //Rimuovo il manager
        if(managerDAO.removeById(manager.getUsername()) == 0) { //manager non rimosso
            result.setResult(ManagerResult.Result.RIMOZIONE_ERROR);
            result.setMessage("Manager non rimosso! Riprova!");
            return result;
        }

        //la rimozione è andata a buon fine
        result.setResult(ManagerResult.Result.RIMOZIONE_OK);
        result.setMessage("Manager rimosso correttamente!");
        return result;
    }

    public ManagerResult caricaManagerByUsername(String username) {
        ManagerResult result = new ManagerResult();

        ManagerDAO managerDAO = ManagerDAO.getInstance();

        Manager manager = managerDAO.findByUsername(username);

        if(manager == null){ //Manager non trovato
            result.setResult(ManagerResult.Result.MANAGER_ERROR);
            result.setMessage("Manager non trovato!");
            return result;

        }else result.getManagers().add(manager); //Manager caricato

        result.setResult(ManagerResult.Result.MANAGER_CARICATI);
        result.setMessage("Manager caricato correttamente");

        return result;
    }
}
