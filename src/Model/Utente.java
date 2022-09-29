package Model;

public class Utente {

    private int idUtente;
    private String name;
    private String surname;
    private String username;
    private String pwd;
    private String email;
    private String tipo;

    public Utente() {}

    public Utente(String name, String surname, String username, String pwd, String email, String tipo) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.pwd = pwd;
        this.email = email;
        this.tipo = tipo;
    }

    public int getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Utente{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}