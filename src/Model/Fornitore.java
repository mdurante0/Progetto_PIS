package Model;

public class Fornitore {

    private int idFornitore;
    private String nome;
    private String mail;
    private String citta;
    private String nazione;
    private String telefono;
    private String descrizione;
    private String sito;

    public Fornitore(){}

    public Fornitore(String nome, String mail, String citta, String nazione, String telefono, String descrizione, String sito) {
        this.nome = nome;
        this.mail = mail;
        this.citta = citta;
        this.nazione = nazione;
        this.telefono = telefono;
        this.descrizione = descrizione;
        this.sito = sito;
    }

    public Fornitore(String nome, String mail, String citta, String nazione, String telefono, String descrizione) {
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
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

    public String getSito() {
        return sito;
    }

    public void setSito(String sito) {
        this.sito = sito;
    }

    @Override
    public String toString() {
        return "Fornitore{" +
                "nome='" + nome + '\'' +
                ", mail='" + mail + '\'' +
                ", citta='" + citta + '\'' +
                ", nazione='" + nazione + '\'' +
                ", telefono=" + telefono +
                ", descrizione='" + descrizione + '\'' +
                '}';
    }
}
