package Model;

public class Amministratore extends Utente {

    public Amministratore() {
        super();
    }
    public Amministratore(String name, String surname, String username, String pwd, String email, String tipo) {
        super(name, surname, username, pwd, email, tipo);
    }

    @Override
    public String toString() {
        return "Amministratore{" +
                "nome=" + super.getName() +
                ", cognome="+super.getSurname()+
                ", username="+super.getUsername()+
                ", email=" + super.getEmail()+
                "}";
    }
}
