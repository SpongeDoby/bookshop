package com.lqf.service;

import com.lqf.entity.Cart;
import com.lqf.entity.CartItem;
import com.lqf.entity.User;

public interface CartService {
    void addCartItem(CartItem cartItem);
    void updateCartItem(CartItem cartItem);
    void addOrUpdateCartItem(CartItem CartItem, Cart cart);

    //生成特定用户的购物车信息
    Cart getCart(User user);

    CartItem getCartItemById(Integer id);

    void deleteCartItemById(Integer id);
}
