package Business;

public class ClienteBusiness {

    private static ClienteBusiness instance;

    public static synchronized ClienteBusiness getInstance() {
        if (instance == null) {
            instance = new ClienteBusiness();
        }
        return instance;
    }

}
