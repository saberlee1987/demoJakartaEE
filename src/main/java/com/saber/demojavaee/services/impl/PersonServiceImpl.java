package com.saber.demojavaee.services.impl;

import com.saber.demojavaee.dto.PersonRequestDto;
import com.saber.demojavaee.models.Person;
import com.saber.demojavaee.repositories.PersonRepository;
import com.saber.demojavaee.repositories.impl.PersonRepositoryImpl;
import com.saber.demojavaee.services.PersonService;

import java.util.List;
import java.util.Optional;

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

    @Override
    public boolean savePerson(PersonRequestDto personRequestFromRequest) {
        String nationalCode = personRequestFromRequest.getNationalCode();
        Optional<Person> person = personRepository.findByNationalCode(nationalCode);
        if (person.isPresent()){
            return true;
        }
        Person newPerson = createPersonFromRequest(personRequestFromRequest);
        personRepository.savePerson(newPerson);
        return false;
    }

    @Override
    public boolean updatePerson(PersonRequestDto personRequestFromRequest,int id) {
        String nationalCode = personRequestFromRequest.getNationalCode();
        Optional<Person> person = personRepository.findByNationalCode(nationalCode);
        if (person.isPresent() && !person.get().getId().equals(id)){
            return true;
        }
        if (person.isPresent()) {
            Person personUpdate = person.get();
            createPersonFromRequest(personRequestFromRequest,personUpdate);
            personUpdate.setId(id);
            personRepository.updatePerson(personUpdate);
        }
        return false;
    }

    public boolean existPerson(int id) {
          Person personExist = personRepository.findById(id);
        return personExist!=null;
    }

    private Person createPersonFromRequest(PersonRequestDto personRequest){
        Person person = new Person();
        person.setFirstName(personRequest.getFirstName());
        person.setLastName(personRequest.getLastName());
        person.setNationalCode(personRequest.getNationalCode());
        person.setAge(personRequest.getAge());
        person.setEmail(personRequest.getEmail());
        person.setMobile(personRequest.getMobile());
        return person;
    }

    private void createPersonFromRequest(PersonRequestDto personRequest,Person updatePerson){
        updatePerson.setFirstName(personRequest.getFirstName());
        updatePerson.setLastName(personRequest.getLastName());
        updatePerson.setNationalCode(personRequest.getNationalCode());
        updatePerson.setAge(personRequest.getAge());
        updatePerson.setEmail(personRequest.getEmail());
        updatePerson.setMobile(personRequest.getMobile());

    }
}
