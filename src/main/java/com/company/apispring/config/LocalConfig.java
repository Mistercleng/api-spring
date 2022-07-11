package com.company.apispring.config;

import com.company.apispring.domain.UserCompany;
import com.company.apispring.domain.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("local")
public class LocalConfig {

    @Autowired
    private UserRepository repository;

    @Bean
    public void startDB() {
        UserCompany u1 = new UserCompany(null,"Mistercleng","mrcleng@gmail.com","123");
        UserCompany u2 = new UserCompany(null,"Teodora","teodorag@gmail.com","123");

        repository.saveAll(List.of(u1,u2));
    }
}
