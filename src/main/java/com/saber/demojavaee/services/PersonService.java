package com.saber.demojavaee.services;

import com.saber.demojavaee.models.Person;

import java.util.List;

public interface PersonService {
    List<Person> findAll();
    Person findById(Integer id);
}
