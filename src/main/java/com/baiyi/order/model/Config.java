package com.baiyi.order.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Config {// TODO terminalId?

	private Integer id;

	private boolean takeShow;// 是否显示取餐方式
	private boolean takeAway;// tackshow == true 时设置默认方式

	private boolean seat;// 餐桌显示

	private boolean foodType;// 分类显示餐点

	/* 单据显示 */
	private boolean shopOrder;// 客户端
	private boolean kitchenOrder;// 厨房端

	/* 支付方式 */
	private boolean cash;// 现金
	private boolean creditcard;// 信用卡
	private boolean wechat;// 微信
	private boolean alipay;// 支付宝
	private boolean member;// 会员
	private boolean metrocard;// 一卡通
	private boolean easycard;// 悠游卡

	/* 附加费 */
	private boolean accessory;
	private String accessoryName;
	private int accessoryPercent;

	private Integer userId;

	private Date createtime;

	private Date updatetime;

	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean isTakeShow() {
		return takeShow;
	}

	public void setTakeShow(boolean takeShow) {
		this.takeShow = takeShow;
	}

	public boolean isTakeAway() {
		return takeAway;
	}

	public void setTakeAway(boolean takeAway) {
		this.takeAway = takeAway;
	}

	public boolean isSeat() {
		return seat;
	}

	public void setSeat(boolean seat) {
		this.seat = seat;
	}

	public boolean isFoodType() {
		return foodType;
	}

	public void setFoodType(boolean foodType) {
		this.foodType = foodType;
	}

	public boolean isShopOrder() {
		return shopOrder;
	}

	public void setShopOrder(boolean shopOrder) {
		this.shopOrder = shopOrder;
	}

	public boolean isKitchenOrder() {
		return kitchenOrder;
	}

	public void setKitchenOrder(boolean kitchenOrder) {
		this.kitchenOrder = kitchenOrder;
	}

	public boolean isCash() {
		return cash;
	}

	public void setCash(boolean cash) {
		this.cash = cash;
	}

	public boolean isCreditcard() {
		return creditcard;
	}

	public void setCreditcard(boolean creditcard) {
		this.creditcard = creditcard;
	}

	public boolean isWechat() {
		return wechat;
	}

	public void setWechat(boolean wechat) {
		this.wechat = wechat;
	}

	public boolean isAlipay() {
		return alipay;
	}

	public void setAlipay(boolean alipay) {
		this.alipay = alipay;
	}

	public boolean isMember() {
		return member;
	}

	public void setMember(boolean member) {
		this.member = member;
	}

	public boolean isMetrocard() {
		return metrocard;
	}

	public void setMetrocard(boolean metrocard) {
		this.metrocard = metrocard;
	}

	public boolean isEasycard() {
		return easycard;
	}

	public void setEasycard(boolean easycard) {
		this.easycard = easycard;
	}

	public String getAccessoryName() {
		return accessoryName;
	}

	public boolean isAccessory() {
		return accessory;
	}

	public void setAccessory(boolean accessory) {
		this.accessory = accessory;
	}

	public void setAccessoryName(String accessoryName) {
		this.accessoryName = accessoryName;
	}

	public int getAccessoryPercent() {
		return accessoryPercent;
	}

	public void setAccessoryPercent(int accessoryPercent) {
		this.accessoryPercent = accessoryPercent;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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

}
