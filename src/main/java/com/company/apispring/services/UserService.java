package com.company.apispring.services;

import com.company.apispring.domain.UserCompany;
import com.company.apispring.domain.dto.UserDTO;

import java.util.List;

public interface UserService {

    UserCompany findById(Integer id);
    List<UserCompany> findAll();
    UserCompany create(UserDTO obj);
    UserCompany update(UserDTO obj);
    void delete(Integer id);
}
