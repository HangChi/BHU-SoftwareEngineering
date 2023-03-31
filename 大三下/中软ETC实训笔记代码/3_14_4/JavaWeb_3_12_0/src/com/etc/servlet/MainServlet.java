package com.etc.servlet;

import com.etc.bean.Product;
import com.etc.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


@WebServlet(name = "MainServlet")
public class MainServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //控制层调用业务层
        ProductService productService = new ProductService();
        List<Product> list = productService.productSelect();

        //把商品列表接口集合返回给前端调用
        PrintWriter pw = response.getWriter();
        //只能返回String或int
        //要把list集合转化为json串格式
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
