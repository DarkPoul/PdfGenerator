package esvar.ua.dto;

import java.util.List;
import java.util.Objects;

/**
 * Holds the marks received by a student for the disciplines that are included in the summary report.
 */
public record SummaryStudentDto(String pib, List<Integer> marks) {

    public SummaryStudentDto {
        Objects.requireNonNull(pib, "pib");
        Objects.requireNonNull(marks, "marks");
        marks = List.copyOf(marks);
    }

    @Override
    public List<Integer> marks() {
        return marks;
    }
}