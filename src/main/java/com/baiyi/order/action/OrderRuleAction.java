package com.baiyi.order.action;

import java.util.List;

import com.baiyi.order.model.OrderRule;
import com.baiyi.order.util.Feedback;

@SuppressWarnings("serial")
public class OrderRuleAction extends CommonsAction {

	public String save() {
		OrderRule orderRule = new OrderRule();
		orderRule.setPrefix(prefix);
		orderRule.setStart(start);
		orderRule.setLength(length);
		orderRuleService.save(orderRule);
		jsonData.put("result", Feedback.CREATE.toString());
		return SUCCESS;
	}

	public String delete() {
		orderRuleService.delete(ids);
		jsonData.put("result", Feedback.DELETE.toString());
		return SUCCESS;
	}

	public String update() {
		OrderRule orderRule = orderRuleService.find(id);
		orderRule.setPrefix(prefix);
		orderRule.setStart(start);
		orderRule.setLength(length);
		orderRuleService.update(orderRule);
		jsonData.put("result", Feedback.UPDATE.toString());
		return SUCCESS;
	}

	public String used() {
		if (used) {
			orderRuleService.enable(id);
			jsonData.put("result", Feedback.ENABLE.toString());
		} else {
			orderRuleService.disable(id);
			jsonData.put("result", Feedback.DISABLE.toString());
		}
		return SUCCESS;
	}

	public String find() {
		List<OrderRule> list = orderRuleService.findList();
		int count = orderRuleService.count();
		jsonData.put("list", list);
		jsonData.put("count", count);
		jsonData.put("pageNo", pageNo);
		jsonData.put("pageSize", pageSize);
		return SUCCESS;
	}

	private Integer id;

	private Integer ids;

	private String prefix;

	private int start;

	private int length;

	private boolean used;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIds() {
		return ids;
	}

	public void setIds(Integer ids) {
		this.ids = ids;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public boolean isUsed() {
		return used;
	}

	public void setUsed(boolean used) {
		this.used = used;
	}

}
