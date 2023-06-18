package DAO;

import Model.Prenotazione;
import Model.composite.IProdotto;

import java.util.ArrayList;

public interface IPrenotazioneDAO {
    Prenotazione findById(int id);
    ArrayList<Prenotazione> findByUser(int idUser);
    ArrayList<Prenotazione> findAll();
    int add(Prenotazione prenotazione);
    int removeById(int id);
    int removeByUser(int idUser);
    int addProdotto(int idPrenotazione, IProdotto prodotto);
    int removeProdotto(int idPrenotazione, IProdotto prodotto);
    int update(Prenotazione prenotazione);
}