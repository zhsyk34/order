package com.baiyi.order.dao;

import java.util.List;

import com.baiyi.order.model.User;

public interface UserDao {

	public void save(User user);

	public void delete(Integer id);

	public void delete(User user);

	public void delete(Integer[] ids);

	public void delete(List<User> users);

	public void update(User user);

	public void merge(User user);

	public User find(Integer id);

	public User find(String name);

	public List<User> findList();

	public List<User> findList(String name);

	public List<User> findList(String name, String sort, String order, int pageNo, int pageSize);

	public int count(String name);
}
