package esvar.ua;

import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;

import java.io.IOException;

public class PdfFirstMC {
    public static void PdfFirstMC(Document doc, PdfFont font, DataModelForZalik zalik) throws IOException {
        doc.setFont(font);

        doc.add(new Paragraph("ВІДОМІСТЬ ПІДСУМКОВИХ ОЦІНОК ЗА ПЕРШИЙ МОДУЛЬНИЙ КОНТРОЛЬ")
                .setFont(FontLoader.bold())
                .setFontSize(11)
                .setTextAlignment(TextAlignment.CENTER));
    }
}
