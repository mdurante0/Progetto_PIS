package Model;

import javax.swing.*;

public class Immagine {
    private int idImmagine;
    private ImageIcon pic;
    private int idArticolo;

    public ImageIcon getPic() {
        return pic;
    }

    public void setPic(ImageIcon pic) {
        this.pic = pic;
    }

    public int getIdArticolo() {
        return idArticolo;
    }

    public void setIdArticolo(int idArticolo) {
        this.idArticolo = idArticolo;
    }

    public int getIdImmagine() {
        return idImmagine;
    }

    public void setIdImmagine(int idImmagine) {
        this.idImmagine = idImmagine;
    }

    public Immagine(ImageIcon pic) {
        this.pic = pic;
    }

    public Immagine() {
    }
}
