package esvar.ua;

import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.itextpdf.layout.properties.VerticalAlignment;
import esvar.ua.dto.FirstModuleSummaryReportData;
import esvar.ua.dto.SummaryDisciplineDto;
import esvar.ua.dto.SummaryStudentDto;
import esvar.ua.settings.FontLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Generates the summary report for the first module control.
 */
public class FirstModuleSummaryReportPdfGenerator {

    private static final double CM_TO_PT = 28.3464567;

    public Path generate(FirstModuleSummaryReportData data, Path pdfPath) {
        try {
            Files.createDirectories(pdfPath.getParent());

            try (PdfWriter writer = new PdfWriter(pdfPath.toFile());
                 PdfDocument pdfDoc = new PdfDocument(writer);
                 Document doc = new Document(pdfDoc, PageSize.A4.rotate())) {

                doc.setMargins((float) (3 * CM_TO_PT), (float) (2 * CM_TO_PT),
                        (float) (1 * CM_TO_PT), (float) (2 * CM_TO_PT));

                PdfFont regular = FontLoader.regular();
                PdfFont bold = FontLoader.bold();

                String title = "Зведений звіт результатів оцінювання студентів групи "
                        + data.groupName() + " за перший модульний контроль.";

                doc.add(new Paragraph(title)
                        .setFont(bold)
                        .setFontSize(12)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setMarginBottom(15));

                Table table = createSummaryTable(data, regular, bold);
                doc.add(table);
            }

            return pdfPath;
        } catch (IOException e) {
            throw new RuntimeException("Failed to generate summary PDF", e);
        }
    }

    private Table createSummaryTable(FirstModuleSummaryReportData data, PdfFont regular, PdfFont bold) {
        int disciplineCount = data.disciplines().size();
        float[] columnWidths = createColumnWidths(disciplineCount);

        Table table = new Table(UnitValue.createPercentArray(columnWidths)).useAllAvailableWidth();

        Cell topHeader = new Cell(1, columnWidths.length)
                .add(new Paragraph("Дисципліна (макс. кількість балів)")
                        .setFont(bold)
                        .setFontSize(11)
                        .setTextAlignment(TextAlignment.CENTER));
        topHeader.setPadding(5);
        table.addHeaderCell(topHeader);

        table.addHeaderCell(headerCell("ПІБ", bold));

        for (SummaryDisciplineDto discipline : data.disciplines()) {
            Cell disciplineCell = headerCell(discipline.formattedTitle(), bold);
            disciplineCell.setRotationAngle(Math.PI / 2);
            disciplineCell.setMinHeight(80);
            table.addHeaderCell(disciplineCell);
        }

        table.addHeaderCell(headerCell("Кількість нулів", bold));

        int[] zeroPerDiscipline = new int[disciplineCount];
        int totalZeroCount = 0;

        for (SummaryStudentDto student : data.students()) {
            table.addCell(new Cell()
                    .add(new Paragraph(student.pib())
                            .setFont(regular)
                            .setFontSize(10)
                            .setTextAlignment(TextAlignment.LEFT))
                    .setPadding(5));

            List<Integer> marks = student.marks();
            if (marks.size() != disciplineCount) {
                throw new IllegalArgumentException("Student " + student.pib()
                        + " has " + marks.size() + " marks while " + disciplineCount
                        + " disciplines were provided.");
            }

            int rowZeroCount = 0;

            for (int i = 0; i < disciplineCount; i++) {
                Integer mark = marks.get(i);
                String markValue = mark == null ? "" : String.valueOf(mark);

                table.addCell(bodyCell(markValue, regular));

                if (mark != null && mark == 0) {
                    zeroPerDiscipline[i]++;
                    rowZeroCount++;
                }
            }

            totalZeroCount += rowZeroCount;
            table.addCell(bodyCell(String.valueOf(rowZeroCount), regular));
        }

        Cell zeroHeaderCell = new Cell()
                .add(new Paragraph("Кількість нулів")
                        .setFont(bold)
                        .setFontSize(10)
                        .setTextAlignment(TextAlignment.CENTER))
                .setPadding(5)
                .setVerticalAlignment(VerticalAlignment.MIDDLE);
        table.addCell(zeroHeaderCell);

        for (int zeroCount : zeroPerDiscipline) {
            table.addCell(new Cell()
                    .add(new Paragraph(String.valueOf(zeroCount))
                            .setFont(bold)
                            .setFontSize(10)
                            .setTextAlignment(TextAlignment.CENTER))
                    .setVerticalAlignment(VerticalAlignment.MIDDLE)
                    .setPadding(5));
        }

        table.addCell(new Cell()
                .add(new Paragraph(String.valueOf(totalZeroCount))
                        .setFont(bold)
                        .setFontSize(10)
                        .setTextAlignment(TextAlignment.CENTER))
                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                .setPadding(5));

        return table;
    }

    private Cell headerCell(String text, PdfFont bold) {
        return new Cell()
                .add(new Paragraph(text)
                        .setFont(bold)
                        .setFontSize(10)
                        .setTextAlignment(TextAlignment.CENTER))
                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                .setPadding(5);
    }

    private Cell bodyCell(String text, PdfFont font) {
        return new Cell()
                .add(new Paragraph(text)
                        .setFont(font)
                        .setFontSize(10)
                        .setTextAlignment(TextAlignment.CENTER))
                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                .setPadding(5);
    }

    private float[] createColumnWidths(int disciplineCount) {
        float[] widths = new float[disciplineCount + 2];
        widths[0] = 35f;
        for (int i = 1; i < widths.length - 1; i++) {
            widths[i] = 15f;
        }
        widths[widths.length - 1] = 12f;
        return widths;
    }
}
