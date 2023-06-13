package DAO;

import Model.Manager;

import java.util.ArrayList;

public interface IManagerDAO {
    Manager findById(int idUtente);
    Manager findByUsername(String username);
    ArrayList<Manager> findAll();
    int add(Manager manager);
    int removeById(String username);
    int update(Manager manager);
}