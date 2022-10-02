package DAO;

import Model.Prodotto;

import java.util.ArrayList;

public interface IPrenotazioneDAO {
    Prodotto findById(int id);
    ArrayList<Prodotto> findByUser(int idUser);
    ArrayList<Prodotto> findAll();
    int add(Prodotto prenotazione);
    int removeById(int id);
    int removeByUser(int idUser);
    int update(Prodotto prenotazione);
}