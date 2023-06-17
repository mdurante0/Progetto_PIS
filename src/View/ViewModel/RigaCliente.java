package View.ViewModel;

public class RigaCliente {

    private String username;
    private String email;
    private String nomeCliente;
    private String congnomeCliente;

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getCongnomeCliente() {
        return congnomeCliente;
    }

    public void setCongnomeCliente(String congnomeCliente) {
        this.congnomeCliente = congnomeCliente;
    }

    private Object abilitazione;
    private Object eliminaButton;

    public Object getEliminaButton() {
        return eliminaButton;
    }

    public void setEliminaButton(Object eliminaButton) {
        this.eliminaButton = eliminaButton;
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

    public Object getAbilitazione() {
        return abilitazione;
    }

    public void setAbilitazione(Object abilitazione) {
        this.abilitazione = abilitazione;
    }
}
