package DAO;

import Model.CategoriaServizio;

import java.util.ArrayList;

public interface ICategoriaServizioDAO {
    CategoriaServizio findById(int idCategoria);
    ArrayList<CategoriaServizio> findAll();
    int add(CategoriaServizio categoriaServizio);
    int removeById(String nome);
    CategoriaServizio findByName(String name);
    int update(CategoriaServizio categoriaServizio);
}