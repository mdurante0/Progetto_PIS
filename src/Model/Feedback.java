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
    private Date data;

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
}
