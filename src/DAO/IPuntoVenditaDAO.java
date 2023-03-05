package DAO;

import Model.PuntoVendita;

import java.util.ArrayList;

public interface IPuntoVenditaDAO {
    PuntoVendita findById(int id);
    PuntoVendita findByName(String name);
    ArrayList<PuntoVendita> findByManager(int idManager);
    ArrayList<PuntoVendita> findAll();
    int add(PuntoVendita puntoVendita);
    int removeById(int id);
    int update(PuntoVendita puntoVendita);
}