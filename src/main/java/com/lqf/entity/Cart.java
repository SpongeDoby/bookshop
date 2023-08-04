package com.lqf.entity;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

//购物车类
public class Cart {
    private Map<Integer,CartItem> cartItemMap; //购物车项集合，以bookId作为Map的key
    private Double totalMoney; // 购物车中所有购物车项的总金额
    private Integer totalCount; // 购物车中所有购物车项的数目
    private Integer totalBookCount; //购物车书本的总数量

    public Cart() {
    }

    public Map<Integer, CartItem> getCartItemMap() {
        return cartItemMap;
    }

    public void setCartItemMap(Map<Integer, CartItem> cartItemMap) {
        this.cartItemMap = cartItemMap;
    }

    public Double getTotalMoney() {
        BigDecimal totalMoneyTemp=new BigDecimal(0+"");
        if(cartItemMap!=null && cartItemMap.size()!=0){
            Set<Map.Entry<Integer, CartItem>> entries = cartItemMap.entrySet();
            for(Map.Entry<Integer,CartItem> cartItemEntry:entries){
                CartItem value = cartItemEntry.getValue();
                BigDecimal buyCount =new BigDecimal(value.getBuyCount()+"");
                BigDecimal price=new BigDecimal(value.getBook().getPrice()+"");
                BigDecimal multiply = buyCount.multiply(price);
                totalMoneyTemp=totalMoneyTemp.add(multiply);
            }
        }
        return  totalMoneyTemp.doubleValue();
    }



    public Integer getTotalCount() {
        if(cartItemMap!=null && cartItemMap.size()!=0){
            return cartItemMap.size();
        }
        return 0;
    }

    public Integer getTotalBookCount() {
        totalBookCount=0;
        if(cartItemMap!=null && cartItemMap.size()!=0){
            Set<Map.Entry<Integer, CartItem>> entries = cartItemMap.entrySet();
            for(Map.Entry<Integer,CartItem> cartItemEntry:entries){
                CartItem value = cartItemEntry.getValue();
                Integer buyCount = value.getBuyCount();
                totalBookCount+=buyCount;
            }
        }
        return totalBookCount;
    }
}
