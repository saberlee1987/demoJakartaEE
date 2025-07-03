package com.saber.demojavaee.repositories.impl;

import com.saber.demojavaee.models.Person;
import com.saber.demojavaee.repositories.PersonRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

public class PersonRepositoryImpl implements PersonRepository {

    @PersistenceContext(unitName = "saber66")
    private EntityManager entityManager;

    @Override
    public List<Person> findAll() {
        return entityManager.createNamedQuery("findAll",Person.class)
                .getResultList();
    }

    @Override
    public Person findById(Integer id) {
        return null;
    }
}
