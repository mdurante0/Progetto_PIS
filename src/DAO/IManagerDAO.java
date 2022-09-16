package DAO;

import Model.Manager;
import Model.Utente;

import java.util.ArrayList;

public interface IManagerDAO {
    Manager findById(String username);
    ArrayList<Manager> findAll();
    int add(Manager manager);
    int removeById(String username);
    int update(Manager manager);
}