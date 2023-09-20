package com.openclassrooms.starterjwt.mapper;

import com.openclassrooms.starterjwt.dto.UserDto;
import com.openclassrooms.starterjwt.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserMapperTest {

    @InjectMocks
    private UserMapper userMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testToEntity() {
        // Crée un objet UserDto factice
        UserDto userDto = new UserDto();
        userDto.setEmail("test@gmail.com");
        userDto.setFirstName("Nicolas");
        userDto.setLastName("Ruiz");
        userDto.setAdmin(true);

        // Utilise le mapper pour convertir UserDto en User
        User user = userMapper.toEntity(userDto);

        // Vérifie que les propriétés de l'entité User correspondent aux données du DTO
        assertEquals("test@gmail.com", user.getEmail());
        assertEquals("Nicolas", user.getFirstName());
        assertEquals("Ruiz", user.getLastName());
        assertEquals(true, user.isAdmin());
    }

    @Test
    public void testToDto() {
        // Crée un objet User factice
        User user = new User();
        user.setEmail("test@gmail.com");
        user.setFirstName("Nicolas");
        user.setLastName("Ruiz");
        user.setAdmin(false);

        // Utilise le mapper pour convertir User en UserDto
        UserDto userDto = userMapper.toDto(user);

        // Vérifie que les propriétés du DTO correspondent aux données de l'entité User
        assertEquals("test@gmail.com", userDto.getEmail());
        assertEquals("Nicolas", userDto.getFirstName());
        assertEquals("Ruiz", userDto.getLastName());
        assertEquals(false, userDto.isAdmin());
    }
}
