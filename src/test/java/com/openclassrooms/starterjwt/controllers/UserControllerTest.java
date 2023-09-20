package com.openclassrooms.starterjwt.controllers;

import com.openclassrooms.starterjwt.controllers.UserController;
import com.openclassrooms.starterjwt.models.User;
import com.openclassrooms.starterjwt.services.UserService;
import com.openclassrooms.starterjwt.mapper.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class UserControllerTest {

    private UserController userController;
    private UserService userService;
    private UserMapper userMapper;

    @BeforeEach
    public void setUp() {
        userService = Mockito.mock(UserService.class);
        userMapper = Mockito.mock(UserMapper.class);
        userController = new UserController(userService, userMapper);
    }

    @Test
    public void testFindById() {
        User expectedUser = new User();
        expectedUser.setId(1L);
        expectedUser.setFirstName("Nicolas");
        expectedUser.setLastName("Ruiz");

        // Enveloppe l'objet User dans Optional
        Optional<User> optionalUser = Optional.of(expectedUser);

        // Configure le comportement simulé de userService.findById() en renvoyant l'Optional factice
        Mockito.when(userService.findById(1L)).thenReturn(expectedUser);

        // Appele la méthode findById() du contrôleur
        ResponseEntity<?> responseEntity = userController.findById("1");

        // Vérifie que le statut de la réponse est OK (200)
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Vérifie que le corps de la réponse correspond à l'objet User attendu
        assertEquals(optionalUser.get(), responseEntity.getBody());
    }

    @Test
    public void testSave() {
        // Crée un objet User factice
        User expectedUser = new User();
        expectedUser.setId(1L);
        expectedUser.setEmail("test@gmail.com");
        expectedUser.setFirstName("Nicolas");
        expectedUser.setLastName("Ruiz");

        // Enveloppe l'objet User dans Optional
        Optional<User> optionalUser = Optional.of(expectedUser);

        // Configure le comportement simulé de userService.findById() en renvoyant l'Optional factice
        Mockito.when(userService.findById(1L)).thenReturn(expectedUser);

        // Configure le contexte de sécurité simulé pour avoir un utilisateur authentifié
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                expectedUser.getEmail(), "password", null);
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Appele la méthode save() du contrôleur
        ResponseEntity<?> responseEntity = userController.save("1");

        // Vérifie que le statut de la réponse est OK (200)
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}
