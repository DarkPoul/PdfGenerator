package esvar.ua;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;

import java.io.IOException;

public class FontLoader {
    public static PdfFont regular() throws IOException {
        return PdfFontFactory.createFont("src/main/resources/fonts/times.ttf", PdfEncodings.IDENTITY_H);
    }

    public static PdfFont bold() throws IOException {
        return PdfFontFactory.createFont("src/main/resources/fonts/timesbd.ttf", PdfEncodings.IDENTITY_H);
    }

    public static PdfFont italic() throws IOException {
        return PdfFontFactory.createFont("src/main/resources/fonts/timesi.ttf", PdfEncodings.IDENTITY_H);
    }

    public static PdfFont boldItalic() throws IOException {
        return PdfFontFactory.createFont("src/main/resources/fonts/timesbi.ttf", PdfEncodings.IDENTITY_H);
    }
}
