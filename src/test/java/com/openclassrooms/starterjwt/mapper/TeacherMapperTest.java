package com.openclassrooms.starterjwt.mapper;

import com.openclassrooms.starterjwt.dto.TeacherDto;
import com.openclassrooms.starterjwt.models.Teacher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TeacherMapperTest {

    @InjectMocks
    private TeacherMapper teacherMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testToEntity() {
        // Crée un objet TeacherDto factice
        TeacherDto teacherDto = new TeacherDto();
        teacherDto.setFirstName("Nicolas");
        teacherDto.setLastName("Ruiz");

        // Utilise le mapper pour convertir TeacherDto en Teacher
        Teacher teacher = teacherMapper.toEntity(teacherDto);

        // Vérifie que les propriétés de l'entité Teacher correspondent aux données du DTO
        assertEquals("Nicolas", teacher.getFirstName());
        assertEquals("Ruiz", teacher.getLastName());
    }

    @Test
    public void testToDto() {
        // Crée un objet Teacher factice
        Teacher teacher = new Teacher();
        teacher.setFirstName("Nicolas");
        teacher.setLastName("Ruiz");
        teacher.setCreatedAt(LocalDateTime.now());

        // Utilise le mapper pour convertir Teacher en TeacherDto
        TeacherDto teacherDto = teacherMapper.toDto(teacher);

        // Vérifie que les propriétés du DTO correspondent aux données de l'entité Teacher
        assertEquals("Nicolas", teacherDto.getFirstName());
        assertEquals("Ruiz", teacherDto.getLastName());
    }
}
