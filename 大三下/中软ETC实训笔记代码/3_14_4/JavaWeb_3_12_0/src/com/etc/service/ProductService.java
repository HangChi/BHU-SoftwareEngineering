package com.etc.service;

import com.etc.bean.Product;
import com.etc.dao.ProductDAO;

import java.util.List;

public class ProductService {

    ProductDAO productDAO = new ProductDAO();

    /**
     * 查询商品列表
     */
    public List<Product> productSelect(){
        return productDAO.productSelect();
    }
}
