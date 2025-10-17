package esvar.ua.generate.header;

import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.pdf.canvas.draw.SolidLine;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.LineSeparator;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import esvar.ua.DataModelForZalik;
import esvar.ua.settings.FontLoader;

import java.io.IOException;

public class GenerateHeader {
    public static void generateHeader(Document doc, PdfFont font, DataModelForZalik zalik, String typeControlHeader) throws IOException {
        doc.setFont(font);

        doc.add(new Paragraph("НАЦІОНАЛЬНИЙ ТРАНСПОРТНИЙ УНІВЕРСИТЕТ")
                .setFont(FontLoader.bold())
                .setFontSize(11)
                .setTextAlignment(TextAlignment.CENTER));

        SolidLine solidLine = new SolidLine(1f);
        LineSeparator line = new LineSeparator(solidLine);
        line.setMarginTop(-6);
        line.setMarginBottom(0);

        doc.add(line);
        doc.add(new Paragraph(zalik.facultyName()).setFontSize(11));
        doc.add(line);
        doc.add(new Paragraph("Спеціальність: " + zalik.specialityName()).setFontSize(11));
        doc.add(line);


        Table groupTable = new Table(UnitValue.createPercentArray(new float[]{25, 10, 25, 40}))
                .useAllAvailableWidth();

        groupTable.addCell(new Cell().setPadding(0)
                .add(new Paragraph("Курс: ").setFont(FontLoader.regular()).setFontSize(11))
                .setBorder(Border.NO_BORDER));

        groupTable.addCell(new Cell().setPadding(0)
                .add(new Paragraph(zalik.courseNumber()).setFont(FontLoader.regular()).setFontSize(11).setTextAlignment(TextAlignment.CENTER))
                .setBorderTop(Border.NO_BORDER)
                .setBorderLeft(Border.NO_BORDER)
                .setBorderRight(Border.NO_BORDER)
                .setBorderBottom(new SolidBorder(0.5f)));

        groupTable.addCell(new Cell().setPadding(0)
                .add(new Paragraph("\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0Група: ").setFont(FontLoader.regular()).setFontSize(11))
                .setBorder(Border.NO_BORDER));

        groupTable.addCell(new Cell().setPadding(0)
                .add(new Paragraph(zalik.groupName()).setFont(FontLoader.regular()).setFontSize(11).setTextAlignment(TextAlignment.CENTER))
                .setBorderTop(Border.NO_BORDER)
                .setBorderLeft(Border.NO_BORDER)
                .setBorderRight(Border.NO_BORDER)
                .setBorderBottom(new SolidBorder(0.5f)));

        doc.add(groupTable);



        doc.add(new Paragraph(zalik.yearOfStudy() + " навчальний рік")
                .setFontSize(11)
                .setTextAlignment(TextAlignment.CENTER));
        doc.add(new Paragraph(typeControlHeader)
                .setFont(FontLoader.bold())
                .setFontSize(11)
                .setTextAlignment(TextAlignment.CENTER));
        doc.add(new Paragraph(zalik.day() + "  " + zalik.month() + "  " + zalik.year() + " року")
                .setFont(FontLoader.bold())
                .setFontSize(11)
                .setTextAlignment(TextAlignment.CENTER)
                .setUnderline());

        Table disciplineTable = new Table(UnitValue.createPercentArray(new float[]{15, 70, 15}))
                .useAllAvailableWidth();

// Рядок 1: назва дисципліни з підкресленням
        disciplineTable.addCell(
                new Cell().setPadding(0)
                        .setBorder(Border.NO_BORDER)
                        .add(new Paragraph("з дисципліни: ")
                                .setFont(FontLoader.regular())
                                .setFontSize(11))
        );

        disciplineTable.addCell(
                new Cell().setPadding(0)
                        .setBorderTop(Border.NO_BORDER)
                        .setBorderLeft(Border.NO_BORDER)
                        .setBorderRight(Border.NO_BORDER)
                        .setBorderBottom(new SolidBorder(0.5f))
                        .add(new Paragraph(zalik.disciplineTitle())
                                .setFont(FontLoader.regular())
                                .setFontSize(11)
                                .setTextAlignment(TextAlignment.CENTER)
                        )
        );


        disciplineTable.addCell(
                new Cell().setPadding(0)
                        .setBorderTop(Border.NO_BORDER)
                        .setBorderLeft(Border.NO_BORDER)
                        .setBorderRight(Border.NO_BORDER)
                        .setBorderBottom(new SolidBorder(0.5f))
                        .add(new Paragraph("")
                                .setFont(FontLoader.regular())
                                .setFontSize(11))
        );

        disciplineTable.addCell(
                new Cell().setPadding(0)
                        .setBorder(Border.NO_BORDER)
                        .add(new Paragraph("")
                                .setFont(FontLoader.regular())
                                .setFontSize(11))
        );

        disciplineTable.addCell(
                new Cell().setPadding(0)
                        .setBorder(Border.NO_BORDER)
                        .add(new Paragraph("(назва дисципліни)")
                                .setFont(FontLoader.regular())
                                .setFontSize(8)
                                .setTextAlignment(TextAlignment.CENTER)
                        )
        );

        disciplineTable.addCell(
                new Cell().setPadding(0)
                        .setBorder(Border.NO_BORDER)
                        .add(new Paragraph("")
                                .setFont(FontLoader.regular())
                                .setFontSize(11))
        );

        doc.add(disciplineTable);

        Table semControlTable = new Table(UnitValue.createPercentArray(new float[]{2, 5, 93}))
                .useAllAvailableWidth();
        semControlTable.addCell(new Cell().setPadding(0)
                .setBorder(Border.NO_BORDER)
                .add(new Paragraph("за")
                        .setFont(FontLoader.regular())
                        .setFontSize(11)));
        semControlTable.addCell(new Cell().setPadding(0)
                .setBorderTop(Border.NO_BORDER)
                .setBorderLeft(Border.NO_BORDER)
                .setBorderRight(Border.NO_BORDER)
                .setBorderBottom(new SolidBorder(0.5f))
                .add(new Paragraph(zalik.semester())
                        .setFont(FontLoader.regular())
                        .setFontSize(11)
                        .setTextAlignment(TextAlignment.CENTER)));
        semControlTable.addCell(new Cell().setPadding(0)
                .setBorder(Border.NO_BORDER)
                .add(new Paragraph("навчальний семестр.")
                        .setFont(FontLoader.regular())
                        .setFontSize(11)));

        doc.add(semControlTable);

        Table semesterControlTable = new Table(UnitValue.createPercentArray(new float[]{30, 30, 30, 10}))
                .useAllAvailableWidth();

        semesterControlTable.addCell(new Cell().setPadding(0)
                .setBorder(Border.NO_BORDER)
                .add(new Paragraph("Форма семестрового контролю")
                        .setFont(FontLoader.regular())
                        .setFontSize(11)));
        semesterControlTable.addCell(new Cell().setPadding(0)
                .setBorderTop(Border.NO_BORDER)
                .setBorderLeft(Border.NO_BORDER)
                .setBorderRight(Border.NO_BORDER)
                .setBorderBottom(new SolidBorder(0.5f))
                .add(new Paragraph(zalik.formOfSemesterControl())
                        .setFont(FontLoader.regular())
                        .setFontSize(11)
                        .setTextAlignment(TextAlignment.CENTER)));
        semesterControlTable.addCell(new Cell().setPadding(0)
                .setBorder(Border.NO_BORDER)
                .add(new Paragraph("Загальна кількість годин")
                        .setFont(FontLoader.regular())
                        .setFontSize(11)
                        .setTextAlignment(TextAlignment.CENTER)));
        semesterControlTable.addCell(new Cell().setPadding(0)
                .setBorderTop(Border.NO_BORDER)
                .setBorderLeft(Border.NO_BORDER)
                .setBorderRight(Border.NO_BORDER)
                .setBorderBottom(new SolidBorder(0.5f))
                .add(new Paragraph(zalik.totalHours())
                        .setFont(FontLoader.regular())
                        .setFontSize(11)
                        .setTextAlignment(TextAlignment.CENTER)));

        doc.add(semesterControlTable);

        Table firstTeacherTable = new Table(UnitValue.createPercentArray(new float[]{10, 80, 10}))
                .useAllAvailableWidth();

// Рядок 1: назва дисципліни з підкресленням
        firstTeacherTable.addCell(
                new Cell().setPadding(0)
                        .setBorder(Border.NO_BORDER)
                        .add(new Paragraph("Викладач")
                                .setFont(FontLoader.regular())
                                .setFontSize(11))
        );

        firstTeacherTable.addCell(
                new Cell().setPadding(0)
                        .setBorderTop(Border.NO_BORDER)
                        .setBorderLeft(Border.NO_BORDER)
                        .setBorderRight(Border.NO_BORDER)
                        .setBorderBottom(new SolidBorder(0.5f))
                        .add(new Paragraph(zalik.firstTeacherName())
                                .setFont(FontLoader.regular())
                                .setFontSize(11)
                                .setTextAlignment(TextAlignment.CENTER)
                        )
        );


        firstTeacherTable.addCell(
                new Cell().setPadding(0)
                        .setBorderTop(Border.NO_BORDER)
                        .setBorderLeft(Border.NO_BORDER)
                        .setBorderRight(Border.NO_BORDER)
                        .setBorderBottom(new SolidBorder(0.5f))
                        .add(new Paragraph("")
                                .setFont(FontLoader.regular())
                                .setFontSize(11))
        );

        firstTeacherTable.addCell(
                new Cell().setPadding(0)
                        .setBorder(Border.NO_BORDER)
                        .add(new Paragraph("")
                                .setFont(FontLoader.regular())
                                .setFontSize(11))
        );

        firstTeacherTable.addCell(
                new Cell().setPadding(0)
                        .setBorder(Border.NO_BORDER)
                        .add(new Paragraph("(прізвище, ім’я та по батькові викладача, який виставляє підсумкову оцінку)")
                                .setFont(FontLoader.regular())
                                .setFontSize(8)
                                .setTextAlignment(TextAlignment.CENTER)
                        )
        );

        firstTeacherTable.addCell(
                new Cell().setPadding(0)
                        .setBorder(Border.NO_BORDER)
                        .add(new Paragraph("")
                                .setFont(FontLoader.regular())
                                .setFontSize(11))
        );

        doc.add(firstTeacherTable);

        Table secondTeacherTable = new Table(UnitValue.createPercentArray(new float[]{10, 80, 10}))
                .useAllAvailableWidth();

// Рядок 1: назва дисципліни з підкресленням
        secondTeacherTable.addCell(
                new Cell().setPadding(0)
                        .setBorder(Border.NO_BORDER)
                        .add(new Paragraph("Викладач")
                                .setFont(FontLoader.regular())
                                .setFontSize(11))
        );

        secondTeacherTable.addCell(
                new Cell().setPadding(0)
                        .setBorderTop(Border.NO_BORDER)
                        .setBorderLeft(Border.NO_BORDER)
                        .setBorderRight(Border.NO_BORDER)
                        .setBorderBottom(new SolidBorder(0.5f))
                        .add(new Paragraph(zalik.secondTeacherName())
                                .setFont(FontLoader.regular())
                                .setFontSize(11)
                                .setTextAlignment(TextAlignment.CENTER)
                        )
        );


        secondTeacherTable.addCell(
                new Cell().setPadding(0)
                        .setBorderTop(Border.NO_BORDER)
                        .setBorderLeft(Border.NO_BORDER)
                        .setBorderRight(Border.NO_BORDER)
                        .setBorderBottom(new SolidBorder(0.5f))
                        .add(new Paragraph("")
                                .setFont(FontLoader.regular())
                                .setFontSize(11))
        );

        secondTeacherTable.addCell(
                new Cell().setPadding(0)
                        .setBorder(Border.NO_BORDER)
                        .add(new Paragraph("")
                                .setFont(FontLoader.regular())
                                .setFontSize(11))
        );

        secondTeacherTable.addCell(
                new Cell().setPadding(0)
                        .setBorder(Border.NO_BORDER)
                        .add(new Paragraph("(прізвище, ім’я та по батькові викладача, який здійснював поточний контроль)")
                                .setFont(FontLoader.regular())
                                .setFontSize(8)
                                .setTextAlignment(TextAlignment.CENTER)
                        )
        );

        secondTeacherTable.addCell(
                new Cell().setPadding(0)
                        .setBorder(Border.NO_BORDER)
                        .add(new Paragraph("")
                                .setFont(FontLoader.regular())
                                .setFontSize(11))
        );

        doc.add(secondTeacherTable);

        Paragraph emptyParagraph = new Paragraph("")
                .setFont(FontLoader.regular())
                .setFontSize(11)
                .setTextAlignment(TextAlignment.CENTER);

        doc.add(emptyParagraph);

    }
}
