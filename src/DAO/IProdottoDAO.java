package DAO;

import Model.composite.Prodotto;

import java.util.ArrayList;

public interface IProdottoDAO {
    Prodotto findById(int idProdotto);
    ArrayList<Prodotto> findAll();
    int add(Prodotto prodotto);
    int removeById(int idProdotto);
    int update(Prodotto prodotto);
}