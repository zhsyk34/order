package com.baiyi.order.util;

//枚举管理
public interface EnumList {

	public enum MaterialTypeEnum {// 素材类型
		IMAGE, VIDEO, AUDIO;
	}

	public enum MaterialConvertEnum {// 素材(转换)类型
		IMAGE, AUDIO, ORIGINAL, DIRECT, INDIRECT, UNKNOW
	}

	public enum MarqueeDirectionEnum {// 跑马灯方向
		LEFT, RIGHT
	}

	public enum TemplateTypeEnum {// 模板类型
		S01, S02, E01
	}

	public enum TemplateContentEnum {// 模板内容
		NUMBER, VIDEO, PICTURE
	}

	public enum TemplateMaterialEnum {// 模板素材类型
		LOGO, NUMBER, VIDEO, PICTURE
	}

	public enum Effect {// 图片轮播效果
		RANDOM, ALPHA, CIRCLE, MOVE, BLINDS
	}

	public enum OrderStatus {// 订单状态
		NORMAL, NULLIFY
	}

	public enum TerminalTypeEnum {// 终端类型
		SHOP, KITCHEN
	}

	public enum TemplateDownEnum {// 模板下载状态 TODO
		// 待下载,已下载,取消下载,待删除,已删除
		WAITDOWN, HASDOWN, CANCELDOWN, WAITDELETE, HASDELETE
	}

	public enum RefundReasonEnum {// 退币异常原因
		MACHINE, BALANCE
	}

	public enum RefundTypeEnum {// 退币异常类型
		LACK, ERROR
	}

	public enum RemoteTimeEnum {// 终端开关机
		BOOT, SHUT
	}

	public enum RemoteEnum {// 远程操作
		// 开机,关机,校正时间,开启远端,关闭远端
		BOOT, SHUT, CORRECT, OPEN, CLOSE
	}

	public enum ActivityTypeEnum {// 餐点活动类型
		STOP, GIFT, DISCOUNT
	}

	public enum PaymentEnum {// 支付方式
		// 现金,信用卡,微信,支付宝,会员,一卡通,悠游卡
		CASH, CREDITCARD, WECHAT, ALIPAY, MEMBER, METROCARD, EASYCARD
	}

	public enum SexEnum {// 性别
		MALE, FEMALE
	}

}
