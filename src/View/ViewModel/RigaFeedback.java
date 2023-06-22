package View.ViewModel;

import Model.Feedback;

import java.util.Date;

public class RigaFeedback {
    private Date data;
    private String usernameCliente;
    private String commento;
    private Feedback.Punteggio punteggio;
    private String risposta;
    private Object rispondiButton;

    public Object getRispondi() {
        return rispondiButton;
    }

    public void setRispondi(Object rispondi) {
        this.rispondiButton = rispondi;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getUsernameCliente() {
        return usernameCliente;
    }

    public void setUsernameCliente(String usernameCliente) {
        this.usernameCliente = usernameCliente;
    }

    public String getCommento() {
        return commento;
    }

    public void setCommento(String commento) {
        this.commento = commento;
    }

    public Feedback.Punteggio getPunteggio() {
        return punteggio;
    }

    public void setPunteggio(Feedback.Punteggio punteggio) {
        this.punteggio = punteggio;
    }
    public String getRisposta() {
        return risposta;
    }

    public void setRisposta(String risposta) {
        this.risposta = risposta;
    }

}
