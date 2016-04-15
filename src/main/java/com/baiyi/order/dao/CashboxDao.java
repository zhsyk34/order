package com.baiyi.order.dao;

import java.util.List;

import com.baiyi.order.model.Cashbox;

public interface CashboxDao {

	public void save(Cashbox cashbox);

	public void delete(Integer id);

	public void delete(Cashbox cashbox);

	public void delete(Integer[] ids);

	public void delete(List<Cashbox> cashboxs);

	public void update(Cashbox cashbox);

	public void merge(Cashbox cashbox);

	public Cashbox find(Integer id);

	public Cashbox findByTerminal(Integer terminalId);

	public List<Cashbox> findList();

}
