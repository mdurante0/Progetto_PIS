package Business.Results;

public class CategoriaResult {

    public enum Result { ADD_OK, CATEGORY_ALREADY_EXISTS, CATEGORY_ERROR }

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
