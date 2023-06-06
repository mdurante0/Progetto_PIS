package Business.Results;

import Model.Feedback;

import java.util.ArrayList;

public class FeedbackResult {

    public enum Result { ADD_OK, UPDATE_OK, FEEDBACK_CARICATI, USER_DOESNT_EXIST, ITEM_DOESNT_EXIST, FEEDBACK_DOESNT_EXIST, ACQUISTO_ERROR, ADD_ERROR, UPDATE_ERROR }
    private String message;
    private Result result;
    private ArrayList<Feedback> feedbacks = new ArrayList<>();
    public String getMessage(){return this.message;}
    public void setMessage(String message){this.message = message;}
    public Result getResult(){return this.result;}
    public void setResult(Result result){this.result = result;}
    public ArrayList<Feedback> getFeedbacks() {
        return feedbacks;
    }
    public void setFeedbacks(ArrayList<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
    }
}
