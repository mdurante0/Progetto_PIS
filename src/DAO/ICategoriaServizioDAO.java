package DAO;

import Model.CategoriaServizio;

import java.util.ArrayList;

public interface ICategoriaServizioDAO {
    CategoriaServizio findById(String nome);
    ArrayList<CategoriaServizio> findAll();
    int add(CategoriaServizio categoriaServizio);
    int removeById(String nome);
    int update(CategoriaServizio categoriaServizio);
}