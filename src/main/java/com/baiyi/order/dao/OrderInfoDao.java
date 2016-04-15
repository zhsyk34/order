package com.baiyi.order.dao;

import java.util.Date;
import java.util.List;

import com.baiyi.order.model.OrderInfo;
import com.baiyi.order.util.EnumList.OrderStatus;

public interface OrderInfoDao {

	public void save(OrderInfo orderInfo);

	public void delete(Integer id);

	public void delete(OrderInfo orderInfo);

	public void delete(Integer[] ids);

	public void delete(List<OrderInfo> orderInfos);

	public void update(OrderInfo orderInfo);

	public void merge(OrderInfo orderInfo);

	public OrderInfo find(Integer id);

	public List<OrderInfo> findList();

	public List<OrderInfo> findList(String orderNo);

	public List<OrderInfo> findList(String orderNo, String shop, String kitchen, Date begin, Date end, Boolean original, OrderStatus status);

	public List<OrderInfo> findList(String orderNo, String shop, String kitchen, Date begin, Date end, Boolean original, OrderStatus status, String sort, String order, int pageNo, int pageSize);

	public int count(String orderNo, String shop, String kitchen, Date begin, Date end, Boolean original, OrderStatus status);

}
