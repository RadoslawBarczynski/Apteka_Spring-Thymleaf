package com.example.testdemo.services;

import com.example.testdemo.Repositories.ChemicalsRepository;
import com.example.testdemo.Repositories.UsersRepository;
import com.example.testdemo.model.Chemicals;
import com.example.testdemo.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class InitService {

    @Autowired
    ChemicalsRepository chemicalsRepository;

    @Autowired
    UsersRepository usersRepository;

    @PostConstruct
    public void init(){
        Users u1 = usersRepository.save(new Users(1L, "user", "pass", false ));
        Users u2 = usersRepository.save(new Users(2L, "admin", "admin", true ));

        Chemicals ch1 = chemicalsRepository.save(new Chemicals(1L, "Cholinex", 5L, 23.99, "5mg"));
        Chemicals ch2 = chemicalsRepository.save(new Chemicals(2L, "Polopiryna", 7L, 13.99, "2mg"));
        Chemicals ch3 = chemicalsRepository.save(new Chemicals(3L, "Gripex", 2L, 20.99, "10g"));
        Chemicals ch4 = chemicalsRepository.save(new Chemicals(4L, "Ketanol", 3L, 53.99, "5mg"));
    }
}
