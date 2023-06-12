package DAO;

import Model.Cliente;

import java.util.ArrayList;

public interface IClienteDAO {
    Cliente findById(int idUtente);
    Cliente findByUsername(String username);
    ArrayList<Cliente> findAll();
    boolean isGestibile(Cliente c, int idManager);
    int add(Cliente cliente);
    int removeById(String username);
    int update(Cliente cliente);
}