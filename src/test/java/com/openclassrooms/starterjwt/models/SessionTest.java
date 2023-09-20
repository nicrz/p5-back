package com.openclassrooms.starterjwt.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class SessionTest {

    private Session session;

    @BeforeEach
    public void setUp() {
        session = new Session();
    }

    @Test
    public void testIdGetterAndSetter() {
        Long id = 1L;
        session.setId(id);
        assertEquals(id, session.getId());
    }

    @Test
    public void testNameGetterAndSetter() {
        String name = "Session de test";
        session.setName(name);
        assertEquals(name, session.getName());
    }

    @Test
    public void testDateGetterAndSetter() {
        Date date = new Date();
        session.setDate(date);
        assertEquals(date, session.getDate());
    }

    @Test
    public void testDescriptionGetterAndSetter() {
        String description = "Description de test";
        session.setDescription(description);
        assertEquals(description, session.getDescription());
    }

    @Test
    public void testTeacherGetterAndSetter() {
        Teacher teacher = new Teacher();
        teacher.setId(1L);
        teacher.setFirstName("Nicolas");
        teacher.setLastName("Ruiz");

        session.setTeacher(teacher);

        assertNotNull(session.getTeacher());
        assertEquals(1L, session.getTeacher().getId());
        assertEquals("Nicolas", session.getTeacher().getFirstName());
        assertEquals("Ruiz", session.getTeacher().getLastName());
    }

    @Test
    public void testUsersGetterAndSetter() {
        User user1 = new User();
        user1.setId(1L);
        user1.setFirstName("Nicolas");
        user1.setLastName("Ruiz");

        User user2 = new User();
        user2.setId(2L);
        user2.setFirstName("Eric");
        user2.setLastName("Legba");

        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);

        session.setUsers(users);

        assertNotNull(session.getUsers());
        assertEquals(2, session.getUsers().size());
        assertEquals(1L, session.getUsers().get(0).getId());
        assertEquals("Nicolas", session.getUsers().get(0).getFirstName());
        assertEquals("Ruiz", session.getUsers().get(0).getLastName());
        assertEquals(2L, session.getUsers().get(1).getId());
        assertEquals("Eric", session.getUsers().get(1).getFirstName());
        assertEquals("Legba", session.getUsers().get(1).getLastName());
    }

    @Test
    public void testCreatedAtGetterAndSetter() {
        assertNull(session.getCreatedAt());

        LocalDateTime createdAt = LocalDateTime.now();
        session.setCreatedAt(createdAt);

        assertNotNull(session.getCreatedAt());
        assertEquals(createdAt, session.getCreatedAt());
    }

    @Test
    public void testUpdatedAtGetterAndSetter() {
        assertNull(session.getUpdatedAt());

        LocalDateTime updatedAt = LocalDateTime.now();
        session.setUpdatedAt(updatedAt);

        assertNotNull(session.getUpdatedAt());
        assertEquals(updatedAt, session.getUpdatedAt());
    }
}
