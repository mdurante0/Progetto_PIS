package Model;

import Business.AbstractFactory.ICategoria;

public class CategoriaServizio implements ICategoria {
    private int idCategoriaServizio;
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdCategoriaServizio() {
        return idCategoriaServizio;
    }
}
