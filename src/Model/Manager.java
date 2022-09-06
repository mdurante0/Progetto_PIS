package Model;

public class Manager extends Utente {

    private int durataIncarico;
    private Float salario;
    public Manager(String name, String surname, String username, String pwd, String email, String tipo, Float salario) {
        super(name, surname, username, pwd, email, tipo);
        this.salario = salario;
    }

    public Float getSalario() {
        return salario;
    }

    public void setSalario(Float salario) {
        this.salario = salario;
    }

    public int getDurataIncarico() {
        return durataIncarico;
    }

    public void setDurataIncarico(int durataIncarico) {
        this.durataIncarico = durataIncarico;
    }
}
