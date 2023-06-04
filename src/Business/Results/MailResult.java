package Business.Results;

public class MailResult {
    public enum Result { INVIO_OK, USER_DOESNT_EXIST, INVIO_ERROR, ALLEGATO_ERROR }

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
