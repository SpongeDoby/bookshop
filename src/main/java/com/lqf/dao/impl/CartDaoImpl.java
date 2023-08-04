package com.lqf.dao.impl;

import com.lqf.dao.CartDao;
import com.lqf.entity.CartItem;
import com.lqf.entity.User;
import com.lqf.myssm.dao.BaseDao;

import java.util.List;

public class CartDaoImpl extends BaseDao<CartItem> implements CartDao {
    private CartDao cartDao;

    @Override
    public void addCartItem(CartItem cartItem) {
        String sql="INSERT INTO t_cart_item values(0,?,?,?)";
        super.executeUpdate(sql,cartItem.getBook().getId(),cartItem.getBuyCount(),cartItem.getUserBean().getId());
    }

    @Override
    public void updateCartItem(CartItem cartItem) {
        String sql="update t_cart_item set buyCount=? where id=?";
        super.executeUpdate(sql,cartItem.getBuyCount(),cartItem.getId());
    }

    @Override
    public List<CartItem> getCartItemListByUserId(User user) {
        String sql="select * from t_cart_item where userBean=?";
        return super.executeQuery(sql,user.getId());
    }

    @Override
    public CartItem getCartItemById(Integer id) {
        String sql="select * from t_cart_item where id=?";
        return super.getOne(sql,id);
    }

    @Override
    public void deleteCartItem(Integer id) {
        String sql="delete from t_cart_item where id=?";
        super.executeUpdate(sql,id);
    }
}
