package DAO;

import Model.composite.IProdotto;
import Model.composite.Prodotto;

import java.util.ArrayList;

public interface IProdottoDAO {
    Prodotto findById(int idProdotto);
    Prodotto findByName(String name);
    ArrayList<Prodotto> findAll();
    ArrayList<Prodotto> findAllByCategoria(int idCategoria);
    int add(IProdotto prodotto);
    int removeById(int idProdotto);
    int update(IProdotto prodotto);
}