package DAO;

import Model.Servizio;

import java.util.ArrayList;

public interface IServizioDAO {
    Servizio findById(int idServizio);
    ArrayList<Servizio> findAll();
    int add(Servizio servizio);
    int removeById(String nome);
    int update(Servizio servizio);
}