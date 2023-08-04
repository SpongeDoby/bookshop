package com.lqf.dao;

import com.lqf.entity.User;

public interface UserDao {
    User getUser(String uname,String pwd);

    User getUserById(Integer id);

    User getUserByUname(String uname);

    void addUser(User user);
}
