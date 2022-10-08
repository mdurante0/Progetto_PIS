package Model;

import Business.AbstractFactory.ICategoria;
import java.util.ArrayList;
import java.util.List;

public class CategoriaServizio implements ICategoria {
    private int idCategoriaServizio;
    private String nome;
    private List<Servizio> servizi;

    public CategoriaServizio(){
        this.servizi = new ArrayList<>();
    }
    public CategoriaServizio(String nome, ArrayList<Servizio> servizi) {
        this.nome = nome;
        this.servizi = servizi;
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

    public void setIdCategoriaServizio(int idCategoriaServizio) {
        this.idCategoriaServizio = idCategoriaServizio;
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
