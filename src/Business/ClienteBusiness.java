package Business;

import DAO.ClienteDAO;
import DAO.UtenteDAO;
import Model.Cliente;

public class ClienteBusiness {

    private static ClienteBusiness instance;

    public static synchronized ClienteBusiness getInstance() {
        if (instance == null) {
            instance = new ClienteBusiness();
        }
        return instance;
    }

    public RegisterResult register(Cliente c){

        UtenteDAO uDao = UtenteDAO.getInstance();
        RegisterResult result = new RegisterResult();

        // 1. controllare se l'utente esiste già
        boolean userExists = uDao.userExists(c.getUsername());
        if (userExists) {
            result.setResult(RegisterResult.Result.USER_ALREADY_EXISTS);
            result.setMessage("Lo username inserito è già esistente! Riprova");
            return result;
        }

        //2. controllare se la password è troppo debole
        String pwd = c.getPwd();
        final int MINIMUM_LENGTH = 8;
        boolean length = pwd.length() >= MINIMUM_LENGTH;
        if(length){
            result.setResult(RegisterResult.Result.WEAK_PASSWORD);
            result.setMessage("La password inserita deve contenere almeno 8 caratteri! Riprova");
            return result;
        }
        boolean containsUpper = pwd.matches(".*[A-Z].*");
        if (!containsUpper) {
            result.setResult(RegisterResult.Result.WEAK_PASSWORD);
            result.setMessage("La password deve contenere almento una lettera maiuscola! Riprova");
            return result;
        }
        boolean containsDigits = pwd.matches(".*[0-9].*");
        if (!containsDigits) {
            result.setResult(RegisterResult.Result.WEAK_PASSWORD);
            result.setMessage("La password deve contenere almeno un numero! Riprova");
            return result;
        }
        boolean containsSimbols = pwd.matches(".*\\p{Punct}.*");
        if (!containsSimbols) {
            result.setResult(RegisterResult.Result.WEAK_PASSWORD);
            result.setMessage("La password deve contenere almeno un simbolo! Riprova");
            return result;
        }

        //3. verifichiamo l'età del nuovo cliente
        if(c.getEta() < 18 ) {
            result.setResult(RegisterResult.Result.TOO_YOUNG);
            result.setMessage("L'iscrizione non è permessa ai minori di 18 anni");
            return result;
        }

        c.setAbilitazione(true);

        //4. inserimento del nuovo cliente
        ClienteDAO cDao = ClienteDAO.getInstance();
        cDao.add(c);

        result.setResult(RegisterResult.Result.REGISTER_OK);
        result.setMessage("Registrazione effettuata con successo! Benvenuto in MyShop!");
        return result;
    }
}
