package Business;

import DAO.*;
import Model.Amministratore;
import Model.Cliente;
import Model.Manager;

public class UtenteBusiness {

    private static UtenteBusiness instance;

    public static synchronized UtenteBusiness getInstance() {
        if (instance == null) {
            instance = new UtenteBusiness();
        }
        return instance;
    }

    public LoginResult login(String username, String password) {

        UtenteDAO uDao = UtenteDAO.getInstance();

        LoginResult result = new LoginResult();

        // 1. controllare se l'utente esiste
        boolean userExists = uDao.userExists(username);
        if (!userExists) {
            result.setResult(LoginResult.Result.USER_DOESNT_EXIST);
            result.setMessage("Lo username inserito non esiste! Riprova");
            return result;
        }

        // 2. controllare se username e password sono ok
        boolean credentialsOk = uDao.checkCredentials(username, password);
        if (!credentialsOk) {
            result.setResult(LoginResult.Result.WRONG_PASSWORD);
            result.setMessage("La password digitata non è corretta! Riprova");
            return result;
        }

        // 3. che tipo di utente e' (cliente/manager/amministratore)
        boolean isCliente = uDao.isCliente(username);
        boolean isManager = uDao.isManager(username);
        boolean isAmministratore = uDao.isAmministratore(username);

        // 4. caricare oggetto utente (a seconda della tipologia)
       if(isCliente) {
           IClienteDAO cDao = ClienteDAO.getInstance();
           Cliente c = cDao.findById(username);
           SessionManager.getSession().put(SessionManager.LOGGED_USER, c);
           result.setMessage("Benvenuto "+ c.getName() +" e buona spesa con MyShop!!");

       } else if(isManager) {
           IManagerDAO mDao = ManagerDAO.getInstance();
           Manager m = mDao.findById(username);
           SessionManager.getSession().put(SessionManager.LOGGED_USER, m);
           result.setMessage("Benvenuto "+ m.getName() +" e buon lavoro con MyShop!!");

       } else if(isAmministratore) {
           IAmministratoreDAO aDao = AmministratoreDAO.getInstance();
           Amministratore a = aDao.findById(username);
           SessionManager.getSession().put(SessionManager.LOGGED_USER, a);
           result.setMessage("Benvenuto "+ a.getName() +" e buon lavoro con MyShop!!");

       }

        result.setResult(LoginResult.Result.LOGIN_OK);
        return result;
    }

    public RegisterResult register(Cliente c){

        UtenteDAO uDao = UtenteDAO.getInstance();
        RegisterResult result = new RegisterResult();

        // 1. controllare se l'utente esiste già
        boolean userExists = uDao.userExists(c.getUsername());
        if (userExists) {
            result.setResult(RegisterResult.Result.USER_ALREADY_EXISTS);
            result.setMessage("Lo username inserito non esiste! Riprova");
            return result;
        }

        //2. controllare se la password è troppo debole
        String pwd = c.getPwd();
        final int MINIMUM_LENGTH = 8;
        boolean length = pwd.length() >= MINIMUM_LENGTH;
        if(length){
            result.setResult(RegisterResult.Result.USER_ALREADY_EXISTS);
            result.setMessage("Lo username inserito non esiste! Riprova");
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

        //4. inserimento del nuovo cliente
        ClienteDAO cDao = ClienteDAO.getInstance();
        cDao.add(c);

        result.setResult(RegisterResult.Result.REGISTER_OK);
        result.setMessage("Registrazione effettuata con successo! Benvenuto in MyShop!");
        return result;
    }
}
