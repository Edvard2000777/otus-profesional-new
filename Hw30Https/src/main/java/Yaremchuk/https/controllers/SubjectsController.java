package Yaremchuk.https.controllers;

import Yaremchuk.https.dtos.CreateOrUpdateSubjectDtoRq;
import Yaremchuk.https.entities.Subject;
import Yaremchuk.https.sevices.SubjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/v1/subjects")
public class SubjectsController {
    private final SubjectsService subjectsService;

    @Autowired
    public SubjectsController(SubjectsService subjectsService) {
        this.subjectsService = subjectsService;
    }

    @GetMapping("/{id}")
    public Subject findById(@PathVariable Long id) {
        return subjectsService.findById(id).get();
    }
    @GetMapping
    public List<Subject> findAll() {
        return subjectsService.findAll();
    }
    @PostMapping
    public void createNewSubject(@RequestBody CreateOrUpdateSubjectDtoRq createOrUpdateSubjectDtoRq) throws Exception {
        subjectsService.createNewSubject(createOrUpdateSubjectDtoRq);
    }
}
