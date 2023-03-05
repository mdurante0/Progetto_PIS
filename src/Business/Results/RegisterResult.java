package Business.Results;

public class RegisterResult {

    public enum Result { REGISTER_OK, WEAK_PASSWORD, USER_ALREADY_EXISTS, WRONG_TYPE, REGISTER_ERROR }

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
