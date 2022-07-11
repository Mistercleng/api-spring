package com.company.apispring.services.impl;

import com.company.apispring.domain.UserCompany;
import com.company.apispring.domain.repositories.UserRepository;
import com.company.apispring.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserCompany findById(Integer id) {
        Optional<UserCompany> obj = repository.findById(id);
        return obj.orElse(null);
    }
}
