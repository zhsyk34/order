package com.baiyi.order.dao;

import java.util.List;

import com.baiyi.order.model.Terminal;
import com.baiyi.order.util.EnumList.TerminalTypeEnum;

public interface TerminalDao {

	public void save(Terminal terminal);

	public void delete(Integer id);

	public void delete(Terminal terminal);

	public void delete(Integer[] ids);

	public void delete(List<Terminal> terminals);

	public void update(Terminal terminal);

	public void merge(Terminal terminal);

	public Terminal find(Integer id);

	public Terminal find(String terminalNo);

	public List<Terminal> findList();

	public List<Terminal> findList(String terminalNo, TerminalTypeEnum type, Integer userId);

	public List<Terminal> findList(String terminalNo, TerminalTypeEnum type, Integer userId, String sort, String order, int pageNo, int pageSize);

	public int count(String terminalNo, TerminalTypeEnum type, Integer userId);

}
