package Model.composite;

import Model.Produttore;

public interface IProdotto {
    public Float getPrezzo();
    String getName();
    int getIdArticolo();
    int getQuantita();
    Produttore getProduttore();
    void setQuantita(int quantita);
}
