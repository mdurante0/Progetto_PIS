package DAO;

import Model.composite.ProdottoComposito;

import java.util.ArrayList;

public interface IProdottoCompositoDAO {
    ProdottoComposito findById(int idProdottoComposito);
    ArrayList<ProdottoComposito> findAll();
    int add(ProdottoComposito prodottoComposito);
    int removeById(int idProdottoComposito);
    int update(ProdottoComposito prodottoComposito);
}