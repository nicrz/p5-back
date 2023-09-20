package com.openclassrooms.starterjwt.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.openclassrooms.starterjwt.models.Session;
import com.openclassrooms.starterjwt.models.User;
import com.openclassrooms.starterjwt.repository.SessionRepository;
import com.openclassrooms.starterjwt.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class SessionServiceTest {

    @InjectMocks
    private SessionService sessionService;

    @Mock
    private SessionRepository sessionRepository;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreate(){
        Session session = new Session();
        when(sessionRepository.save(session)).thenReturn(session);
        sessionService.create(session);
        verify(sessionRepository.save(session));
    }

    @Test
    public void testDelete(){
        Long id = 1L;
        sessionService.delete(id);
        verify(sessionRepository.deleteById(id));
    }

    @Test
    public void testFindAll(){
        List<Session> sessions = Arrays.asList(new Session(), new Session());
        when(sessionRepository.findAll()).thenReturn(sessions);
        List<Session> result = sessionService.findAll();
        assertEquals(sessions, result);
    }

    @Test
    public void testGetById(){
        Long id = 1L;
        Session session = new Session();
        when(sessionRepository.findById(id)).thenReturn(Optional.of(session));
        Session result = sessionService.getById(id);
        assertEquals(session, result);
    }

    @Test
    public void testUpdate(){
        Long id = 1L;
        Session session = new Session();
        when(sessionRepository.save(session)).thenReturn(session);
        sessionService.update(id, session);
        verify(sessionRepository.save(session));
    }

    @Test
    public void testParticipate() {
        User user = new User();
        user.setId(1L);

        Session session = new Session();
        session.setId(1L);

        when(sessionRepository.findById(1L)).thenReturn(Optional.of(session));
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        sessionService.participate(1L, 1L);

        verify(sessionRepository, times(1)).save(session);
    }

    @Test
    public void testNoLongerParticipate() {

        User user = new User();
        user.setId(1L);

        Session session = new Session();
        session.setId(1L);
        List<User> participants = new ArrayList<>();
        participants.add(user);
        session.setUsers(participants);

        when(sessionRepository.findById(1L)).thenReturn(Optional.of(session));

        sessionService.noLongerParticipate(1L, 1L);

        verify(sessionRepository, times(1)).save(session);
    }
    
}
