package Yaremchuk.https.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("subjects")
public class Subject {
    @Id
    private Long id;
    private String name;
    private String description;

    @PersistenceCreator
    public Subject(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
