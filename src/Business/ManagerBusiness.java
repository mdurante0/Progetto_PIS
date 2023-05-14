package Business;

import Business.Results.AbilitazioneResult;
import Model.Cliente;

public class ManagerBusiness {

    private static ManagerBusiness instance;

    public static synchronized ManagerBusiness getInstance() {
        if (instance == null) {
            instance = new ManagerBusiness();
        }
        return instance;
    }

        public AbilitazioneResult disabilitaCliente (Cliente cliente){

            AbilitazioneResult result = new AbilitazioneResult();



            return result;
        }
}
