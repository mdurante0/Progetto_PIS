package DAO;

import Model.Prenotazione;
import Model.Produttore;

import java.util.ArrayList;

public interface IProduttoreDAO {
    Produttore findById(String email);
    ArrayList<Produttore> findAll();
    int add(String email);
    int removeById(String email);
    int update(Produttore produttore);
}