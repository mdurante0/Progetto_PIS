package DAO;

import Model.PuntoVendita;
import Model.Servizio;

import java.util.ArrayList;

public interface IServizioDAO {
    Servizio findById(String nome);
    ArrayList<Servizio> findAll();
    int add(String nome);
    int removeById(String nome);
    int update(Servizio servizio);
}