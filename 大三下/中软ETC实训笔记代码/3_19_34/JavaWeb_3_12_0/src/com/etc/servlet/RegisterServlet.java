package com.etc.servlet;

import com.etc.bean.Person;
import com.etc.service.PersonService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "RegisterServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String name = request.getParameter("name");
        String password = request.getParameter("password");

        Person person = new Person();
        person.setName(name);
        person.setPassword(password);

        PersonService personService = new PersonService();
        int num = personService.personRegister(person);

        //接口返回对象
        PrintWriter pw = response.getWriter();
        if(num==1){
            pw.write(num + "");
        }else{
            pw.write(num + "");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
