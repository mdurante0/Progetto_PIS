package Business.Results;

public class AbilitazioneResult {
    public enum Result { ABILITAZIONE_OK, USER_DOESNT_EXIST, ABILITAZIONE_ERROR }

    private Result result;
    private String message;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
