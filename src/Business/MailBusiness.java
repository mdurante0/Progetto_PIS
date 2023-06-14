package Business;

import Business.Bridge.Mail.MailHelper;
import Business.Bridge.Mail.MailHelperAPI;
import Business.Results.MailResult;
import DAO.ClienteDAO;
import DAO.UtenteDAO;
import Model.Cliente;

import java.io.File;

public class MailBusiness {

    private static MailBusiness instance;

    public static synchronized MailBusiness getInstance() {
        if (instance == null) {
            instance = new MailBusiness();
        }
        return instance;
    }
    public MailResult invioEmail(String clienteUsername, int idManager, String oggetto, String messaggio){
        UtenteDAO utenteDAO = UtenteDAO.getInstance();
        ClienteDAO clienteDAO = ClienteDAO.getInstance();
        MailResult result = new MailResult();

        //Verifico l'esistenza del cliente
        if(!utenteDAO.userExists(clienteUsername) || !utenteDAO.isCliente(clienteUsername)){
            result.setResult(MailResult.Result.USER_DOESNT_EXIST);
            result.setMessage("Il cliente non esiste! Riprova!");
            return result;
        }

        Cliente cliente = clienteDAO.findByUsername(clienteUsername);
        //Verifico che il cliente indicato sia registrato nello stesso punto vendita gestito dal manager
        if(!clienteDAO.isGestibile(cliente,idManager)){
            result.setResult(MailResult.Result.USER_ERROR);
            result.setMessage("Il cliente indicato è registrato in un altro punto vendita! Riprova!");
            return result;
        }

        //invio l'email
        int invio = MailHelper.getInstance(new MailHelperAPI(), cliente.getEmail(), oggetto, messaggio).send();
        if(invio == 1) { //email non inviata
            result.setResult(MailResult.Result.INVIO_ERROR);
            result.setMessage("Errore nell'invio della mail! Riprova!");
            return result;
        }

        //Email inviata correttamente
        result.setResult(MailResult.Result.INVIO_OK);
        result.setMessage("Email inviata correttamente al destinatario!");
        return result;
    }

    public MailResult invioEmail(String clienteUsername, int idManager, String oggetto, String messaggio, String percorsoFile){
        UtenteDAO utenteDAO = UtenteDAO.getInstance();
        ClienteDAO clienteDAO = ClienteDAO.getInstance();
        MailResult result = new MailResult();

        //Verifico l'esistenza del cliente
        if(!utenteDAO.userExists(clienteUsername) || !utenteDAO.isCliente(clienteUsername)){
            result.setResult(MailResult.Result.USER_DOESNT_EXIST);
            result.setMessage("Il cliente non esiste! Riprova!");
            return result;
        }

        Cliente cliente = clienteDAO.findByUsername(clienteUsername);
        //Verifico che il cliente indicato sia registrato nello stesso punto vendita gestito dal manager
        if(!clienteDAO.isGestibile(cliente,idManager)){
            result.setResult(MailResult.Result.USER_ERROR);
            result.setMessage("Il cliente indicato è registrato in un altro punto vendita! Riprova!");
            return result;
        }

        //Verifico l'esistenza dell'allegato
        File file = new File(percorsoFile);
        if(!file.isFile()) {
            result.setResult(MailResult.Result.ALLEGATO_ERROR);
            result.setMessage("Allegato non trovato! Riprova!");
            return result;
        }

        //invio l'email
        int invio = MailHelper.getInstance(new MailHelperAPI(), cliente.getEmail(), oggetto, messaggio).send(percorsoFile);
        if(invio == 1) { //email non inviata
            result.setResult(MailResult.Result.INVIO_ERROR);
            result.setMessage("Errore nell'invio della mail! Riprova!");
            return result;
        }

        //Email inviata correttamente
        result.setResult(MailResult.Result.INVIO_OK);
        result.setMessage("Email inviata correttamente al destinatario!");
        return result;
    }

}
