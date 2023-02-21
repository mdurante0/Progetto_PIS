package DAO;

import Model.Utente;

import java.util.ArrayList;

public interface IUtenteDAO {
    Utente findById(String username);
    ArrayList<Utente> findAll();
    int add(Utente utente);
    int removeById(String username);
    int update(Utente utente);
    boolean userExists(String username);
    boolean checkCredentials(String username, String password);
    boolean isCliente(String username);
    boolean isManager(String username);
    boolean isAmministratore(String username);
}