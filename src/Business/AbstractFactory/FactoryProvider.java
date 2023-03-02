package Business.AbstractFactory;

public class FactoryProvider {

    public enum FactoryType { PRODOTTO, PRODOTTO_COMPOSITO, SERVIZIO }

    public static AbstractFactory getFactory(FactoryType type) {

        switch(type) {
            case PRODOTTO: return new ProdottoFactory();
            case PRODOTTO_COMPOSITO: return new ProdottoCompositoFactory();
            case SERVIZIO: return new ServizioFactory();
            default: return null;
        }
    }

}
