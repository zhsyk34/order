package com.baiyi.order.service;

import java.util.List;

import com.baiyi.order.model.OrderRule;

public interface OrderRuleService {

	public void save(OrderRule orderRule);

	public void delete(Integer id);

	public void delete(OrderRule orderRule);

	public void delete(Integer[] ids);

	public void delete(List<OrderRule> orderRules);

	public void update(OrderRule orderRule);

	public void merge(OrderRule orderRule);

	public void enable(Integer id);

	public void disable(Integer id);

	public OrderRule find(Integer id);

	public OrderRule findUsed();

	public List<OrderRule> findList();

	public int count();
}
