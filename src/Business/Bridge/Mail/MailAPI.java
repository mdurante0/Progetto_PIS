package Business.Bridge.Mail;

public interface MailAPI {
    int send(String from, String password, String to,String sub,String msg, String pathFile);
    int send(String from, String password, String to,String sub,String msg);

}
