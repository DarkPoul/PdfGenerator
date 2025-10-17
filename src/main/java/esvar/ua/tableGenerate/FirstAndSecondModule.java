package esvar.ua.tableGenerate;

import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.itextpdf.layout.properties.VerticalAlignment;
import esvar.ua.generate.footer.ForFMC;
import esvar.ua.generate.footer.ForSMC;
import esvar.ua.settings.FontLoader;
import esvar.ua.dto.FirstAndSecondModuleDto;

import java.io.IOException;
import java.util.List;

import static esvar.ua.generate.header.NumericHeader.addNumericHeader;

public class FirstAndSecondModule {

    public static void generate(Document doc, PdfFont font, List<FirstAndSecondModuleDto> modules, String examiner, boolean first) throws IOException {

        Table headerTable = new Table(UnitValue.createPercentArray(new float[]{5, 35, 20, 20, 20})).useAllAvailableWidth();

        headerTable.addCell(new Cell().setBorderBottom(Border.NO_BORDER).setPadding(0).setVerticalAlignment(VerticalAlignment.MIDDLE).add(new Paragraph("№\nз/п")).setFont(FontLoader.regular()).setFontSize(8).setTextAlignment(TextAlignment.CENTER));
        headerTable.addCell(new Cell().setBorderBottom(Border.NO_BORDER).setPadding(0).setVerticalAlignment(VerticalAlignment.MIDDLE).add(new Paragraph("Прізвище та ініціали студента")).setFont(FontLoader.regular()).setFontSize(10).setTextAlignment(TextAlignment.CENTER));
        headerTable.addCell(new Cell().setBorderBottom(Border.NO_BORDER).setPadding(0).setVerticalAlignment(VerticalAlignment.MIDDLE).add(new Paragraph("Номер залікової книжки")).setFont(FontLoader.regular()).setFontSize(10).setTextAlignment(TextAlignment.CENTER));

        String label = first
                ? "Кількість балів за результатами першого модуля (від 0 до 30 балів)"
                : "Кількість балів за результатами першого модуля (від 30 до 60 балів)";

        headerTable.addCell(new Cell().setBorderBottom(Border.NO_BORDER).setPadding(0).setVerticalAlignment(VerticalAlignment.MIDDLE).add(new Paragraph(label)).setFont(FontLoader.regular()).setFontSize(10).setTextAlignment(TextAlignment.CENTER));

        headerTable.addCell(new Cell().setBorderBottom(Border.NO_BORDER).setPadding(0).setVerticalAlignment(VerticalAlignment.MIDDLE).add(new Paragraph("Підпис викладача")).setFont(FontLoader.regular()).setFontSize(10).setTextAlignment(TextAlignment.CENTER));

        doc.setFont(font);
        doc.add(headerTable);



        // Заголовок колонок (цифри)
        Table mainTable = new Table(UnitValue.createPercentArray(new float[]{5, 35, 20, 20, 20})).useAllAvailableWidth();
        doc.add(addNumericHeader(mainTable, 5));

        FirstAndSecondModuleDto last = modules.get(modules.size() - 1);


        // Додаємо студентів //todo можливо винести додавання студентів
        for (int i = 0; i < modules.size() - 1; i++) {
            FirstAndSecondModuleDto s = modules.get(i);
            float remainingHeight = doc.getRenderer().getCurrentArea().getBBox().getHeight();
            if (remainingHeight < 20) {
                doc.add(addNumericHeader(addStudentRow(s, false), 5));
            }

            doc.add(addStudentRow(s, false));

            if (modules.size()-2==i && first){
                if (remainingHeight < 80) doc.add(ForFMC.generate(examiner, addStudentRow(last, false), true));
                else doc.add(ForFMC.generate(examiner, addStudentRow(last, false), false));
            } else if (modules.size()-2==i && !first) {
                System.out.println(remainingHeight);
                if (remainingHeight < 260) doc.add(ForSMC.generate(modules,examiner, addStudentRow(last, false), true));
                else doc.add(ForSMC.generate(modules ,examiner, addStudentRow(last, false), false));
            }


        }

    }

    private static Table addStudentRow(FirstAndSecondModuleDto s, boolean addBottomBorder) {

        Table table1 = new Table(UnitValue.createPercentArray(new float[]{5, 35, 20, 20, 20})).useAllAvailableWidth();

        Border studentBorder = addBottomBorder ? new SolidBorder(0.5f) : Border.NO_BORDER;



        table1.addCell(new Cell().add(new Paragraph(String.valueOf(s.index())))
                .setTextAlignment(TextAlignment.CENTER).setFontSize(8)
                .setBorderTop(studentBorder));
        table1.addCell(new Cell().add(new Paragraph(s.pib())).setFontSize(10)
                .setBorderTop(studentBorder));
        table1.addCell(new Cell().add(new Paragraph(s.studentBookNumber()))
                .setTextAlignment(TextAlignment.CENTER).setFontSize(10)
                .setBorderTop(studentBorder));
        table1.addCell(new Cell().add(new Paragraph(s.mark()))
                .setTextAlignment(TextAlignment.CENTER).setFontSize(10)
                .setBorderTop(studentBorder));
        table1.addCell(new Cell().add(new Paragraph(""))
                .setTextAlignment(TextAlignment.CENTER).setFontSize(10)
                .setBorderTop(studentBorder));

        return table1;
    }

}
