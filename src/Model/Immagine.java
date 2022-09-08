package Model;

import javax.swing.*;

public class Immagine {
    private int idImmagine;
    private ImageIcon pic;

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
