package DAO;

import Model.CategoriaProdotto;

import java.util.ArrayList;

public interface ICategoriaProdottoDAO {
    CategoriaProdotto findById(String nome);
    ArrayList<CategoriaProdotto> findAll();
    int add(CategoriaProdotto categoriaProdotto);
    int removeById(String nome);
    int update(CategoriaProdotto categoriaProdotto);
}