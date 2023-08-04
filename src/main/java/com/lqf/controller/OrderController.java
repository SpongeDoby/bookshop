package com.lqf.controller;

import com.lqf.entity.CartItem;
import com.lqf.entity.OrderBean;
import com.lqf.entity.User;
import com.lqf.service.OrderBeanService;
import jakarta.servlet.http.HttpSession;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class OrderController {
    private OrderBeanService orderBeanService;

    public String checkout(HttpSession session){
        User currUser=(User) session.getAttribute("currUser");
        //初始化订单详情
        OrderBean orderBean=new OrderBean();
        LocalDateTime now = LocalDateTime.now();
        orderBean.setOrderNo(UUID.randomUUID().toString()+"_"+now.toString());
        orderBean.setOrderDate(now);
        orderBean.setOrderUser(currUser);
        Double totalMoney = currUser.getCart().getTotalMoney();
        orderBean.setOrderMoney(totalMoney);
        orderBean.setOrderStatus(0);
        orderBeanService.addOrder(orderBean);
        return "index";
    }

    public String getOrderList(HttpSession session){
        User currUser=(User)session.getAttribute("currUser");
        List<OrderBean> orderList = orderBeanService.getOrderList(currUser.getId());
        currUser.setOrderBeanList(orderList);
        session.setAttribute("currUser",currUser);
        return "order/order";
    }
}
