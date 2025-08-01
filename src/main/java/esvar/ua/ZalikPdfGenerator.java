package esvar.ua;

import com.itextpdf.io.font.FontProgram;
import com.itextpdf.io.font.FontProgramFactory;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.draw.SolidLine;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import esvar.ua.tableGenerate.FirstModule;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ZalikPdfGenerator {
    public Path generatePdf(DataModelForZalik zalik) {
        try {
            String timestamp = java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            Path dir = Path.of("generated");
            Files.createDirectories(dir); // створити, якщо нема
            Path pdfPath = dir.resolve("zalik_" + timestamp + ".pdf");
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

            PdfHeaderUtils.addZalikHeader(doc, font, zalik, "ВІДОМІСТЬ ПІДСУМКОВИХ ОЦІНОК ЗА ПЕРШИЙ МОДУЛЬНИЙ КОНТРОЛЬ");
            FirstModule.generate(doc, font, zalik.students());

//            PdfHeaderUtils.addDoubleLine(doc, font, zalik, "з ", "(назва дисципліни)");

            doc.setFont(font);



//            float[] columnWidths = {20, 160, 80, 60, 40, 40, 60, 60};
//            Table table = new Table(UnitValue.createPercentArray(columnWidths))
//                    .useAllAvailableWidth();
//            addHeaderCell(table, "№");
//            addHeaderCell(table, "ПІБ");
//            addHeaderCell(table, "Номер залікової");
//            addHeaderCell(table, "Нац.");
//            addHeaderCell(table, "Бали");
//            addHeaderCell(table, "ECTS");
//            addHeaderCell(table, "Дата");
//            addHeaderCell(table, "Підпис");
//
//            DateTimeFormatter df = DateTimeFormatter.ofPattern("dd.MM.yyyy");
//            for (StudentModelToDocumentGenerate s : zalik.students()) {
//                int mark100 = parseIntSafe(s.mark());
//                table.addCell(new Cell().add(new Paragraph(String.valueOf(s.index())))
//                        .setTextAlignment(TextAlignment.CENTER));
//                table.addCell(new Cell().add(new Paragraph(s.name())));
//                table.addCell(new Cell().add(new Paragraph(s.studentNumber()))
//                        .setTextAlignment(TextAlignment.CENTER));
//                table.addCell(new Cell().add(new Paragraph(convertMarkToNationalGrade(mark100)))
//                        .setTextAlignment(TextAlignment.CENTER));
//                table.addCell(new Cell().add(new Paragraph(String.valueOf(mark100)))
//                        .setTextAlignment(TextAlignment.CENTER));
//                table.addCell(new Cell().add(new Paragraph(convertMarkToECTSGrade(mark100)))
//                        .setTextAlignment(TextAlignment.CENTER));
//                table.addCell(new Cell().add(new Paragraph(LocalDate.now().format(df)))
//                        .setTextAlignment(TextAlignment.CENTER));
//                table.addCell(new Cell().add(new Paragraph(""))
//                        .setTextAlignment(TextAlignment.CENTER));
//            }
//
//            doc.add(table);
            doc.close();
            Desktop.getDesktop().open(pdfPath.toFile());
            return pdfPath;
        } catch (IOException e) {
            throw new RuntimeException("Failed to generate PDF", e);
        }
    }

    private void addHeaderCell(Table table, String text) {
        table.addHeaderCell(new Cell().add(new Paragraph(text).setFontSize(11))
                .setTextAlignment(TextAlignment.CENTER));
    }

    private int parseIntSafe(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private String convertMarkToNationalGrade(int mark) {
        return mark >= 90 ? "Відмінно" :
                mark >= 74 ? "Добре" :
                        mark >= 60 ? "Задовільно" : "Незадовільно";
    }

    private String convertMarkToECTSGrade(int mark) {
        return mark >= 90 ? "A" :
                mark >= 82 ? "B" :
                        mark >= 74 ? "C" :
                                mark >= 64 ? "D" :
                                        mark >= 60 ? "E" :
                                                mark >= 35 ? "FX" : "F";
    }
}
