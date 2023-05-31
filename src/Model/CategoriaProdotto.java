package Model;

import Business.AbstractFactory.ICategoria;
import Model.composite.IProdotto;

import java.util.ArrayList;
import java.util.List;

public class CategoriaProdotto implements ICategoria {


    private int idCategoriaProdotto;
    private String nome;
    private CategoriaProdotto categoriaProdottoParent;
    private List<CategoriaProdotto> sottocategorie = new ArrayList<>();
    private List<IProdotto> prodotti = new ArrayList<>();

    public CategoriaProdotto() {
    }
    public CategoriaProdotto(String nome) {
        this.nome = nome;

    }
    public CategoriaProdotto(String nome, CategoriaProdotto categoriaProdottoParent) {
        this.categoriaProdottoParent = categoriaProdottoParent;
        this.nome = nome;
    }


    public CategoriaProdotto(String nome, ArrayList<CategoriaProdotto> sottocategorie, ArrayList<IProdotto> prodotti) {
        this.nome = nome;
        this.sottocategorie = sottocategorie;
        this.prodotti = prodotti;
    }

    @Override
    public int getIdCategoria() {
        return idCategoriaProdotto;
    }

    @Override
    public void setIdCategoria(int idCategoriaProdotto) {
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

    public CategoriaProdotto getCategoriaProdottoParent() {
        return categoriaProdottoParent;
    }

    public void setCategoriaProdottoParent(CategoriaProdotto categoriaProdottoParent) {
        this.categoriaProdottoParent = categoriaProdottoParent;
    }

    @Override
    public String toString() {
        return "CategoriaProdotto{" +
                "nome='" + nome + '\'' +
                ", sottocategorie=" + sottocategorie +
                '}';
    }
}
