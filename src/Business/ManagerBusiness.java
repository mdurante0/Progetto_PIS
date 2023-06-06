package Business;

import Business.Bridge.Mail.MailHelper;
import Business.Bridge.Mail.MailHelperAPI;
import Business.Results.ArticoloResult;
import Business.Results.ClienteResult;
import Business.Results.FeedbackResult;
import Business.Results.MailResult;
import DAO.*;
import Model.Cliente;
import Model.Feedback;
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

        public ClienteResult abilitazioneCliente (String clienteUsername, int idManager, boolean abilitazione){
            UtenteDAO utenteDAO = UtenteDAO.getInstance();
            ClienteDAO clienteDAO = ClienteDAO.getInstance();
            ClienteResult result = new ClienteResult();

            //Verifico l'esistenza del cliente
            if(!utenteDAO.userExists(clienteUsername) || !utenteDAO.isCliente(clienteUsername)){
                result.setResult(ClienteResult.Result.USER_DOESNT_EXIST);
                result.setMessage("Il cliente non esiste! Riprova!");
                return result;
            }

            Cliente cliente = clienteDAO.findById(clienteUsername);
            //Verifico che il cliente indicato sia registrato nello stesso punto vendita gestito dal manager
            if(!clienteDAO.isGestibile(cliente,idManager)){
                result.setResult(ClienteResult.Result.ABILITAZIONE_ERROR);
                result.setMessage("Il cliente indicato è registrato in un altro punto vendita! Riprova!");
                return result;
            }

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

        public ClienteResult rimuoviCliente(String clienteUsername, int idManager){
            UtenteDAO utenteDAO = UtenteDAO.getInstance();
            ClienteDAO clienteDAO = ClienteDAO.getInstance();
            ClienteResult result = new ClienteResult();

            //Verifico l'esistenza del cliente
            if(!utenteDAO.userExists(clienteUsername) || !utenteDAO.isCliente(clienteUsername)){
                result.setResult(ClienteResult.Result.USER_DOESNT_EXIST);
                result.setMessage("Il cliente non esiste! Riprova!");
                return result;
            }

            Cliente cliente = clienteDAO.findById(clienteUsername);
            //Verifico che il cliente indicato sia registrato nello stesso punto vendita gestito dal manager
            if(!clienteDAO.isGestibile(cliente,idManager)){
                result.setResult(ClienteResult.Result.RIMOZIONE_ERROR);
                result.setMessage("Il cliente indicato è registrato in un altro punto vendita! Riprova!");
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

        Cliente cliente = clienteDAO.findById(clienteUsername);
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

        Cliente cliente = clienteDAO.findById(clienteUsername);
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

    public FeedbackResult rispondi(Feedback feedback, String risposta){
        FeedbackResult result = new FeedbackResult();
        FeedbackDAO feedbackDAO = FeedbackDAO.getInstance();

        //Verifico l'esistenza del feedback
        feedback = feedbackDAO.findById(feedback.getIdFeedback());
        if(feedback == null){
            result.setResult(FeedbackResult.Result.ITEM_DOESNT_EXIST);
            result.setMessage("Feedback non trovato! Riprova!");
            return result;
        }

        //Aggiungo la risposta
        feedback.setRisposta(risposta);
        int risposto = feedbackDAO.setRisposta(feedback.getIdFeedback(), risposta);
        if(risposto == 0){ //Risposta non inserita
            result.setResult(FeedbackResult.Result.UPDATE_ERROR);
            result.setMessage("Risposta non inserita! Riprova!");
            return result;
        }

        //Risposta inserita correttamente
        result.setResult(FeedbackResult.Result.UPDATE_OK);
        result.setMessage("Risposta inserita correttamente!");
        return result;
    }
}
