package DAO;

import Model.Collocazione;

import java.util.ArrayList;

public interface ICollocazioneDAO {
    Collocazione findById(int corsia, int scaffale, int idMagazzino);
    ArrayList<Collocazione> findAll();
    int add(Collocazione collocazione);
    int removeById(int id);
    int update(Collocazione collocazione);
}