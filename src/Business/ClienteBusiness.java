package Business;

import Business.Results.ClienteResult;
import DAO.ClienteDAO;
import DAO.UtenteDAO;
import Model.Cliente;
import Model.PuntoVendita;

import java.util.ArrayList;

public class ClienteBusiness {

    private static ClienteBusiness instance;

    public static synchronized ClienteBusiness getInstance() {
        if (instance == null) {
            instance = new ClienteBusiness();
        }
        return instance;
    }
    public ClienteResult abilitazioneCliente (Cliente cliente, boolean abilitazione){
        ClienteDAO clienteDAO = ClienteDAO.getInstance();
        ClienteResult result = new ClienteResult();

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
    public ClienteResult caricaClienti(){
        ClienteResult result = new ClienteResult();

        ClienteDAO clienteDAO = ClienteDAO.getInstance();

        ArrayList<Cliente> clienti = clienteDAO.findAll();

        if(clienti.isEmpty()){ //Non ci sono clienti
            result.setResult(ClienteResult.Result.CLIENTE_ERROR);
            result.setMessage("Nessun cliente trovato!");
            return result;

        }else result.setClienti(clienti); //Clienti caricati

        result.setResult(ClienteResult.Result.CLIENTI_CARICATI);
        result.setMessage("Clienti caricati correttamente");

        return result;
    }

    public ClienteResult caricaClienteByPuntoVendita(PuntoVendita puntoVendita){
        ClienteResult result = new ClienteResult();

        ClienteDAO clienteDAO = ClienteDAO.getInstance();

        ArrayList<Cliente> clienti = clienteDAO.findAllByPuntoVendita(puntoVendita.getIdPuntoVendita());

        if(clienti.isEmpty()){ //Al punto vendita indicato non è registrato nessun cliente
            result.setResult(ClienteResult.Result.CLIENTE_ERROR);
            result.setMessage("Nessun cliente trovato!");
            return result;

        }else result.setClienti(clienti); //Clienti caricati

        result.setResult(ClienteResult.Result.CLIENTI_CARICATI);
        result.setMessage("Punti vendita caricati correttamente");

        return result;
    }

}
