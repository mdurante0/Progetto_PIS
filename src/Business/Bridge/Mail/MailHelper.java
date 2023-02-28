package Business.Bridge.Mail;

public class MailHelper extends Mail {

    private static MailHelper instance;

    private static String FROM = "myshopdurantescelsi@gmail.com";
    private static String PASSWORD = "gjrkxarpiovtrpwn";

    private String to;
    private String sub;
    private String msg;

    private MailHelper(MailAPI mailAPI, String to, String sub, String msg) {
        super(mailAPI);
        this.to = to;
        this.sub = sub;
        this.msg = msg;
    }


    public static synchronized MailHelper getInstance(MailAPI mailAPI, String to, String sub, String msg) {
        instance = new MailHelper(mailAPI,to,sub,msg);
        return instance;
    }

    public void send(){
        mailAPI.send(FROM, PASSWORD, to,sub,msg);
    }

    public void send(String pathFile){
        mailAPI.send(FROM, PASSWORD, to,sub,msg,pathFile);
    }

}