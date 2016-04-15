package com.baiyi.order.dao;

import java.util.List;

import com.baiyi.order.model.OrderDetail;

public interface OrderDetailDao {

	public void save(OrderDetail orderDetail);

	public void delete(Integer id);

	public void delete(OrderDetail orderDetail);

	public void delete(Integer[] ids);

	public void delete(List<OrderDetail> orderDetails);

	public void update(OrderDetail orderDetail);

	public void merge(OrderDetail orderDetail);

	public OrderDetail find(Integer id);

	public List<OrderDetail> findList();

	public List<OrderDetail> findList(Integer orderId, String name);

	public int count(Integer orderId, String name);

	public List<String> findTaste(Integer id);
}
