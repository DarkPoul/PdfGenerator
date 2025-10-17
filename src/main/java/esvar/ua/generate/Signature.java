package esvar.ua.generate;

import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import esvar.ua.settings.FontLoader;

import java.io.IOException;

public class Signature {
    public static Table generateSignature(String examiner) throws IOException {
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
        signTable.addCell(new Cell().add(new Paragraph(examiner)).setBorderTop(Border.NO_BORDER).setBorderLeft(Border.NO_BORDER).setBorderRight(Border.NO_BORDER).setBorderBottom(new SolidBorder(0.5f))
                .setFont(FontLoader.bold())
                .setFontSize(10)
        );


        signTable.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        signTable.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        signTable.addCell(new Cell().add(new Paragraph("(підпис)").setFontSize(8).setTextAlignment(TextAlignment.CENTER)).setBorder(Border.NO_BORDER));
        signTable.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        signTable.addCell(new Cell().add(new Paragraph("(прізвище,ініціали)").setFontSize(8).setTextAlignment(TextAlignment.CENTER)).setBorder(Border.NO_BORDER));

        return signTable;
    }
}
