package Business.Results;

public class PuntoVenditaResult {

    public enum Result { ADD_OK, SALEPOINT_ALREADY_EXISTS, DEPOSIT_ERROR, SALEPOINT_ERROR }

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
