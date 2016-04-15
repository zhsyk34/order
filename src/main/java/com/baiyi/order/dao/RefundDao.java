package com.baiyi.order.dao;

import java.util.Date;
import java.util.List;

import com.baiyi.order.model.Refund;
import com.baiyi.order.util.EnumList.RefundReasonEnum;
import com.baiyi.order.util.EnumList.RefundTypeEnum;

public interface RefundDao {

	public void save(Refund refund);

	public void delete(Integer id);

	public void delete(Refund refund);

	public void delete(Integer[] ids);

	public void delete(List<Refund> refunds);

	public void update(Refund refund);

	public void merge(Refund refund);

	public Refund find(Integer id);

	public List<Refund> findList();

	public List<Refund> findList(String authenticode, String terminalNo, String orderNo, RefundReasonEnum reason, RefundTypeEnum type, Date begin, Date end, Integer userId, Boolean over);

	public List<Refund> findList(String authenticode, String terminalNo, String orderNo, RefundReasonEnum reason, RefundTypeEnum type, Date begin, Date end, Integer userId, Boolean over, String sort, String order, int pageNo, int pageSize);

	public int count(String authenticode, String terminalNo, String orderNo, RefundReasonEnum reason, RefundTypeEnum type, Date begin, Date end, Integer userId, Boolean over);

}
