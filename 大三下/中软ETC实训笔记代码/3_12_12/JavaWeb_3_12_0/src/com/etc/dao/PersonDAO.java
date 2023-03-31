package com.etc.dao;

import com.etc.bean.Person;
import com.etc.db.ConnDB;

/**
 * DAO层
 */
public class PersonDAO {

    /**
     * 登录
     */
    public void personLogin(Person person){
        //查询Oracle数据库Person员工表
        ConnDB.openConn();
    }

}
