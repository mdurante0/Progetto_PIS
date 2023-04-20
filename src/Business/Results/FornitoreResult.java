package Business.Results;

public class FornitoreResult {

    public enum Result { ADD_OK, SUPPLIER_ALREADY_EXISTS, SUPPLIER_DOESNT_EXIST, UPDATE_OK, DELETE_OK, SUPPLIER_ERROR }

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
