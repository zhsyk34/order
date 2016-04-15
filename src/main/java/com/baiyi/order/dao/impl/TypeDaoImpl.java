package com.baiyi.order.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.baiyi.order.dao.TypeDao;
import com.baiyi.order.model.Type;
import com.baiyi.order.util.ValidateUtil;

@Repository
public class TypeDaoImpl extends CommonsDaoImpl<Type> implements TypeDao {

	@Override
	public void delete(List<Type> types) {
		super.delete(types);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Type find(String name) {
		List<Type> list = (List<Type>) hibernateTemplate.find("from Type as type where type.name = ?", name);
		return CollectionUtils.isEmpty(list) ? null : list.get(0);
	}

	@Override
	public List<Type> findList(String name, Integer userId) {
		return findList(name, userId, null, null, -1, -1);
	}

	@Override
	public List<Type> findList(String name, Integer userId, String sort, String order, int pageNo, int pageSize) {
		StringBuffer queryString = new StringBuffer("from Type as type where 1 = 1");
		Map<String, Object> map = new HashMap<>();

		if (StringUtils.isNotBlank(name)) {
			queryString.append(" and type.name like :name");
			map.put("name", "%" + name + "%");
		}

		if (ValidateUtil.isPK(userId)) {
			queryString.append(" and type.userId = :userId");
			map.put("userId", userId);
		}

		if (StringUtils.isNotBlank(sort) && StringUtils.isNotBlank(order)) {
			queryString.append(" order by type." + sort + " " + order);
		}

		return super.findList(queryString.toString(), (pageNo - 1) * pageSize, pageSize, map);
	}

	@Override
	public int count(String name, Integer userId) {
		StringBuffer queryString = new StringBuffer("select count(*) from Type as type where 1 = 1");
		Map<String, Object> map = new HashMap<>();

		if (StringUtils.isNotBlank(name)) {
			queryString.append(" and type.name like :name");
			map.put("name", "%" + name + "%");
		}

		if (ValidateUtil.isPK(userId)) {
			queryString.append(" and type.userId = :userId");
			map.put("userId", userId);
		}
		return super.count(queryString.toString(), map);
	}
}
