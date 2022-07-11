package com.company.apispring.services;

import com.company.apispring.domain.UserCompany;

import java.util.List;

public interface UserService {

    UserCompany findById(Integer id);
    List<UserCompany> findAll();
}
