package Business.Bridge;

public abstract class Mail {
    protected MailAPI mailAPI;

    public Mail(MailAPI mailAPI) {
        this.mailAPI = mailAPI;
    }

    public abstract void send();
    public abstract void send(String pathFile);
}
