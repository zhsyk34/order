package com.baiyi.order.vo;

import java.util.List;

//子订单详细信息
public class OrderDetailVO {

	private Integer id;

	private Integer orderId;

	private Integer foodId;

	private String name;

	private List<String> tasteList;// VO

	private double price;

	private int count;

	private double total;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getFoodId() {
		return foodId;
	}

	public void setFoodId(Integer foodId) {
		this.foodId = foodId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getTasteList() {
		return tasteList;
	}

	public void setTasteList(List<String> tasteList) {
		this.tasteList = tasteList;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

}
