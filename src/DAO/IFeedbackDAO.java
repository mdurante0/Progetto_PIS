package DAO;

import Model.Feedback;

import java.util.ArrayList;

public interface IFeedbackDAO {
    Feedback findById(int id);
    ArrayList<Feedback> findAll();
    int add(Feedback feedback);
    int removeById(int id);
    int update(Feedback feedback);
}