package Model.composite;

import Business.AbstractFactory.ICategoria;
import Model.Produttore;

public interface IProdotto {
    void setIdArticolo(int idArticolo);
    Float getPrezzo();
    String getName();
    int getIdArticolo();
    int getQuantita();
    ICategoria getCategoria();
    Produttore getProduttore();
    void setQuantita(int quantita);
}
