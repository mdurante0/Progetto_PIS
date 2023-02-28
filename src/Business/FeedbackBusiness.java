package Business;

import DAO.FeedbackDAO;
import Model.Feedback;

public class FeedbackBusiness {

    private static FeedbackBusiness instance;

    public static synchronized FeedbackBusiness getInstance() {
        if (instance == null) {
            instance = new FeedbackBusiness();
        }
        return instance;
    }

    public boolean addFeedback(Feedback feedback){
        FeedbackDAO fDao = FeedbackDAO.getInstance();
        return fDao.add(feedback) == 1;
    }
}
