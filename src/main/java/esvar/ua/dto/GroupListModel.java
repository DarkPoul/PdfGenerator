package esvar.ua.dto;

import java.util.List;

public record GroupListModel(
        String title,
        String groupName,
        List<GroupStudentDto> students
) {
}
