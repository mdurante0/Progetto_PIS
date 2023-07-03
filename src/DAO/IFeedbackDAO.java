package DAO;

import Model.Feedback;

import java.util.ArrayList;

public interface IFeedbackDAO {
    Feedback findById(int id);
    ArrayList<Feedback> findAll();
    int add(Feedback feedback);
    ArrayList<Feedback> findAllByUser(int idUtenteAcquirente);
    ArrayList<Feedback> findAllByArticolo(int idArticolo);
    int removeByUser(int idUtenteAcquirente);
    int removeByArticolo(int idArticolo);
    int removeById(int id);
    int setRisposta(int idFeedback, String risposta);
    int update(Feedback feedback);
}