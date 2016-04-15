package com.baiyi.order.action;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.baiyi.order.model.Activity;
import com.baiyi.order.model.Cashbox;
import com.baiyi.order.model.Config;
import com.baiyi.order.model.Marquee;
import com.baiyi.order.model.Material;
import com.baiyi.order.model.OrderRule;
import com.baiyi.order.model.Refund;
import com.baiyi.order.model.Seat;
import com.baiyi.order.model.Taste;
import com.baiyi.order.model.Template;
import com.baiyi.order.model.Terminal;
import com.baiyi.order.model.TerminalTemplate;
import com.baiyi.order.model.TerminalTime;
import com.baiyi.order.util.EnumList.ActivityTypeEnum;
import com.baiyi.order.util.EnumList.RefundReasonEnum;
import com.baiyi.order.util.EnumList.RefundTypeEnum;
import com.baiyi.order.util.EnumList.TemplateContentEnum;
import com.baiyi.order.util.EnumList.TemplateDownEnum;
import com.baiyi.order.util.Feedback;
import com.baiyi.order.util.FormatUtil;
import com.baiyi.order.util.ValidateUtil;
import com.baiyi.order.vo.FoodVO;
import com.baiyi.order.vo.TemplateVO;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

//终端交互
@SuppressWarnings("serial")
public class ServiceAction extends CommonsAction {

	// result = .... key
	// TODO
	public String a() {
		jsonData.put(result, Feedback.SUCCESS.toString());
		return SUCCESS;
	}

	// 下载模板:terminalId TODO
	public String downTemplate() {
		Map<String, Object> data = new HashMap<>();
		Terminal terminal = terminalService.find(terminalId);
		if (terminal == null) {
			jsonData.put(result, Feedback.ERROR.toString());
			return SUCCESS;
		}
		Integer terminalId = terminal.getId();
		List<TerminalTemplate> terminalTemplates = terminalService.findTemplateList(terminalId, null, TemplateDownEnum.WAITDOWN, null, null);
		if (CollectionUtils.isEmpty(terminalTemplates)) {
			jsonData.put(result, "not need");
			return SUCCESS;
		}
		// 第一个待下载模板
		TerminalTemplate terminalTemplate = terminalTemplates.get(0);
		TemplateVO tv = templateService.findVO(terminalTemplate.getTemplateId());
		// base
		data.put("id", tv.getId());
		data.put("type", tv.getType().name().toLowerCase());
		data.put("size", tv.getRowcount() + "x" + tv.getColcount());

		// logo
		Material logo = tv.getLogo();
		data.put("titleLogo", logo == null ? "default" : logo.getPath());

		// marquee
		List<Marquee> marqueeList = tv.getMarqueeList();
		if (CollectionUtils.isNotEmpty(marqueeList)) {
			data.put("isMarquee", true);
			for (Marquee marquee : marqueeList) {// TODO
			}
		} else {
			data.put("isMarquee", false);
		}

		TemplateContentEnum contentEnum = tv.getContent();
		data.put("banner", "default");
		// number video picture
		switch (contentEnum) {
		case NUMBER:
			data.put("showCtrl", "numberCtrl");
			Material number = tv.getNumber();
			data.put("banner", number.getPath());
			break;
		case PICTURE:
			data.put("showCtrl", "pictureCtrl");
			List<Material> pictureList = tv.getPictureList();
			List<String> pictures = new ArrayList<>();
			for (Material material : pictureList) {
				pictures.add(material.getPath());
			}
			data.put("picture", "pictures");
			break;
		case VIDEO:
			data.put("showCtrl", "videoCtrl");
			List<Material> videoList = tv.getVideoList();
			List<String> videos = new ArrayList<>();
			for (Material material : videoList) {
				videos.add(material.getPath());
			}
			data.put("video", "videos");
			break;
		}

		// food(taste type style)
		List<FoodVO> foodList = tv.getFoodList();
		List<Map<String, Object>> cakeArray = new ArrayList<>();

		Map<Integer, Integer> typeMap = new LinkedHashMap<Integer, Integer>();
		List<Map<String, Object>> typeArray = new ArrayList<>();

		Map<Integer, Integer> tasteMap = new LinkedHashMap<Integer, Integer>();
		List<Map<String, Object>> tasteArray = new ArrayList<>();
		for (FoodVO fv : foodList) {
			Map<String, Object> cake = new HashMap<>();
			cake.put("id", fv.getId());
			cake.put("name", fv.getName());
			cake.put("shortname", fv.getAbbreviation());
			cake.put("alias", fv.getNickname());

			cake.put("price", fv.getPrice());
			cake.put("necessary", fv.getStyleList());// TODO
			cake.put("introduce", fv.getIntroduction());
			cake.put("image", fv.getPath());

			// type
			Integer typeId = fv.getTypeId();
			if (typeMap.containsKey(typeId)) {
				typeMap.put(typeId, typeMap.get(typeId) + 1);
			} else {
				typeMap.put(typeId, 1);
			}
			cake.put("type", typeId);

			// taste
			List<Taste> tasteList = fv.getTasteList();// TODO
			List<Integer> tastes = new ArrayList<>();
			if (CollectionUtils.isNotEmpty(tasteList)) {
				for (Taste taste : tasteList) {
					Integer tasteId = taste.getId();
					tastes.add(tasteId);
					if (tasteMap.containsKey(tasteId)) {
						tasteMap.put(tasteId, tasteMap.get(tasteId) + 1);
					} else {
						tasteMap.put(tasteId, 1);
					}
				}
			}
			cake.put("taste", tastes);
			cakeArray.add(cake);
		}

		data.put("cakeArray", cakeArray);
		data.put("typeArray", typeArray);// TODO
		data.put("tasteArray", tasteArray);// TODO
		jsonData.put(result, data);
		return SUCCESS;
	}

	// 查询厨房端 TODO
	public String findTerminal() {
		jsonData.put("result", Feedback.SUCCESS.toString());
		return SUCCESS;
	}

	// 分类读取餐点活动数据:terminalId
	public String getActivity() {
		Terminal terminal = terminalService.find(terminalId);
		if (terminal == null) {
			jsonData.put(result, Feedback.ERROR.toString());
			return SUCCESS;
		}
		Integer tid = terminal.getId();

		List<Activity> stopList = activityService.findList(tid, null, ActivityTypeEnum.STOP, true);
		List<Activity> giftList = activityService.findList(tid, null, ActivityTypeEnum.GIFT, true);
		List<Activity> discountList = activityService.findList(tid, null, ActivityTypeEnum.DISCOUNT, true);

		jsonData.put("stopList", stopList);
		jsonData.put("giftList", giftList);
		jsonData.put("discountList", discountList);
		jsonData.put(result, Feedback.SUCCESS.toString());
		return SUCCESS;
	}

	// 获取系统配置信息
	public String getSystemConfig() {
		Config config = configService.find();

		// 钱箱最小最大存量
		Cashbox min = cashboxService.findByTerminal(-1);
		Cashbox max = cashboxService.findByTerminal(0);

		// 座位
		List<String> seats = new ArrayList<>();
		List<Seat> seatList = seatService.findList();
		if (CollectionUtils.isNotEmpty(seatList)) {
			for (Seat seat : seatList) {
				seats.add(seat.getName());
			}
		}

		jsonData.put("config", config);
		jsonData.put("min", min);
		jsonData.put("max", max);
		jsonData.put("seats", seats);
		jsonData.put(result, Feedback.SUCCESS.toString());
		return SUCCESS;
	}

	// 暂不启用 TODO
	public String getTerminalInfo() {
		Terminal terminal = terminalService.find(terminalId);
		if (terminal == null) {
			jsonData.put(result, Feedback.ERROR.toString());
			return SUCCESS;
		}

		Integer terminalId = terminal.getId();
		// 服务器时间
		String time = FormatUtil.dateToString(new Date(), null);
		// 座位
		List<String> seats = new ArrayList<>();
		List<Integer> seatIds = terminalService.findSeat(terminalId);// TODO
		List<Seat> seatList = seatService.findList();
		if (CollectionUtils.isNotEmpty(seatList) && CollectionUtils.isNotEmpty(seatIds)) {
			for (Seat seat : seatList) {
				Integer seatId = seat.getId();
				if (seatIds.contains(seatId)) {
					seats.add(seat.getName());
				}
			}
		}
		// 模板
		Template template = terminalService.findUsedTemplate(terminalId);
		Integer templateId = template == null ? null : template.getId();
		// 开关机
		List<TerminalTime> terminalTimes = terminalService.findTime(terminalId);
		List<String> boots = new ArrayList<>();
		List<String> shuts = new ArrayList<>();
		if (CollectionUtils.isNotEmpty(terminalTimes)) {
			for (TerminalTime terminalTime : terminalTimes) {
				String date = FormatUtil.dateToString(terminalTime.getTime(), "HH:mm:ss");
				switch (terminalTime.getType()) {
				case BOOT:
					boots.add(date);
					break;
				case SHUT:
					shuts.add(date);
					break;
				}
			}
		}
		// 活动
		List<Activity> stopList = activityService.findList(terminalId, null, ActivityTypeEnum.STOP, true);
		List<Activity> giftList = activityService.findList(terminalId, null, ActivityTypeEnum.GIFT, true);
		List<Activity> discountList = activityService.findList(terminalId, null, ActivityTypeEnum.DISCOUNT, true);

		jsonData.put("time", time);
		jsonData.put("template", templateId);
		jsonData.put("seats", seats);
		jsonData.put("boots", boots);
		jsonData.put("shuts", shuts);
		jsonData.put("stopList", stopList);
		jsonData.put("giftList", giftList);
		jsonData.put("discountList", discountList);
		jsonData.put("result", Feedback.SUCCESS.toString());
		return SUCCESS;
	}

	// 获取使用模板:terminalId
	public String getUsedTemplate() {
		Terminal terminal = terminalService.find(terminalId);
		if (terminal == null) {
			jsonData.put(result, Feedback.ERROR.toString());
			return SUCCESS;
		}
		Template template = terminalService.findUsedTemplate(terminal.getId());
		jsonData.put("used", template == null ? null : template.getId());
		jsonData.put(result, Feedback.SUCCESS.toString());
		return SUCCESS;
	}

	// 上传订单 TODO json??
	public String saveOrder() {
		// OrderInfo orderInfo = new OrderInfo();
		// orderInfo.setShop(shop);
		// orderInfo.setKitchen(kitchen);
		// orderInfo.setTotal(total);
		// orderInfo.setIncome(income);
		// orderInfo.setExpense(expense);
		// orderInfo.setCreatetime(new Date());
		// orderInfo.setUpdatetime(null);
		// orderInfo.setUserId(null);
		// orderInfo.setStatus(OrderStatus.NORMAL);
		//
		// if (CollectionUtils.isEmpty(foods)) {
		// jsonData.put("result", Feedback.ERROR.toString());
		// return SUCCESS;
		// }
		//
		// List<OrderDetailVO> orderDetails = new ArrayList<>();
		// for (int i = 0; i < foods.size(); i++) {
		// OrderDetailVO orderDetailVO = new OrderDetailVO();
		// orderDetailVO.setFood(foods.get(i));
		// orderDetailVO.setPrice(prices.get(i));
		// orderDetailVO.setCount(counts.get(i));
		//
		// List<String> tasteList = null;
		// String tasteString = tastes.get(i);
		// if (StringUtils.isNotBlank(tasteString)) {
		// String[] tasteArr = tasteString.split("\\s*,\\s*");
		// tasteList = Arrays.asList(tasteArr);
		// }
		// orderDetailVO.setTasteList(tasteList);
		//
		// orderDetails.add(orderDetailVO);
		// }
		// orderService.save(orderInfo, orderDetails);
		jsonData.put("result", Feedback.SUCCESS.toString());
		return SUCCESS;
	}

	// 上传退币异常:authenticode,orderNo,terminalId,reason,type,amount,happentime
	public String saveRefund() {
		Refund refund = new Refund();
		refund.setAuthenticode(authenticode);
		refund.setOrderNo(orderNo);
		refund.setTerminalNo(terminalId);
		refund.setReason(FormatUtil.getEnum(RefundReasonEnum.class, reason));
		refund.setType(FormatUtil.getEnum(RefundTypeEnum.class, type));
		refund.setAmount(amount);
		refund.setHappentime(FormatUtil.stringToDate(happentime, null));
		refund.setOver(false);

		refundService.save(refund);
		jsonData.put(result, Feedback.SUCCESS.toString());
		return SUCCESS;
	}

	// 服务器时间
	public String getServerTime() {
		String date = FormatUtil.dateToString(new Date(), null);
		jsonData.put(result, date);
		return SUCCESS;
	}

	// 更新模板下载进度:terminalId,templateId,total,down
	public String updateTemplateProgress() {
		if (StringUtils.isBlank(terminalId) || !ValidateUtil.isPK(templateId)) {
			jsonData.put(result, Feedback.ERROR.toString());
			return SUCCESS;
		}
		Terminal terminal = terminalService.find(terminalId);
		if (terminal == null) {
			jsonData.put(result, "终端不存在");
			return SUCCESS;
		}

		TerminalTemplate terminalTemplate = terminalService.findTemplate(terminal.getId(), templateId);
		if (terminalTemplate == null) {
			jsonData.put(result, "未在下载队列中...");
			return SUCCESS;
		}

		TemplateDownEnum status = terminalTemplate.getStatus();
		if (status == TemplateDownEnum.HASDOWN) {
			jsonData.put(result, "已完成下载...");
		} else if (status == TemplateDownEnum.WAITDOWN) {
			if (total > 0) {
				terminalTemplate.setTotal(total);
			}
			terminalTemplate.setDown(terminalTemplate.getDown() + down);
			jsonData.put(result, "已更新下载进度...");
			terminalService.mergeTemplate(terminalTemplate);
		} else {
			jsonData.put(result, "未在下载队列中...");
		}

		return SUCCESS;
	}

	// 更新模板状态:terminalId,templateId,status
	public String updateTemplateStatus() {
		TemplateDownEnum downEnum = FormatUtil.getEnum(TemplateDownEnum.class, status);
		if (StringUtils.isBlank(terminalId) || !ValidateUtil.isPK(templateId) || downEnum == null) {
			jsonData.put(result, Feedback.ERROR.toString());
			return SUCCESS;
		}

		Terminal terminal = terminalService.find(terminalId);
		if (terminal == null) {
			jsonData.put(result, "终端不存在");
			return SUCCESS;
		}

		TerminalTemplate terminalTemplate = terminalService.findTemplate(terminal.getId(), templateId);
		if (terminalTemplate == null) {
			jsonData.put(result, "无相关模板的下载信息...");
			return SUCCESS;
		}
		if (terminalTemplate.isUsed()) {
			jsonData.put(result, "模板正在使用中...不能修改...");
			return SUCCESS;
		}
		terminalTemplate.setStatus(downEnum);
		terminalTemplate.setTotal(0);
		terminalTemplate.setDown(0);
		terminalService.mergeTemplate(terminalTemplate);
		jsonData.put(result, Feedback.SUCCESS.toString());
		return SUCCESS;
	}

	// 终端开关机时间:terminalId
	public String getSwitchTime() {
		Terminal terminal = terminalService.find(terminalId);
		if (terminal == null) {
			jsonData.put(result, Feedback.ERROR.toString());
			return SUCCESS;
		}
		List<TerminalTime> terminalTimes = terminalService.findTime(terminal.getId());
		List<String> boots = new ArrayList<>();
		List<String> shuts = new ArrayList<>();
		if (CollectionUtils.isNotEmpty(terminalTimes)) {
			for (TerminalTime terminalTime : terminalTimes) {
				String date = FormatUtil.dateToString(terminalTime.getTime(), "HH:mm:ss");
				switch (terminalTime.getType()) {
				case BOOT:
					boots.add(date);
					break;
				case SHUT:
					shuts.add(date);
					break;
				}
			}
		}
		jsonData.put("boots", boots);
		jsonData.put("shuts", shuts);
		jsonData.put(result, Feedback.SUCCESS.toString());
		return SUCCESS;
	}

	// 更新餐点活动送出数量:params
	public String updateActivitySend() {

		JSONArray array = JSONArray.fromObject(params);
		System.out.println(array);

		for (int i = 0; i < array.size(); i++) {
			JSONObject json = array.getJSONObject(i);

			String terminalId = json.getString("terminalId");
			Integer foodId = json.getInt("foodId");
			int send = json.getInt("send");

			System.out.printf("%s %d %d\n", terminalId, foodId, send);

			Terminal terminal = terminalService.find(terminalId);
			if (terminal != null) {
				Activity activity = activityService.find(terminal.getId(), foodId);
				if (activity != null) {
					activity.setSend(send);
					activityService.update(activity);
				}
			}

		}

		jsonData.put("result", Feedback.SUCCESS.toString());
		return SUCCESS;
	}

	// 更新现金数量: params={terminalId:'tno10004',nd100tw100:55,nv9tw100:83}
	public String updateCashbox() {
		Terminal terminal = null;
		JSONObject json = JSONObject.fromObject(params);
		System.out.println(json);
		// 验证终端
		try {
			terminalId = json.getString("terminalId");
		} catch (JSONException e) {
			jsonData.put("warning", "终端编号错误");
			jsonData.put(result, Feedback.ERROR.toString());
			return SUCCESS;
		}
		terminal = terminalService.find(terminalId);
		if (terminal == null) {
			jsonData.put(result, Feedback.ERROR.toString());
			return SUCCESS;
		}

		// 读取配置
		Cashbox cashbox = cashboxService.findByTerminal(terminal.getId());
		if (cashbox == null) {
			cashbox = new Cashbox();
			cashbox.setTerminalId(terminal.getId());
			cashbox.setCreatetime(new Date());
		} else {
			cashbox.setUpdatetime(new Date());
		}

		// 更新
		Method[] methods = cashbox.getClass().getDeclaredMethods();
		for (Method method : methods) {
			String name = method.getName();
			if (name.matches("^set(Nd100|Nv9|Hopper)\\S+$")) {
				String key = FormatUtil.getField(name);
				int count = 0;
				try {
					count = json.getInt(key);
				} catch (JSONException e) {
					// e.printStackTrace();
					// jsonData.put("warning", key + "参数错误");
					continue;// 忽视?
				}
				System.out.printf("%s %d\n", key, count);
				try {
					method.invoke(cashbox, count);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
					jsonData.put(result, Feedback.ERROR.toString());
					return SUCCESS;
				}
			}
		}

		cashboxService.merge(cashbox);
		jsonData.put(result, Feedback.SUCCESS.toString());
		return SUCCESS;
	}

	// 更新终端版本号:termianlNo,version
	public String updateTerminalVersion() {
		Terminal terminal = terminalService.find(terminalId);
		if (terminal == null) {
			jsonData.put(result, Feedback.ERROR.toString());
			return SUCCESS;
		}
		terminal.setVersion(version);
		terminalService.update(terminal);
		jsonData.put(result, Feedback.SUCCESS.toString());
		return SUCCESS;
	}

	// 使用的订单规则
	public String getUsedOrdrRule() {
		OrderRule orderRule = orderRuleService.findUsed();
		jsonData.put(result, orderRule);
		jsonData.put(result, Feedback.SUCCESS.toString());
		return SUCCESS;
	}

	/* ===========params============= */

	private String terminalId;// 终端编号
	private String version;// 终端版本
	private Integer templateId;// 模板id
	private Integer foodId;// 餐点id

	/* 退币异常 */
	private String authenticode;
	private String orderNo;
	private String reason;
	private String type;
	private double amount;
	private String happentime;

	/* 上传进度 */
	private long total;
	private long down;

	private String status;// 模板状态

	// json格式数据
	// 活动送出params=[{terminalId:4,foodId:4,send:99}];
	private String params;

	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Integer getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}

	public Integer getFoodId() {
		return foodId;
	}

	public void setFoodId(Integer foodId) {
		this.foodId = foodId;
	}

	public String getAuthenticode() {
		return authenticode;
	}

	public void setAuthenticode(String authenticode) {
		this.authenticode = authenticode;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getHappentime() {
		return happentime;
	}

	public void setHappentime(String happentime) {
		this.happentime = happentime;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public long getDown() {
		return down;
	}

	public void setDown(long down) {
		this.down = down;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

}
