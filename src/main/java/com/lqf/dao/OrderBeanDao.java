package com.lqf.dao;

import com.lqf.entity.OrderBean;

import java.util.List;

public interface OrderBeanDao {
    void addOrder(OrderBean orderBean);

    List<OrderBean> getOrderList(Integer uid);


}
