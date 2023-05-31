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
    private int idArticolo;
    private int idUtente;

    public Feedback(){}

    public Feedback(Punteggio gradimento, String commento, boolean letto, boolean risposto, String risposta, Date data, int idArticolo, int idUtente) {
        this.gradimento = gradimento;
        this.commento = commento;
        this.letto = letto;
        this.risposto = risposto;
        this.risposta = risposta;
        this.data = data;
        this.idArticolo = idArticolo;
        this.idUtente = idUtente;
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

    public int getIdArticolo() {
        return idArticolo;
    }

    public void setIdArticolo(int idArticolo) {
        this.idArticolo = idArticolo;
    }

    public int getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
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
