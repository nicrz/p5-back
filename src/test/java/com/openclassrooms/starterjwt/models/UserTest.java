package com.openclassrooms.starterjwt.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class UserTest {

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();
    }

    @Test
    public void testIdGetterAndSetter() {
        Long id = 1L;
        user.setId(id);
        assertEquals(id, user.getId());
    }

    @Test
    public void testEmailGetterAndSetter() {
        String email = "test@gmail.com";
        user.setEmail(email);
        assertEquals(email, user.getEmail());
    }

    @Test
    public void testLastNameGetterAndSetter() {
        String lastName = "Ruiz";
        user.setLastName(lastName);
        assertEquals(lastName, user.getLastName());
    }

    @Test
    public void testFirstNameGetterAndSetter() {
        String firstName = "Nicolas";
        user.setFirstName(firstName);
        assertEquals(firstName, user.getFirstName());
    }

    @Test
    public void testPasswordGetterAndSetter() {
        String password = "Test64170";
        user.setPassword(password);
        assertEquals(password, user.getPassword());
    }

    @Test
    public void testAdminGetterAndSetter() {
        boolean isAdmin = true;
        user.setAdmin(isAdmin);
        assertEquals(isAdmin, user.isAdmin());
    }

    @Test
    public void testCreatedAtGetterAndSetter() {
        assertNull(user.getCreatedAt());

        LocalDateTime createdAt = LocalDateTime.now();
        user.setCreatedAt(createdAt);

        assertNotNull(user.getCreatedAt());
        assertEquals(createdAt, user.getCreatedAt());
    }

    @Test
    public void testUpdatedAtGetterAndSetter() {
        assertNull(user.getUpdatedAt());

        LocalDateTime updatedAt = LocalDateTime.now();
        user.setUpdatedAt(updatedAt);

        assertNotNull(user.getUpdatedAt());
        assertEquals(updatedAt, user.getUpdatedAt());
    }
}
