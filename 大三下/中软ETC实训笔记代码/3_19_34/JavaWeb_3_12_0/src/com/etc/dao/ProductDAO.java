package com.etc.dao;

import com.etc.bean.Product;
import com.etc.db.ConnDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    /**
     * 根据ID删除商品
     */
    public int productDeleteById(int id){
        int num = 0;
        conn = ConnDB.openConn();

        try {
            String sql = "delete from product where id=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            num = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return num;
    }


    /**
     * 查询商品列表
     */
    public List<Product> productSelect(){
        List<Product> list = new ArrayList<Product>();
        try {
            //IDEA连通Oracle
            conn = ConnDB.openConn();
            //查询操作
            String sql = "select * from product";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                int id = rs.getInt(1);
                String name = rs.getString(2);
                int price = rs.getInt(3);
                String slogan = rs.getString(4);

                //封装Product实体类
                Product product = new Product();
                product.setId(id);
                product.setName(name);
                product.setPrice(price);
                product.setSlogan(slogan);
                //添加到集合
                list.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnDB.closeConn(conn,ps,rs);
        }
        return list;
    }
}
