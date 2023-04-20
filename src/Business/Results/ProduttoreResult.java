package Business.Results;

public class ProduttoreResult {

    public enum Result { ADD_OK, PRODUCER_ALREADY_EXISTS, PRODUCER_DOESNT_EXIST, UPDATE_OK, DELETE_OK, PRODUCER_ERROR }

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
