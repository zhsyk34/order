package com.baiyi.order.action;

import org.apache.commons.lang3.StringUtils;

import com.baiyi.order.model.Config;
import com.baiyi.order.model.PaySetting;
import com.baiyi.order.util.Feedback;
import com.baiyi.order.util.FormatUtil;
import com.baiyi.order.util.EnumList.PaymentEnum;

@SuppressWarnings("serial")
public class ConfigAction extends CommonsAction {

	public String update() {
		Config config = configService.find();

		config.setTakeShow(takeShow);
		config.setTakeAway(takeAway);

		config.setSeat(seat);

		config.setFoodType(foodType);

		config.setShopOrder(shopOrder);
		config.setKitchenOrder(kitchenOrder);

		config.setCash(cash);
		config.setCreditcard(creditcard);
		config.setWechat(wechat);
		config.setAlipay(alipay);
		config.setMember(member);
		config.setMetrocard(metrocard);
		config.setEasycard(easycard);

		config.setAccessory(accessory);
		if (accessory) {
			config.setAccessoryName(accessoryName);
			config.setAccessoryPercent(accessoryPercent);
		}

		configService.update(config);
		jsonData.put("result", Feedback.UPDATE.toString());
		return SUCCESS;
	}

	public String find() {
		Config config = configService.find();

		if (config == null) {
			config = new Config();
			config.setShopOrder(true);
			config.setCash(true);
			configService.save(config);
		}

		jsonData.put("config", config);
		return SUCCESS;
	}

	public String payFind() {
		PaymentEnum payEnum = FormatUtil.getEnum(PaymentEnum.class, name);
		PaySetting paySetting = paySettingService.find(payEnum);
		jsonData.put("pay", paySetting);
		return SUCCESS;
	}

	public String paySet() {
		PaymentEnum payEnum = FormatUtil.getEnum(PaymentEnum.class, name);
		if (payEnum == null) {
			jsonData.put(result, Feedback.ERROR.toString());
			return SUCCESS;
		}
		PaySetting paySetting = paySettingService.find(payEnum);
		if (paySetting == null) {
			if (StringUtils.isBlank(privateKey) || (payEnum == PaymentEnum.ALIPAY && StringUtils.isBlank(publicKey))) {
				jsonData.put(result, Feedback.ERROR.toString());
				return SUCCESS;
			}
			paySetting = new PaySetting();
			paySetting.setName(payEnum);

		}

		paySetting.setTitle(title);
		paySetting.setAppId(appId);
		paySetting.setPartner(partner);

		if (StringUtils.isNotBlank(privateKey)) {
			paySetting.setPrivateKey(privateKey);
		}
		if (StringUtils.isNotBlank(publicKey)) {
			paySetting.setPrivateKey(publicKey);
		}

		paySettingService.merge(paySetting);
		jsonData.put(result, Feedback.UPDATE.toString());
		return SUCCESS;
	}

	/* 取餐方式(是否外带) */
	private boolean takeShow;
	private boolean takeAway;

	private boolean seat;// 餐桌显示(取餐方式默认为显示时提供设置)

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

	/* 支付设置 */
	private String name;
	private String title;
	private String appId;
	private String partner;// 合伙人
	private String publicKey;// 公钥
	private String privateKey;// 密钥

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

	public boolean isAccessory() {
		return accessory;
	}

	public void setAccessory(boolean accessory) {
		this.accessory = accessory;
	}

	public String getAccessoryName() {
		return accessoryName;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getPartner() {
		return partner;
	}

	public void setPartner(String partner) {
		this.partner = partner;
	}

	public String getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

	public String getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}

}
