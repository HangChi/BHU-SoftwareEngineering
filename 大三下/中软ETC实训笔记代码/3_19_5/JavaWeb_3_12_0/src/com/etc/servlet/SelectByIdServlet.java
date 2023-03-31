package com.etc.servlet;

import com.etc.bean.Product;
import com.etc.service.ProductService;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SelectByIdServlet")
public class SelectByIdServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        ProductService productService = new ProductService();
        Product product = productService.productSelectById(Integer.parseInt(id));

        //把Product实体类转化为json
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id",product.getId());
        jsonObject.put("name",product.getName());
        jsonObject.put("price",product.getPrice());
        jsonObject.put("slogan",product.getSlogan());

        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(jsonObject.toString());

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
