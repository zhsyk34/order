package com.baiyi.order.dao;

import java.util.List;

import com.baiyi.order.model.TerminalSeat;

public interface TerminalSeatDao {

	public void save(TerminalSeat terminalSeat);

	public void delete(Integer id);

	public void delete(TerminalSeat terminalSeat);

	public void delete(Integer[] ids);

	public void delete(List<TerminalSeat> terminalSeats);

	public void update(TerminalSeat terminalSeat);

	public void merge(TerminalSeat terminalSeat);

	public TerminalSeat find(Integer id);

	public List<TerminalSeat> findList();

	public List<TerminalSeat> findList(Integer terminalId, Integer seatId);

}
