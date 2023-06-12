package Model;

import java.util.Date;

public class Feedback {

    public enum Punteggio { SCARSO, MEDIOCRE, BUONO, OTTIMO, ECCELLENTE }
    private int idFeedback;
    private Punteggio gradimento;
    private String commento;
    private boolean letto;
    private boolean risposto;
    private String risposta;
    private Date data = new Date();
    private Articolo articolo = new Articolo();
    private Cliente cliente;

    public Feedback(){}

    public Feedback(Punteggio gradimento, String commento, boolean letto, boolean risposto, String risposta, Date data, Articolo articolo, Cliente cliente) {
        this.gradimento = gradimento;
        this.commento = commento;
        this.letto = letto;
        this.risposto = risposto;
        this.risposta = risposta;
        this.data = data;
        this.articolo = articolo;
        this.cliente = cliente;
    }

    public int getIdFeedback() {
        return idFeedback;
    }

    public void setIdFeedback(int idFeedback) {
        this.idFeedback = idFeedback;
    }

    public Punteggio getGradimento() {
        return gradimento;
    }

    public void setGradimento(Punteggio gradimento) {
        this.gradimento = gradimento;
    }

    public String getCommento() {
        return commento;
    }

    public void setCommento(String commento) {
        this.commento = commento;
    }

    public boolean isLetto() {
        return letto;
    }

    public void setLetto(boolean letto) {
        this.letto = letto;
    }

    public String getRisposta() {
        return risposta;
    }

    public void setRisposta(String risposta) {
        this.risposta = risposta;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public boolean isRisposto() {
        return risposto;
    }

    public void setRisposto(boolean risposto) {
        this.risposto = risposto;
    }

    public Articolo getArticolo() {
        return articolo;
    }

    public void setArticolo(Articolo articolo) {
        this.articolo = articolo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "gradimento=" + gradimento +
                ", commento='" + commento + '\'' +
                ", letto=" + letto +
                ", risposto=" + risposto +
                ", risposta='" + risposta + '\'' +
                ", data=" + data +
                '}';
    }
}
