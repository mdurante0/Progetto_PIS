package Model;

import Business.AbstractFactory.ICategoria;

import java.util.ArrayList;
import java.util.List;

public class CategoriaProdotto implements ICategoria {


    private int idCategoriaProdotto;
    private String nome;
    private List<CategoriaProdotto> sottocategorie;

    public CategoriaProdotto() {
        this.sottocategorie = new ArrayList<>();
    }

    public CategoriaProdotto(String nome, List sottocategorie) {
        this.nome = nome;
        this.sottocategorie = sottocategorie;
    }

    public int getIdCategoriaProdotto() {
        return idCategoriaProdotto;
    }

    public void setIdCategoriaProdotto(int idCategoriaProdotto) {
        this.idCategoriaProdotto = idCategoriaProdotto;
    }

    public void setSottocategorie(List<CategoriaProdotto> sottocategorie) {
        this.sottocategorie = sottocategorie;
    }

    public List<CategoriaProdotto> getSottocategorie() {
        return sottocategorie;
    }

    public void aggiungiSottocategoria(CategoriaProdotto c) {
        sottocategorie.add(c);
    }

    public void aggiungiSottocategoria(List<CategoriaProdotto> c) {
        sottocategorie.addAll(c);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "CategoriaProdotto{" +
                "nome='" + nome + '\'' +
                ", sottocategorie=" + sottocategorie +
                '}';
    }
}
