package com.baiyi.order.dao;

import java.util.Date;
import java.util.List;

import com.baiyi.order.model.TerminalConnect;
import com.baiyi.order.vo.Record;

public interface TerminalConnectDao {

	public void save(TerminalConnect terminalConnect);

	public void delete(Integer id);

	public void delete(TerminalConnect terminalConnect);

	public void delete(Integer[] ids);

	public void delete(List<TerminalConnect> terminalConnects);

	public void update(TerminalConnect terminalConnect);

	public void merge(TerminalConnect terminalConnect);

	public TerminalConnect find(Integer id);

	public TerminalConnect findLast(Integer terminalId);

	public List<TerminalConnect> findList();

	public List<TerminalConnect> findList(Integer terminalId, Date begin, Date end, Boolean online);

	public List<TerminalConnect> findList(Integer terminalId, Date begin, Date end, Boolean online, String sort, String order, int pageNo, int pageSize);

	public int count(Integer terminalId, Date begin, Date end, Boolean online);

	public List<Record> findVOList(Integer terminalId, Date begin, Date end, Boolean online);

	public List<Record> findVOList(Integer terminalId, Date begin, Date end, Boolean online, String sort, String order, int pageNo, int pageSize);

	public List<Record> findVOList(String terminalNo, Date begin, Date end, Boolean online);

	public List<Record> findVOList(String terminalNo, Date begin, Date end, Boolean online, String sort, String order, int pageNo, int pageSize);

	public int count(String terminalNo, Date begin, Date end, Boolean online);

}
