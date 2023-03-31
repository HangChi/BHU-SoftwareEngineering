package com.etc.servlet;

import com.etc.pojo.Product;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

@WebServlet(name = "SelectByIdServlet")
public class SelectByIdServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        //调用后台根据ID修改商品接口
        //获取连接
        URL url = new URL("http://10.101.57.200:8080/selectByIdServlet?id=" + id);
        //获取连接
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        InputStream inputStream = conn.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,"utf-8"));
        StringBuilder sb = new StringBuilder();
        String line;
        while((line=reader.readLine())!=null)
        {
            sb.append(line);
        }
        String result = sb.toString();

        //把json串转变为Product实体类
        JSONObject jsonObject = JSONObject.fromObject(result);
        Product product = new Product();
        product.setId(jsonObject.getInt("id"));
        product.setName(jsonObject.getString("name"));
        product.setPrice(jsonObject.getInt("price"));
        product.setSlogan(jsonObject.getString("slogan"));

        request.setAttribute("product",product);
        request.getRequestDispatcher("update.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
