package Business;

import Business.Bridge.Mail.MailHelper;
import Business.Bridge.Mail.MailHelperAPI;
import Business.Bridge.Pdf.Documento;
import Business.Bridge.Pdf.DocumentoListaAcquisto;
import Business.Bridge.Pdf.DocumentoPrenotazione;
import Business.Bridge.Pdf.PdfBoxAPI;
import Business.Results.MailResult;
import Model.Cliente;
import Model.ListaAcquisto;
import Model.Prenotazione;

import java.io.File;
import java.util.ArrayList;

public class MailBusiness {

    private static MailBusiness instance;

    public static synchronized MailBusiness getInstance() {
        if (instance == null) {
            instance = new MailBusiness();
        }
        return instance;
    }
    public MailResult invioEmail(Cliente cliente, String oggetto, String messaggio){
        MailResult result = new MailResult();

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

    public MailResult invioEmail(Cliente cliente, String oggetto, String messaggio, String percorsoFile){
        MailResult result = new MailResult();

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

    public MailResult invioListaAcquisto(ListaAcquisto listaAcquisto){
        MailResult result = new MailResult();
        Documento doc = new DocumentoListaAcquisto(listaAcquisto, new PdfBoxAPI());

        //invio l'email
        int invio = doc.invia(listaAcquisto.getCliente().getEmail());
        if(invio == 1) { //email non inviata
            result.setResult(MailResult.Result.INVIO_ERROR);
            result.setMessage("Errore nell'invio della Lista d'Acquisto! Riprova!");
            return result;
        }

        //Email inviata correttamente
        result.setResult(MailResult.Result.INVIO_OK);
        result.setMessage("Lista d'Acquisto inviata correttamente! Controlli la sua mail!");
        return result;
    }

    public MailResult invioPrenotazioni(ArrayList<Prenotazione> prenotazioni, Cliente cliente){
        MailResult result = new MailResult();
        Documento doc = new DocumentoPrenotazione(prenotazioni, new PdfBoxAPI());

        //invio l'email
        int invio = doc.invia(cliente.getEmail());
        if(invio == 1) { //email non inviata
            result.setResult(MailResult.Result.INVIO_ERROR);
            result.setMessage("Errore nell'invio delle Prenotazioni! Riprova!");
            return result;
        }

        //Email inviata correttamente
        result.setResult(MailResult.Result.INVIO_OK);
        result.setMessage("Prenotazioni inviate correttamente! Controlli la sua mail!");
        return result;
    }

}
