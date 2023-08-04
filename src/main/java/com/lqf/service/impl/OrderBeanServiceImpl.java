package com.lqf.service.impl;

import com.lqf.dao.OrderBeanDao;
import com.lqf.entity.*;
import com.lqf.service.CartService;
import com.lqf.service.OrderBeanService;
import com.lqf.service.OrderItemService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrderBeanServiceImpl implements OrderBeanService {
    private OrderBeanDao orderBeanDao;
    private OrderItemService orderItemService;
    private CartService cartService;

    @Override
    public void addOrder(OrderBean orderBean) {
        //执行插入操作，顺便获得插入后的id值
        orderBeanDao.addOrder(orderBean);
        //按照购物车项在订单项表添加相应记录
        Map<Integer, CartItem> cartItemMap = orderBean.getOrderUser().getCart().getCartItemMap();
        List<OrderItem> list=new ArrayList<>();
        for(CartItem cartItem:cartItemMap.values()){
            OrderItem orderItem=new OrderItem();
            orderItem.setBook(cartItem.getBook());
            orderItem.setBuyCount(cartItem.getBuyCount());
            orderItem.setOrderBean(orderBean);
            orderItemService.addOrderItem(orderItem);
        }
        //删除购物车项
        User currUser = orderBean.getOrderUser();
        for(CartItem cartItem: cartItemMap.values()){
            cartService.deleteCartItemById(cartItem.getId());
        }
    }

    @Override
    public List<OrderBean> getOrderList(Integer id) {
        List<OrderBean> orderBeanList=orderBeanDao.getOrderList(id);
        for(OrderBean orderBean: orderBeanList){
            List<OrderItem> orderItemList = orderItemService.getListByOrderId(orderBean.getId());
            Integer totalBookCount=0;
            for(OrderItem orderItem:orderItemList){
                totalBookCount+=orderItem.getBuyCount();
            }
            orderBean.setTotalBookCount(totalBookCount);
        }
        return orderBeanList;
    }
}
