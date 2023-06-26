package View.ViewModel;

public class RigaCliente {

    private String username;
    private String email;
    private String nomeCliente;
    private String congnomeCliente;
    private Object emailButton;
    private Object abilitazioneButton;
    private Object eliminaButton;

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }
    public Object getEmailButton() {
        return emailButton;
    }

    public void setEmailButton(Object emailButton) {
        this.emailButton = emailButton;
    }
    public String getCongnomeCliente() {
        return congnomeCliente;
    }

    public void setCongnomeCliente(String congnomeCliente) {
        this.congnomeCliente = congnomeCliente;
    }
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

    public Object getAbilitazioneButton() {
        return abilitazioneButton;
    }

    public void setAbilitazioneButton(Object abilitazioneButton) {
        this.abilitazioneButton = abilitazioneButton;
    }
}
