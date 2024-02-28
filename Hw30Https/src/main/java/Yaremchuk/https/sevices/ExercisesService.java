package Yaremchuk.https.sevices;

import Yaremchuk.https.dtos.ExerciseDtoRq;
import org.springframework.security.access.prepost.PreAuthorize;


import java.util.List;

public interface ExercisesService {
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    List<ExerciseDtoRq> findAllBySubjectId(Long id);

    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    List<ExerciseDtoRq> findAllByCostumerId(Long id);

    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    List<ExerciseDtoRq> findAllByTeacherId(Long id);
}
