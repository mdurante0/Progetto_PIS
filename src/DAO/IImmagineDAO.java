package DAO;

import Model.Immagine;

import java.io.File;
import java.util.ArrayList;

public interface IImmagineDAO {
    Immagine findById(int id);
    ArrayList<Immagine> findAll();
    int add(File file, int idArticolo);
    int removeById(int id);
    ArrayList<Immagine> findByArticolo(int idArticolo);
   int removeByArticolo(int idArticolo);
}