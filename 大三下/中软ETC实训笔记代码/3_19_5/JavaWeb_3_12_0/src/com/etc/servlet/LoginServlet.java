package com.etc.servlet;

import com.etc.bean.Person;
import com.etc.service.PersonService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 控制层
 */
@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //解决传入参数乱码问题
        request.setCharacterEncoding("utf-8");
        //获取传入参数
        String name = request.getParameter("name");
        String password = request.getParameter("password");

        //封装实体类
        Person person = new Person();
        person.setName(name);
        person.setPassword(password);

        //控制层跳转到业务层
        PersonService personService = new PersonService();
        int num = personService.personLogin(person);
        if(num==1){
            //接口返回
            response.getWriter().write(num + "");
        }else{
            response.getWriter().write(num + "");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
