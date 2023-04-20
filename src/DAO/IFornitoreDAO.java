package DAO;

import Model.Fornitore;

import java.util.ArrayList;

public interface IFornitoreDAO {
    Fornitore findById(int id);
    Fornitore findByName(String name);
    ArrayList<Fornitore> findAll();
    int add(Fornitore fornitore);
    int removeById(String nome);
    int update(Fornitore fornitore);
}