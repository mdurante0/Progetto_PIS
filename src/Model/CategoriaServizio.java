package Model;

import Business.AbstractFactory.ICategoria;
import java.util.ArrayList;
import java.util.List;

public class CategoriaServizio implements ICategoria {
    private int idCategoriaServizio;
    private String nome;
    private List<CategoriaServizio> sottocategorie;

    public CategoriaServizio(){
        this.sottocategorie = new ArrayList<>();
    }
    public CategoriaServizio(String nome, List sottocategorie) {
        this.nome = nome;
        this.sottocategorie = sottocategorie;
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

    @Override
    public String toString() {
        return "CategoriaServizio{" +
                ", nome='" + nome + '\'' +
                '}';
    }
}
