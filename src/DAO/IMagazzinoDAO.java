package DAO;

import Model.Collocazione;
import Model.Magazzino;
import Model.composite.IProdotto;

import java.util.ArrayList;
import java.util.Collection;

public interface IMagazzinoDAO {
    Magazzino findById(int id);
    Magazzino findByAddress(String indirizzo);
    ArrayList<Magazzino> findAll();
    int add(Magazzino magazzino);
    int removeById(int id);
    int update(Magazzino magazzino);
    int addProdotto(int idMagazzino, IProdotto iProdotto, int quantita, Collocazione collocazione);
    int removeProdotto(int idMagazzino, IProdotto iProdotto);
    ArrayList<Magazzino> getProdottiInMagazzino();
    public Magazzino getProdottiInMagazzinoByName(String indirizzo);
}