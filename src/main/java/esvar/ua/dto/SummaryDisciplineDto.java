package esvar.ua.dto;

import java.util.Objects;

/**
 * Describes a discipline that is present in the summary report. The maxPoints value is used
 * in the header to indicate the maximum amount of points that can be achieved for the discipline.
 */
public record SummaryDisciplineDto(String name, int maxPoints) {

    public SummaryDisciplineDto {
        Objects.requireNonNull(name, "name");
    }

    /**
     * Formats the discipline title together with the maximum number of points. The value is used in
     * the rotated header cells of the summary table.
     */
    public String formattedTitle() {
        return maxPoints > 0 ? name + " (" + maxPoints + ")" : name;
    }
}