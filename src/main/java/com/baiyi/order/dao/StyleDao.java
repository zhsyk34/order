package com.baiyi.order.dao;

import java.util.List;

import com.baiyi.order.model.Style;

public interface StyleDao {

	public void save(Style style);

	public void delete(Integer id);

	public void delete(Style style);

	public void delete(Integer[] ids);

	public void delete(List<Style> styles);

	public void update(Style style);

	public void merge(Style style);

	public Style find(Integer id);

	public Style find(String name);

	public List<Style> findList();

	public List<Style> findList(String name, Integer userId);

	public List<Style> findList(String name, Integer userId, String sort, String order, int pageNo, int pageSize);

	public int count(String name, Integer userId);

}
