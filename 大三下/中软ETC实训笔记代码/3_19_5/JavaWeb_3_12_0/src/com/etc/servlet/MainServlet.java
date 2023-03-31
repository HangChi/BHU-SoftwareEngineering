package com.etc.servlet;

import com.etc.bean.Product;
import com.etc.service.ProductService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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

        //设置浏览器编码格式和程序编程格式都是UTF-8
        response.setContentType("text/html;charset=utf-8");
        //把商品列表接口集合返回给前端调用
        PrintWriter pw = response.getWriter();
        //只能返回String或int
        //要把list集合转化为json串格式
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        for(int i=0;i<list.size();i++){
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("id",list.get(i).getId());
            jsonObject1.put("name",list.get(i).getName());
            jsonObject1.put("price",list.get(i).getPrice());
            jsonObject1.put("slogan",list.get(i).getSlogan());

            jsonArray.add(jsonObject1);
        }
        jsonObject.put("list",jsonArray);

        pw.write(jsonObject.toString());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
