package Model.composite;

import Business.AbstractFactory.ICategoria;
import Model.*;

public interface IProdotto {
    void setIdArticolo(int idArticolo);
    Float getPrezzo();
    String getName();
    int getIdArticolo();
    int getQuantita();
    ICategoria getCategoria();
    void setCategoria(CategoriaProdotto categoriaProdotto);
    void setProduttore(Produttore produttore);
    Produttore getProduttore();
    void setQuantita(int quantita);
    String getDescrizione();
    Collocazione getCollocazione();
    void setCollocazione(Collocazione c);
    Magazzino getMagazzino();
    void setMagazzino(Magazzino magazzino);
    void setDescrizione(String descrizione);


}
