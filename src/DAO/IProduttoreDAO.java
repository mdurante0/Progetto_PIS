package DAO;

import Model.Prenotazione;
import Model.Produttore;

import java.util.ArrayList;

public interface IProduttoreDAO {
    Produttore findById(String nome);
    ArrayList<Produttore> findAll();
    int add(Produttore produttore);
    int removeById(String nome);
    int update(Produttore produttore);
}