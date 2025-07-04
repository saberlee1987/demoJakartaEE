package com.saber.demojavaee.controllers;

import com.saber.demojavaee.models.Person;
import com.saber.demojavaee.services.PersonService;
import com.saber.demojavaee.services.impl.PersonServiceImpl;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/person", displayName = "personServlet")
public class PersonServlet extends HttpServlet {

    private PersonService personService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.personService = new PersonServiceImpl();
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
            default:
                response.sendRedirect(request.getContextPath() + "/hello-servlet");
                break;
        }

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
        request.setAttribute("persons", personList);
        request.getRequestDispatcher("persons.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
