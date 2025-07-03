package com.saber.demojavaee.repositories;

import com.saber.demojavaee.models.Person;

import java.util.List;

public interface PersonRepository {
    List<Person> findAll();
    Person findById(Integer id);

}
