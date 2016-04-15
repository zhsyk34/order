package com.baiyi.order.dao;

import java.util.List;

import com.baiyi.order.model.Marquee;

public interface MarqueeDao {

	public void save(Marquee marquee);

	public void delete(Integer id);

	public void delete(Marquee marquee);

	public void delete(Integer[] ids);

	public void delete(List<Marquee> marquees);

	public void update(Marquee marquee);

	public void merge(Marquee marquee);

	public Marquee find(Integer id);

	public Marquee find(String title);

	public List<Marquee> findList();

	public List<Marquee> findList(String title, String content, Integer userId);

	public List<Marquee> findList(String title, String content, Integer userId, String sort, String order, int pageNo, int pageSize);

	public int count(String title, String content, Integer userId);

}
