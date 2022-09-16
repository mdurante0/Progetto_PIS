package DAO;

import Model.Immagine;

import java.util.ArrayList;

public interface IImmagineDAO {
    Immagine findById(int id);
    ArrayList<Immagine> findAll();
    int add(Immagine immagine);
    int removeById(int id);
    int update(Immagine immagine);
}