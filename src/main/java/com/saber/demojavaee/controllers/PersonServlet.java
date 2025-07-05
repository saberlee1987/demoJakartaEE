package com.saber.demojavaee.controllers;

import com.github.mfathi91.time.PersianDate;
import com.saber.demojavaee.dto.PersonRequestDto;
import com.saber.demojavaee.models.Person;
import com.saber.demojavaee.services.PersonService;
import com.saber.demojavaee.services.impl.PersonServiceImpl;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@WebServlet(urlPatterns = "/person", displayName = "personServlet")
public class PersonServlet extends HttpServlet {

    private PersonService personService;

    @Override
    public void init() {
        this.personService = new PersonServiceImpl(Persistence.createEntityManagerFactory("saber66"));
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null || action.isBlank()) {
            action = "listPersons";
        }
        switch (action) {
            case "listPersons":
                showPersons(request, response);
                break;
            case "view":
                viewPerson(request, response);
                break;
            case "savePerson":
                showSavePerson(request, response);
                break;
            case "updatePerson":
                showUpdatePerson(request, response);
                break;
            default:
                response.sendRedirect(request.getContextPath() + "/person");
                break;
        }

    }

    private void showUpdatePerson(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        if (idStr != null && !idStr.isEmpty() && idStr.matches("\\d+")) {
            int id = Integer.parseInt(idStr);
            Person person = personService.findById(id);
            List<String> errors = new ArrayList<>();
            PersonRequestDto personRequest = createPersonRequestPersonModel(person);
            request.setAttribute("errors", errors);
            request.setAttribute("personRequest", personRequest);
            request.getRequestDispatcher("updatePerson.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/person");
        }
    }

    private void showSavePerson(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PersonRequestDto personRequestDto = new PersonRequestDto();
        List<String> errors = new ArrayList<>();
        request.setAttribute("errors", errors);
        request.setAttribute("personRequest", personRequestDto);
        request.getRequestDispatcher("createPerson.jsp").forward(request, response);
    }

    private void viewPerson(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        if (id == null || id.isBlank() || !id.matches("\\d+")) {
            showPersons(request, response);
        } else {
            Integer idInt = Integer.parseInt(id);
            Person person = personService.findById(idInt);
            request.setAttribute("person", person);
            request.getRequestDispatcher("person.jsp").forward(request, response);
        }
    }

    private void showPersons(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Person> personList = personService.findAll();
        for (Person person : personList) {
            person.setCreatedAtPersian(PersianDate.fromGregorian(person.getCreatedAt().toLocalDate())
                    .format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
            if (person.getUpdatedAt() != null) {
                person.setUpdatedAtPersian(PersianDate.fromGregorian(person.getUpdatedAt().toLocalDate())
                        .format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));

            }
        }
        request.setAttribute("persons", personList);
        request.getRequestDispatcher("persons.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null || action.isBlank()) {
            action = "listPersons";
        }
        switch (action) {
            case "savePerson":
                savePerson(request, response);
                break;
            case "updatePerson":
                updatePerson(request, response);
                break;
            default:
                response.sendRedirect(request.getContextPath() + "/person");
                break;
        }
    }

    private void updatePerson(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = getIdFromRequest(request, response);
        if (id == null) return;
        PersonRequestDto personRequestFromRequest = validationRequest(request, response, "updatePerson.jsp");
        if (personRequestFromRequest != null) {
            personService.updatePerson(personRequestFromRequest, id, request, response);
        }
    }

    private Integer getIdFromRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        Integer idInt = null;
        if (id == null || id.isBlank() || !id.matches("\\d+")) {
            showPersons(request, response);
        } else {
            idInt = Integer.parseInt(id);
        }
        return idInt;
    }

    private void savePerson(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PersonRequestDto personRequestFromRequest = validationRequest(request, response, "createPerson.jsp");
        if (personRequestFromRequest != null) {
            personService.savePerson(personRequestFromRequest, request, response);
        }
    }

    private PersonRequestDto validationRequest(HttpServletRequest request, HttpServletResponse response, String pageDispatcher) throws ServletException, IOException {
        PersonRequestDto personRequestFromRequest = createPersonRequestFromRequest(request);
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<PersonRequestDto>> violations = validator.validate(personRequestFromRequest);
        List<String> errors = new ArrayList<>();
        if (!violations.isEmpty()) {
            for (ConstraintViolation<PersonRequestDto> violation : violations) {
                errors.add(violation.getPropertyPath().toString() + " ===> " + violation.getMessage());
            }
            request.setAttribute("errors", errors);
            request.setAttribute("personRequest", personRequestFromRequest);
            request.getRequestDispatcher(pageDispatcher).forward(request, response);
            return null;
        }
        return personRequestFromRequest;
    }

    private PersonRequestDto createPersonRequestFromRequest(HttpServletRequest request) {
        PersonRequestDto personRequestDto = new PersonRequestDto();
        personRequestDto.setFirstName(request.getParameter("firstName"));
        personRequestDto.setLastName(request.getParameter("lastName"));
        personRequestDto.setNationalCode(request.getParameter("nationalCode"));
        String ageStr = request.getParameter("age");
        if (ageStr != null && !ageStr.isBlank() && ageStr.matches("\\d+")) {
            personRequestDto.setAge(Integer.parseInt(ageStr));
        }
        personRequestDto.setEmail(request.getParameter("email"));
        personRequestDto.setMobile(request.getParameter("mobile"));
        return personRequestDto;
    }

    private PersonRequestDto createPersonRequestPersonModel(Person person) {
        PersonRequestDto personRequestDto = new PersonRequestDto();
        personRequestDto.setFirstName(person.getFirstName());
        personRequestDto.setLastName(person.getLastName());
        personRequestDto.setNationalCode(person.getNationalCode());
        personRequestDto.setAge(person.getAge());
        personRequestDto.setEmail(person.getEmail());
        personRequestDto.setMobile(person.getMobile());
        return personRequestDto;
    }
}
