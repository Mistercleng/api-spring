package com.company.apispring.services.impl;

import com.company.apispring.domain.UserCompany;
import com.company.apispring.domain.dto.UserDTO;
import com.company.apispring.domain.repositories.UserRepository;
import com.company.apispring.services.UserService;
import com.company.apispring.services.exceptions.DataIntegratyViolationException;
import com.company.apispring.services.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public UserCompany findById(Integer id) {
        Optional<UserCompany> obj = repository.findById(id);
        return obj.orElseThrow(()-> new ObjectNotFoundException(("Objeto não encontrado!")));
    }
    @Override
    public List<UserCompany> findAll() {
        return repository.findAll();
    }

    @Override
    public UserCompany create(UserDTO obj) {
        findByEmail(obj);
        return repository.save(mapper.map(obj,UserCompany.class));
    }

    @Override
    public UserCompany update(UserDTO obj) {
        findByEmail(obj);
        return repository.save(mapper.map(obj,UserCompany.class));
    }

    private void findByEmail(UserDTO obj) {
        Optional<UserCompany> user = repository.findByEmail(obj.getEmail());
        if(user.isPresent() && !user.get().getId().equals(obj.getId())){
            throw new DataIntegratyViolationException("Email já cadastrado no sistema");
        }
    }
}
