package com.company.apispring.resources;

import com.company.apispring.domain.UserCompany;
import com.company.apispring.domain.dto.UserDTO;
import com.company.apispring.services.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserResourceTest {

    public static final Integer ID = 1;
    public static final String NAME     = "Mistercleng";
    public static final String EMAIL    = "mrcleng@gmail.com";
    public static final String PASSWORD = "123";
    public static final int INDEX = 0;

    private UserCompany user;
    private UserDTO userDTO;


    @InjectMocks
    private UserResource resourceMock;

    @Mock
    private UserServiceImpl serviceMock;

    @Mock
    private ModelMapper mapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void findById() {
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
    }
}