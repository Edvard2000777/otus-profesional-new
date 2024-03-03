package Yaremchuk.https.entities;


import lombok.Data;
import org.springframework.data.annotation.AccessType;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AccessType(AccessType.Type.PROPERTY)
@Table("costumers")
public class Costumer {
    @Id
    private Long id;
    private String firstName;
    private String secondName;

    @PersistenceCreator
    public Costumer(Long id, String firstName, String secondName) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
    }
}
