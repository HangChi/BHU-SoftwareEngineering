package com.etc.service;

import com.etc.bean.Person;
import com.etc.dao.PersonDAO;

/**
 * 业务层
 */
public class PersonService {

    PersonDAO personDAO = new PersonDAO();

    /**
     * 登录
     */
    public void personLogin(Person person){
        personDAO.personLogin(person);
    }

}
