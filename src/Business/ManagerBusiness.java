package Business;

import Business.Results.AbilitazioneResult;
import DAO.ClienteDAO;
import Model.Cliente;

public class ManagerBusiness {

    private static ManagerBusiness instance;

    public static synchronized ManagerBusiness getInstance() {
        if (instance == null) {
            instance = new ManagerBusiness();
        }
        return instance;
    }

        public AbilitazioneResult abilitazioneCliente (Cliente cliente, boolean abilitazione){
            ClienteDAO clienteDAO = ClienteDAO.getInstance();
            AbilitazioneResult result = new AbilitazioneResult();

            //Verifico l'esistenza del cliente
            cliente = clienteDAO.findById(cliente.getUsername());
            boolean clienteExists = cliente != null;
            if(!clienteExists){
                result.setResult(AbilitazioneResult.Result.USER_DOESNT_EXIST);
                result.setMessage("Il cliente non esiste! Riprova!");
                return result;
            }

            //Aggiorno l'abilitazione del cliente
            cliente.setAbilitazione(abilitazione);
            int aggiornato = clienteDAO.update(cliente);
            if(aggiornato == 0){ //cliente non aggiornato
                result.setResult(AbilitazioneResult.Result.ABILITAZIONE_ERROR);
                result.setMessage("Abilitazione non assegnata! Riprova!");
                return result;
            }
            //Abilitazione aggiornata correttamente
            result.setResult(AbilitazioneResult.Result.ABILITAZIONE_OK);
            result.setMessage("Abilitazione modificata correttamente!");
            return result;
        }
}
