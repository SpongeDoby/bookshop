package com.lqf.service.impl;

import com.lqf.dao.OrderItemDao;
import com.lqf.entity.OrderItem;
import com.lqf.service.OrderItemService;

import java.util.List;

public class OrderItemServiceImpl implements OrderItemService {
    private OrderItemDao orderItemDao;
    @Override
    public void addOrderItem(OrderItem orderItem) {
        orderItemDao.addOrderItem(orderItem);
    }

    @Override
    public List<OrderItem> getListByOrderId(Integer id) {
        return orderItemDao.getOrderItemListByOrderId(id);
    }
}
