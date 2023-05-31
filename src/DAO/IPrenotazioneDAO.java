package DAO;

import Model.Prenotazione;
import Model.composite.Prodotto;

import java.util.ArrayList;

public interface IPrenotazioneDAO {
    Prenotazione findById(int id);
    ArrayList<Prenotazione> findByUser(int idUser);
    ArrayList<Prenotazione> findAll();
    int add(Prenotazione prenotazione);
    int removeById(int id);
    int removeByUser(int idUser);
    int addProdotto(int idPrenotazione, Prodotto prodotto);
    int removeProdotto(int idPrenotazione, Prodotto prodotto);
    int update(Prenotazione prenotazione);
}