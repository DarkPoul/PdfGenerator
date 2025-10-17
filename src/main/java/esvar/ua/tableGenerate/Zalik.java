package esvar.ua.tableGenerate;

import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Div;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.itextpdf.layout.properties.VerticalAlignment;
import esvar.ua.DataModelForZalik;
import esvar.ua.settings.FontLoader;
import esvar.ua.dto.FirstAndSecondModuleDto;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Zalik {

    public static void generate(Document doc, PdfFont font, List<FirstAndSecondModuleDto> students, DataModelForZalik zalik) throws IOException {

        Table headerTable = new Table(UnitValue.createPercentArray(new float[]{5, 35, 20, 10, 10, 10, 5, 5}))
                .useAllAvailableWidth();

        doc.setFont(font);

        headerTable.addCell(new Cell().setBorderBottom(Border.NO_BORDER).setPadding(0)
                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                .add(new Paragraph("№\nз/п"))
                .setFont(FontLoader.regular()).setFontSize(8)
                .setTextAlignment(TextAlignment.CENTER));
        headerTable.addCell(new Cell().setBorderBottom(Border.NO_BORDER).setPadding(0)
                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                .add(new Paragraph("Прізвище та ініціали студента"))
                .setFont(FontLoader.regular()).setFontSize(10)
                .setTextAlignment(TextAlignment.CENTER));
        headerTable.addCell(new Cell().setBorderBottom(Border.NO_BORDER).setPadding(0)
                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                .add(new Paragraph("Номер залікової книжки"))
                .setFont(FontLoader.regular()).setFontSize(10)
                .setTextAlignment(TextAlignment.CENTER));
        headerTable.addCell(new Cell().setBorderBottom(Border.NO_BORDER).setPadding(0)
                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                .add(new Paragraph("Нац."))
                .setFont(FontLoader.regular()).setFontSize(10)
                .setTextAlignment(TextAlignment.CENTER));
        headerTable.addCell(new Cell().setBorderBottom(Border.NO_BORDER).setPadding(0)
                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                .add(new Paragraph("Бали"))
                .setFont(FontLoader.regular()).setFontSize(10)
                .setTextAlignment(TextAlignment.CENTER));
        headerTable.addCell(new Cell().setBorderBottom(Border.NO_BORDER).setPadding(0)
                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                .add(new Paragraph("ECTS"))
                .setFont(FontLoader.regular()).setFontSize(10)
                .setTextAlignment(TextAlignment.CENTER));
        headerTable.addCell(new Cell().setBorderBottom(Border.NO_BORDER).setPadding(0)
                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                .add(new Paragraph("Дата"))
                .setFont(FontLoader.regular()).setFontSize(10)
                .setTextAlignment(TextAlignment.CENTER));
        headerTable.addCell(new Cell().setBorderBottom(Border.NO_BORDER).setPadding(0)
                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                .add(new Paragraph("Підпис"))
                .setFont(FontLoader.regular()).setFontSize(10)
                .setTextAlignment(TextAlignment.CENTER));

        doc.add(headerTable);

        Table mainTable = new Table(UnitValue.createPercentArray(new float[]{5, 35, 20, 10, 10, 10, 5, 5}))
                .useAllAvailableWidth();

        addNumericHeader(mainTable);

        for (int i = 0; i < students.size() - 1; i++) {
            FirstAndSecondModuleDto s = students.get(i);
            addStudentRow(mainTable, s, true);
        }

        doc.add(mainTable);

        FirstAndSecondModuleDto last = students.get(students.size() - 1);
        Table lastRowTable = new Table(UnitValue.createPercentArray(new float[]{5, 35, 20, 10, 10, 10, 5, 5}))
                .useAllAvailableWidth();

        float remainingHeight = doc.getRenderer().getCurrentArea().getBBox().getHeight();
        if (remainingHeight < 60) {
            addNumericHeader(lastRowTable);
        }
        addStudentRow(lastRowTable, last, false);

        Table signTable = new Table(UnitValue.createPercentArray(new float[]{20, 5, 20, 5, 20}))
                .useAllAvailableWidth();
        signTable.addCell(new Cell().add(new Paragraph("Екзаменатор (Викладач)"))
                .setBorder(Border.NO_BORDER)
                .setFont(FontLoader.bold())
                .setFontSize(10)
        );
        signTable.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        signTable.addCell(new Cell().add(new Paragraph(""))
                .setBorderTop(Border.NO_BORDER)
                .setBorderBottom(new SolidBorder(0.5f))
                .setBorderLeft(Border.NO_BORDER)
                .setBorderRight(Border.NO_BORDER)
                .setFont(FontLoader.bold())
                .setFontSize(10)
        );
        signTable.addCell(new Cell().setBorder(Border.NO_BORDER).add(new Paragraph())
                .setFont(FontLoader.regular())
                .setFontSize(10)
        );
        signTable.addCell(new Cell().add(new Paragraph(zalik.firstTeacherName()))
                .setBorderTop(Border.NO_BORDER)
                .setBorderLeft(Border.NO_BORDER)
                .setBorderRight(Border.NO_BORDER)
                .setBorderBottom(new SolidBorder(0.5f))
                .setFont(FontLoader.bold())
                .setFontSize(10)
        );

        signTable.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        signTable.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        signTable.addCell(new Cell().add(new Paragraph("(підпис)").setFontSize(8).setTextAlignment(TextAlignment.CENTER)).setBorder(Border.NO_BORDER));
        signTable.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        signTable.addCell(new Cell().add(new Paragraph("(прізвище,ініціали)").setFontSize(8).setTextAlignment(TextAlignment.CENTER)).setBorder(Border.NO_BORDER));

        Div finalBlock = new Div()
                .add(lastRowTable)
                .add(new Paragraph(""))
                .add(signTable)
                .setKeepTogether(true);

        doc.add(finalBlock);
    }

    private static void addStudentRow(Table table, FirstAndSecondModuleDto s, boolean addBottomBorder) {
        Border studentBorder = addBottomBorder ? new SolidBorder(0.5f) : Border.NO_BORDER;
        int mark100 = parseIntSafe(s.mark());
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        table.addCell(new Cell().add(new Paragraph(String.valueOf(s.index())))
                .setTextAlignment(TextAlignment.CENTER).setFontSize(8)
                .setBorderTop(studentBorder));
        table.addCell(new Cell().add(new Paragraph(s.pib())).setFontSize(10)
                .setBorderTop(studentBorder));
        table.addCell(new Cell().add(new Paragraph(s.studentBookNumber()))
                .setTextAlignment(TextAlignment.CENTER).setFontSize(10)
                .setBorderTop(studentBorder));
        table.addCell(new Cell().add(new Paragraph(convertMarkToNationalGrade(mark100)))
                .setTextAlignment(TextAlignment.CENTER).setFontSize(10)
                .setBorderTop(studentBorder));
        table.addCell(new Cell().add(new Paragraph(String.valueOf(mark100)))
                .setTextAlignment(TextAlignment.CENTER).setFontSize(10)
                .setBorderTop(studentBorder));
        table.addCell(new Cell().add(new Paragraph(convertMarkToECTSGrade(mark100)))
                .setTextAlignment(TextAlignment.CENTER).setFontSize(10)
                .setBorderTop(studentBorder));
        table.addCell(new Cell().add(new Paragraph(LocalDate.now().format(df)))
                .setTextAlignment(TextAlignment.CENTER).setFontSize(10)
                .setBorderTop(studentBorder));
        table.addCell(new Cell().add(new Paragraph(""))
                .setTextAlignment(TextAlignment.CENTER).setFontSize(10)
                .setBorderTop(studentBorder));
    }

    private static void addNumericHeader(Table table) throws IOException {
        for (int i = 1; i <= 8; i++) {
            table.addHeaderCell(new Cell().setPadding(0)
                    .setVerticalAlignment(VerticalAlignment.MIDDLE)
                    .add(new Paragraph(String.valueOf(i)))
                    .setFont(FontLoader.bold())
                    .setFontSize(i == 1 ? 8 : 10)
                    .setTextAlignment(TextAlignment.CENTER));
        }
    }

    private static int parseIntSafe(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private static String convertMarkToNationalGrade(int mark) {
        return mark >= 90 ? "Відмінно" :
                mark >= 74 ? "Добре" :
                        mark >= 60 ? "Задовільно" : "Незадовільно";
    }

    private static String convertMarkToECTSGrade(int mark) {
        return mark >= 90 ? "A" :
                mark >= 82 ? "B" :
                        mark >= 74 ? "C" :
                                mark >= 64 ? "D" :
                                        mark >= 60 ? "E" :
                                                mark >= 35 ? "FX" : "F";
    }
}