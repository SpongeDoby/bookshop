package com.lqf.dao.impl;

import com.lqf.dao.OrderItemDao;
import com.lqf.entity.OrderItem;
import com.lqf.myssm.dao.BaseDao;

import java.util.List;

public class OrderItemDaoImpl extends BaseDao<OrderItem> implements OrderItemDao {
    @Override
    public void addOrderItem(OrderItem orderItem) {
        String sql="INSERT into t_order_item values (0,?,?,?)";
        super.executeUpdate(sql,orderItem.getBook().getId(),orderItem.getBuyCount(),orderItem.getOrderBean().getId());
    }

    @Override
    public List<OrderItem> getOrderItemListByOrderId(Integer id) {
        String sql="select * from t_order_item where orderBean=?";
        return super.executeQuery(sql,id);
    }
}
