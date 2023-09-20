package com.openclassrooms.starterjwt.mapper;

import com.openclassrooms.starterjwt.dto.SessionDto;
import com.openclassrooms.starterjwt.models.Session;
import com.openclassrooms.starterjwt.models.User;
import com.openclassrooms.starterjwt.services.TeacherService;
import com.openclassrooms.starterjwt.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class SessionMapperTest {

    @InjectMocks
    private SessionMapper sessionMapper;

    @Mock
    private TeacherService teacherService;

    @Mock
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testToEntity() {
        SessionDto sessionDto = new SessionDto();
        sessionDto.setDescription("Description de test");
        sessionDto.setTeacher_id(1L);
        sessionDto.setUsers(Arrays.asList(1L, 2L));

        // Crée des objets User factices
        User user1 = new User();
        user1.setId(1L);
        user1.setFirstName("Nicolas");
        user1.setLastName("Ruiz");

        User user2 = new User();
        user2.setId(2L);
        user2.setFirstName("Eric");
        user2.setLastName("Legba");

        // Configure le comportement simulé de userService.findById() pour renvoyer les utilisateurs factices
        when(userService.findById(1L)).thenReturn(user1);
        when(userService.findById(2L)).thenReturn(user2);

        // Crée un objet Session simulé en utilisant le mapper
        Session session = sessionMapper.toEntity(sessionDto);

        // Vérifie que les propriétés de la session correspondent aux données du DTO
        assertEquals("Description de test", session.getDescription());
        assertEquals(1L, session.getTeacher().getId());

        // Vérifie que la liste des utilisateurs dans la session contient les utilisateurs factices
        List<User> users = session.getUsers();
        assertEquals(2, users.size());
        assertEquals("Nicolas", users.get(0).getFirstName());
        assertEquals("Eric", users.get(1).getFirstName());
    }

    @Test
    public void testToDto() {
        // Crée un objet Session factice
        Session session = new Session();
        session.setDescription("Description de test");

        // Crée des objets User factices et les associe à la session
        User user1 = new User();
        user1.setId(1L);
        user1.setFirstName("Nicolas");
        user1.setLastName("Ruiz");

        User user2 = new User();
        user2.setId(2L);
        user2.setFirstName("Eric");
        user2.setLastName("Legba");

        session.setUsers(Arrays.asList(user1, user2));

        // Crée un objet SessionDto simulé en utilisant le mapper
        SessionDto sessionDto = sessionMapper.toDto(session);

        // Vérifie que les propriétés du DTO correspondent aux données de la session
        assertEquals("Description de test", sessionDto.getDescription());
        assertEquals(1L, sessionDto.getUsers().get(0));
        assertEquals(2L, sessionDto.getUsers().get(1));
    }
}

