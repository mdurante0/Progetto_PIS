package Model;

import Business.AbstractFactory.ICategoria;
import java.util.ArrayList;
import java.util.List;

public class CategoriaServizio implements ICategoria {
    private int idCategoriaServizio;
    private String nome;
    private List<Servizio> servizi = new ArrayList<>();

    public CategoriaServizio(){
        this.servizi = new ArrayList<>();
    }
    public CategoriaServizio(String nome, ArrayList<Servizio> servizi) {
        this.nome = nome;
        this.servizi = servizi;
    }
    public CategoriaServizio(String nome) {
        this.nome = nome;

    }

    @Override
    public int getIdCategoria() {
        return idCategoriaServizio;
    }

    @Override
    public void setIdCategoria(int idCategoriaServizio) {
        this.idCategoriaServizio = idCategoriaServizio;
    }

    public void clear() {
        servizi.clear();
    }

    public void add(Servizio servizio) {
        servizi.add(servizio);
    }

    public boolean remove(Servizio servizio) {
        return servizi.remove(servizio);
    }

    public List<Servizio> getServizi() {
        return servizi;
    }

    public void setServizi(List<Servizio> servizi) {
        this.servizi = servizi;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }



    @Override
    public String toString() {
        return "CategoriaServizio{" +
                ", nome='" + nome + '\'' +
                '}';
    }
}
