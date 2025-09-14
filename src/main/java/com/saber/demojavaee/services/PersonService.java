package com.saber.demojavaee.services;

import com.saber.demojavaee.dto.PersonRequestDto;
import com.saber.demojavaee.models.Person;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public interface PersonService {
    List<Person> findAll();
    Person findById(Integer id);
    void savePerson(PersonRequestDto personRequestFromRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;
    void updatePerson(PersonRequestDto personRequestFromRequest,int id, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
    boolean existPerson(Integer id);

    void deletePersonById(Integer id,HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException;
}
