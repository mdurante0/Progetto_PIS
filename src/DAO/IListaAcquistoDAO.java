package DAO;

import Model.Articolo;
import Model.ListaAcquisto;

import java.util.ArrayList;

public interface IListaAcquistoDAO {
    ListaAcquisto findById(int id);
    ListaAcquisto findByName(String nome);
    ArrayList<ListaAcquisto> findByUser(int idUtenteAcquirente);
    ArrayList<ListaAcquisto> findAll();
    int add(ListaAcquisto listaAcquisto);
    int removeById(int id);
    int removeByUser(int idUtenteAcquirente);
    int setPagata(int idListaAcquisto);
    ArrayList<ListaAcquisto> findNotPaidByPuntoVendita(int idPuntoVendita);
    int addArticolo(int idListaAcquisto, Articolo articolo);
    int removeArticolo(int idListaAcquisto, Articolo articolo);
    int update(ListaAcquisto listaAcquisto);
}