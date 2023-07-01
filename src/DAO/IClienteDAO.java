package DAO;

import Model.Cliente;

import java.util.ArrayList;

public interface IClienteDAO {
    Cliente findById(int idUtente);
    Cliente findByUsername(String username);
    ArrayList<Cliente> findAll();
    ArrayList<Cliente> findAllByPuntoVendita(int idPuntoVendita);
    int add(Cliente cliente);
    int removeById(String username);
    int update(Cliente cliente);
}