package View.ViewModel;

public class RigaCategoriaProdotto {

  private String nomeCategoria;
  private String nomeSopraCategoria;
  private Object modificaButton;
  private Object eliminaButton;

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    public String getNomeSopraCategoria() {
        return nomeSopraCategoria;
    }

    public void setNomeSopraCategoria(String nomeSopraCategoria) {
        this.nomeSopraCategoria = nomeSopraCategoria;
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
