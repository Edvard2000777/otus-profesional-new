package Yaremchuk.https.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("teachers")
public class Teacher {
    @Id
    private Long id;
    private String firstName;
    private String secondName;

    @PersistenceCreator
    public Teacher(Long id, String firstName, String secondName) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
    }
}
