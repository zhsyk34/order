package com.baiyi.order.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.baiyi.order.dao.OrderDetailTasteDao;
import com.baiyi.order.model.OrderDetailTaste;
import com.baiyi.order.util.ValidateUtil;

@Repository
public class OrderDetailTasteDaoImpl extends CommonsDaoImpl<OrderDetailTaste> implements OrderDetailTasteDao {

	@Override
	public void delete(List<OrderDetailTaste> orderDetailTastes) {
		super.delete(orderDetailTastes);
	}

	@Override
	public List<OrderDetailTaste> findList(Integer orderDetailId, String name) {
		StringBuffer queryString = new StringBuffer("from OrderDetailTaste as orderDetailTaste where 1 = 1");
		Map<String, Object> map = new HashMap<>();

		if (ValidateUtil.isPK(orderDetailId)) {
			queryString.append(" and orderDetailTaste.orderDetailId = :orderDetailId");
			map.put("orderDetailId", orderDetailId);
		}
		if (StringUtils.isNotBlank(name)) {
			queryString.append(" and orderDetailTaste.name = :name");
			map.put("name", name);
		}
		return super.findList(queryString.toString(), map);
	}

}
