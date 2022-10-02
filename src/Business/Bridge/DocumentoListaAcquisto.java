package Business.Bridge;

import Model.Articolo;
import Model.ListaAcquisto;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class DocumentoListaAcquisto extends Documento {

    private ListaAcquisto lista;

    public DocumentoListaAcquisto(ListaAcquisto lista, PdfAPI pdfAPI) {
        super(pdfAPI);
        this.lista = lista;
    }

    @Override
    public void invia(String indirizzo) {

        List<Articolo> articoli = lista.getArticoli();
        String text = "";

        Iterator<Articolo> i = articoli.iterator();
        while(i.hasNext()) {
            Articolo a = i.next();
            text += a.getName()+", ";
        }

        try {
            File tempFile = File.createTempFile("myshop", ".pdf");
            System.out.println(tempFile);
            pdfAPI.creaPdf(text, tempFile.getAbsolutePath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
