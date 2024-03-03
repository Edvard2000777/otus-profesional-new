package Yaremchuk.https.sevices.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Yaremchuk.https.entities.Teacher;
import Yaremchuk.https.sevices.TeachersService;

import Yaremchuk.https.repositories.TeachersRepository;

import java.util.Optional;

@Service
public class TeacherServiceImpl implements TeachersService {
    private final TeachersRepository teachersRepository;

    @Autowired
    public TeacherServiceImpl(TeachersRepository teachersRepository) {
        this.teachersRepository = teachersRepository;
    }

    @Override
    public Optional<Teacher> findById(Long id) {
        return teachersRepository.findById(id);
    }
}
