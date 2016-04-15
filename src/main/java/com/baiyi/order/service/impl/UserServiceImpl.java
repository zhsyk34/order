package com.baiyi.order.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.baiyi.order.dao.UserDao;
import com.baiyi.order.model.User;
import com.baiyi.order.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Resource
	private UserDao userDao;

	@Override
	public void save(User user) {
		userDao.save(user);
	}

	@Override
	public void delete(Integer id) {
		userDao.delete(id);
	}

	@Override
	public void delete(User user) {
		userDao.delete(user);
	}

	@Override
	public void delete(Integer[] ids) {
		userDao.delete(ids);
	}

	@Override
	public void delete(List<User> users) {
		userDao.delete(users);
	}

	@Override
	public void update(User user) {
		userDao.update(user);
	}

	@Override
	public void merge(User user) {
		userDao.merge(user);
	}

	@Override
	public User find(Integer id) {
		return userDao.find(id);
	}

	@Override
	public User find(String name) {
		return userDao.find(name);
	}

	@Override
	public List<User> findList() {
		return userDao.findList();
	}

	@Override
	public List<User> findList(String name) {
		return userDao.findList(name);
	}

	@Override
	public List<User> findList(String name, String sort, String order, int pageNo, int pageSize) {
		return userDao.findList(name, sort, order, pageNo, pageSize);
	}

	@Override
	public int count(String name) {
		return userDao.count(name);
	}

	@Override
	public boolean exist(Integer id, String name) {
		User user = this.find(name);
		return user == null ? false : !user.getId().equals(id);
	}

	@Override
	public User login(String name, String password) {
		if (StringUtils.isBlank(name) || StringUtils.isBlank(password)) {
			return null;
		}
		User user = this.find(name);
		if (user == null || !password.equals(user.getPassword())) {
			return null;
		}
		return user;
	}

}
