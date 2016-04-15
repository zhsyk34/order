package com.baiyi.order.service;

import java.util.List;

import com.baiyi.order.model.Type;

public interface TypeService {

	public void save(Type type);

	public void delete(Integer id);

	public void delete(Type type);

	public void delete(Integer[] ids);

	public void delete(List<Type> types);

	public void update(Type type);

	public void merge(Type type);

	public Type find(Integer id);

	public Type find(String name);

	public List<Type> findList();

	public List<Type> findList(String name, Integer userId);

	public List<Type> findList(String name, Integer userId, String sort, String order, int pageNo, int pageSize);

	public int count(String name, Integer userId);

	public boolean exist(Integer id, String name);

	public boolean relate(Integer id);

	public boolean relate(Integer[] ids);
}
