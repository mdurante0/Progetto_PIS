package DAO;

import Model.composite.IProdotto;
import Model.composite.ProdottoComposito;

import java.util.ArrayList;

public interface IProdottoCompositoDAO {
    ProdottoComposito findById(int idProdottoComposito);
    ArrayList<ProdottoComposito> findAll();
    ProdottoComposito findByName(String name);
    int add(ProdottoComposito prodottoComposito);
    int addSottoprodotto(int idProdottoComposito, IProdotto sottoprodotto);
    int removeSottoprodotto(int idProdottoComposito, IProdotto sottoprodotto);
    int removeAllSottoprodotti(int idProdottoComposito);
    int removeById(int idProdottoComposito);
    int update(ProdottoComposito prodottoComposito);
}