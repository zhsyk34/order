package com.baiyi.order.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.baiyi.order.dao.OrderDetailDao;
import com.baiyi.order.model.OrderDetail;
import com.baiyi.order.util.ValidateUtil;

@Repository
public class OrderDetailDaoImpl extends CommonsDaoImpl<OrderDetail> implements OrderDetailDao {

	@Override
	public void delete(List<OrderDetail> orderDetails) {
		super.delete(orderDetails);
	}

	@Override
	public List<OrderDetail> findList(Integer orderId, String name) {
		StringBuffer queryString = new StringBuffer("from OrderDetail as orderDetail where 1 = 1");
		Map<String, Object> map = new HashMap<>();

		if (ValidateUtil.isPK(orderId)) {
			queryString.append(" and orderDetail.orderId = :orderId");
			map.put("orderId", orderId);
		}

		if (StringUtils.isNotBlank(name)) {
			queryString.append(" and orderDetail.name = :name");
			map.put("name", name);
		}

		return super.findList(queryString.toString(), map);
	}

	@Override
	public int count(Integer orderId, String name) {
		StringBuffer queryString = new StringBuffer("select count(*) from OrderDetail as orderDetail where 1 = 1");
		Map<String, Object> map = new HashMap<>();

		if (ValidateUtil.isPK(orderId)) {
			queryString.append(" and orderDetail.orderId = :orderId");
			map.put("orderId", orderId);
		}

		if (StringUtils.isNotBlank(name)) {
			queryString.append(" and orderDetail.name = :name");
			map.put("name", name);
		}
		return super.count(queryString.toString(), map);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> findTaste(Integer id) {
		String queryString = "select orderDetailTaste.name from OrderDetailTaste as orderDetailTaste where orderDetailTaste.orderDetailId = ?";
		return (List<String>) hibernateTemplate.find(queryString, id);
	}

}
