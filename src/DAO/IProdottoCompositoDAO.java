package DAO;

import Model.composite.Prodotto;
import Model.composite.ProdottoComposito;

import java.util.ArrayList;

public interface IProdottoCompositoDAO {
    ProdottoComposito findById(int idProdottoComposito);
    ArrayList<ProdottoComposito> findAll();
    ProdottoComposito findByName(String name);
    int add(ProdottoComposito prodottoComposito);

    int addSottoprodotto(int idProdottoComposito, Prodotto sottoprodotto);

    // int addSottoprodotto(int idProdottoComposito, Prodotto sottoprodotto);
    int removeSottoprodotto(int idProdottoComposito, Prodotto sottoprodotto);
    int removeById(int idProdottoComposito);
    int update(ProdottoComposito prodottoComposito);
}