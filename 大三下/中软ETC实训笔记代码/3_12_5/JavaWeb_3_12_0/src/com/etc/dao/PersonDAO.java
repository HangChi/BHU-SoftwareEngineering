package com.etc.dao;

import com.etc.bean.Person;
import com.etc.db.ConnDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * DAO层
 */
public class PersonDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    /**
     * 注册
     */
    public int personRegister(Person person){
        int num = 0;
        try {
            conn = ConnDB.openConn();
            String sql = "insert into person(name,password) values(?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1,person.getName());
            ps.setString(2,person.getPassword());
            num = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnDB.closeConn(conn,ps,rs);
        }
        return num;
    }


    /**
     * 登录
     */
    public int personLogin(Person person){
        int num = 0;
        try {
            //查询Oracle数据库Person员工表
            conn = ConnDB.openConn();
            String sql = "select * from person where name=? and password=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,person.getName());
            ps.setString(2,person.getPassword());
            rs = ps.executeQuery();
            if(rs.next()){
                num = 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnDB.closeConn(conn,ps,rs);
        }
        return num;
    }
}
