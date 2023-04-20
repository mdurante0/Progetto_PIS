package DAO;

import Model.Produttore;

import java.util.ArrayList;

public interface IProduttoreDAO {
    Produttore findById(int id);
    Produttore findByName(String name);
    ArrayList<Produttore> findAll();
    int add(Produttore produttore);
    int removeById(String nome);
    int update(Produttore produttore);
}