package View.ViewModel;

public class RigaCatalogo {

    private int idArticolo;
    private String nome;
    private String nomeRifornitore;
    private float prezzo;
    private String nomeCategoria;
    private Object dettagliButton;

    public Object getDettagliButton() {
        return dettagliButton;
    }

    public void setDettagliButton(Object dettagliButton) {
        this.dettagliButton = dettagliButton;
    }

    public int getIdArticolo() {
        return idArticolo;
    }

    public void setIdArticolo(int idArticolo) {
        this.idArticolo = idArticolo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeRifornitore() {
        return nomeRifornitore;
    }

    public void setNomeRifornitore(String nomeRifornitore) {
        this.nomeRifornitore = nomeRifornitore;
    }

    public float getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(float prezzo) {
        this.prezzo = prezzo;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }
}
