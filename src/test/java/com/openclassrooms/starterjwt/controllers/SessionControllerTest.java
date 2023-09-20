package com.openclassrooms.starterjwt.controllers;

import com.openclassrooms.starterjwt.dto.SessionDto;
import com.openclassrooms.starterjwt.mapper.SessionMapper;
import com.openclassrooms.starterjwt.models.Session;
import com.openclassrooms.starterjwt.services.SessionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class SessionControllerTest {

    @InjectMocks
    private SessionController sessionController;

    @Mock
    private SessionService sessionService;

    @Mock
    private SessionMapper sessionMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindSessionById() {
        // Crée un ID factice à rechercher
        String sessionId = "1L";

        // Crée une session factice
        Session expectedSession = new Session();
        expectedSession.setId(1L);
        expectedSession.setName("Session de test");
        expectedSession.setDate(new Date()); 
        expectedSession.setDescription("Description test");

        // Configure le comportement simulé de sessionService.getById()
        when(sessionService.getById(Long.valueOf(sessionId))).thenReturn(expectedSession);

        // Crée un DTO de session factice que vous attendez de retourner
        SessionDto expectedSessionDto = new SessionDto();
        expectedSessionDto.setId(1L);
        expectedSessionDto.setName("Session DTO test");
        expectedSessionDto.setDate(new Date());
        expectedSessionDto.setDescription("Description session dto test");
        expectedSessionDto.setTeacher_id(101L);


        // Configure le comportement simulé de sessionMapper.toDto()
        when(sessionMapper.toDto(expectedSession)).thenReturn(expectedSessionDto);

        // Appele la méthode findById() du contrôleur en utilisant l'ID factice
        ResponseEntity<?> responseEntity = sessionController.findById(sessionId);

        // Vérifie que le statut de la réponse est OK (200)
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Vérifie que le corps de la réponse correspond au DTO de session attendu
        assertEquals(expectedSessionDto, responseEntity.getBody());
    }

    @Test
    public void testFindAllSessions() {
        // Crée une liste factice de sessions
        List<Session> expectedSessions = new ArrayList<>();
        Session expectedSession1 = new Session();
        expectedSession1.setId(1L);
        expectedSession1.setName("Session de test 1");
        expectedSession1.setDate(new Date()); 
        expectedSession1.setDescription("Description test 1");
        Session expectedSession2 = new Session();
        expectedSession2.setId(2L);
        expectedSession2.setName("Session de test 2");
        expectedSession2.setDate(new Date()); 
        expectedSession2.setDescription("Description test 2");
        expectedSessions.add(expectedSession1);
        expectedSessions.add(expectedSession2);

        // Configure le comportement simulé de sessionService.findAll()
        when(sessionService.findAll()).thenReturn(expectedSessions);

        // Crée une liste factice de DTO de session
        List<SessionDto> expectedSessionDtos = new ArrayList<>();
        SessionDto expectedSessionDto1 = new SessionDto();
        expectedSessionDto1.setId(1L);
        expectedSessionDto1.setName("Session DTO test 1");
        expectedSessionDto1.setDate(new Date());
        expectedSessionDto1.setDescription("Description session dto test 1");
        expectedSessionDto1.setTeacher_id(101L);
        SessionDto expectedSessionDto2 = new SessionDto();
        expectedSessionDto2.setId(2L);
        expectedSessionDto2.setName("Session DTO test 2");
        expectedSessionDto2.setDate(new Date());
        expectedSessionDto2.setDescription("Description session dto test 2");
        expectedSessionDto2.setTeacher_id(101L);
        expectedSessionDtos.add(expectedSessionDto1);
        expectedSessionDtos.add(expectedSessionDto2);

        // Configure le comportement simulé de sessionMapper.toDto()
        when(sessionMapper.toDto(expectedSessions)).thenReturn(expectedSessionDtos);

        // Appele la méthode findAll() du contrôleur
        ResponseEntity<?> responseEntity = sessionController.findAll();

        // Vérifie que le statut de la réponse est OK (200)
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Vérifie que le corps de la réponse correspond à la liste attendue de DTO de session
        assertEquals(expectedSessionDtos, responseEntity.getBody());
    }

    @Test
    public void testCreateSession() {
        // Crée un objet SessionDto factice
        SessionDto sessionDto = new SessionDto();
        sessionDto.setId(1L);
        sessionDto.setName("Session DTO test");
        sessionDto.setDate(new Date());
        sessionDto.setDescription("Description session dto test");
        sessionDto.setTeacher_id(101L);    
        // Crée une session factice
        Session session = new Session();
        session.setId(1L);
        session.setName("Session de test");
        session.setDate(new Date()); 
        session.setDescription("Description test");    
        
        // Configure le comportement simulé de sessionService.create()
        when(sessionService.create(Mockito.any(Session.class))).thenReturn(session);
    
        // Appele la méthode create() du contrôleur avec le SessionDto factice
        ResponseEntity<?> responseEntity = sessionController.create(sessionDto);
    
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    
        // Vérifie que le corps de la réponse correspond à la session créée
        SessionDto responseSessionDto = (SessionDto) responseEntity.getBody();
        assertEquals(session.getId(), responseSessionDto.getId());
        assertEquals(session.getName(), responseSessionDto.getName());
        assertEquals(session.getDate(), responseSessionDto.getDate());
        assertEquals(session.getDescription(), responseSessionDto.getDescription());
    
        // Vérifie que la méthode sessionService.create() a été appelée avec le bon argument
        Mockito.verify(sessionService).create(Mockito.any(Session.class));
    }

    @Test
    public void testUpdateSession() {

        String sessionId = "1L";
    
        SessionDto sessionDto = new SessionDto();
        sessionDto.setId(3L);
        sessionDto.setName("Session DTO test à modif");
        sessionDto.setDate(new Date());
        sessionDto.setDescription("Description session dto test à modif");
        sessionDto.setTeacher_id(101L);  

        Session updatedSession = new Session();
        updatedSession.setId(3L);
        updatedSession.setName("Session de test à modif");
        updatedSession.setDate(new Date()); 
        updatedSession.setDescription("Description test à modif"); 
    
        // Configure le comportement simulé de sessionService.update()
        when(sessionService.update(Mockito.anyLong(), Mockito.any(Session.class))).thenReturn(updatedSession);
    
        // Appele la méthode update() du contrôleur avec l'ID et le SessionDto factices
        ResponseEntity<?> responseEntity = sessionController.update(sessionId, sessionDto);
    
        // Vérifie que le statut de la réponse est OK (200)
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    
        // Vérifie que le corps de la réponse correspond à la session mise à jour
        SessionDto responseSessionDto = (SessionDto) responseEntity.getBody();
        assertEquals(updatedSession.getId(), responseSessionDto.getId());
        assertEquals(updatedSession.getName(), responseSessionDto.getName());
        assertEquals(updatedSession.getDate(), responseSessionDto.getDate());
        assertEquals(updatedSession.getDescription(), responseSessionDto.getDescription());
    
        Mockito.verify(sessionService).update(Mockito.eq(Long.parseLong(sessionId)), Mockito.any(Session.class));
    }

    @Test
    public void testSaveSession() {

        String sessionId = "1L"; 

        // Crée une session factice à supp
        Session sessionToDelete = new Session();
        sessionToDelete.setId(4L);
        sessionToDelete.setName("Session de test à supp");
        sessionToDelete.setDate(new Date()); 
        sessionToDelete.setDescription("Description test à supp"); 

        // Appele la méthode save() du contrôleur avec l'ID factice
        ResponseEntity<?> responseEntity = sessionController.save(sessionId);

        // Vérifie que le statut de la réponse est OK (200) car la session a été trouvée et supprimée avec succès
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Vérifie que la méthode sessionService.getById() a été appelée avec le bon ID
        Mockito.verify(sessionService).getById(Mockito.eq(Long.parseLong(sessionId)));

        // Vérifiee que la méthode sessionService.delete() a été appelée avec le bon ID
        Mockito.verify(sessionService).delete(Mockito.eq(Long.parseLong(sessionId)));
    }

    @Test
    public void testParticipation() {

        String sessionId = "1L"; 
        String userId = "1"; 

        // Appele la méthode participate() du contrôleur avec les IDs factices
        ResponseEntity<?> responseEntity = sessionController.participate(sessionId, userId);

        // Vérifie que le statut de la réponse est OK (200) car la participation a été effectuée avec succès
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Vérifie que la méthode sessionService.participate() a été appelée avec les bons IDs
        Mockito.verify(sessionService).participate(Long.parseLong(sessionId), Long.parseLong(userId));
    }
}

