package DAO;

import Model.composite.Prodotto;

import java.util.ArrayList;

public interface IProdottoDAO {
    Prodotto findById(int idProdotto);
    Prodotto findByName(String name);
    ArrayList<Prodotto> findAll();
    ArrayList<Prodotto> findAllByCategoria(int idCategoria);
    int add(Prodotto prodotto);
    int removeById(int idProdotto);
    int update(Prodotto prodotto);
}