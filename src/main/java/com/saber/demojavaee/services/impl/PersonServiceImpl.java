package com.saber.demojavaee.services.impl;

import com.saber.demojavaee.models.Person;
import com.saber.demojavaee.repositories.PersonRepository;
import com.saber.demojavaee.repositories.impl.PersonRepositoryImpl;
import com.saber.demojavaee.services.PersonService;

import java.util.List;

public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    public PersonServiceImpl() {
        personRepository = new PersonRepositoryImpl();
    }

    @Override
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @Override
    public Person findById(Integer id) {
        return personRepository.findById(id);
    }
}
