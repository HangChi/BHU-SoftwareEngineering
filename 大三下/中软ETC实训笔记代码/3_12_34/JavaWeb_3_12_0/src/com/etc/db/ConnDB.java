package com.etc.db;

import java.sql.*;

/**
 * JDBC工具类
 */
public class ConnDB {


    /**
     * 打开数据库
     */
    public static Connection openConn(){
        Connection conn = null;
        //导入Oracle11g驱动包ojdbc6.jar
        try {
            //2.编写驱动
            Class.forName("oracle.jdbc.driver.OracleDriver");
            //3.编写连接
            String url = "jdbc:oracle:thin:@localhost:1521:orcl";
            //4.设置账号密码
            String user = "etc";
            String password = "111111";
            //5.获取连接
            conn =  DriverManager.getConnection(url,user,password);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return conn;
    }


    /**
     * 关闭数据库
     */
    public static void closeConn(Connection conn, PreparedStatement ps,ResultSet rs){
        try {
            if(rs!=null){
                rs.close();
            }
            if(ps!=null){
                ps.close();
            }
            if(conn!=null){
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
