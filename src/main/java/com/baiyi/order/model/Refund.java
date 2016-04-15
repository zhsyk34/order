package com.baiyi.order.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.baiyi.order.util.EnumList.RefundReasonEnum;
import com.baiyi.order.util.EnumList.RefundTypeEnum;

@Entity
public class Refund {

	private Integer id;

	private String authenticode;// 验证码

	private String terminalNo;// TODO

	private String orderNo;

	private RefundReasonEnum reason;// 原因:1.机器异常;2.余额不足

	private RefundTypeEnum type;// 类型:1.找零失败;2.退币异常

	private double amount;// 应退金额

	private Date happentime;// 发生时间

	/* 处理 */
	private Date dealtime;// 操作时间

	private Integer userId;// 操作员

	private boolean over;// 是否处理

	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAuthenticode() {
		return authenticode;
	}

	public void setAuthenticode(String authenticode) {
		this.authenticode = authenticode;
	}

	public String getTerminalNo() {
		return terminalNo;
	}

	public void setTerminalNo(String terminalNo) {
		this.terminalNo = terminalNo;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	@Enumerated(EnumType.STRING)
	public RefundReasonEnum getReason() {
		return reason;
	}

	public void setReason(RefundReasonEnum reason) {
		this.reason = reason;
	}

	@Enumerated(EnumType.STRING)
	public RefundTypeEnum getType() {
		return type;
	}

	public void setType(RefundTypeEnum type) {
		this.type = type;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getHappentime() {
		return happentime;
	}

	public void setHappentime(Date happentime) {
		this.happentime = happentime;
	}

	public Date getDealtime() {
		return dealtime;
	}

	public void setDealtime(Date dealtime) {
		this.dealtime = dealtime;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public boolean isOver() {
		return over;
	}

	public void setOver(boolean over) {
		this.over = over;
	}

}
