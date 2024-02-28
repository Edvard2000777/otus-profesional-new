package Yaremchuk.repositories;

import Yaremchuk.https.dtos.ExerciseDtoRq;
import Yaremchuk.https.entities.Exercise;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
@Repository
public interface ExercisesRepository extends ListCrudRepository<Exercise, Long> {
    @Query("select exercise_date, subject_name, subject_description, " +
            "costumer_first_name, costumer_second_name, teacher_first_name, teacher_second_name " +
            "from v_exercises where subject_id = :id")
    List<ExerciseDtoRq> findAllBySubjectId(Long id);
    @Query("select exercise_date, subject_name, subject_description, " +
            "costumer_first_name, costumer_second_name, teacher_first_name, teacher_second_name " +
            "from v_exercises where costumer_id = :id")
    List<ExerciseDtoRq> findAllByCostumerId(Long id);
    @Query("select exercise_date, subject_name, subject_description, " +
            "costumer_first_name, costumer_second_name, teacher_first_name, teacher_second_name " +
            "from v_exercises where teacher_id = :id")
    List<ExerciseDtoRq> findAllByTeacherId(Long id);
}
