package com.baiyi.order.service;

import java.util.Date;
import java.util.List;

import com.baiyi.order.model.OrderDetail;
import com.baiyi.order.model.OrderInfo;
import com.baiyi.order.util.EnumList.OrderStatus;
import com.baiyi.order.vo.OrderDetailVO;
import com.baiyi.order.vo.OrderInfoVO;
import com.baiyi.order.vo.OrderVO;

public interface OrderService {

	public void save(OrderInfo orderInfo);

	public void save(OrderInfo orderInfo, List<OrderDetailVO> orderDetails);// 子订单明细

	public void delete(Integer id);

	public void delete(OrderInfo orderInfo);

	public void delete(Integer[] ids);

	public void delete(List<OrderInfo> orderInfos);

	public void revoke(Integer id);// 撤销订单,修改状态

	public void update(OrderInfo orderInfo);

	public void update(OrderInfo orderInfo, List<OrderDetailVO> orderDetails);// 子订单明细

	public void merge(OrderInfo orderInfo);

	public OrderInfo find(Integer id);

	public List<OrderInfo> findList();

	public List<OrderInfo> findList(String orderNo);

	public List<OrderInfo> findList(String orderNo, String shop, String kitchen, Date begin, Date end, Boolean original, OrderStatus status);

	public List<OrderInfo> findList(String orderNo, String shop, String kitchen, Date begin, Date end, Boolean original, OrderStatus status, String sort, String order, int pageNo, int pageSize);

	public int count(String orderNo, String shop, String kitchen, Date begin, Date end, Boolean original, OrderStatus status);

	/* VO */
	public List<OrderInfoVO> findVOList();

	public List<OrderInfoVO> findVOList(String orderNo, String shop, String kitchen, Date begin, Date end, Boolean original, OrderStatus status);

	public List<OrderInfoVO> findVOList(String orderNo, String shop, String kitchen, Date begin, Date end, Boolean original, OrderStatus status, String sort, String order, int pageNo, int pageSize);

	/* detail */
	public List<OrderDetail> findDetailList(String orderNo, String shop, String kitchen, Date begin, Date end, Boolean original, OrderStatus status);

	public List<OrderVO> findOrderList(String orderNo, String shop, String kitchen, Date begin, Date end, Boolean original, OrderStatus status);

}
