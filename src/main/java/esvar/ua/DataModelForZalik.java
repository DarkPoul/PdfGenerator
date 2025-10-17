package esvar.ua;

import esvar.ua.dto.FirstAndSecondModuleDto;

import java.util.List;

public record DataModelForZalik(
        String facultyName,
        String specialityName,
        String courseNumber,
        String groupName,
        String yearOfStudy,
        String day,
        String month,
        String year,
        String disciplineTitle,
        String semester,
        String formOfSemesterControl,
        String totalHours,
        String firstTeacherName,
        String secondTeacherName,
        List<FirstAndSecondModuleDto> students
) {}

