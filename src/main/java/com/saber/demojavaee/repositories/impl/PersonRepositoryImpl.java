package com.saber.demojavaee.repositories.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.saber.demojavaee.models.Person;
import com.saber.demojavaee.models.QPerson;
import com.saber.demojavaee.repositories.PersonRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public class PersonRepositoryImpl implements PersonRepository, Serializable {

    //    @PersistenceContext(unitName = "saber66")
    private final EntityManager entityManager;
    private final QPerson person = new QPerson("person");
    private final JPAQueryFactory queryFactory;

    public PersonRepositoryImpl(EntityManagerFactory entityManagerFactory) {
//        this.entityManager = Persistence.createEntityManagerFactory("saber66").createEntityManager();
        this.entityManager = entityManagerFactory.createEntityManager();
        queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<Person> findAll() {
//        return entityManager.createNamedQuery("findAll",Person.class)
//                .getResultList();
        return queryFactory.selectFrom(person).fetch();
    }

    @Override
    public Person findById(Integer id) {
        return queryFactory.selectFrom(person).where(person.id.eq(id))
                .fetchOne();
    }

    @Override
    public Optional<Person> findByNationalCode(String nationalCode) {
        return Optional.ofNullable(queryFactory.selectFrom(person)
                .where(person.nationalCode.eq(nationalCode))
                .fetchOne());
    }

    @Override
    public void savePerson(Person person) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(person);
        transaction.commit();
    }

    @Override
    public void updatePerson(Person person) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(person);
        transaction.commit();
    }
}
