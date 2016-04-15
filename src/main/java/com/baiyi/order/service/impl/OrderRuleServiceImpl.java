package com.baiyi.order.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.baiyi.order.dao.OrderRuleDao;
import com.baiyi.order.model.OrderRule;
import com.baiyi.order.service.OrderRuleService;

@Service
public class OrderRuleServiceImpl implements OrderRuleService {

	@Resource
	private OrderRuleDao orderRuleDao;

	@Override
	public void save(OrderRule orderRule) {
		orderRuleDao.save(orderRule);
	}

	@Override
	public void delete(Integer id) {
		orderRuleDao.delete(id);
	}

	@Override
	public void delete(OrderRule orderRule) {
		orderRuleDao.delete(orderRule);
	}

	@Override
	public void delete(Integer[] ids) {
		orderRuleDao.delete(ids);
	}

	@Override
	public void delete(List<OrderRule> orderRules) {
		orderRuleDao.delete(orderRules);
	}

	@Override
	public void update(OrderRule orderRule) {
		orderRuleDao.update(orderRule);
	}

	@Override
	public void merge(OrderRule orderRule) {
		orderRuleDao.merge(orderRule);
	}

	@Override
	public void enable(Integer id) {
		List<OrderRule> orderRules = orderRuleDao.findList();
		if (CollectionUtils.isNotEmpty(orderRules)) {
			for (OrderRule orderRule : orderRules) {
				orderRule.setUsed(orderRule.getId().equals(id));
				orderRuleDao.update(orderRule);
			}
		}
	}

	@Override
	public void disable(Integer id) {
		OrderRule orderRule = orderRuleDao.find(id);
		orderRule.setUsed(false);
		orderRuleDao.update(orderRule);
	}

	@Override
	public OrderRule find(Integer id) {
		return orderRuleDao.find(id);
	}

	@Override
	public OrderRule findUsed() {
		return orderRuleDao.findUsed();
	}

	@Override
	public List<OrderRule> findList() {
		return orderRuleDao.findList();
	}

	@Override
	public int count() {
		return orderRuleDao.count();
	}

}
