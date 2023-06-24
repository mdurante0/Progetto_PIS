package DAO;

import Model.Collocazione;

import java.util.ArrayList;

public interface ICollocazioneDAO {
    Collocazione findById(int idCollocazione);
    Collocazione findByMagazzinoAndProdotto(int idMagazzino, int idProdotto);
    ArrayList<Collocazione> findAll();
    ArrayList<Collocazione> findAllByMagazzino(int idMagazzino);
    ArrayList<Collocazione> findAllByProdotto(int idProdotto);
    int add(Collocazione collocazione);
    int removeById(int idCollocazione);
    int update(Collocazione collocazione);
}