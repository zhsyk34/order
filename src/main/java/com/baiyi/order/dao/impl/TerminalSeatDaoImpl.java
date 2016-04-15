package com.baiyi.order.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.baiyi.order.dao.TerminalSeatDao;
import com.baiyi.order.model.TerminalSeat;
import com.baiyi.order.util.ValidateUtil;

@Repository
public class TerminalSeatDaoImpl extends CommonsDaoImpl<TerminalSeat> implements TerminalSeatDao {

	@Override
	public void delete(List<TerminalSeat> terminalSeats) {
		super.delete(terminalSeats);
	}

	@Override
	public List<TerminalSeat> findList(Integer terminalId, Integer seatId) {
		StringBuffer queryString = new StringBuffer("from TerminalSeat as terminalSeat where 1 = 1");
		Map<String, Object> map = new HashMap<>();

		if (ValidateUtil.isPK(terminalId)) {
			queryString.append(" and terminalSeat.terminalId = :terminalId");
			map.put("terminalId", terminalId);
		}
		if (ValidateUtil.isPK(seatId)) {
			queryString.append(" and terminalSeat.seatId = :seatId");
			map.put("seatId", seatId);
		}
		return super.findList(queryString.toString(), map);
	}

}
