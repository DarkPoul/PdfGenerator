package esvar.ua;

import esvar.ua.dto.*;
import esvar.ua.settings.PdfGenerator;
import esvar.ua.settings.TableType;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        PdfGenerator generator = new PdfGenerator();
        GroupListPdfGenerator groupListPdfGenerator = new GroupListPdfGenerator();

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
        int[] counts = {10, 15, 16, 17, 18, 19, 20, 25, 30, 35, 40, 45, 50, 57, 100};

        for (int count : counts) {
            List<FirstAndSecondModuleDto> students = generateStudents(count);

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

            Path pdfPath = outputDir.resolve("first_modal_" + count + ".pdf");
            Path path = generator.generatePdf(data, pdfPath,
                    "ВІДОМІСТЬ ПІДСУМКОВИХ ОЦІНОК ЗА ПЕРШИЙ МОДУЛЬНИЙ КОНТРОЛЬ", TableType.FIRST_MODULE);
            System.out.println("PDF створено: " + path.toAbsolutePath());
        }

        Path outputDirSecond = Path.of("2 модульний контроль");
        for (int count : counts) {
            List<FirstAndSecondModuleDto> students = generateStudents(count);

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

            Path pdfPath = outputDirSecond.resolve("second_modal_" + count + ".pdf");
            Path path = generator.generatePdf(data, pdfPath,
                    "ВІДОМІСТЬ ПІДСУМКОВИХ ОЦІНОК ЗА ДРУГИЙ МОДУЛЬНИЙ КОНТРОЛЬ", TableType.SECOND_MODULE);
            System.out.println("PDF створено: " + path.toAbsolutePath());
        }

        Path outputDirZalik = Path.of("залік");

        for (int count : counts) {
            List<FirstAndSecondModuleDto> students = generateStudents(count);

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

        List<GroupStudentDto> groupStudents = generateGroupStudents(30);
        GroupListModel groupList = new GroupListModel(
                "Список групи",
                "КІм-1-1-2024",
                groupStudents
        );

        Path groupListDir = Path.of("список групи");
        Path groupListPath = groupListDir.resolve("group_list.pdf");
        Path generatedGroupList = groupListPdfGenerator.generatePdf(groupList, groupListPath);
        System.out.println("PDF створено: " + generatedGroupList.toAbsolutePath());


        FirstModuleSummaryReportPdfGenerator summaryGenerator = new FirstModuleSummaryReportPdfGenerator();
        List<SummaryDisciplineDto> summaryDisciplines = List.of(
                new SummaryDisciplineDto("Вища математика", 30),
                new SummaryDisciplineDto("Програмування", 30),
                new SummaryDisciplineDto("Комп'ютерна логіка", 20),
                new SummaryDisciplineDto("Комп'ютерна логіка", 20),
                new SummaryDisciplineDto("Комп'ютерна логіка", 20),
                new SummaryDisciplineDto("Комп'ютерна логіка", 20),
                new SummaryDisciplineDto("Комп'ютерна логіка", 20),
                new SummaryDisciplineDto("Іноземна мова", 20)
        );

        List<SummaryStudentDto> summaryStudents = List.of(
                new SummaryStudentDto("Студент 1", List.of(30, 25, 20, 20,28, 29, 0, 0)),
                new SummaryStudentDto("Студент 2", List.of(30, 0, 18, 17,28, 29, 0, 0)),
                new SummaryStudentDto("Студент 3", List.of(28, 29, 0, 0,28, 29, 0, 0)),
                new SummaryStudentDto("Студент 4", List.of(28, 29, 0, 0,28, 29, 0, 0)),
                new SummaryStudentDto("Студент 5", List.of(28, 29, 0, 0,28, 29, 0, 0)),
                new SummaryStudentDto("Студент 6", List.of(28, 29, 0, 0,28, 29, 0, 0)),
                new SummaryStudentDto("Студент 7", List.of(28, 29, 0, 0,28, 29, 0, 0)),
                new SummaryStudentDto("Студент 8", List.of(30, 30, 20, 19,28, 29, 0, 0))
        );

        FirstModuleSummaryReportData summaryData = new FirstModuleSummaryReportData(
                "КІм-1-1-2024",
                summaryDisciplines,
                summaryStudents
        );

        Path summaryOutput = Path.of("зведений звіт");
        Path summaryPdf = summaryOutput.resolve("summary.pdf");
        Path summaryPath = summaryGenerator.generate(summaryData, summaryPdf);
        System.out.println("Зведений звіт створено: " + summaryPath.toAbsolutePath());

    }

    private static List<FirstAndSecondModuleDto> generateStudents(int count) {
        List<FirstAndSecondModuleDto> list = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            String pib = "Студент " + i;
            String book = String.format("КН%06d", i);
            String mark = String.valueOf(20 + (i % 41));
            list.add(new FirstAndSecondModuleDto(i, pib, book, mark));
        }
        return list;
    }

    private static List<GroupStudentDto> generateGroupStudents(int count) {
        List<GroupStudentDto> list = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            String pib = "Студент " + i;
            String book = String.format("КН%06d", i);
            list.add(new GroupStudentDto(i, pib, book));
        }
        return list;
    }
}





