package Yaremchuk.https.sevices;



import Yaremchuk.https.entities.Teacher;

import java.util.Optional;

public interface TeachersService {
    Optional<Teacher> findById(Long id);
}
