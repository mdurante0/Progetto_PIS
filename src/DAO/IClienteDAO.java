package DAO;

import Model.Cliente;

import java.util.ArrayList;

public interface IClienteDAO {
    Cliente findById(String username);
    ArrayList<Cliente> findAll();
    int add(Cliente cliente);
    int removeById(String username);
    int update(Cliente cliente);
}