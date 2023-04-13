package DAO;

import Model.Collocazione;

import java.util.ArrayList;

public interface ICollocazioneDAO {
    Collocazione findById(int idCollocazione);
    ArrayList<Collocazione> findAll();
    ArrayList<Collocazione> findAllByMagazzino(int idMagazzino);
    int add(Collocazione collocazione);
    int removeById(int id);
    int update(Collocazione collocazione);
}