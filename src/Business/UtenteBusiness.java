package Business;

import Business.Results.LoginResult;
import Business.Results.RegisterResult;
import DAO.*;
import Model.Amministratore;
import Model.Cliente;
import Model.Manager;
import Model.Utente;

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

        String encryptedPwd = Encrypt.encrypt(password);
        // 2. controllare se username e password sono ok
        boolean credentialsOk = uDao.checkCredentials(username, encryptedPwd);
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
           if(c.isAbilitazione()) {
               SessionManager.getSession().put(SessionManager.LOGGED_USER, c);
               result.setMessage("Benvenuto " + c.getName() + " e buona spesa con MyShop!!");
           }
           else{
               result.setResult(LoginResult.Result.USER_BLOCKED);
               result.setMessage("Accesso negato! Chiedere assistenza");
               return result;
           }

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


    public RegisterResult register(Utente u) {

        UtenteDAO uDao = UtenteDAO.getInstance();
        RegisterResult result = new RegisterResult();

        // 1. controllare se l'utente esiste già
        boolean userExists = uDao.userExists(u.getUsername());
        if (userExists) {
            result.setResult(RegisterResult.Result.USER_ALREADY_EXISTS);
            result.setMessage("Lo username inserito è già esistente! Riprova");
            return result;
        }

        //2. controllare se la password è troppo debole
        String pwd = u.getPwd();
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

        String pwdEncrypted = Encrypt.encrypt(u.getPwd());
        u.setPwd(pwdEncrypted);

        //3. inserimento del nuovo utente
        int inserito = 0;

        if(u.getTipo().equalsIgnoreCase("CL")) { //il nuovo utente è un cliente
            ClienteDAO cDao = ClienteDAO.getInstance();
            inserito = cDao.add((Cliente) u);

        } else if (u.getTipo().equalsIgnoreCase("MN")){ //il nuovo utente è un manager
            ManagerDAO mDao = ManagerDAO.getInstance();
            inserito = mDao.add((Manager) u);

        }else { //tipo non previsto
            result.setResult(RegisterResult.Result.WRONG_TYPE);
            result.setMessage("Tipo di utente non specificato!");
            return result;
        }

        if(inserito == 0){
            result.setResult(RegisterResult.Result.REGISTER_ERROR);
            result.setMessage("Errore nella registrazione! Riprova!");
            return result;
        }
        result.setResult(RegisterResult.Result.REGISTER_OK);
        result.setMessage("Registrazione effettuata con successo! Benvenuto in MyShop!");
        return result;
    }
}
