package DAO;

import Model.Collocazione;

import java.util.ArrayList;

public interface ICollocazioneDAO {
    Collocazione findById(int corsia, int scaffale);
    ArrayList<Collocazione> findAll();
    int add(Collocazione collocazione);
    int removeById(int corsia, int scaffale);
    int update(Collocazione collocazione);
}