package DAO;

import Model.CategoriaProdotto;
import Model.Magazzino;

import java.util.ArrayList;

public interface IMagazzinoDAO {
    Magazzino findById(int id);
    ArrayList<Magazzino> findAll();
    int add(Magazzino magazzino);
    int removeById(int id);
    int update(Magazzino magazzino);
}