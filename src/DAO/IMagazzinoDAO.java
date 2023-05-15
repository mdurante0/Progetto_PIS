package DAO;

import Model.Magazzino;
import Model.composite.IProdotto;

import java.util.ArrayList;

public interface IMagazzinoDAO {
    Magazzino findById(int id);
    Magazzino findByAddress(String indirizzo);
    ArrayList<Magazzino> findAll();
    int add(Magazzino magazzino);
    int removeById(int id);
    int update(Magazzino magazzino);
    int addProdotto(int idMagazzino, IProdotto iProdotto);
    int removeProdotto(int idMagazzino, IProdotto iProdotto);
    Magazzino getProdottiInMagazzinoByAddress(String indirizzo);
    Magazzino getProdottiInMagazzinoById(int idMagazzino);
}