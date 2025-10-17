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
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import esvar.ua.dto.GroupListModel;
import esvar.ua.dto.GroupStudentDto;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class GroupListPdfGenerator {

    public Path generatePdf(GroupListModel groupList, Path pdfPath) {
        try {
            Files.createDirectories(pdfPath.getParent());
            PdfWriter writer = new PdfWriter(pdfPath.toFile());
            PdfDocument pdfDoc = new PdfDocument(writer);

            PdfFont regularFont = loadRegularFont();
            PdfFont boldFont = loadBoldFont(regularFont);

            Document doc = new Document(pdfDoc);
            doc.setFont(regularFont);

            String headerText = buildHeader(groupList);
            doc.add(new Paragraph(headerText)
                    .setFont(boldFont)
                    .setFontSize(14)
                    .setTextAlignment(TextAlignment.CENTER));
            doc.add(new Paragraph(" "));

            Table table = new Table(UnitValue.createPercentArray(new float[]{10, 45, 45}))
                    .useAllAvailableWidth();

            table.addHeaderCell(createHeaderCell("№ з/п", boldFont));
            table.addHeaderCell(createHeaderCell("ПІБ студента", boldFont));
            table.addHeaderCell(createHeaderCell("Номер залікової книжки", boldFont));

            for (GroupStudentDto student : groupList.students()) {
                table.addCell(createBodyCell(String.valueOf(student.index()), TextAlignment.CENTER));
                table.addCell(createBodyCell(student.pib(), TextAlignment.LEFT));
                String book = student.studentBookNumber() == null ? "" : student.studentBookNumber();
                table.addCell(createBodyCell(book, TextAlignment.CENTER));
            }

            doc.add(table);
            doc.close();
            return pdfPath;
        } catch (IOException e) {
            throw new RuntimeException("Failed to generate group list PDF", e);
        }
    }

    private static String buildHeader(GroupListModel groupList) {
        String title = groupList.title() == null || groupList.title().isBlank()
                ? "Список групи"
                : groupList.title();
        String groupName = groupList.groupName() == null ? "" : groupList.groupName();
        if (groupName.isBlank()) {
            return title.trim();
        }
        return (title + " " + groupName).trim();
    }

    private static Cell createHeaderCell(String text, PdfFont font) {
        return new Cell()
                .add(new Paragraph(text)
                        .setFont(font)
                        .setFontSize(10)
                        .setTextAlignment(TextAlignment.CENTER))
                .setTextAlignment(TextAlignment.CENTER);
    }

    private static Cell createBodyCell(String text, TextAlignment alignment) {
        return new Cell()
                .add(new Paragraph(text == null ? "" : text)
                        .setFontSize(10)
                        .setTextAlignment(alignment))
                .setTextAlignment(alignment);
    }

    private static PdfFont loadRegularFont() throws IOException {
        try (InputStream fontStream = GroupListPdfGenerator.class.getResourceAsStream("/fonts/times.ttf")) {
            if (fontStream != null) {
                FontProgram fontProgram = FontProgramFactory.createFont(fontStream.readAllBytes());
                return PdfFontFactory.createFont(fontProgram, PdfEncodings.IDENTITY_H);
            }
        }
        return PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN);
    }

    private static PdfFont loadBoldFont(PdfFont fallback) throws IOException {
        try (InputStream fontStream = GroupListPdfGenerator.class.getResourceAsStream("/fonts/timesbd.ttf")) {
            if (fontStream != null) {
                FontProgram fontProgram = FontProgramFactory.createFont(fontStream.readAllBytes());
                return PdfFontFactory.createFont(fontProgram, PdfEncodings.IDENTITY_H);
            }
        }
        return fallback;
    }
}
