package com.saber.demojavaee.controllers;

import com.saber.demojavaee.models.Person;
import com.saber.demojavaee.repositories.PersonRepository;
import com.saber.demojavaee.repositories.impl.PersonRepositoryImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {

    private PersonRepository personRepository;

    @Override
    public void init() throws ServletException {
        super.init();
        personRepository = new PersonRepositoryImpl();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<String> colors = new ArrayList<>();
        //System.out.println("test ...........................");
        List<Person> persons = personRepository.findAll();
      //  persons.forEach(System.out::println);
        colors.add("Red");
        colors.add("Green");
        colors.add("Blue");
        colors.add("Yellow");
        colors.add("Black");
        colors.add("Orange");
        colors.add("White");
        colors.add("Pink");
        colors.add("Brown");

        request.setAttribute("colors",colors);
        request.setAttribute("persons",persons);
        request.getRequestDispatcher("hello.jsp").forward(request,response);
    }

}