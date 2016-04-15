package com.baiyi.order.dao;

import java.util.List;

import com.baiyi.order.model.OrderDetailTaste;

public interface OrderDetailTasteDao {

	public void save(OrderDetailTaste orderDetailTaste);

	public void delete(Integer id);

	public void delete(OrderDetailTaste orderDetailTaste);

	public void delete(Integer[] ids);

	public void delete(List<OrderDetailTaste> orderDetailTastes);

	public void update(OrderDetailTaste orderDetailTaste);

	public void merge(OrderDetailTaste orderDetailTaste);

	public OrderDetailTaste find(Integer id);

	public List<OrderDetailTaste> findList();

	public List<OrderDetailTaste> findList(Integer orderDetailId, String name);

}
