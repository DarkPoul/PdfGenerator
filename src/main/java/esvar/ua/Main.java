package esvar.ua;

import esvar.ua.dto.FirstModuleDto;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        ZalikPdfGenerator generator = new ZalikPdfGenerator();

        Map<Integer, String> monthGenitive = Map.ofEntries(
                Map.entry(1, "січня"),
                Map.entry(2, "лютого"),
                Map.entry(3, "березня"),
                Map.entry(4, "квітня"),
                Map.entry(5, "травня"),
                Map.entry(6, "червня"),
                Map.entry(7, "липня"),
                Map.entry(8, "серпня"),
                Map.entry(9, "вересня"),
                Map.entry(10, "жовтня"),
                Map.entry(11, "листопада"),
                Map.entry(12, "грудня")
        );

        LocalDate today = LocalDate.now();
        String day = String.format("%02d", today.getDayOfMonth());
        String month = monthGenitive.get(today.getMonthValue());
        String year  = String.valueOf(today.getYear());



        Path outputDir = Path.of("1 модульний контроль");
        int[] counts = {10, 20, 30, 40, 50};

        for (int count : counts) {
            List<FirstModuleDto> students = generateStudents(count);

            DataModelForZalik data = new DataModelForZalik(
                    "Факультет транспортних та інформаційних технологій",
                    "Інформаційна, бібліотечна та архівна справа",
                    "1",
                    "КІм-1-1-2024",
                    "2024-2025",
                    day,
                    month,
                    year,
                    "Забезпечення надійності функціонування комп'ютеризованих систем",
                    "1-й",
                    "Перший модульний контроль",
                    "120",
                    "Клочан Арсен Євгенійович",
                    "Гончар Павло Олександрович",
                    students
            );

            Path pdfPath = outputDir.resolve("zalik_" + count + ".pdf");
            Path path = generator.generatePdf(data, pdfPath,
                    "ВІДОМІСТЬ ПІДСУМКОВИХ ОЦІНОК ЗА ПЕРШИЙ МОДУЛЬНИЙ КОНТРОЛЬ", TableType.FIRST_MODULE);
            System.out.println("PDF створено: " + path.toAbsolutePath());
        }

        Path outputDirSecond = Path.of("2 модульний контроль");
        for (int count : counts) {
            List<FirstModuleDto> students = generateStudents(count);

            DataModelForZalik data = new DataModelForZalik(
                    "Факультет транспортних та інформаційних технологій",
                    "Інформаційна, бібліотечна та архівна справа",
                    "1",
                    "КІм-1-1-2024",
                    "2024-2025",
                    day,
                    month,
                    year,
                    "Забезпечення надійності функціонування комп'ютеризованих систем",
                    "1-й",
                    "Другий модульний контроль",
                    "120",
                    "Клочан Арсен Євгенійович",
                    "Гончар Павло Олександрович",
                    students
            );

            Path pdfPath = outputDirSecond.resolve("zalik_" + count + ".pdf");
            Path path = generator.generatePdf(data, pdfPath,
                    "ВІДОМІСТЬ ПІДСУМКОВИХ ОЦІНОК ЗА ДРУГИЙ МОДУЛЬНИЙ КОНТРОЛЬ", TableType.SECOND_MODULE);
            System.out.println("PDF створено: " + path.toAbsolutePath());
        }

        Path outputDirZalik = Path.of("залік");

        for (int count : counts) {
            List<FirstModuleDto> students = generateStudents(count);

            DataModelForZalik data = new DataModelForZalik(
                    "Факультет транспортних та інформаційних технологій",
                    "Інформаційна, бібліотечна та архівна справа",
                    "1",
                    "КІм-1-1-2024",
                    "2024-2025",
                    day,
                    month,
                    year,
                    "Забезпечення надійності функціонування комп'ютеризованих систем",
                    "1-й",
                    "Залік",
                    "120",
                    "Клочан Арсен Євгенійович",
                    "Гончар Павло Олександрович",
                    students
            );

            Path pdfPath = outputDirZalik.resolve("zalik_" + count + ".pdf");
            Path path = generator.generatePdf(data, pdfPath,
                    "ВІДОМІСТЬ ПІДСУМКОВИХ ОЦІНОК ЗА ЗАЛІК", TableType.ZALIK);
            System.out.println("PDF створено: " + path.toAbsolutePath());
        }

    }

    private static List<FirstModuleDto> generateStudents(int count) {
        List<FirstModuleDto> list = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            String pib = "Студент " + i;
            String book = String.format("КН%06d", i);
            String mark = String.valueOf(60 + (i % 41));
            list.add(new FirstModuleDto(i, pib, book, mark));
        }
        return list;
    }
}





