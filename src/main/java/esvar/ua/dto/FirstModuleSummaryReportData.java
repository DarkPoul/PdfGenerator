package esvar.ua.dto;

import java.util.List;
import java.util.Objects;

/**
 * Container with all data required to build the summary report for the first module control.
 */
public record FirstModuleSummaryReportData(String groupName,
                                           List<SummaryDisciplineDto> disciplines,
                                           List<SummaryStudentDto> students) {

    public FirstModuleSummaryReportData {
        Objects.requireNonNull(groupName, "groupName");
        Objects.requireNonNull(disciplines, "disciplines");
        Objects.requireNonNull(students, "students");

        if (disciplines.isEmpty()) {
            throw new IllegalArgumentException("At least one discipline is required for the summary report.");
        }
        if (students.isEmpty()) {
            throw new IllegalArgumentException("At least one student is required for the summary report.");
        }

        disciplines = List.copyOf(disciplines);
        students = List.copyOf(students);
    }

    @Override
    public List<SummaryDisciplineDto> disciplines() {
        return disciplines;
    }

    @Override
    public List<SummaryStudentDto> students() {
        return students;
    }
}