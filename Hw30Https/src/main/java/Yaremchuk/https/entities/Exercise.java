package Yaremchuk.https.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Data
@Table("exercises")
public class Exercise {
    @Id
    private Long id;
    private LocalDate exerciseDate;
    @MappedCollection(idColumn = "ID")
    private Subject subject;
    @MappedCollection(idColumn = "ID")
    private Costumer costumer;
    @MappedCollection(idColumn = "ID")
    private Teacher teacher;

    @PersistenceCreator
    public Exercise(Long id, LocalDate exerciseDate, Subject subject, Costumer costumer, Teacher teacher) {
        this.id = id;
        this.exerciseDate = exerciseDate;
        this.subject = subject;
        this.costumer = costumer;
        this.teacher = teacher;
    }
}
