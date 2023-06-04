package Business.Bridge.Mail;

public class MailHelper extends Mail {

    private static MailHelper instance;

    private static final String FROM = "myshopdurantescelsi@gmail.com";
    private static final String PASSWORD = "gjrkxarpiovtrpwn";

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

    public int send(){
        return mailAPI.send(FROM, PASSWORD, to,sub,msg);
    }

    public int send(String pathFile){
        return mailAPI.send(FROM, PASSWORD, to,sub,msg,pathFile);
    }

}