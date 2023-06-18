package DAO;

import Model.composite.IProdotto;

import java.util.ArrayList;

public interface IProdottoDAO {
    IProdotto findById(int idProdotto);
    IProdotto findByName(String name);
    ArrayList<IProdotto> findAll();
    ArrayList<IProdotto> findAllByCategoria(int idCategoria);
    int add(IProdotto prodotto);
    int removeById(int idProdotto);
    int update(IProdotto prodotto);
}