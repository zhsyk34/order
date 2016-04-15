package com.baiyi.order.action;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.baiyi.order.model.Refund;
import com.baiyi.order.util.EnumList.RefundReasonEnum;
import com.baiyi.order.util.EnumList.RefundTypeEnum;
import com.baiyi.order.util.Feedback;
import com.baiyi.order.util.FormatUtil;

@SuppressWarnings("serial")
public class RefundAction extends CommonsAction {

	public String save() {
		Refund refund = new Refund();
		refund.setAuthenticode(authenticode);
		refund.setOrderNo(orderNo);
		refund.setTerminalNo(terminalNo);
		refund.setReason(FormatUtil.getEnum(RefundReasonEnum.class, reason));
		refund.setType(FormatUtil.getEnum(RefundTypeEnum.class, type));
		refund.setAmount(amount);
		refund.setHappentime(FormatUtil.stringToDate(happentime, null));
		refund.setOver(false);
		jsonData.put(result, Feedback.CREATE.toString());
		return SUCCESS;
	}

	public String update() {
		Refund refund = refundService.find(id);
		refund.setUserId(loginId);

		boolean overStatus = FormatUtil.intToboolean(over);
		refund.setOver(overStatus);
		refund.setDealtime(overStatus ? new Date() : null);
		refundService.update(refund);
		jsonData.put(result, overStatus ? Feedback.DEAL.toString() : Feedback.REVOKE.toString());
		return SUCCESS;
	}

	public String find() {
		RefundReasonEnum reasonEnum = FormatUtil.getEnum(RefundReasonEnum.class, reason);
		RefundTypeEnum typeEnum = FormatUtil.getEnum(RefundTypeEnum.class, type);
		Date beginDate = FormatUtil.stringToDate(begin, null);
		Date endDate = FormatUtil.stringToDate(StringUtils.isBlank(end) ? null : end + " 23:59:59", null);
		Boolean overStatus = FormatUtil.intToBoolean(over);

		List<Refund> list = refundService.findList(authenticode, terminalNo, orderNo, reasonEnum, typeEnum, beginDate, endDate, userId, overStatus, authenticode, orderNo, pageNo, pageSize);
		int count = refundService.count(authenticode, terminalNo, orderNo, reasonEnum, typeEnum, beginDate, endDate, userId, overStatus);
		jsonData.put("list", list);
		jsonData.put("count", count);
		jsonData.put("pageNo", pageNo);
		jsonData.put("pageSize", pageSize);
		return SUCCESS;
	}

	private Integer id;

	private String authenticode;

	private String terminalNo;

	private String orderNo;

	private String reason;

	private String type;

	private double amount;

	private String happentime;
	/* search date for happentime */
	private String begin;
	private String end;

	/* 处理 */
	private Integer over;

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

	public Integer getOver() {
		return over;
	}

	public void setOver(Integer over) {
		this.over = over;
	}

}
