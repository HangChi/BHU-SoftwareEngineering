package com.etc.service;

import com.etc.bean.Person;
import com.etc.dao.PersonDAO;

/**
 * 业务层
 */
public class PersonService {

    PersonDAO personDAO = new PersonDAO();

    /**
     * 注册
     */
    public int personRegister(Person person){
        return personDAO.personRegister(person);
    }

    /**
     * 登录
     */
    public int personLogin(Person person){
        return personDAO.personLogin(person);
    }

    /**
     * 查询用户名是否已经被占用
     */
    public int personSelectByName(String name){
        return personDAO.personSelectByName(name);
    }
}
