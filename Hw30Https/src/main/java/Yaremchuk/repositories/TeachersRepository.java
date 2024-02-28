package Yaremchuk.repositories;

import Yaremchuk.https.entities.Teacher;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeachersRepository extends ListCrudRepository<Teacher, Long> {
}
