package com.lqf.dao.impl;

import com.lqf.dao.OrderBeanDao;
import com.lqf.entity.OrderBean;
import com.lqf.myssm.dao.BaseDao;

import java.util.List;

public class OrderBeanDaoImpl extends BaseDao<OrderBean> implements OrderBeanDao {
    @Override
    public void addOrder(OrderBean orderBean) {
        String sql="INSERT INTO t_order values (0,?,?,?,?,?)";
        int id=super.executeUpdate(sql,orderBean.getOrderNo(),orderBean.getOrderDate(),orderBean.getOrderUser().getId(),orderBean.getOrderMoney(),orderBean.getOrderStatus());
        orderBean.setId(id);
    }

    @Override
    public List<OrderBean> getOrderList(Integer uid) {
        String sql="select * from t_order where orderUser=?";
        return super.executeQuery(sql,uid);
    }


}
