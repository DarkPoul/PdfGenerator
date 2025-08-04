package esvar.ua;

import com.itextpdf.io.font.FontProgram;
import com.itextpdf.io.font.FontProgramFactory;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;
import esvar.ua.tableGenerate.FirstModule;
import esvar.ua.tableGenerate.SecondModule;
import esvar.ua.tableGenerate.Zalik;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class ZalikPdfGenerator {
    public Path generatePdf(DataModelForZalik zalik, Path pdfPath, String typeControlHeader, TableType tableType) {
        try {
            Files.createDirectories(pdfPath.getParent());
            PdfWriter writer = new PdfWriter(pdfPath.toFile());
            PdfDocument pdfDoc = new PdfDocument(writer);

            PdfFont font;
            try (InputStream fontStream = ZalikPdfGenerator.class.getResourceAsStream("/fonts/times.ttf")) {
                if (fontStream != null) {
                    FontProgram fp = FontProgramFactory.createFont(fontStream.readAllBytes());
                    font = PdfFontFactory.createFont(fp, PdfEncodings.IDENTITY_H);
                } else {
                    font = PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN);
                }
            } catch (IOException e) {
                font = PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN);
            }

            Document doc = new Document(pdfDoc);

            PdfHeaderUtils.addZalikHeader(doc, font, zalik, typeControlHeader);
            switch (tableType) {
                case SECOND_MODULE -> SecondModule.generate(doc, font, zalik.students(), zalik);
                case FIRST_MODULE -> FirstModule.generate(doc, font, zalik.students(), zalik);
                case ZALIK -> Zalik.generate(doc, font, zalik.students(), zalik);
            }

            doc.setFont(font);
            doc.close();
//            Desktop.getDesktop().open(pdfPath.toFile());
            return pdfPath;
        } catch (IOException e) {
            throw new RuntimeException("Failed to generate PDF", e);
        }
    }


}
