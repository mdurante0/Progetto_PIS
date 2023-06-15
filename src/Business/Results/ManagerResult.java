package Business.Results;

import Model.Manager;

import java.util.ArrayList;

public class ManagerResult {
    public enum Result { USER_DOESNT_EXIST, RIMOZIONE_OK, RIMOZIONE_ERROR, MANAGER_ERROR, USER_ALREADY_EXISTS, ADD_OK, UPDATE_OK, MANAGER_CARICATI}

    private Result result;
    private String message;
    private ArrayList<Manager> managers = new ArrayList<>();

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

    public ArrayList<Manager> getManagers() {
        return managers;
    }

    public void setManagers(ArrayList<Manager> managers) {
        this.managers = managers;
    }
}
