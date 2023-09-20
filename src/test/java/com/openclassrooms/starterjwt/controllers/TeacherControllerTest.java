package com.openclassrooms.starterjwt.controllers;

import com.openclassrooms.starterjwt.controllers.TeacherController;
import com.openclassrooms.starterjwt.models.Teacher;
import com.openclassrooms.starterjwt.services.TeacherService;
import com.openclassrooms.starterjwt.mapper.TeacherMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class TeacherControllerTest {

    private TeacherController teacherController;
    private TeacherService teacherService;
    private TeacherMapper teacherMapper;

    @BeforeEach
    public void setUp() {
        teacherService = Mockito.mock(TeacherService.class);
        teacherMapper = Mockito.mock(TeacherMapper.class);
        teacherController = new TeacherController(teacherService, teacherMapper);
    }

    @Test
    public void testFindById() {
        // Crée un objet Teacher factice
        Teacher expectedTeacher = new Teacher();
        expectedTeacher.setId(1L);
        expectedTeacher.setFirstName("Nicolas");
        expectedTeacher.setLastName("Ruiz");

        // Enveloppe l'objet Teacher dans Optional
        Optional<Teacher> optionalTeacher = Optional.of(expectedTeacher);

        // Configure le comportement simulé de teacherService.findById() en renvoyant l'Optional factice
        Mockito.when(teacherService.findById(1L)).thenReturn(expectedTeacher);

        // Appele la méthode findById() du contrôleur
        ResponseEntity<?> responseEntity = teacherController.findById("1");

        // Vérifie que le statut de la réponse est OK (200)
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Vérifie que le corps de la réponse correspond à l'objet Teacher attendu
        assertEquals(optionalTeacher.get(), responseEntity.getBody());
    }

    @Test
    public void testFindAll() {
        // Crée un tableau factice d'enseignants
        Teacher[] expectedTeachersArray = {
            new Teacher(1L, "Nicolas", "Ruiz"),
            new Teacher(2L, "Eric", "Legba")
        };

        // Convertit le tableau en une liste
        List<Teacher> expectedTeachers = Arrays.asList(expectedTeachersArray);        

        // Configure le comportement simulé de teacherService.findAll() pour renvoyer la liste factice
        Mockito.when(teacherService.findAll()).thenReturn(expectedTeachers);

        // Appelle la méthode findAll() du contrôleur
        ResponseEntity<?> responseEntity = teacherController.findAll();

        // Vérifie que le statut de la réponse est OK
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Vérifie que le corps de la réponse correspond à la liste attendue d'enseignants
        assertEquals(expectedTeachers, responseEntity.getBody());
    }
}
