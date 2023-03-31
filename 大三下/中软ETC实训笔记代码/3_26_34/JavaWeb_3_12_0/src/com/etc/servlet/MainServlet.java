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

    int pageNow = 0; //每页起始下标
    int pageSize = 4;//每页显示行数
    int rowCount = 0;//数据库一共有多少条数据
    int pageCount = 0;//一共可以分为多少也

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String changePageNow = request.getParameter("changePageNow");
        if(changePageNow!=null){
            pageNow = Integer.parseInt(changePageNow);
            if(pageNow<0){
                pageNow = 0;
            }
            if(pageNow>(pageCount-1)*pageSize){
                pageNow = (pageCount-1)*pageSize;
            }
        }

        //控制层调用业务层
        ProductService productService = new ProductService();

        rowCount = productService.productSelectCount();
        if(rowCount%pageSize==0){
            pageCount = rowCount / pageSize;
        }else{
            pageCount = rowCount / pageSize + 1;
        }

        List<Product> list = productService.productSelectByPage(pageNow,pageSize);

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
        jsonObject.put("code","200");
        jsonObject.put("meg","成功");
        jsonObject.put("pageNow",pageNow);
        jsonObject.put("pageSize",pageSize);
        jsonObject.put("pageCount",pageCount);
        jsonObject.put("list",jsonArray);

        pw.write(jsonObject.toString());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
