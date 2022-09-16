package DAO;

import Model.Utente;

import java.util.ArrayList;

public interface IUtenteDAO {
    Utente findById(String username);
    ArrayList<Utente> findAll();
    int add(Utente utente);
    int removeById(String username);
    int update(Utente utente);
}