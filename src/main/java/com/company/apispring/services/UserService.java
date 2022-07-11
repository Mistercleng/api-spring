package com.company.apispring.services;

import com.company.apispring.domain.UserCompany;

public interface UserService {

    UserCompany findById(Integer id);
}
