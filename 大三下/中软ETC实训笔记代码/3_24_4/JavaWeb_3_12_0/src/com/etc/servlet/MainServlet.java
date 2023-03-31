package com.etc.servlet;

import com.etc.bean.Product;
import com.etc.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "MainServlet")
public class MainServlet extends HttpServlet {
    int pageNow = 0; //当前页的起始下标(包括自己)
    int pageSize = 4;//每页显示几条数据

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String changePageNow = request.getParameter("changePageNow");
        if(changePageNow!=null){
            pageNow = Integer.parseInt(changePageNow);
        }
        ProductService productService = new ProductService();
        List<Product> list = productService.productSelectByPage(pageNow,pageSize);

        request.setAttribute("pageNow",pageNow);
        request.setAttribute("pageSize",pageSize);
        request.setAttribute("list",list);
        request.getRequestDispatcher("main.jsp").forward(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
