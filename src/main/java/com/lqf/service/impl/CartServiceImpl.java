package com.lqf.service.impl;

import com.lqf.dao.CartDao;
import com.lqf.entity.Book;
import com.lqf.entity.Cart;
import com.lqf.entity.CartItem;
import com.lqf.entity.User;
import com.lqf.service.BookService;
import com.lqf.service.CartService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartServiceImpl implements CartService {
    private CartDao cartDao;
    private BookService bookService;

    @Override
    public void addCartItem(CartItem cartItem) {
        cartDao.addCartItem(cartItem);
    }

    @Override
    public void updateCartItem(CartItem cartItem) {
        cartDao.updateCartItem(cartItem);
    }

    @Override
    public void addOrUpdateCartItem(CartItem cartItem, Cart cart) {
        //将指定图书添加到当前用户的购物车中，若已存在该图书，则将其加一，否则在购物车中新增一个购物车项。设涉及新增和修改
        //购物车不为空
        if(cart.getCartItemMap()!=null && cart.getCartItemMap().size()!=0){
            Map<Integer, CartItem> cartItemMap = cart.getCartItemMap();
            if(cartItemMap==null){
                cartItemMap=new HashMap<>();
            }
            if(cartItemMap.containsKey(cartItem.getBook().getId())){
                CartItem cartItemTemp = cartItemMap.get(cartItem.getBook().getId());
                cartItemTemp.setBuyCount(cartItemTemp.getBuyCount()+1);
                cartDao.updateCartItem(cartItemTemp);
            }else{
                addCartItem(cartItem);
            }
        }else{
            addCartItem(cartItem);
        }
    }

    @Override
    public Cart getCart(User user) {
        List<CartItem> cartItemListByUserId = cartDao.getCartItemListByUserId(user);
        Map<Integer,CartItem> cartItemMap=new HashMap<>();
        for(CartItem cartItem:cartItemListByUserId){
            Book bookById = bookService.getBookById(cartItem.getBook().getId());
            cartItem.setBook(bookById);
            cartItemMap.put(cartItem.getBook().getId(),cartItem);
        }
        Cart cart=new Cart();
        cart.setCartItemMap(cartItemMap);
        return cart;
    }

    @Override
    public CartItem getCartItemById(Integer id) {
        return cartDao.getCartItemById(id);
    }

    @Override
    public void deleteCartItemById(Integer id) {
        cartDao.deleteCartItem(id);
    }


}
