package com.etc.service;

import com.etc.bean.Product;
import com.etc.dao.ProductDAO;

import java.util.List;

public class ProductService {

    ProductDAO productDAO = new ProductDAO();

    /**
     * 根据ID删除商品
     */
    public int productDeleteById(int id){
        return productDAO.productDeleteById(id);
    }

    /**
     * 查询一共有多少级数据
     */
    public int productSelectCount(){
        return productDAO.productSelectCount();
    }

    /**
     * 分页查询
     */
    public List<Product> productSelectByPage(int pageNow, int pageSize){
        return productDAO.productSelectByPage(pageNow,pageSize);
    }

    /**
     * 查询商品列表
     */
    public List<Product> productSelect(){
        return productDAO.productSelect();
    }
}
