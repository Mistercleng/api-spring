package com.company.apispring.services;

import com.company.apispring.domain.User;

public interface UserService {

    User findById(Integer id);
}
