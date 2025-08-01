package esvar.ua;

import esvar.ua.dto.FirstModuleDto;

import java.nio.file.Path;
import java.time.LocalDate;
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

        String day   = String.format("%02d", today.getDayOfMonth()); // "01"
        String month = monthGenitive.get(today.getMonthValue());
        String year  = String.valueOf(today.getYear());

        List<FirstModuleDto> students = List.of(
                new FirstModuleDto(1, "Іванов І. І.", "КН123456", "91"),
                new FirstModuleDto(2, "Петренко П. П.", "КН234567", "78"),
                new FirstModuleDto(3, "Сидоренко С. С.", "КН345678", "85"),
                new FirstModuleDto(4, "Коваленко К. К.", "КН456789", "92"),
                new FirstModuleDto(5, "Тарасенко Т. Т.", "КН567890", "88"),
                new FirstModuleDto(6, "Грищенко Г. Г.", "КН678901", "95"),
                new FirstModuleDto(7, "Мельник М. М.", "КН789012", "80"),
                new FirstModuleDto(8, "Бондаренко Б. Б.", "КН890123", "75"),
                new FirstModuleDto(9, "Кравченко К. К.", "КН901234", "90"),
                new FirstModuleDto(10, "Лисенко Л. Л.", "КН012345", "82"),
                new FirstModuleDto(11, "Лисенко Л. Л.", "КН012345", "82"),
                new FirstModuleDto(12, "Іванов І. І.", "КН123456", "91"),
                new FirstModuleDto(13, "Петренко П. П.", "КН234567", "78"),
                new FirstModuleDto(14, "Сидоренко С. С.", "КН345678", "85"),
                new FirstModuleDto(15, "Коваленко К. К.", "КН456789", "92"),
                new FirstModuleDto(16, "Тарасенко Т. Т.", "КН567890", "88"),
                new FirstModuleDto(17, "Грищенко Г. Г.", "КН678901", "95"),
                new FirstModuleDto(18, "Мельник М. М.", "КН789012", "80"),
                new FirstModuleDto(19, "Бондаренко Б. Б.", "КН890123", "75")
//                new FirstModuleDto(20, "Кравченко К. К.", "КН901234", "90")
//                new FirstModuleDto(21, "Лисенко Л. Л.", "КН012345", "82")
//                new FirstModuleDto(22, "Іванов І. І.", "КН123456", "91"),
//                new FirstModuleDto(23, "Петренко П. П.", "КН234567", "78"),
//                new FirstModuleDto(24, "Сидоренко С. С.", "КН345678", "85"),
//                new FirstModuleDto(25, "Коваленко К. К.", "КН456789", "92"),
//                new FirstModuleDto(26, "Тарасенко Т. Т.", "КН567890", "88"),
//                new FirstModuleDto(27, "Грищенко Г. Г.", "КН678901", "95"),
//                new FirstModuleDto(28, "Мельник М. М.", "КН789012", "80"),
//                new FirstModuleDto(29, "Бондаренко Б. Б.", "КН890123", "75"),
//                new FirstModuleDto(30, "Кравченко К. К.", "КН901234", "90")

        );

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

        Path path = generator.generatePdf(data);
        System.out.println("PDF створено: " + path.toAbsolutePath());




    }
}
