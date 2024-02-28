package Yaremchuk.https.sevices.impl;

import Yaremchuk.https.dtos.ExerciseDtoRq;
import Yaremchuk.https.sevices.ExercisesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import Yaremchuk.repositories.ExercisesRepository;

import java.util.List;

@Service
public class ExercisesServiceImpl implements ExercisesService {
    private final ExercisesRepository exercisesRepository;

    @Autowired
    public ExercisesServiceImpl(ExercisesRepository exercisesRepository, RestTemplateBuilder templateBuilder) {
        this.exercisesRepository = exercisesRepository;
    }

    @Override
    public List<ExerciseDtoRq> findAllBySubjectId(Long id) {
        return exercisesRepository.findAllBySubjectId(id);
    }

    @Override
    public List<ExerciseDtoRq> findAllByCostumerId(Long id) {
        return exercisesRepository.findAllByCostumerId(id);
    }

    @Override
    public List<ExerciseDtoRq> findAllByTeacherId(Long id) {
        return exercisesRepository.findAllByTeacherId(id);
    }

}
