package DAO;

import Model.CategoriaProdotto;

import java.util.ArrayList;

public interface ICategoriaProdottoDAO {
    CategoriaProdotto findById(int idCateogria);
    ArrayList<CategoriaProdotto> findAll();
    int add(CategoriaProdotto categoriaProdotto);
    CategoriaProdotto findByName(String name);
    int removeById(String nome);
    ArrayList<CategoriaProdotto> findAllByParent(int idCategoriaParent);
    int update(CategoriaProdotto categoriaProdotto);
}