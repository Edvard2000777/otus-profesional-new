package Yaremchuk.https.repositories;


import Yaremchuk.https.entities.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectsRepository extends ListCrudRepository<Subject, Long> {
}
