package com.etc.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String name = request.getParameter("name");
        String password = request.getParameter("password");

        //调用登录接口
        //获取连接
        URL url = new URL("http://10.101.57.200:8080/loginServlet?name=" + name + "&password=" + password );
        //获取连接
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        //把返回值(0,1)转化为InputStream输入流
        InputStream is = conn.getInputStream();
        //获取输出流
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = -1;
        while((len=is.read(buffer))!=-1){
            baos.write(buffer,0,len);
        }
        String result = baos.toString();

        if(result.equals("1")){
            request.getRequestDispatcher("mainServlet").forward(request,response);
        }else{
            response.sendRedirect("login.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
