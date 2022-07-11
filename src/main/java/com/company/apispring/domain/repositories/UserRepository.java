package com.company.apispring.domain.repositories;

import com.company.apispring.domain.UserCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <UserCompany, Integer>{

    Optional<UserCompany> findByEmail(String email);
}
