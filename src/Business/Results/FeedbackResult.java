package Business.Results;

public class FeedbackResult {

    public enum Result { ADD_OK, UPDATE_OK, USER_DOESNT_EXIST, ITEM_DOESNT_EXIST, ACQUISTO_ERROR, ADD_ERROR, UPDATE_ERROR }
    private String message;
    private Result result;

    public String getMessage(){return this.message;}
    public void setMessage(String message){this.message = message;}
    public Result getResult(){return this.result;}
    public void setResult(Result result){this.result = result;}
}
