package com.saber.demojavaee.repositories;

import com.saber.demojavaee.models.Person;

import java.util.List;
import java.util.Optional;

public interface PersonRepository {
    List<Person> findAll();
    Person findById(Integer id);
    Optional<Person> findByNationalCode(String nationalCode);
    void savePerson(Person person);
    void updatePerson(Person person);

}
