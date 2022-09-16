package DAO;

import Model.ListaAcquisto;

import java.util.ArrayList;

public interface IListaAcquistoDAO {
    ListaAcquisto findById(int id);
    ArrayList<ListaAcquisto> findAll();
    int add(ListaAcquisto listaAcquisto);
    int removeById(int id);
    int update(ListaAcquisto listaAcquisto);
}