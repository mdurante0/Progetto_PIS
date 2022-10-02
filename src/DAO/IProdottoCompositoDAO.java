package DAO;

import Model.composite.ProdottoComposito;

import java.util.ArrayList;

public interface IProdottoCompositoDAO {
    ProdottoComposito findById(int idProdottoComposito);
    ArrayList<ProdottoComposito> findAll();
    int add(String nome);
    int removeById(String nome);
    int update(ProdottoComposito prodottoComposito);
}