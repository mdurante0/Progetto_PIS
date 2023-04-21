package Business;

public class ManagerBusiness {

    private static ManagerBusiness instance;

    public static synchronized ManagerBusiness getInstance() {
        if (instance == null) {
            instance = new ManagerBusiness();
        }
        return instance;
    }


}
