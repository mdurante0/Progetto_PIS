package Model;

public class Fornitore {

    private int idFornitore;
    private String nome;
    private String mail;
    private String citta;
    private String nazione;
    private int telefono;
    private String descrizione;

    public  Fornitore(){}
    public Fornitore(String nome, String mail, String citta, String nazione, int telefono, String descrizione) {
        this.nome = nome;
        this.mail = mail;
        this.citta = citta;
        this.nazione = nazione;
        this.telefono = telefono;
        this.descrizione = descrizione;
    }

    public int getIdFornitore() {
        return idFornitore;
    }

    public void setIdFornitore(int idProduttore) {
        this.idFornitore = idProduttore;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getNazione() {
        return nazione;
    }

    public void setNazione(String nazione) {
        this.nazione = nazione;
    }
    public String toString(){return null;}
}
