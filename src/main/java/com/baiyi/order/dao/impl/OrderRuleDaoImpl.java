package com.baiyi.order.dao.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Repository;

import com.baiyi.order.dao.OrderRuleDao;
import com.baiyi.order.model.OrderRule;

@Repository
public class OrderRuleDaoImpl extends CommonsDaoImpl<OrderRule> implements OrderRuleDao {

	@Override
	public void delete(List<OrderRule> orderRules) {
		super.delete(orderRules);
	}

	@SuppressWarnings("unchecked")
	@Override
	public OrderRule findUsed() {
		String queryString = "from OrderRule as orderRule where orderRule.used = ?";
		List<OrderRule> list = (List<OrderRule>) hibernateTemplate.find(queryString, true);
		return CollectionUtils.isEmpty(list) ? null : list.get(0);
	}

	@Override
	public int count() {
		String queryString = "select count(*) from OrderRule";
		return super.count(queryString, null);
	}

}
