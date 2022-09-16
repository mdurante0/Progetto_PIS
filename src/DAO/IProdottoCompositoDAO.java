package DAO;

import Model.Servizio;
import Model.composite.ProdottoComposito;

import java.util.ArrayList;

public interface IProdottoCompositoDAO {
    ProdottoComposito findById(String nome);
    ArrayList<ProdottoComposito> findAll();
    int add(String nome);
    int removeById(String nome);
    int update(ProdottoComposito prodottoComposito);
}