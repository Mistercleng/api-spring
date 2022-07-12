package com.company.apispring.services.impl;

import com.company.apispring.domain.UserCompany;
import com.company.apispring.domain.dto.UserDTO;
import com.company.apispring.domain.repositories.UserRepository;
import com.company.apispring.services.exceptions.DataIntegratyViolationException;
import com.company.apispring.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
@SpringBootTest
class UserServiceImplTest {

    public static final Integer ID = 1;
    public static final String NAME     = "Mistercleng";
    public static final String EMAIL    = "mrcleng@gmail.com";
    public static final String PASSWORD = "123";
    public static final int INDEX = 0;
    public static final String EMAIL_JÁ_CADASTRADO_NO_SISTEMA = "Email já cadastrado no sistema";
    public static final String OBJETO_NÃO_ENCONTRADO = "Objeto não encontrado";
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
        when(repositoryMock.findById(anyInt())).thenThrow(new ObjectNotFoundException(OBJETO_NÃO_ENCONTRADO));

        try{
            serviceMock.findById(ID);
        }catch (Exception ex) {
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals(OBJETO_NÃO_ENCONTRADO,ex.getMessage());
        }
    }

    @Test
    void whenFindAllThenReturnAListOfUsers() {
        when(repositoryMock.findAll()).thenReturn(List.of(user));
        List<UserCompany> response = serviceMock.findAll();

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(UserCompany.class, response.get(INDEX).getClass());
        assertEquals(ID, response.get(INDEX).getId());
        assertEquals(NAME, response.get(INDEX).getNome());
        assertEquals(EMAIL, response.get(INDEX).getEmail());
        assertEquals(PASSWORD, response.get(INDEX).getPassword());

    }

    @Test
    void whenCreateThenReturnSuccess() {
        when(repositoryMock.save(any())).thenReturn(user);

        UserCompany response = serviceMock.create(userDTO);

        assertNotNull(response);
        assertEquals(UserCompany.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getNome());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(PASSWORD, response.getPassword());
    }

    @Test
    void whenCreateThenReturnDataIntegrityViolationException() {
        when(repositoryMock.findByEmail(anyString())).thenReturn(optionalUser);

        try{
            optionalUser.get().setId(2);
            serviceMock.create(userDTO);
        }catch (Exception ex) {
            assertEquals(DataIntegratyViolationException.class,ex.getClass());
            assertEquals(EMAIL_JÁ_CADASTRADO_NO_SISTEMA,ex.getMessage());
        }

    }

    @Test
    void whenUpdatyeThenReturnSuccess() {
        when(repositoryMock.save(any())).thenReturn(user);

        UserCompany response = serviceMock.update(userDTO);

        assertNotNull(response);
        assertEquals(UserCompany.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getNome());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(PASSWORD, response.getPassword());
    }

    @Test
    void whenUpdateThenReturnDataIntegrityViolationException() {
        when(repositoryMock.findByEmail(anyString())).thenReturn(optionalUser);

        try{
            optionalUser.get().setId(2);
            serviceMock.create(userDTO);
        }catch (Exception ex) {
            assertEquals(DataIntegratyViolationException.class,ex.getClass());
            assertEquals(EMAIL_JÁ_CADASTRADO_NO_SISTEMA,ex.getMessage());
        }

    }

    @Test
    void deleteWithSuccess() {
        when(repositoryMock.findById(anyInt())).thenReturn(optionalUser);
        doNothing().when(repositoryMock).deleteById(anyInt());
        serviceMock.delete(ID);
        verify(repositoryMock, times(1)).deleteById(anyInt());
    }

    @Test
    void deleteWithObjectNotFoundException() {
        when(repositoryMock.findById(anyInt())).thenThrow(new ObjectNotFoundException(OBJETO_NÃO_ENCONTRADO));
        try {
            serviceMock.delete(ID);
        }catch (Exception ex) {
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals(OBJETO_NÃO_ENCONTRADO, ex.getMessage());
        }
    }

    private void startUser() {
        user = new UserCompany(ID, NAME, EMAIL, PASSWORD);
        userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);
        optionalUser = Optional.of(new UserCompany(ID, NAME, EMAIL, PASSWORD));
    }
}