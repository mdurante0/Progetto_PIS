package Model.composite;

import Business.AbstractFactory.ICategoria;
import Model.CategoriaProdotto;
import Model.Collocazione;
import Model.Produttore;

public interface IProdotto {
    void setIdArticolo(int idArticolo);
    Float getPrezzo();
    String getName();
    int getIdArticolo();
    int getQuantita();
    ICategoria getCategoria();
    void setCategoria(CategoriaProdotto categoriaProdotto);
    Produttore getProduttore();
    void setQuantita(int quantita);
    String getDescrizione();
    Collocazione getCollocazione();
    void setCollocazione(Collocazione c);
}
