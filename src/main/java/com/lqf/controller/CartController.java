package com.lqf.controller;



import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.lqf.entity.Book;
import com.lqf.entity.Cart;
import com.lqf.entity.CartItem;
import com.lqf.entity.User;
import com.lqf.service.CartService;
import jakarta.servlet.http.HttpSession;

public class CartController {
    private CartService cartService;

    //加载当前用户的购物车信息
    private String index(HttpSession session){
        User currUser = (User)session.getAttribute("currUser");
        Cart cart = cartService.getCart(currUser);
        currUser.setCart(cart);
        session.setAttribute("currUser",currUser);
        return "cart/cart";
    }

    private String addCart(Integer bookId, HttpSession session){
        User currUser=(User) session.getAttribute("currUser");
        CartItem cartItem=new CartItem(new Book(bookId),1,currUser);
        cartService.addOrUpdateCartItem(cartItem,currUser.getCart());
        return "redirect:cart.do?operateType=index";//转到index方法（dispatcherServlet默认operateType是index，也可以重定向到首页
    }

    private String editCart(Integer cartId,Integer buyCount){
        CartItem cartItem = new CartItem();
        cartItem.setId(cartId);
        cartItem.setBuyCount(buyCount);
        cartService.updateCartItem(cartItem);
        return "code:200";
    }

    //给Vue异步请求用的
    public String getCart(HttpSession session){
        User currUser = (User)session.getAttribute("currUser");
        Cart cart = cartService.getCart(currUser);
        String cartJson = JSON.toJSONString(cart, SerializerFeature.WriteNonStringKeyAsString);
        return "json:"+cartJson;
    }
}
