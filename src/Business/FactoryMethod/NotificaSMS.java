package Business.FactoryMethod;

public class NotificaSMS extends Notifica {

    @Override
    public boolean inviaNotifica() {
        System.out.println("Invio tramite SMS");
        return true;
    }
}
