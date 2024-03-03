package Yaremchuk.https.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
public class ExerciseDtoRq {
    private LocalDate exerciseDate;
    private String subjectName;
    private String subjectDescription;
    private String costumerFirstName;
    private String costumerSecondName;
    private String teacherFirstName;
    private String teacherSecondName;
}
