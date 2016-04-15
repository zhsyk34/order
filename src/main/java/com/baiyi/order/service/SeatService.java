package com.baiyi.order.service;

import java.util.List;

import com.baiyi.order.model.Seat;

public interface SeatService {

	public void save(Seat seat);

	public void delete(Integer id);

	public void delete(Seat seat);

	public void delete(Integer[] ids);

	public void delete(List<Seat> seats);

	public void update(Seat seat);

	public void merge(Seat seat);

	public Seat find(Integer id);

	public Seat find(String name);

	public List<Seat> findList();

	public List<Seat> findList(String name, Integer userId);

	public List<Seat> findList(String name, Integer userId, String sort, String order, int pageNo, int pageSize);

	public int count(String name, Integer userId);

	/**/
	public void save(List<Seat> seats);

	public boolean exist(Integer id, String name);

	public boolean relate(Integer id);

	public boolean relate(Integer[] ids);
}
