package com.lqf.dao;

import com.lqf.entity.OrderItem;

import java.util.List;

public interface OrderItemDao {
    void addOrderItem(OrderItem orderItem);

    List<OrderItem> getOrderItemListByOrderId(Integer id);
}
