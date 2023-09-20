package com.openclassrooms.starterjwt.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.mockito.Mockito;
import com.openclassrooms.starterjwt.security.jwt.JwtUtils;
import com.openclassrooms.starterjwt.security.services.UserDetailsImpl;
import com.openclassrooms.starterjwt.payload.request.LoginRequest;
import com.openclassrooms.starterjwt.payload.request.SignupRequest;
import com.openclassrooms.starterjwt.payload.response.JwtResponse;
import com.openclassrooms.starterjwt.payload.response.MessageResponse;
import com.openclassrooms.starterjwt.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class AuthControllerTest {

    @Autowired
    private UserRepository userRepository;
    private AuthController authController;
    private PasswordEncoder passwordEncoder;
    private JwtUtils jwtUtils;
    private AuthenticationManager authenticationManager;

    @Test
    public void testAuthenticateUser() {
        // Crée un objet LoginRequest factice pour la simulation de la demande
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("ruiz.nico64@gmail.com");
        loginRequest.setPassword("Test64170");

        // Crée un objet UserDetailsImpl factice pour la simulation de l'utilisateur authentifié
        UserDetailsImpl userDetails = new UserDetailsImpl(1L, "ruiz.nico64@gmail.com", "Nicolas", "Ruiz", false);

        // Configure la simulation de l'AuthenticationManager pour renvoyer une Authentication réussie
        Authentication authentication = Mockito.mock(Authentication.class);
        when(authenticationManager.authenticate(Mockito.any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);

        // Configure la simulation de l'objet UserDetails et du jeton JWT
        when(authentication.getPrincipal()).thenReturn(userDetails);
        when(jwtUtils.generateJwtToken(authentication)).thenReturn("mockedJwtToken");

        // Appele la méthode authenticateUser() du contrôleur avec la demande factice
        ResponseEntity<?> responseEntity = authController.authenticateUser(loginRequest);

        // Vérifie que le statut de la réponse est OK (200)
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Vérifie que la réponse contient le bon objet JwtResponse avec les informations de l'utilisateur
        JwtResponse jwtResponse = (JwtResponse) responseEntity.getBody();
        assertEquals("mockedJwtToken", jwtResponse.getToken());
        assertEquals(1L, jwtResponse.getId());
        assertEquals("ruiz.nico64@gmail.com", jwtResponse.getUsername());
        assertEquals("Nicolas", jwtResponse.getFirstName());
        assertEquals("Ruiz", jwtResponse.getLastName());
        assertEquals(false, jwtResponse.getAdmin());
    }

    @Test
    public void testRegisterUser() {
        // Crée un objet SignupRequest factice pour la simulation de la demande
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setEmail("test@gmail.com");
        signupRequest.setFirstName("Test");
        signupRequest.setLastName("Ruiz");
        signupRequest.setPassword("Test64170");

        // Configure la simulation du UserRepository pour renvoyer false (l'utilisateur n'existe pas)
        when(userRepository.existsByEmail(signupRequest.getEmail())).thenReturn(false);

        // Appele la méthode registerUser() du contrôleur avec la demande factice
        ResponseEntity<?> responseEntity = authController.registerUser(signupRequest);

        // Vérifie que le statut de la réponse est OK (200)
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Vérifie que la réponse contient le bon objet MessageResponse
        MessageResponse messageResponse = (MessageResponse) responseEntity.getBody();
        assertEquals("User registered successfully!", messageResponse.getMessage());
    }
}

