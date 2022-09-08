package Model;

import Business.AbstractFactory.ICategoria;

public class CategoriaServizio implements ICategoria {
    private int idCategoriaServizio;
    private String nome;

    public CategoriaServizio(){}
    public CategoriaServizio(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdCategoriaServizio() {
        return idCategoriaServizio;
    }
    public String toString(){return null;}
}
