package DAO;

import Model.Articolo;

import java.util.ArrayList;

public interface IArticoloDAO {
    Articolo findById(int idArticolo);
    ArrayList<Articolo> findAll();
    int add(Articolo articolo);
    int removeById(int idArticolo);
    int update(Articolo articolo);
    Articolo findByName(String name);
}