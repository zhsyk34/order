package com.baiyi.order.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baiyi.order.dao.CashboxDao;
import com.baiyi.order.model.Cashbox;
import com.baiyi.order.service.CashboxService;

@Service
public class CashboxServiceImpl implements CashboxService {

	@Resource
	private CashboxDao cashboxDao;

	@Override
	public void save(Cashbox cashbox) {
		cashboxDao.save(cashbox);
	}

	@Override
	public void delete(Integer id) {
		cashboxDao.delete(id);
	}

	@Override
	public void delete(Cashbox cashbox) {
		cashboxDao.delete(cashbox);
	}

	@Override
	public void delete(Integer[] ids) {
		cashboxDao.delete(ids);
	}

	@Override
	public void delete(List<Cashbox> cashboxs) {
		cashboxDao.delete(cashboxs);
	}

	@Override
	public void update(Cashbox cashbox) {
		cashboxDao.update(cashbox);
	}

	@Override
	public void merge(Cashbox cashbox) {
		cashboxDao.merge(cashbox);
	}

	@Override
	public Cashbox find(Integer id) {
		return cashboxDao.find(id);
	}

	@Override
	public Cashbox findByTerminal(Integer terminalId) {
		return cashboxDao.findByTerminal(terminalId);
	}

	@Override
	public List<Cashbox> findList() {
		return cashboxDao.findList();
	}

}
