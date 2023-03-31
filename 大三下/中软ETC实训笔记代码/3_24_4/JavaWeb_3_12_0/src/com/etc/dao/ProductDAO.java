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
     * 分页查询
     */
    public List<Product> productSelectByPage(int pageNow,int pageSize){
        List<Product> list = new ArrayList<Product>();
        conn = ConnDB.openConn();
        try {
            String sql = "select * from product limit ?,?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,pageNow);
            ps.setInt(2,pageSize);
            rs=ps.executeQuery();
            while(rs.next()){
                Product product = new Product();
                product.setId(rs.getInt(1));
                product.setName(rs.getString(2));
                product.setPrice(rs.getDouble(3));
                product.setSlogan(rs.getString(4));

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
