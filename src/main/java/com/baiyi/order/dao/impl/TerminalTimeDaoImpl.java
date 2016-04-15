package com.baiyi.order.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.baiyi.order.dao.TerminalTimeDao;
import com.baiyi.order.model.TerminalTime;
import com.baiyi.order.util.EnumList.RemoteTimeEnum;
import com.baiyi.order.util.ValidateUtil;

@Repository
public class TerminalTimeDaoImpl extends CommonsDaoImpl<TerminalTime> implements TerminalTimeDao {

	@Override
	public void delete(List<TerminalTime> terminalTimes) {
		super.delete(terminalTimes);
	}

	@Override
	public List<TerminalTime> findList(Integer terminalId, RemoteTimeEnum type) {
		StringBuffer queryString = new StringBuffer("from TerminalTime as terminalTime where 1 = 1");
		Map<String, Object> map = new HashMap<>();

		if (ValidateUtil.isPK(terminalId)) {
			queryString.append(" and terminalTime.terminalId = :terminalId");
			map.put("terminalId", terminalId);
		}
		if (type != null) {
			queryString.append(" and terminalTime.type = :type");
			map.put("type", type);
		}

		return super.findList(queryString.toString(), map);
	}
}
