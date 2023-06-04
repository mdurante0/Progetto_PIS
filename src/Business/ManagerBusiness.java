package Business;

import Business.Bridge.Mail.MailHelper;
import Business.Bridge.Mail.MailHelperAPI;
import Business.Results.ArticoloResult;
import Business.Results.ClienteResult;
import Business.Results.MailResult;
import DAO.ClienteDAO;
import DAO.MagazzinoDAO;
import DAO.ProdottoDAO;
import DAO.UtenteDAO;
import Model.Cliente;
import Model.Magazzino;
import Model.composite.IProdotto;

import java.io.File;

public class ManagerBusiness {

    private static ManagerBusiness instance;

    public static synchronized ManagerBusiness getInstance() {
        if (instance == null) {
            instance = new ManagerBusiness();
        }
        return instance;
    }

        public ClienteResult abilitazioneCliente (Cliente cliente, boolean abilitazione){
            UtenteDAO utenteDAO = UtenteDAO.getInstance();
            ClienteResult result = new ClienteResult();

            //Verifico l'esistenza del cliente
            if(!utenteDAO.userExists(cliente.getUsername()) && utenteDAO.isCliente(cliente.getUsername())){
                result.setResult(ClienteResult.Result.USER_DOESNT_EXIST);
                result.setMessage("Il cliente non esiste! Riprova!");
                return result;
            }

            ClienteDAO clienteDAO = ClienteDAO.getInstance();
            cliente = clienteDAO.findById(cliente.getUsername());
            //Aggiorno l'abilitazione del cliente
            cliente.setAbilitazione(abilitazione);
            int aggiornato = clienteDAO.update(cliente);
            if(aggiornato == 0){ //cliente non aggiornato
                result.setResult(ClienteResult.Result.ABILITAZIONE_ERROR);
                result.setMessage("Abilitazione non assegnata! Riprova!");
                return result;
            }
            //Abilitazione aggiornata correttamente
            result.setResult(ClienteResult.Result.ABILITAZIONE_OK);
            result.setMessage("Abilitazione modificata correttamente!");
            return result;
        }

        public ClienteResult rimuoviCliente(Cliente cliente){
            ClienteDAO clienteDAO = ClienteDAO.getInstance();
            ClienteResult result = new ClienteResult();

            //Verifico l'esistenza del cliente
            cliente = clienteDAO.findById(cliente.getUsername());
            boolean clienteExists = cliente != null;
            if(!clienteExists){
                result.setResult(ClienteResult.Result.USER_DOESNT_EXIST);
                result.setMessage("Il cliente non esiste! Riprova!");
                return result;
            }

            //Rimozione del cliente
            int rimosso = clienteDAO.removeById(cliente.getUsername());
            if(rimosso == 0){
                result.setResult(ClienteResult.Result.RIMOZIONE_ERROR);
                result.setMessage("Errore nella rimozione del cliente! Riprova!");
                return result;
            }

            //Cliente rimosso correttamente
            result.setResult(ClienteResult.Result.RIMOZIONE_OK);
            result.setMessage("Cliente rimosso correttamente!");
            return result;
        }

    public ArticoloResult updateProdottoInMagazzino(IProdotto p, int idMagazzino){

        ProdottoDAO pDao = ProdottoDAO.getInstance();
        MagazzinoDAO mDao = MagazzinoDAO.getInstance();
        ArticoloResult result = new ArticoloResult();

        //Verifica esistenza articolo
        if (!mDao.prodottoExists(idMagazzino, p.getIdArticolo())){
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

    public MailResult invioEmail(Cliente c, String oggetto, String messaggio){
        UtenteDAO utenteDAO = UtenteDAO.getInstance();
        MailResult result = new MailResult();

        //Verifico l'esistenza del cliente
        if(!utenteDAO.userExists(c.getUsername()) && utenteDAO.isCliente(c.getUsername())){
            result.setResult(MailResult.Result.USER_DOESNT_EXIST);
            result.setMessage("Il cliente non esiste! Riprova!");
            return result;
        }

        //invio l'email
        int invio = MailHelper.getInstance(new MailHelperAPI(), c.getEmail(), oggetto, messaggio).send();
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

    public MailResult invioEmail(Cliente c, String oggetto, String messaggio, String percorsoFile){
        UtenteDAO utenteDAO = UtenteDAO.getInstance();
        MailResult result = new MailResult();

        //Verifico l'esistenza del cliente
        if(!utenteDAO.userExists(c.getUsername()) && utenteDAO.isCliente(c.getUsername())){
            result.setResult(MailResult.Result.USER_DOESNT_EXIST);
            result.setMessage("Il cliente non esiste! Riprova!");
            return result;
        }

        //Verifico l'esistenza dell'allegato
        File file = new File(percorsoFile);
        if(file.isFile()) {
            result.setResult(MailResult.Result.ALLEGATO_ERROR);
            result.setMessage("Allegato non trovato! Riprova!");
            return result;
        }

        //invio l'email
        int invio = MailHelper.getInstance(new MailHelperAPI(), c.getEmail(), oggetto, messaggio).send(percorsoFile);
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
