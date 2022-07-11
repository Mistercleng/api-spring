package com.company.apispring.services.impl;

import com.company.apispring.domain.UserCompany;
import com.company.apispring.domain.dto.UserDTO;
import com.company.apispring.domain.repositories.UserRepository;
import com.company.apispring.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

class UserServiceImplTest {

    public static final Integer ID = 1;
    public static final String NAME     = "Mistercleng";
    public static final String EMAIL    = "mrcleng@gmail.com";
    public static final String PASSWORD = "123";
    @InjectMocks
    private UserServiceImpl serviceMock;
    @Mock
    private UserRepository repositoryMock;
    @Mock
    private ModelMapper mapper;

    private UserCompany user;
    private UserDTO userDTO;
    private Optional<UserCompany> optionalUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void whenfindByIdReturnAnUserInstance() {
        when(repositoryMock.findById(anyInt())).thenReturn(optionalUser);
        UserCompany response = serviceMock.findById(ID);
        assertNotNull(response);
        assertEquals(UserCompany.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getNome());
        assertEquals(EMAIL, response.getEmail());
    }

    @Test
    void whenFindByIdReturnAnObjectNotFoundException() {
        when(repositoryMock.findById(anyInt())).thenThrow(new ObjectNotFoundException("Objeto não encontrado"));

        try{
            serviceMock.findById(ID);
        }catch (Exception ex) {
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals("Objeto não encontrado",ex.getMessage());
        }
    }

    @Test
    void findAll() {
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    private void startUser() {
        user = new UserCompany(ID, NAME, EMAIL, PASSWORD);
        userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);
        optionalUser = Optional.of(new UserCompany(ID, NAME, EMAIL, PASSWORD));
    }
}