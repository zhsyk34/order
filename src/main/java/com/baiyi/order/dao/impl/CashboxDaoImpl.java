package com.baiyi.order.dao.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Repository;

import com.baiyi.order.dao.CashboxDao;
import com.baiyi.order.model.Cashbox;

@Repository
public class CashboxDaoImpl extends CommonsDaoImpl<Cashbox> implements CashboxDao {

	@Override
	public void delete(List<Cashbox> cashboxs) {
		super.delete(cashboxs);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Cashbox findByTerminal(Integer terminalId) {
		List<Cashbox> list = (List<Cashbox>) hibernateTemplate.find("from Cashbox as cashbox where cashbox.terminalId = ?", terminalId);
		return CollectionUtils.isEmpty(list) ? null : list.get(0);
	}

}
