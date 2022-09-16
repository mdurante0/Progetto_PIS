package DAO;

import Model.composite.Prodotto;

import java.util.ArrayList;

public interface IProdottoDAO {
    Prodotto findById(String nome);
    ArrayList<Prodotto> findAll();
    int add(Prodotto prodotto);
    int removeById(String nome);
    int update(Prodotto prodotto);
}