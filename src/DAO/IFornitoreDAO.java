package DAO;

import Model.Fornitore;

import java.util.ArrayList;

public interface IFornitoreDAO {
    Fornitore findById(String name);
    ArrayList<Fornitore> findAll();
    int add(Fornitore fornitore);
    int removeById(String email);
    int update(Fornitore fornitore);
}