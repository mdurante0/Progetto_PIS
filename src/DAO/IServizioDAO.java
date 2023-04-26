package DAO;

import Model.Servizio;

import java.util.ArrayList;

public interface IServizioDAO {
    Servizio findById(int idServizio);
    ArrayList<Servizio> findAll();
    ArrayList<Servizio> findAllByCategoria(int idCategoria);
    Servizio findByName(String name);
    int add(Servizio servizio);
    int removeById(int idServizio);
    int update(Servizio servizio);
}