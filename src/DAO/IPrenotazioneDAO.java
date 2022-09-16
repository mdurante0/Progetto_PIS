package DAO;

import Model.Magazzino;
import Model.Prenotazione;

import java.util.ArrayList;

public interface IPrenotazioneDAO {
    Prenotazione findById(int id);
    ArrayList<Prenotazione> findAll();
    int add(Prenotazione prenotazione);
    int removeById(int id);
    int update(Prenotazione prenotazione);
}