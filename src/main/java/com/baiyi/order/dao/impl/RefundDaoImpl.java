package com.baiyi.order.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.baiyi.order.dao.RefundDao;
import com.baiyi.order.model.Refund;
import com.baiyi.order.util.EnumList.RefundReasonEnum;
import com.baiyi.order.util.EnumList.RefundTypeEnum;
import com.baiyi.order.util.ValidateUtil;

@Repository
public class RefundDaoImpl extends CommonsDaoImpl<Refund> implements RefundDao {

	@Override
	public void delete(List<Refund> refunds) {
		super.delete(refunds);
	}

	@Override
	public List<Refund> findList(String authenticode, String terminalNo, String orderNo, RefundReasonEnum reason, RefundTypeEnum type, Date begin, Date end, Integer userId, Boolean over) {
		return this.findList(authenticode, terminalNo, orderNo, reason, type, begin, end, userId, over, null, null, -1, -1);
	}

	@Override
	public List<Refund> findList(String authenticode, String terminalNo, String orderNo, RefundReasonEnum reason, RefundTypeEnum type, Date begin, Date end, Integer userId, Boolean over, String sort, String order, int pageNo, int pageSize) {
		StringBuffer queryString = new StringBuffer("from Refund as refund where 1 = 1");
		Map<String, Object> map = new HashMap<>();

		if (StringUtils.isNotBlank(authenticode)) {
			queryString.append(" and refund.authenticode like :authenticode");
			map.put("authenticode", "%" + authenticode + "%");
		}
		if (StringUtils.isNotBlank(terminalNo)) {
			queryString.append(" and refund.terminalNo like :terminalNo");
			map.put("terminalNo", "%" + terminalNo + "%");
		}
		if (StringUtils.isNotBlank(orderNo)) {
			queryString.append(" and refund.orderNo like :orderNo");
			map.put("orderNo", "%" + orderNo + "%");
		}
		if (reason != null) {
			queryString.append(" and refund.reason = :reason");
			map.put("reason", reason);
		}
		if (type != null) {
			queryString.append(" and refund.type = :type");
			map.put("type", type);
		}
		if (begin != null) {
			queryString.append(" and refund.happentime >= :begin");
			map.put("begin", begin);
		}
		if (end != null) {
			queryString.append(" and refund.happentime <= :end");
			map.put("end", end);
		}
		if (ValidateUtil.isPK(userId)) {
			queryString.append(" and refund.userId = :userId");
			map.put("userId", userId);
		}
		if (over != null) {
			queryString.append(" and refund.over = :over");
			map.put("over", over);
		}
		if (StringUtils.isNotBlank(sort) && StringUtils.isNotBlank(order)) {
			queryString.append(" order by refund." + sort + " " + order);
		}
		return super.findList(queryString.toString(), (pageNo - 1) * pageSize, pageSize, map);
	}

	@Override
	public int count(String authenticode, String terminalNo, String orderNo, RefundReasonEnum reason, RefundTypeEnum type, Date begin, Date end, Integer userId, Boolean over) {
		StringBuffer queryString = new StringBuffer("select count(*) from Refund as refund where 1 = 1");
		Map<String, Object> map = new HashMap<>();

		if (StringUtils.isNotBlank(authenticode)) {
			queryString.append(" and refund.authenticode like :authenticode");
			map.put("authenticode", "%" + authenticode + "%");
		}
		if (StringUtils.isNotBlank(terminalNo)) {
			queryString.append(" and refund.terminalNo like :terminalNo");
			map.put("terminalNo", "%" + terminalNo + "%");
		}
		if (StringUtils.isNotBlank(orderNo)) {
			queryString.append(" and refund.orderNo like :orderNo");
			map.put("orderNo", "%" + orderNo + "%");
		}
		if (reason != null) {
			queryString.append(" and refund.reason = :reason");
			map.put("reason", reason);
		}
		if (type != null) {
			queryString.append(" and refund.type = :type");
			map.put("type", type);
		}
		if (begin != null) {
			queryString.append(" and refund.happentime >= :begin");
			map.put("begin", begin);
		}
		if (end != null) {
			queryString.append(" and refund.happentime <= :end");
			map.put("end", end);
		}
		if (ValidateUtil.isPK(userId)) {
			queryString.append(" and refund.userId = :userId");
			map.put("userId", userId);
		}
		if (over != null) {
			queryString.append(" and refund.over = :over");
			map.put("over", over);
		}
		return super.count(queryString.toString(), map);
	}

}
