package com.baiyi.order.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.baiyi.order.dao.OrderInfoDao;
import com.baiyi.order.model.OrderInfo;
import com.baiyi.order.util.EnumList.OrderStatus;

@Repository
public class OrderInfoDaoImpl extends CommonsDaoImpl<OrderInfo> implements OrderInfoDao {

	@Override
	public void delete(List<OrderInfo> orderInfos) {
		super.delete(orderInfos);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderInfo> findList(String orderNo) {
		return (List<OrderInfo>) hibernateTemplate.find("from OrderInfo as orderInfo where orderInfo.orderNo = ?", orderNo);
	}

	@Override
	public List<OrderInfo> findList(String orderNo, String shop, String kitchen, Date begin, Date end, Boolean original, OrderStatus status) {
		return this.findList(orderNo, shop, kitchen, begin, end, original, status, null, null, -1, -1);
	}

	@Override
	public List<OrderInfo> findList(String orderNo, String shop, String kitchen, Date begin, Date end, Boolean original, OrderStatus status, String sort, String order, int pageNo, int pageSize) {
		StringBuffer queryString = new StringBuffer("from OrderInfo as orderInfo where 1 = 1");
		Map<String, Object> map = new HashMap<>();

		if (StringUtils.isNotBlank(orderNo)) {
			queryString.append(" and orderInfo.orderNo like :orderNo");
			map.put("orderNo", "%" + orderNo + "%");
		}
		if (StringUtils.isNotBlank(shop)) {
			queryString.append(" and orderInfo.shop like :shop");
			map.put("shop", "%" + shop + "%");
		}
		if (StringUtils.isNotBlank(kitchen)) {
			queryString.append(" and orderInfo.kitchen like :kitchen");
			map.put("kitchen", "%" + kitchen + "%");
		}
		if (begin != null) {
			queryString.append(" and orderInfo.createtime >= :begin");
			map.put("begin", begin);
		}
		if (end != null) {
			queryString.append(" and orderInfo.createtime <= :end");
			map.put("end", end);
		}
		if (original != null) {
			queryString.append(" and orderInfo.userId is");
			queryString.append(original ? " null" : " not null");
		}
		if (status != null) {
			queryString.append(" and orderInfo.status = :status");
			map.put("status", status);
		}
		if (StringUtils.isNotBlank(sort) && StringUtils.isNotBlank(order)) {
			queryString.append(" order by orderInfo." + sort + " " + order);
		}
		return super.findList(queryString.toString(), (pageNo - 1) * pageSize, pageSize, map);
	}

	@Override
	public int count(String orderNo, String shop, String kitchen, Date begin, Date end, Boolean original, OrderStatus status) {
		StringBuffer queryString = new StringBuffer("select count(*) from OrderInfo as orderInfo where 1 = 1");
		Map<String, Object> map = new HashMap<>();

		if (StringUtils.isNotBlank(orderNo)) {
			queryString.append(" and orderInfo.orderNo like :orderNo");
			map.put("orderNo", "%" + orderNo + "%");
		}
		if (StringUtils.isNotBlank(shop)) {
			queryString.append(" and orderInfo.shop like :shop");
			map.put("shop", "%" + shop + "%");
		}
		if (StringUtils.isNotBlank(kitchen)) {
			queryString.append(" and orderInfo.kitchen like :kitchen");
			map.put("kitchen", "%" + kitchen + "%");
		}
		if (begin != null) {
			queryString.append(" and orderInfo.createtime >= :begin");
			map.put("begin", begin);
		}
		if (end != null) {
			queryString.append(" and orderInfo.createtime <= :end");
			map.put("end", end);
		}
		if (original != null) {
			queryString.append(" and orderInfo.userId is");
			queryString.append(original ? " null" : " not null");
		}
		if (status != null) {
			queryString.append(" and orderInfo.status = :status");
			map.put("status", status);
		}

		return super.count(queryString.toString(), map);
	}
}
