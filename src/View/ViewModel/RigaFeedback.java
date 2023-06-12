package View.ViewModel;

import Model.Feedback;

import java.util.Date;

public class RigaFeedback {
    private Date data;
    private String usernameCliente;
    private String commento;
    private Feedback.Punteggio punteggio;
    private String risposta;

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
