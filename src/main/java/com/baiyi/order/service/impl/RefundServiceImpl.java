package com.baiyi.order.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baiyi.order.dao.RefundDao;
import com.baiyi.order.model.Refund;
import com.baiyi.order.service.RefundService;
import com.baiyi.order.util.EnumList.RefundReasonEnum;
import com.baiyi.order.util.EnumList.RefundTypeEnum;

@Service
public class RefundServiceImpl implements RefundService {

	@Resource
	private RefundDao refundDao;

	@Override
	public void save(Refund refund) {
		refundDao.save(refund);
	}

	@Override
	public void delete(Integer id) {
		refundDao.delete(id);
	}

	@Override
	public void delete(Refund refund) {
		refundDao.delete(refund);
	}

	@Override
	public void delete(Integer[] ids) {
		refundDao.delete(ids);
	}

	@Override
	public void delete(List<Refund> refunds) {
		refundDao.delete(refunds);
	}

	@Override
	public void update(Refund refund) {
		refundDao.update(refund);
	}

	@Override
	public void merge(Refund refund) {
		refundDao.merge(refund);
	}

	@Override
	public Refund find(Integer id) {
		return refundDao.find(id);
	}

	@Override
	public List<Refund> findList() {
		return refundDao.findList();
	}

	@Override
	public List<Refund> findList(String authenticode, String terminalNo, String orderNo, RefundReasonEnum reason, RefundTypeEnum type, Date begin, Date end, Integer userId, Boolean over) {
		return refundDao.findList(authenticode, terminalNo, orderNo, reason, type, begin, end, userId, over);
	}

	@Override
	public List<Refund> findList(String authenticode, String terminalNo, String orderNo, RefundReasonEnum reason, RefundTypeEnum type, Date begin, Date end, Integer userId, Boolean over, String sort, String order, int pageNo, int pageSize) {
		return refundDao.findList(authenticode, terminalNo, orderNo, reason, type, begin, end, userId, over, sort, order, pageNo, pageSize);
	}

	@Override
	public int count(String authenticode, String terminalNo, String orderNo, RefundReasonEnum reason, RefundTypeEnum type, Date begin, Date end, Integer userId, Boolean over) {
		return refundDao.count(authenticode, terminalNo, orderNo, reason, type, begin, end, userId, over);
	}

}
