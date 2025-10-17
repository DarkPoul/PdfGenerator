package esvar.ua.generate.footer;

import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Div;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import esvar.ua.dto.FirstAndSecondModuleDto;
import esvar.ua.generate.Signature;
import esvar.ua.generate.header.NumericHeader;
import esvar.ua.settings.FontLoader;

import java.io.IOException;
import java.util.List;

public class ForSMC {

    public static Div generate(List<FirstAndSecondModuleDto> modules, String examiner, Table lastRowTable, boolean numericHeader) throws IOException {

        Paragraph results_of_semester_performance = new Paragraph("Підсумки семестрової успішності")
                .setFont(FontLoader.bold())
                .setFontSize(12)
                .setTextAlignment(TextAlignment.CENTER);

        Table results_of_semester_performance_Table = new Table(new float[]{1,1}).useAllAvailableWidth();
        results_of_semester_performance_Table.addCell(new Cell().add(new Paragraph("Кількість студентів")).setFont(FontLoader.bold()).setFontSize(10).setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER));
        results_of_semester_performance_Table.addCell(new Cell().add(new Paragraph("Сума балів")).setFont(FontLoader.bold()).setFontSize(10).setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER));


        int count20to60 = 0;
        int count0to19 = 0;
        for (FirstAndSecondModuleDto dto : modules) {
            int mark = parseIntSafe(dto.mark());
            if (mark >= 20 && mark <= 60) {
                count20to60++;

            } else if (mark < 20) {
                count0to19++;

            }
        }

        results_of_semester_performance_Table.setWidth(UnitValue.createPercentValue(70));
        // вирівнювання по центру
        results_of_semester_performance_Table.setHorizontalAlignment(HorizontalAlignment.CENTER);

        results_of_semester_performance_Table.addCell(new Cell().add(new Paragraph(String.valueOf(count20to60))).setFont(FontLoader.regular()).setFontSize(10).setTextAlignment(TextAlignment.CENTER).setBorder(new SolidBorder(0.5f)).setWidth(new UnitValue(UnitValue.createPercentValue(50))));
        results_of_semester_performance_Table.addCell(new Cell().add(new Paragraph("20 - 60")).setFont(FontLoader.regular()).setFontSize(10).setTextAlignment(TextAlignment.CENTER).setBorder(new SolidBorder(0.5f)).setWidth(new UnitValue(UnitValue.createPercentValue(50))));
        results_of_semester_performance_Table.addCell(new Cell().add(new Paragraph(String.valueOf(count0to19))).setFont(FontLoader.regular()).setFontSize(10).setTextAlignment(TextAlignment.CENTER).setBorder(new SolidBorder(0.5f)));
        results_of_semester_performance_Table.addCell(new Cell().add(new Paragraph("0 - 19")).setFont(FontLoader.regular()).setFontSize(10).setTextAlignment(TextAlignment.CENTER).setBorder(new SolidBorder(0.5f)));


        if (numericHeader) return new Div()
                .add(NumericHeader.addNumericHeader(lastRowTable, 5))
                .add(new Paragraph(""))
                .add(results_of_semester_performance)
                .add(results_of_semester_performance_Table)
                .add(Signature.generateSignature(examiner))
                .setKeepTogether(true);

        else return new Div()
                .add(lastRowTable)
                .add(new Paragraph(""))
                .add(results_of_semester_performance)
                .add(results_of_semester_performance_Table)
                .add(new Paragraph(""))
                .add(Signature.generateSignature(examiner))
                .setKeepTogether(true);

    }

    private static int parseIntSafe(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
