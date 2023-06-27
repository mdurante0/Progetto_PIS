package Business.Bridge.Pdf;

import Business.Bridge.Mail.MailHelper;
import Business.Bridge.Mail.MailHelperAPI;
import Model.Prenotazione;
import Model.composite.IProdotto;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class DocumentoPrenotazione extends Documento {

    private ArrayList<Prenotazione> prenotazioni;

    public DocumentoPrenotazione(ArrayList<Prenotazione> prenotazioni, PdfAPI pdfAPI) {
        super(pdfAPI);
        this.prenotazioni = prenotazioni;
    }

    @Override
    public int invia(String indirizzo) {

        StringBuilder text = new StringBuilder();
        text.append("MYSHOP \n \n");
        SimpleDateFormat format = new SimpleDateFormat("'Prenotazione del ' yyyy-MM-dd ' alle ' HH:mm:ss");

        for (Prenotazione prenotazione : prenotazioni) {
            text.append(format.format(prenotazione.getDataPrenotazione())).append("\n");

            for (IProdotto prodotto : prenotazione.getProdotti()) {

                text.append(prodotto.getName()).append(" ");
                text.append(String.format(Locale.ITALIAN, "%.2f", prodotto.getPrezzo())).append(" € ");
                text.append("Quantita: ").append(prodotto.getQuantita());
                text.append("\n");
                text.append("\n");
            }
        }
        try {
            File tempFile = File.createTempFile("MyShop: Prenotazioni", ".pdf");
            System.out.println(tempFile);

            String path = tempFile.getAbsolutePath();
            pdfAPI.creaPdf(text.toString(), path);

            //invio della mail
            return MailHelper.getInstance(new MailHelperAPI(), indirizzo, "Ecco le tue prenotazioni", "Utilizza il file in allegato per il resoconto delle tue prenotazioni!").send(path);

        } catch (IOException e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }
}
