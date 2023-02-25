package Business;

import DAO.ManagerDAO;
import DAO.UtenteDAO;
import Model.Manager;

public class AmministratoreBusiness {

    private static AmministratoreBusiness instance;

    public static synchronized AmministratoreBusiness getInstance() {
        if (instance == null) {
            instance = new AmministratoreBusiness();
        }
        return instance;
    }

    public RegisterResult createManager(Manager m){

        UtenteDAO uDao = UtenteDAO.getInstance();
        RegisterResult result = new RegisterResult();

        // 1. controllare se l'utente esiste già
        boolean userExists = uDao.userExists(m.getUsername());
        if (userExists) {
            result.setResult(RegisterResult.Result.USER_ALREADY_EXISTS);
            result.setMessage("Lo username inserito è già esistente! Riprova");
            return result;
        }

        //2. controllare se la password è troppo debole
        String pwd = m.getPwd();
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

        //3. inserimento del nuovo manager
        ManagerDAO mDao = ManagerDAO.getInstance();
        mDao.add(m);

        result.setResult(RegisterResult.Result.REGISTER_OK);
        result.setMessage("Registrazione effettuata con successo!");
        return result;
    }
}
