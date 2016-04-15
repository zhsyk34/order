package com.baiyi.order.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.baiyi.order.dao.SeatDao;
import com.baiyi.order.model.Seat;
import com.baiyi.order.util.ValidateUtil;

@Repository
public class SeatDaoImpl extends CommonsDaoImpl<Seat> implements SeatDao {

	@Override
	public void delete(List<Seat> seats) {
		super.delete(seats);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Seat find(String name) {
		List<Seat> list = (List<Seat>) hibernateTemplate.find("from Seat as seat where seat.name = ?", name);
		return CollectionUtils.isEmpty(list) ? null : list.get(0);
	}

	@Override
	public List<Seat> findList(String name, Integer userId) {
		return this.findList(name, userId, null, null, -1, -1);
	}

	@Override
	public List<Seat> findList(String name, Integer userId, String sort, String order, int pageNo, int pageSize) {
		StringBuffer queryString = new StringBuffer("from Seat as seat where 1 = 1");
		Map<String, Object> map = new HashMap<>();

		if (StringUtils.isNotBlank(name)) {
			queryString.append(" and seat.name like :name");
			map.put("name", "%" + name + "%");
		}
		if (ValidateUtil.isPK(userId)) {
			queryString.append(" and seat.userId = :userId");
			map.put("userId", userId);
		}
		if (StringUtils.isNotBlank(sort) && StringUtils.isNotBlank(order)) {
			queryString.append(" order by seat." + sort + " " + order);
		}

		return super.findList(queryString.toString(), (pageNo - 1) * pageSize, pageSize, map);
	}

	@Override
	public int count(String name, Integer userId) {
		StringBuffer queryString = new StringBuffer("select count(*) from Seat as seat where 1 = 1");
		Map<String, Object> map = new HashMap<>();

		if (StringUtils.isNotBlank(name)) {
			queryString.append(" and seat.name like :name");
			map.put("name", "%" + name + "%");
		}
		if (ValidateUtil.isPK(userId)) {
			queryString.append(" and seat.userId = :userId");
			map.put("userId", userId);
		}
		return super.count(queryString.toString(), map);
	}
}
