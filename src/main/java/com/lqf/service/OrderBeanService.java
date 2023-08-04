package com.lqf.service;

import com.lqf.entity.OrderBean;

import java.util.List;

public interface OrderBeanService {
    void addOrder(OrderBean orderBean);

    List<OrderBean> getOrderList(Integer id);
}
