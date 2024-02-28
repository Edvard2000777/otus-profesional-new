package Yaremchuk.repositories;


import Yaremchuk.https.entities.Subject;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectsRepository extends ListCrudRepository<Subject, Long> {
}
