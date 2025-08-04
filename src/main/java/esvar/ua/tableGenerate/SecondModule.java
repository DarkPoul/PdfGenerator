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
import esvar.ua.FontLoader;
import esvar.ua.dto.FirstModuleDto;

import java.io.IOException;
import java.util.List;

public class SecondModule {

    public static void generate(Document doc, PdfFont font, List<FirstModuleDto> modules, DataModelForZalik zalik) throws IOException {

        Table headerTable = new Table(UnitValue.createPercentArray(new float[]{5, 35, 20, 20, 20}))
                .useAllAvailableWidth();

        doc.setFont(font);

        headerTable.addCell(new Cell().setBorderBottom(Border.NO_BORDER).setPadding(0).setVerticalAlignment(VerticalAlignment.MIDDLE).add(new Paragraph("№\nз/п")).setFont(FontLoader.regular()).setFontSize(8).setTextAlignment(TextAlignment.CENTER));
        headerTable.addCell(new Cell().setBorderBottom(Border.NO_BORDER).setPadding(0).setVerticalAlignment(VerticalAlignment.MIDDLE).add(new Paragraph("Прізвище та ініціали студента")).setFont(FontLoader.regular()).setFontSize(10).setTextAlignment(TextAlignment.CENTER));
        headerTable.addCell(new Cell().setBorderBottom(Border.NO_BORDER).setPadding(0).setVerticalAlignment(VerticalAlignment.MIDDLE).add(new Paragraph("Номер залікової книжки")).setFont(FontLoader.regular()).setFontSize(10).setTextAlignment(TextAlignment.CENTER));
        headerTable.addCell(new Cell().setBorderBottom(Border.NO_BORDER).setPadding(0).setVerticalAlignment(VerticalAlignment.MIDDLE).add(new Paragraph("Кількість балів за результатами двох модулів (від 0 до 60 балів)")).setFont(FontLoader.regular()).setFontSize(10).setTextAlignment(TextAlignment.CENTER));
        headerTable.addCell(new Cell().setBorderBottom(Border.NO_BORDER).setPadding(0).setVerticalAlignment(VerticalAlignment.MIDDLE).add(new Paragraph("Підпис викладача")).setFont(FontLoader.regular()).setFontSize(10).setTextAlignment(TextAlignment.CENTER));


        doc.add(headerTable);

        Table mainTable = new Table(UnitValue.createPercentArray(new float[]{5, 35, 20, 20, 20}))
                .useAllAvailableWidth();

        addNumericHeader(mainTable);

        for (int i = 0; i < modules.size() - 1; i++) {
            FirstModuleDto s = modules.get(i);
            addStudentRow(mainTable, s, true);
        }

        doc.add(mainTable);

        FirstModuleDto last = modules.get(modules.size() - 1);
        Table lastRowTable = new Table(UnitValue.createPercentArray(new float[]{5, 35, 20, 20, 20}))
                .useAllAvailableWidth();

        float remainingHeight = doc.getRenderer().getCurrentArea().getBBox().getHeight();
        if (remainingHeight < 60) {
            addNumericHeader(lastRowTable);
        }
        addStudentRow(lastRowTable, last, false);

        Table signTable = new Table(UnitValue.createPercentArray(new float[]{20, 5, 20, 5, 20})).useAllAvailableWidth();
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
        signTable.addCell(new Cell().add(new Paragraph(zalik.firstTeacherName())).setBorderTop(Border.NO_BORDER).setBorderLeft(Border.NO_BORDER).setBorderRight(Border.NO_BORDER).setBorderBottom(new SolidBorder(0.5f))
                .setFont(FontLoader.bold())
                .setFontSize(10)
        );


        signTable.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        signTable.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        signTable.addCell(new Cell().add(new Paragraph("(підпис)").setFontSize(8).setTextAlignment(TextAlignment.CENTER)).setBorder(Border.NO_BORDER));
        signTable.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        signTable.addCell(new Cell().add(new Paragraph("(прізвище,ініціали)").setFontSize(8).setTextAlignment(TextAlignment.CENTER)).setBorder(Border.NO_BORDER));

        Paragraph results_of_semester_performance = new Paragraph("Підсумки семестрової успішності")
                .setFont(FontLoader.bold())
                .setFontSize(12)
                .setTextAlignment(TextAlignment.CENTER);

        Table results_of_semester_performance_Table = new Table(new float[]{1,1}).useAllAvailableWidth();
        results_of_semester_performance_Table.addCell(new Cell().add(new Paragraph("Кількість студентів")).setFont(FontLoader.bold()).setFontSize(10).setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER));
        results_of_semester_performance_Table.addCell(new Cell().add(new Paragraph("Сума балів")).setFont(FontLoader.bold()).setFontSize(10).setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER));



        Div finalBlock = new Div()
                .add(lastRowTable)
                .add(new Paragraph(""))
                .add(results_of_semester_performance)
                .add(results_of_semester_performance_Table)
                .add(new Paragraph(""))
                .add(signTable)
                .setKeepTogether(true);

        doc.add(finalBlock);

    }

    private static void addStudentRow(Table table, FirstModuleDto s, boolean addBottomBorder) {
        Border studentBorder = addBottomBorder ? new SolidBorder(0.5f) : Border.NO_BORDER;

        table.addCell(new Cell().add(new Paragraph(String.valueOf(s.index())))
                .setTextAlignment(TextAlignment.CENTER).setFontSize(8)
                .setBorderTop(studentBorder));
        table.addCell(new Cell().add(new Paragraph(s.pib())).setFontSize(10)
                .setBorderTop(studentBorder));
        table.addCell(new Cell().add(new Paragraph(s.studentBookNumber()))
                .setTextAlignment(TextAlignment.CENTER).setFontSize(10)
                .setBorderTop(studentBorder));
        table.addCell(new Cell().add(new Paragraph(s.mark()))
                .setTextAlignment(TextAlignment.CENTER).setFontSize(10)
                .setBorderTop(studentBorder));
        table.addCell(new Cell().add(new Paragraph(""))
                .setTextAlignment(TextAlignment.CENTER).setFontSize(10)
                .setBorderTop(studentBorder));
    }

    private static void addNumericHeader(Table table) throws IOException {
        table.addHeaderCell(new Cell().setPadding(0)
                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                .add(new Paragraph("1"))
                .setFont(FontLoader.bold())
                .setFontSize(8)
                .setTextAlignment(TextAlignment.CENTER));

        table.addHeaderCell(new Cell().setPadding(0)
                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                .add(new Paragraph("2"))
                .setFont(FontLoader.bold())
                .setFontSize(10)
                .setTextAlignment(TextAlignment.CENTER));

        table.addHeaderCell(new Cell().setPadding(0)
                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                .add(new Paragraph("3"))
                .setFont(FontLoader.bold())
                .setFontSize(10)
                .setTextAlignment(TextAlignment.CENTER));

        table.addHeaderCell(new Cell().setPadding(0)
                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                .add(new Paragraph("4"))
                .setFont(FontLoader.bold())
                .setFontSize(10)
                .setTextAlignment(TextAlignment.CENTER));

        table.addHeaderCell(new Cell().setPadding(0)
                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                .add(new Paragraph("5"))
                .setFont(FontLoader.bold())
                .setFontSize(10)
                .setTextAlignment(TextAlignment.CENTER));
    }

}