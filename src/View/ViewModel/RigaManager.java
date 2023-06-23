package View.ViewModel;

public class RigaManager {

    private String username;
    private String email;
    private String nomeManager;
    private String congnomeManager;
    private int durataIncarico;
    private Object modificaButton;
    private Object eliminaButton;

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

    public String getNomeManager() {
        return nomeManager;
    }

    public void setNomeManager(String nomeManager) {
        this.nomeManager = nomeManager;
    }

    public String getCongnomeManager() {
        return congnomeManager;
    }

    public void setCongnomeManager(String congnomeManager) {
        this.congnomeManager = congnomeManager;
    }

    public int getDurataIncarico() {
        return durataIncarico;
    }

    public void setDurataIncarico(int durataIncarico) {
        this.durataIncarico = durataIncarico;
    }

    public Object getModificaButton() {
        return modificaButton;
    }

    public void setModificaButton(Object modificaButton) {
        this.modificaButton = modificaButton;
    }

    public Object getEliminaButton() {
        return eliminaButton;
    }

    public void setEliminaButton(Object eliminaButton) {
        this.eliminaButton = eliminaButton;
    }
}
