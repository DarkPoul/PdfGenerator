package esvar.ua.dto;

public record ZalikDto
        (
                int index,
                String pib,
                String studentBookNumber,
                String national_mark,
                String mark,
                String ECTS_mark,
                String date
        ) {}
