package com.lqf.service.impl;

import com.lqf.dao.UserDao;
import com.lqf.entity.Cart;
import com.lqf.entity.User;
import com.lqf.service.CartService;
import com.lqf.service.UserService;

public class UserServiceImpl implements UserService {
    private UserDao userDao;
    private CartService cartService;

    @Override
    public User login(String uname, String pwd) {
        User user = userDao.getUser(uname, pwd);
        Cart cart = cartService.getCart(user);
        user.setCart(cart);
        return user;
    }

    @Override
    public User getUserById(Integer id) {
        return userDao.getUserById(id);
    }

    @Override
    public User getUserByUname(String uname) {
        return userDao.getUserByUname(uname);
    }

    @Override
    public void regist(User user) {
        userDao.addUser(user);
    }
}
