package com.etc.service;

import com.etc.bean.Product;
import com.etc.dao.ProductDAO;

import java.util.List;

public class ProductService {

    ProductDAO productDAO = new ProductDAO();

    /**
     * 分页查询
     */
    public List<Product> productSelectByPage(int pageNow, int pageSize){
        return productDAO.productSelectByPage(pageNow,pageSize);
    }
}
