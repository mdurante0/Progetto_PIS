package Business.AbstractFactory;

import Model.CategoriaProdotto;
import Model.Composite.ProdottoComposito;

public class ProdottoCompositoFactory implements AbstractFactory {
    @Override
    public IArticolo crea() {
        return new ProdottoComposito();
    }

    @Override
    public ICategoria creaCategoria() {
        return new CategoriaProdotto();
    }
}
