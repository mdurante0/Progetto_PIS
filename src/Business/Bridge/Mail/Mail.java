package Business.Bridge.Mail;

public abstract class Mail {
    protected MailAPI mailAPI;

    public Mail(MailAPI mailAPI) {
        this.mailAPI = mailAPI;
    }

    public abstract int send();
    public abstract int send(String pathFile);
}
