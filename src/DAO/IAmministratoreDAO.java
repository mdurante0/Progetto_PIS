package DAO;

import Model.Amministratore;

import java.util.ArrayList;

public interface IAmministratoreDAO {
    Amministratore findById(String username);
    ArrayList<Amministratore> findAll();
    int add(Amministratore amministratore);
    int removeById(String username);
    int update(Amministratore amministratore);
}