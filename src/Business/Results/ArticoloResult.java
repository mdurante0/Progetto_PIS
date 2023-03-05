package Business.Results;

public class ArticoloResult {
    public enum Result { ADD_OK, ITEM_ALREADY_EXISTS, WRONG_TYPE, ITEM_ERROR }

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
