package com.baiyi.order.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.baiyi.order.dao.UserDao;
import com.baiyi.order.model.User;

@Repository
public class UserDaoImpl extends CommonsDaoImpl<User> implements UserDao {

	@Override
	public void delete(List<User> users) {
		super.delete(users);
	}

	@SuppressWarnings("unchecked")
	@Override
	public User find(String name) {
		List<User> list = (List<User>) hibernateTemplate.find("from User as user where user.name = ?", name);
		return CollectionUtils.isEmpty(list) ? null : list.get(0);
	}

	@Override
	public List<User> findList(String name) {
		return this.findList(name, null, null, -1, -1);
	}

	@Override
	public List<User> findList(String name, String sort, String order, int pageNo, int pageSize) {
		StringBuffer queryString = new StringBuffer("from User as user where 1 = 1");
		Map<String, Object> map = new HashMap<>();

		if (StringUtils.isNotBlank(name)) {
			queryString.append(" and user.name like :name");
			map.put("name", "%" + name + "%");
		}

		if (StringUtils.isNotBlank(sort) && StringUtils.isNotBlank(order)) {
			queryString.append(" order by user." + sort + " " + order);
		}
		return super.findList(queryString.toString(), (pageNo - 1) * pageSize, pageSize, map);
	}

	@Override
	public int count(String name) {
		StringBuffer queryString = new StringBuffer("select count(*) from User as user where 1 = 1");
		Map<String, Object> map = new HashMap<>();

		if (StringUtils.isNotBlank(name)) {
			queryString.append(" and user.name like :name");
			map.put("name", "%" + name + "%");
		}
		return super.count(queryString.toString(), map);
	}

}
