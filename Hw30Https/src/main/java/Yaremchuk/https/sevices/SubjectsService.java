package Yaremchuk.https.sevices;

import Yaremchuk.https.entities.Subject;
import Yaremchuk.https.dtos.CreateOrUpdateSubjectDtoRq;
import org.springframework.security.access.prepost.PreAuthorize;


import java.util.List;
import java.util.Optional;

public interface SubjectsService {
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    Optional<Subject> findById(Long id);
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    List<Subject> findAll();
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    void createNewSubject(CreateOrUpdateSubjectDtoRq createOrUpdateSubjectDtoRq) throws Exception;
}
