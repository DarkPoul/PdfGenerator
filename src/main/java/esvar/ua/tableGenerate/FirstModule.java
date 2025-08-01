package esvar.ua.tableGenerate;

import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Div;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.itextpdf.layout.properties.VerticalAlignment;
import esvar.ua.FontLoader;
import esvar.ua.dto.FirstModuleDto;

import java.io.IOException;
import java.util.List;

public class FirstModule {

    public static void generate(Document doc, PdfFont font, List<FirstModuleDto> modules) throws IOException {

        Table headerTable = new Table(UnitValue.createPercentArray(new float[]{5, 35, 20, 20, 20}))
                .useAllAvailableWidth();

        doc.setFont(font);

        headerTable.addCell(new Cell().setPadding(0).setVerticalAlignment(VerticalAlignment.MIDDLE).add(new Paragraph("№\nз/п")).setFont(FontLoader.regular()).setFontSize(8).setTextAlignment(TextAlignment.CENTER));
        headerTable.addCell(new Cell().setPadding(0).setVerticalAlignment(VerticalAlignment.MIDDLE).add(new Paragraph("Прізвище та ініціали студента")).setFont(FontLoader.regular()).setFontSize(10).setTextAlignment(TextAlignment.CENTER));
        headerTable.addCell(new Cell().setPadding(0).setVerticalAlignment(VerticalAlignment.MIDDLE).add(new Paragraph("Номер залікової книжки")).setFont(FontLoader.regular()).setFontSize(10).setTextAlignment(TextAlignment.CENTER));
        headerTable.addCell(new Cell().setPadding(0).setVerticalAlignment(VerticalAlignment.MIDDLE).add(new Paragraph("Кількість балів за результатами першого модуля (від 0 до 30 балів)")).setFont(FontLoader.regular()).setFontSize(10).setTextAlignment(TextAlignment.CENTER));
        headerTable.addCell(new Cell().setPadding(0).setVerticalAlignment(VerticalAlignment.MIDDLE).add(new Paragraph("Підпис викладача")).setFont(FontLoader.regular()).setFontSize(10).setTextAlignment(TextAlignment.CENTER));
        headerTable.addCell(new Cell().setPadding(0).setVerticalAlignment(VerticalAlignment.MIDDLE).add(new Paragraph("1")).setFont(FontLoader.bold()).setFontSize(8).setTextAlignment(TextAlignment.CENTER));
        headerTable.addCell(new Cell().setPadding(0).setVerticalAlignment(VerticalAlignment.MIDDLE).add(new Paragraph("2")).setFont(FontLoader.bold()).setFontSize(10).setTextAlignment(TextAlignment.CENTER));
        headerTable.addCell(new Cell().setPadding(0).setVerticalAlignment(VerticalAlignment.MIDDLE).add(new Paragraph("3")).setFont(FontLoader.bold()).setFontSize(10).setTextAlignment(TextAlignment.CENTER));
        headerTable.addCell(new Cell().setPadding(0).setVerticalAlignment(VerticalAlignment.MIDDLE).add(new Paragraph("4")).setFont(FontLoader.bold()).setFontSize(10).setTextAlignment(TextAlignment.CENTER));
        headerTable.addCell(new Cell().setPadding(0).setVerticalAlignment(VerticalAlignment.MIDDLE).add(new Paragraph("5")).setFont(FontLoader.bold()).setFontSize(10).setTextAlignment(TextAlignment.CENTER));

        doc.add(headerTable);

        Table mainTable = new Table(UnitValue.createPercentArray(new float[]{5, 35, 20, 20, 20}))
                .useAllAvailableWidth();

// Заголовок колонок (цифри)
        mainTable.addHeaderCell(new Cell().setPadding(0)
                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                .add(new Paragraph("1"))
                .setFont(FontLoader.bold())
                .setFontSize(8)
                .setTextAlignment(TextAlignment.CENTER));

        mainTable.addHeaderCell(new Cell().setPadding(0)
                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                .add(new Paragraph("2"))
                .setFont(FontLoader.bold())
                .setFontSize(10)
                .setTextAlignment(TextAlignment.CENTER));

// Далі аналогічно для 3, 4, 5
        mainTable.addHeaderCell(new Cell().setPadding(0)
                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                .add(new Paragraph("3"))
                .setFont(FontLoader.bold())
                .setFontSize(10)
                .setTextAlignment(TextAlignment.CENTER));

        mainTable.addHeaderCell(new Cell().setPadding(0)
                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                .add(new Paragraph("4"))
                .setFont(FontLoader.bold())
                .setFontSize(10)
                .setTextAlignment(TextAlignment.CENTER));

        mainTable.addHeaderCell(new Cell().setPadding(0)
                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                .add(new Paragraph("5"))
                .setFont(FontLoader.bold())
                .setFontSize(10)
                .setTextAlignment(TextAlignment.CENTER));

// Додаємо студентів
        for (int i = 0; i < modules.size() - 1; i++) {
            FirstModuleDto s = modules.get(i);
            addStudentRow(mainTable, s);
        }

        doc.add(mainTable);

        FirstModuleDto last = modules.get(modules.size() - 1);
        Table lastRowTable = new Table(UnitValue.createPercentArray(new float[]{5, 35, 20, 20, 20})).useAllAvailableWidth();
        addStudentRow(lastRowTable, last);

// Таблиця підпису
        Table signTable = new Table(UnitValue.createPercentArray(new float[]{20, 5, 20, 5, 20})).useAllAvailableWidth();
        signTable.addCell(new Cell().add(new Paragraph("Підпис викладача: ____________________"))
                .setBorder(Border.NO_BORDER)
                .setFont(FontLoader.regular())
                .setFontSize(10)
        );

// Обгортка з setKeepTogether
        Div finalBlock = new Div()
                .add(lastRowTable)
                .add(signTable)
                .setKeepTogether(true);

        doc.add(finalBlock);

    }

    private static void addStudentRow(Table table, FirstModuleDto s) {
        table.addCell(new Cell().add(new Paragraph(String.valueOf(s.index())))
                .setTextAlignment(TextAlignment.CENTER).setFontSize(8));
        table.addCell(new Cell().add(new Paragraph(s.pib())).setFontSize(10));
        table.addCell(new Cell().add(new Paragraph(s.studentBookNumber()))
                .setTextAlignment(TextAlignment.CENTER).setFontSize(10));
        table.addCell(new Cell().add(new Paragraph(s.mark()))
                .setTextAlignment(TextAlignment.CENTER).setFontSize(10));
        table.addCell(new Cell().add(new Paragraph(""))
                .setTextAlignment(TextAlignment.CENTER).setFontSize(10));
    }

}
