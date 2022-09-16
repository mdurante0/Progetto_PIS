package DAO;

import Model.Amministratore;

import java.util.ArrayList;

public interface IAmministratoreDAO {
    Amministratore findById(String email);
    ArrayList<Amministratore> findAll();
    int add(Amministratore amministratore);
    int removeById(String email);
    int update(Amministratore amministratore);
}