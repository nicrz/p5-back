package com.openclassrooms.starterjwt.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TeacherTest {

    private Teacher teacher;

    @BeforeEach
    public void setUp() {
        teacher = new Teacher();
    }

    @Test
    public void testIdGetterAndSetter() {
        Long id = 1L;
        teacher.setId(id);
        assertEquals(id, teacher.getId());
    }

    @Test
    public void testFirstNameGetterAndSetter() {
        String firstName = "Nicolas";
        teacher.setFirstName(firstName);
        assertEquals(firstName, teacher.getFirstName());
    }

    @Test
    public void testLastNameGetterAndSetter() {
        String lastName = "Ruiz";
        teacher.setLastName(lastName);
        assertEquals(lastName, teacher.getLastName());
    }

    @Test
    public void testCreatedAtGetterAndSetter() {
        assertNull(teacher.getCreatedAt());

        LocalDateTime createdAt = LocalDateTime.now();
        teacher.setCreatedAt(createdAt);

        assertNotNull(teacher.getCreatedAt());
        assertEquals(createdAt, teacher.getCreatedAt());
    }

    @Test
    public void testUpdatedAtGetterAndSetter() {
        assertNull(teacher.getUpdatedAt());

        LocalDateTime updatedAt = LocalDateTime.now();
        teacher.setUpdatedAt(updatedAt);

        assertNotNull(teacher.getUpdatedAt());
        assertEquals(updatedAt, teacher.getUpdatedAt());
    }
}
