package com.baiyi.order.dao;

import java.util.List;

import com.baiyi.order.model.TerminalTime;
import com.baiyi.order.util.EnumList.RemoteTimeEnum;

public interface TerminalTimeDao {

	public void save(TerminalTime terminalTime);

	public void delete(Integer id);

	public void delete(TerminalTime terminalTime);

	public void delete(Integer[] ids);

	public void delete(List<TerminalTime> terminalTimes);

	public void update(TerminalTime terminalTime);

	public void merge(TerminalTime terminalTime);

	public TerminalTime find(Integer id);

	public List<TerminalTime> findList();

	public List<TerminalTime> findList(Integer terminalId, RemoteTimeEnum type);

}
