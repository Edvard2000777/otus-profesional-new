package Yaremchuk.https.controllers;

import Yaremchuk.https.entities.Teacher;
import Yaremchuk.https.sevices.TeachersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/teachers")
public class TeachersController {
    private final TeachersService teachersService;

    @Autowired
    public TeachersController(TeachersService teachersService) {
        this.teachersService = teachersService;
    }

    @GetMapping("/{id}")
    public Teacher findById(@PathVariable Long id) {
        return teachersService.findById(id).get();
    }
}
