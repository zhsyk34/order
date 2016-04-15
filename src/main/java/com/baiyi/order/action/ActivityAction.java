package com.baiyi.order.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.baiyi.order.model.Activity;
import com.baiyi.order.model.Food;
import com.baiyi.order.util.EnumList.ActivityTypeEnum;
import com.baiyi.order.util.Feedback;
import com.baiyi.order.util.FormatUtil;
import com.baiyi.order.util.ValidateUtil;
import com.baiyi.order.vo.ActivityVO;

@SuppressWarnings("serial")
public class ActivityAction extends CommonsAction {

	public String update() {
		ActivityTypeEnum typeEnum = FormatUtil.getEnum(ActivityTypeEnum.class, type);

		if (typeEnum == null || ValidateUtil.isEmpty(foodIds) || ValidateUtil.isEmpty(kitchenIds) || foodIds.length != kitchenIds.length) {
			jsonData.put(result, Feedback.ERROR.toString());
			return SUCCESS;
		}
		if ((StringUtils.isBlank(begin) || StringUtils.isBlank(end)) && count <= 0) {// 必要的控制信息
			jsonData.put(result, Feedback.ERROR.toString());
			return SUCCESS;
		}

		boolean usedStatus = FormatUtil.intToboolean(used);

		List<Activity> activities = new ArrayList<>();

		for (int i = 0; i < foodIds.length; i++) {
			Integer kitchenId = kitchenIds[i], foodId = foodIds[i];
			Activity activity = activityService.find(kitchenId, foodId);
			if (activity == null) {
				activity = new Activity();
				activity.setKitchenId(kitchenId);
				activity.setFoodId(foodId);
			}

			activity.setBegin(FormatUtil.stringToDate(begin, null));
			activity.setEnd(FormatUtil.stringToDate(end, null));
			activity.setCount(count);

			activity.setType(typeEnum);
			switch (typeEnum) {
			case STOP:
				activity.setUnit(0);
				activity.setDiscount(0);
				activity.setPercent(0);
				if (count > 0) {// 数量控制优先
					activity.setBegin(null);
					activity.setEnd(null);
				}
				break;
			case GIFT:
				activity.setUnit(unit);
				activity.setDiscount(0);
				activity.setPercent(0);
				break;
			case DISCOUNT:
				activity.setUnit(0);
				activity.setPercent(percent);
				if (percent > 0) {
					Food food = foodService.find(activity.getFoodId());
					double price = food.getPrice();
					discount = FormatUtil.format(price * percent / 100, 0);
				}
				activity.setDiscount(discount);
				break;
			}

			activity.setUsed(usedStatus);
			activity.setUserId(loginId);
			activity.setCreatetime(new Date());

			activities.add(activity);
		}

		activityService.merge(activities);
		jsonData.put(result, Feedback.UPDATE.toString());
		return SUCCESS;
	}

	public String used() {
		ActivityTypeEnum typeEnum = FormatUtil.getEnum(ActivityTypeEnum.class, type);
		boolean usedStatus = FormatUtil.intToboolean(used);

		if (typeEnum == null || ValidateUtil.isEmpty(foodIds) || ValidateUtil.isEmpty(kitchenIds) || foodIds.length != kitchenIds.length) {
			jsonData.put(result, Feedback.ERROR.toString());
			return SUCCESS;
		}

		List<Activity> activities = new ArrayList<>();
		for (int i = 0; i < foodIds.length; i++) {
			Integer kitchenId = kitchenIds[i], foodId = foodIds[i];
			Activity activity = activityService.find(kitchenId, foodId);

			if (activity != null && activity.getType() != typeEnum) {
				continue;// 不允许直接更改活动类型
			}

			if (activity == null) {// 直接启用
				if (!usedStatus) {
					continue;
				}
				activity = new Activity();
				activity.setKitchenId(kitchenId);
				activity.setFoodId(foodId);
				activity.setType(typeEnum);// 启用时设置类型

				switch (typeEnum) {
				case STOP:// 直接无限期启用停售
					activity.setBegin(new Date());
					activity.setEnd(FormatUtil.stringToDate("2999-12-31 23:59:59", null));
					activity.setCount(0);
					break;
				case GIFT:// 直接无限期启用促销,促销单位默认设置为1份
					activity.setBegin(new Date());
					activity.setEnd(FormatUtil.stringToDate("2999-12-31 23:59:59", null));
					activity.setUnit(1);
					break;
				case DISCOUNT:
					continue;// 暂不支持直接启用促销(缺少必要的价格或折扣信息)
				}
			}

			activity.setUsed(usedStatus);
			activity.setUserId(loginId);
			activity.setCreatetime(new Date());
			activities.add(activity);
		}
		activityService.merge(activities);
		jsonData.put(result, usedStatus ? Feedback.ENABLE.toString() : Feedback.DISABLE.toString());
		return SUCCESS;

	}

	public String delete() {
		activityService.delete(ids);
		jsonData.put(result, Feedback.REVOKE.toString());
		return SUCCESS;
	}

	public String find() {
		sort = "tid";// 终端:kitchenId
		ActivityTypeEnum typeEnum = FormatUtil.getEnum(ActivityTypeEnum.class, type);
		Boolean usedStatus = FormatUtil.intToBoolean(used);

		List<ActivityVO> list = activityService.findVOList(kitchen, food, typeEnum, usedStatus, sort, order, pageNo, pageSize);
		int count = activityService.countVO(kitchen, food, typeEnum, usedStatus);
		jsonData.put("list", list);
		jsonData.put("count", count);
		jsonData.put("pageNo", pageNo);
		jsonData.put("pageSize", pageSize);
		return SUCCESS;
	}

	private Integer id;

	private String kitchen;

	private String food;

	private Integer[] ids;
	private Integer[] kitchenIds;
	private Integer[] foodIds;

	private String type;

	/* 活动信息 */
	// 1.赠送
	private int unit;
	// 2.打折
	private double discount;
	private int percent;// 1-99

	/* 控制方式 */
	// 1.时间
	private String begin;
	private String end;
	// 2.数量
	private int count;
	// 3.直接
	private Integer used;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getKitchen() {
		return kitchen;
	}

	public void setKitchen(String kitchen) {
		this.kitchen = kitchen;
	}

	public String getFood() {
		return food;
	}

	public void setFood(String food) {
		this.food = food;
	}

	public Integer[] getIds() {
		return ids;
	}

	public void setIds(Integer[] ids) {
		this.ids = ids;
	}

	public Integer[] getKitchenIds() {
		return kitchenIds;
	}

	public void setKitchenIds(Integer[] kitchenIds) {
		this.kitchenIds = kitchenIds;
	}

	public Integer[] getFoodIds() {
		return foodIds;
	}

	public void setFoodIds(Integer[] foodIds) {
		this.foodIds = foodIds;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getUnit() {
		return unit;
	}

	public void setUnit(int unit) {
		this.unit = unit;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public int getPercent() {
		return percent;
	}

	public void setPercent(int percent) {
		this.percent = percent;
	}

	public String getBegin() {
		return begin;
	}

	public void setBegin(String begin) {
		this.begin = begin;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Integer getUsed() {
		return used;
	}

	public void setUsed(Integer used) {
		this.used = used;
	}

}
