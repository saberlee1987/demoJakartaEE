package com.saber.demojavaee.services;

import com.saber.demojavaee.dto.PersonRequestDto;
import com.saber.demojavaee.models.Person;

import java.util.List;

public interface PersonService {
    List<Person> findAll();
    Person findById(Integer id);
    boolean savePerson(PersonRequestDto personRequestFromRequest);
    boolean updatePerson(PersonRequestDto personRequestFromRequest,int id);
    boolean existPerson(int id);
}
