package Model;

import javax.swing.*;
import java.io.File;

public class Immagine {
    private int idImmagine;
    private ImageIcon pic = new ImageIcon();
    private File file;
    private int idArticolo;

    public Immagine(ImageIcon pic, int idArticolo) {
        this.pic = pic;
        this.idArticolo = idArticolo;
    }

    public Immagine() {}

    public ImageIcon getPic() {
        return pic;
    }

    public void setPic(ImageIcon pic) {
        this.pic = pic;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
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


}
