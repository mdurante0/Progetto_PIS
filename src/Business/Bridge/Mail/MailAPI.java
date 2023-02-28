package Business.Bridge.Mail;

public interface MailAPI {
    public void send(String from, String password, String to,String sub,String msg, String pathFile);
    public void send(String from, String password, String to,String sub,String msg);

}
