package com.lqf.service;

import com.lqf.entity.User;

public interface UserService {
    User login(String uname,String pwd);

    User getUserById(Integer id);

    User getUserByUname(String uname);

    void regist(User user);
}
