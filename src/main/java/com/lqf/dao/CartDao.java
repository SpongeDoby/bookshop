package com.lqf.dao;

import com.lqf.entity.CartItem;
import com.lqf.entity.User;

import java.util.List;

public interface CartDao {
    void addCartItem(CartItem cartItem);

    void updateCartItem(CartItem cartItem);

    List<CartItem> getCartItemListByUserId(User user);

    CartItem getCartItemById(Integer id);

    void deleteCartItem(Integer id);
}
