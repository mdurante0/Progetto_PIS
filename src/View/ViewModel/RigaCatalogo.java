package View.ViewModel;

public class RigaCatalogo {

    private int idArticolo;
    private String nomeProdotto;
    private String nomeProduttore;
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

    public String getNomeProdotto() {
        return nomeProdotto;
    }

    public void setNomeProdotto(String nomeProdotto) {
        this.nomeProdotto = nomeProdotto;
    }

    public String getNomeProduttore() {
        return nomeProduttore;
    }

    public void setNomeProduttore(String nomeProduttore) {
        this.nomeProduttore = nomeProduttore;
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
