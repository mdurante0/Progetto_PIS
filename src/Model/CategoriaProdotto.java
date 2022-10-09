package Model;

import Business.AbstractFactory.ICategoria;
import Model.composite.IProdotto;

import java.util.ArrayList;
import java.util.List;

public class CategoriaProdotto implements ICategoria {


    private int idCategoriaProdotto;
    private String nome;

    private int idCategoriaProdottoParent;
    private List<CategoriaProdotto> sottocategorie;
    private List<IProdotto> prodotti;

    public CategoriaProdotto() {
        this.sottocategorie = new ArrayList<>();
        this.prodotti = new ArrayList<>();
    }

    public CategoriaProdotto(String nome, ArrayList<CategoriaProdotto> sottocategorie, ArrayList<IProdotto> prodotti) {
        this.nome = nome;
        this.sottocategorie = sottocategorie;
        this.prodotti = prodotti;
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

    public boolean add(CategoriaProdotto categoriaProdotto) {
        return sottocategorie.add(categoriaProdotto);
    }

    public boolean remove(CategoriaProdotto categoriaProdotto) {
        return sottocategorie.remove(categoriaProdotto);
    }

    public void clearSottocategorie() {
        sottocategorie.clear();
    }

    public boolean add(IProdotto iProdotto) {
        return prodotti.add(iProdotto);
    }

    public boolean remove(IProdotto prodotto){
        return prodotti.remove(prodotto);
    }

    public void clearProdotti(){
        prodotti.clear();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdCategoriaProdottoParent() {
        return idCategoriaProdottoParent;
    }

    public void setIdCategoriaProdottoParent(int idCategoriaProdottoParent) {
        this.idCategoriaProdottoParent = idCategoriaProdottoParent;
    }

    @Override
    public String toString() {
        return "CategoriaProdotto{" +
                "nome='" + nome + '\'' +
                ", sottocategorie=" + sottocategorie +
                '}';
    }
}
