package com.baiyi.order.dao;

import java.util.List;

import com.baiyi.order.model.OrderRule;

public interface OrderRuleDao {

	public void save(OrderRule orderRule);

	public void delete(Integer id);

	public void delete(OrderRule orderRule);

	public void delete(Integer[] ids);

	public void delete(List<OrderRule> orderRules);

	public void update(OrderRule orderRule);

	public void merge(OrderRule orderRule);

	public OrderRule find(Integer id);

	public OrderRule findUsed();

	public List<OrderRule> findList();

	public int count();
}
