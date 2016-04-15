package com.baiyi.order.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.baiyi.order.dao.SeatDao;
import com.baiyi.order.dao.TerminalSeatDao;
import com.baiyi.order.model.Seat;
import com.baiyi.order.model.TerminalSeat;
import com.baiyi.order.service.SeatService;

@Service
public class SeatServiceImpl implements SeatService {

	@Resource
	private SeatDao seatDao;
	@Resource
	private TerminalSeatDao terminalSeatDao;

	@Override
	public void save(Seat seat) {
		seatDao.save(seat);
	}

	@Override
	public void delete(Integer id) {
		seatDao.delete(id);
	}

	@Override
	public void delete(Seat seat) {
		seatDao.delete(seat);
	}

	@Override
	public void delete(Integer[] ids) {
		seatDao.delete(ids);
	}

	@Override
	public void delete(List<Seat> seats) {
		seatDao.delete(seats);
	}

	@Override
	public void update(Seat seat) {
		seatDao.update(seat);
	}

	@Override
	public void merge(Seat seat) {
		seatDao.merge(seat);
	}

	@Override
	public Seat find(Integer id) {
		return seatDao.find(id);
	}

	@Override
	public Seat find(String name) {
		return seatDao.find(name);
	}

	@Override
	public List<Seat> findList() {
		return seatDao.findList();
	}

	@Override
	public List<Seat> findList(String name, Integer userId) {
		return seatDao.findList(name, userId);
	}

	@Override
	public List<Seat> findList(String name, Integer userId, String sort, String order, int pageNo, int pageSize) {
		return seatDao.findList(name, userId, sort, order, pageNo, pageSize);
	}

	@Override
	public int count(String name, Integer userId) {
		return seatDao.count(name, userId);
	}

	@Override
	public void save(List<Seat> seats) {
		if (CollectionUtils.isNotEmpty(seats)) {
			for (Seat seat : seats) {
				seatDao.save(seat);
			}
		}
	}

	@Override
	public boolean exist(Integer id, String name) {
		Seat seat = this.find(name);
		return seat == null ? false : !seat.getId().equals(id);
	}

	@Override
	public boolean relate(Integer id) {
		List<TerminalSeat> list = terminalSeatDao.findList(null, id);
		return CollectionUtils.isNotEmpty(list);
	}

	@Override
	public boolean relate(Integer[] ids) {
		for (Integer id : ids) {
			if (this.relate(id)) {
				return true;
			}
		}
		return false;
	}

}
