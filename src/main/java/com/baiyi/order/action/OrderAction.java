package com.baiyi.order.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.baiyi.order.model.OrderInfo;
import com.baiyi.order.util.EnumList.OrderStatus;
import com.baiyi.order.util.Feedback;
import com.baiyi.order.util.FormatUtil;
import com.baiyi.order.util.OrderStatisticsUtil;
import com.baiyi.order.vo.OrderDetailVO;
import com.baiyi.order.vo.OrderInfoVO;
import com.baiyi.order.vo.OrderVO;

@SuppressWarnings("serial")
public class OrderAction extends CommonsAction {

	public String save() {
		OrderInfo orderInfo = new OrderInfo();
		orderInfo.setShop(shop);
		orderInfo.setKitchen(kitchen);
		orderInfo.setTotal(total);
		orderInfo.setIncome(income);
		orderInfo.setExpense(expense);
		orderInfo.setCreatetime(new Date());
		orderInfo.setUpdatetime(null);
		orderInfo.setUserId(loginId);
		orderInfo.setStatus(OrderStatus.NORMAL);

		if (CollectionUtils.isEmpty(foods)) {
			jsonData.put("result", Feedback.ERROR.toString());
			return SUCCESS;
		}

		List<OrderDetailVO> orderDetails = new ArrayList<>();
		for (int i = 0; i < foods.size(); i++) {
			OrderDetailVO orderDetailVO = new OrderDetailVO();
			orderDetailVO.setName(foods.get(i));
			orderDetailVO.setPrice(prices.get(i));
			orderDetailVO.setCount(counts.get(i));

			List<String> tasteList = null;
			String tasteString = tastes.get(i);
			if (StringUtils.isNotBlank(tasteString)) {
				String[] tasteArr = tasteString.split("\\s*,\\s*");
				tasteList = Arrays.asList(tasteArr);
			}
			orderDetailVO.setTasteList(tasteList);

			orderDetails.add(orderDetailVO);
		}
		orderService.save(orderInfo, orderDetails);
		jsonData.put("result", Feedback.CREATE.toString());
		return SUCCESS;
	}

	public String delete() {
		orderService.revoke(id);
		jsonData.put("result", Feedback.REVOKE.toString());
		return SUCCESS;
	}

	public String update() {
		OrderInfo orderInfo = orderService.find(id);
		orderInfo.setTotal(total);
		orderInfo.setIncome(income);
		orderInfo.setExpense(expense);
		orderInfo.setUpdatetime(new Date());

		List<OrderDetailVO> orderDetails = new ArrayList<>();
		for (int i = 0; i < foods.size(); i++) {
			OrderDetailVO orderDetailVO = new OrderDetailVO();
			orderDetailVO.setName(foods.get(i));
			orderDetailVO.setPrice(prices.get(i));
			orderDetailVO.setCount(counts.get(i));

			List<String> tasteList = null;
			String tasteString = tastes.get(i);
			if (StringUtils.isNotBlank(tasteString)) {
				String[] tasteArr = tasteString.split("\\s*,|，\\s*");
				tasteList = Arrays.asList(tasteArr);
			}
			orderDetailVO.setTasteList(tasteList);

			orderDetails.add(orderDetailVO);
		}
		orderInfo.setUserId(1);// loginId TODO
		orderService.update(orderInfo, orderDetails);
		jsonData.put("result", Feedback.UPDATE.toString());
		return SUCCESS;
	}

	public String find() {
		Date beginDate = FormatUtil.stringToDate(begin, null);
		Date endDate = FormatUtil.stringToDate(end, null);
		OrderStatus orderStatus = FormatUtil.getEnum(OrderStatus.class, status);
		Boolean original = FormatUtil.intToBoolean(userId);// 是否原始订单(终端)

		List<OrderInfoVO> list = orderService.findVOList(orderNo, shop, kitchen, beginDate, endDate, original, orderStatus, sort, order, pageNo, pageSize);
		int count = orderService.count(orderNo, shop, kitchen, beginDate, endDate, original, orderStatus);
		jsonData.put("list", list);
		jsonData.put("count", count);
		jsonData.put("pageNo", pageNo);
		jsonData.put("pageSize", pageSize);
		return SUCCESS;
	}

	public String statistic() {
		Date beginDate = FormatUtil.stringToDate(begin, null);
		Date endDate = FormatUtil.stringToDate(end, null);
		List<OrderVO> orderVOList = orderService.findOrderList(null, shop, kitchen, beginDate, endDate, null, OrderStatus.NORMAL);
		List<OrderVO> detail = OrderStatisticsUtil.mergeByTerminal(orderVOList);
		OrderVO list = OrderStatisticsUtil.mergeOrder(orderVOList);
		jsonData.put("detail", detail);
		jsonData.put("list", list);
		return SUCCESS;
	}

	/**/
	private Integer id;

	private String orderNo;

	private String shop;

	private String kitchen;

	private double total;

	private double income;

	private double expense;

	private String begin;

	private String end;

	private String status;

	// orderDetail
	private List<String> foods;
	private List<Double> prices;
	private List<Integer> counts;
	private List<String> tastes;

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<String> getFoods() {
		return foods;
	}

	public void setFoods(List<String> foods) {
		this.foods = foods;
	}

	public List<Double> getPrices() {
		return prices;
	}

	public void setPrices(List<Double> prices) {
		this.prices = prices;
	}

	public List<Integer> getCounts() {
		return counts;
	}

	public void setCounts(List<Integer> counts) {
		this.counts = counts;
	}

	public List<String> getTastes() {
		return tastes;
	}

	public void setTastes(List<String> tastes) {
		this.tastes = tastes;
	}

}
