package com.lqf.dao.impl;

import com.lqf.dao.UserDao;
import com.lqf.entity.User;
import com.lqf.myssm.dao.BaseDao;

public class UserDaoImpl extends BaseDao<User> implements UserDao {
    @Override
    public User getUser(String uname, String pwd) {
        String sql="select * from t_user where uname=? and pwd=?";
        return super.getOne(sql, uname, pwd);
    }

    @Override
    public User getUserById(Integer id) {
        String sql="select * from t_user where id=?";
        return super.getOne(sql,id);
    }

    @Override
    public User getUserByUname(String uname) {
        String sql="select * from t_user where uname=?";
        return super.getOne(sql,uname);
    }

    @Override
    public void addUser(User user) {
        String sql="INSERT into t_user values (0,?,?,?,0)";
        super.executeUpdate(sql,user.getUname(),user.getPwd(),user.getEmail());
    }
}
