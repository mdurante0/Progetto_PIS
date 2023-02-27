package Business.Bridge;

import Model.Articolo;
import Model.ListaAcquisto;
import Model.composite.Prodotto;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class DocumentoListaAcquisto extends Documento {

    private ListaAcquisto lista;

    public DocumentoListaAcquisto(ListaAcquisto lista, PdfAPI pdfAPI) {
        super(pdfAPI);
        this.lista = lista;
    }

    @Override
    public void invia(String indirizzo) {

        List<Articolo> articoli = lista.getArticoli();
        StringBuilder text = new StringBuilder();
        text.append("MYSHOP \n \n");

        Iterator<Articolo> i = articoli.iterator();
        while(i.hasNext()) {
            Articolo a = i.next();
            text.append(a.getName()).append(" ");
            text.append(String.format(Locale.ITALIAN,"%.2f",a.getPrezzo())).append(" € ");

            //per i prodotti inserisco anche la collocazione
            if(a instanceof Prodotto){
                int scaffale = ((Prodotto) a).getCollocazione().getScaffale();
                int corsia = ((Prodotto) a).getCollocazione().getCorsia();
                text.append("Corsia: ").append(corsia).append(" ");
                text.append("Scaffale: ").append(scaffale);
            }
            text.append("\n");
        }

        try {
            File tempFile = File.createTempFile("MyShop: " + lista.getNome(), ".pdf");
            System.out.println(tempFile);

            String path = tempFile.getAbsolutePath();
            pdfAPI.creaPdf(text.toString(), path);

            //invio della mail
            MailHelper.getInstance(new MailHelperAPI(), indirizzo, "Ecco la tua lista d'acquisto!", "Utilizza il file in allegato per il resoconto della tua spesa!").send(path);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
