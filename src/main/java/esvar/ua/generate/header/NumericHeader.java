package esvar.ua.generate.header;

import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.VerticalAlignment;
import esvar.ua.settings.FontLoader;

import java.io.IOException;

public class NumericHeader {
    public static Table addNumericHeader(Table table, int point) throws IOException { //todo додати case для кількості частин і винести в окремий клас

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

        if (String.valueOf(point).equals("8")){

            table.addHeaderCell(new Cell().setPadding(0)
                    .setVerticalAlignment(VerticalAlignment.MIDDLE)
                    .add(new Paragraph("6"))
                    .setFont(FontLoader.bold())
                    .setFontSize(10)
                    .setTextAlignment(TextAlignment.CENTER));

            table.addHeaderCell(new Cell().setPadding(0)
                    .setVerticalAlignment(VerticalAlignment.MIDDLE)
                    .add(new Paragraph("7"))
                    .setFont(FontLoader.bold())
                    .setFontSize(10)
                    .setTextAlignment(TextAlignment.CENTER));

            table.addHeaderCell(new Cell().setPadding(0)
                    .setVerticalAlignment(VerticalAlignment.MIDDLE)
                    .add(new Paragraph("8"))
                    .setFont(FontLoader.bold())
                    .setFontSize(10)
                    .setTextAlignment(TextAlignment.CENTER));
        }

        return table;
    }
}
