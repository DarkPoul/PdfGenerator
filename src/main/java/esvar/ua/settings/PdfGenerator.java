package esvar.ua.settings;

import com.itextpdf.io.font.FontProgram;
import com.itextpdf.io.font.FontProgramFactory;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import esvar.ua.DataModelForZalik;
import esvar.ua.generate.header.GenerateHeader;
import esvar.ua.tableGenerate.FirstAndSecondModule;
import esvar.ua.tableGenerate.SecondModule;
import esvar.ua.tableGenerate.Zalik;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class PdfGenerator {
    public Path generatePdf(DataModelForZalik zalik, Path pdfPath, String typeControlHeader, TableType tableType) {
        try {
            Files.createDirectories(pdfPath.getParent());
            PdfWriter writer = new PdfWriter(pdfPath.toFile());
            PdfDocument pdfDoc = new PdfDocument(writer);

            PdfFont font;
            try (InputStream fontStream = PdfGenerator.class.getResourceAsStream("/fonts/times.ttf")) {
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



            GenerateHeader.generateHeader(doc, font, zalik, typeControlHeader);



            switch (tableType) {
                case FIRST_MODULE -> FirstAndSecondModule.generate(doc, font, zalik.students(), zalik.firstTeacherName(), true);
                case SECOND_MODULE -> FirstAndSecondModule.generate(doc, font, zalik.students(), zalik.firstTeacherName(), false);
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
