package com.etc.servlet;

import com.etc.pojo.Product;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "MainServlet")
public class MainServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //调用后台商品列表接口
        //获取连接
        URL url = new URL("http://10.101.57.200:8080/mainServlet");
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

        //把result从String型转化为Object
        JSONObject jsonObject = JSONObject.fromObject(result);
        JSONArray jsonArray = jsonObject.getJSONArray("list");
        //把json串转化为List<Product> list
        List<Product> list = new ArrayList<Product>();

        for(int i=0;i<jsonArray.size();i++){
            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
            Product product = new Product();
            product.setId(jsonObject1.getInt("id"));
            product.setName(jsonObject1.getString("name"));
            product.setPrice(jsonObject1.getInt("price"));
            product.setSlogan(jsonObject1.getString("slogan"));

            list.add(product);
        }

        //跳转传值到main.jsp
        request.setAttribute("shop","渤大食堂");
        request.setAttribute("list",list);
        request.getRequestDispatcher("main.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
