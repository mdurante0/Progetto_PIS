package Business.Results;

public class LoginResult {

    public enum Result { LOGIN_OK, WRONG_PASSWORD, USER_DOESNT_EXIST, USER_BLOCKED }

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
