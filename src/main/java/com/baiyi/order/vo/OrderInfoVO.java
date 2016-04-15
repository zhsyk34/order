package com.baiyi.order.vo;

import java.util.Date;
import java.util.List;

import com.baiyi.order.util.EnumList.OrderStatus;

//订单详细信息
public class OrderInfoVO {

	private Integer id;

	private String orderNo;

	private String shop;

	private String kitchen;

	private List<OrderDetailVO> detailList;// VO

	private double total;

	private double income;

	private double expense;

	private Date createtime;

	private Date updatetime;

	private Integer userId;

	private OrderStatus status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getShop() {
		return shop;
	}

	public void setShop(String shop) {
		this.shop = shop;
	}

	public String getKitchen() {
		return kitchen;
	}

	public void setKitchen(String kitchen) {
		this.kitchen = kitchen;
	}

	public List<OrderDetailVO> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<OrderDetailVO> detailList) {
		this.detailList = detailList;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getIncome() {
		return income;
	}

	public void setIncome(double income) {
		this.income = income;
	}

	public double getExpense() {
		return expense;
	}

	public void setExpense(double expense) {
		this.expense = expense;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}
}
