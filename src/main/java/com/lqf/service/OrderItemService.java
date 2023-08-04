package com.lqf.service;

import com.lqf.entity.OrderItem;

import java.util.List;

public interface OrderItemService {
    void addOrderItem(OrderItem orderItem);

    List<OrderItem> getListByOrderId(Integer id);
}
