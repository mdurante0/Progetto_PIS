package Business.Bridge;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;

import java.io.IOException;

public class PdfBoxAPI implements PdfAPI {

    @Override
    public void creaPdf(String text, String outfile) {

        try (PDDocument doc = new PDDocument())
        {
            PDPage page = new PDPage();
            doc.addPage(page);

            PDFont titolo = new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD);
            PDFont testo = new PDType1Font(Standard14Fonts.FontName.HELVETICA);

            try (PDPageContentStream contents = new PDPageContentStream(doc, page))
            {
                contents.beginText();
                contents.setFont(titolo, 22);
                contents.newLineAtOffset(260, 700);

                // Divide la stringa in sottostringhe utilizzando il carattere di nuova riga
                String[] lines = text.split("\\n");

                //inserimento del titolo
                contents.showText(lines[0]);
                contents.newLineAtOffset(-200, -25);

                //inserimento del contenuto
                contents.setFont(testo, 18);
                for (int i = 1; i < lines.length; i++) {
                    // Aggiungi ciascuna sottostringa al contenuto della pagina
                    contents.showText(lines[i]);
                    contents.newLineAtOffset(0, -25);
                }

                contents.endText();
            }

            doc.save(outfile);
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
