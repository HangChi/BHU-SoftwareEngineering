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

@WebServlet(name = "UpdateByIdServlet")
public class UpdateByIdServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        int price = Integer.parseInt(request.getParameter("price"));
        String slogan = request.getParameter("slogan");

        //调用后台根据ID修改商品接口
        URL url = new URL("http://10.101.57.200:8080/updateByIdServlet?id="+id+"&name="+name+"&price="+price+"&slogan="+slogan);
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

        request.getRequestDispatcher("mainServlet").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
