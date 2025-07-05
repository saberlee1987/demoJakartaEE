package com.saber.demojavaee.services.impl;

import com.saber.demojavaee.dto.PersonRequestDto;
import com.saber.demojavaee.models.Person;
import com.saber.demojavaee.repositories.PersonRepository;
import com.saber.demojavaee.repositories.impl.PersonRepositoryImpl;
import com.saber.demojavaee.services.PersonService;
import jakarta.persistence.EntityManagerFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    public PersonServiceImpl(EntityManagerFactory entityManagerFactory) {
        personRepository = new PersonRepositoryImpl(entityManagerFactory);
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
    public void savePerson(PersonRequestDto personRequestFromRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String nationalCode = personRequestFromRequest.getNationalCode();
        Optional<Person> person = personRepository.findByNationalCode(nationalCode);

        if (person.isPresent()) {
            List<String> errors = new ArrayList<>();
            errors.add("person by this nationalCode already exist ");
            request.setAttribute("errors", errors);
            request.setAttribute("personRequest", personRequestFromRequest);
            request.getRequestDispatcher("createPerson.jsp").forward(request, response);
        } else {
            Person newPerson = createPersonFromRequest(personRequestFromRequest);
            personRepository.savePerson(newPerson);
            response.sendRedirect(request.getContextPath() + "/person?message=success");
        }
    }

    @Override
    public void updatePerson(PersonRequestDto personRequestFromRequest,int id,
                                HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> errors = new ArrayList<>();
        boolean existPerson = existPerson(id);
        if (!existPerson) {
            errors.add("person by this nationalCode does not exist ");
            request.setAttribute("errors", errors);
            request.setAttribute("personRequest", personRequestFromRequest);
            request.getRequestDispatcher("updatePerson.jsp").forward(request, response);
            return;
        }
        String nationalCode = personRequestFromRequest.getNationalCode();
        Optional<Person> person = personRepository.findByNationalCode(nationalCode);
        if (person.isPresent() && !person.get().getId().equals(id)){
            errors.add("person by this nationalCode already exist ");
            request.setAttribute("errors", errors);
            request.setAttribute("personRequest", personRequestFromRequest);
            request.getRequestDispatcher("updatePerson.jsp").forward(request, response);
            return;
        }
        if (person.isPresent()) {
            Person personUpdate = person.get();
            createPersonFromRequest(personRequestFromRequest,personUpdate);
            personUpdate.setId(id);
            personRepository.updatePerson(personUpdate);
            response.sendRedirect(request.getContextPath() + "/person?message=success");
        }
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
