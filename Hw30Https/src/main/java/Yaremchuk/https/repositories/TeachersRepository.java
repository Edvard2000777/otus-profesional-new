package Yaremchuk.https.repositories;

import Yaremchuk.https.entities.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeachersRepository extends ListCrudRepository<Teacher, Long> {
}
