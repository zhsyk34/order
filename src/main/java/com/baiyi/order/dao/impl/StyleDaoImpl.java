package com.baiyi.order.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.baiyi.order.dao.StyleDao;
import com.baiyi.order.model.Style;
import com.baiyi.order.util.ValidateUtil;

@Repository
public class StyleDaoImpl extends CommonsDaoImpl<Style> implements StyleDao {

	@Override
	public void delete(List<Style> styles) {
		super.delete(styles);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Style find(String name) {
		List<Style> list = (List<Style>) hibernateTemplate.find("from Style as style where style.name = ?", name);
		return CollectionUtils.isEmpty(list) ? null : list.get(0);
	}

	@Override
	public List<Style> findList(String name, Integer userId) {
		return this.findList(name, userId, null, null, -1, -1);
	}

	@Override
	public List<Style> findList(String name, Integer userId, String sort, String order, int pageNo, int pageSize) {
		StringBuffer queryString = new StringBuffer("from Style as style where 1 = 1");
		Map<String, Object> map = new HashMap<>();

		if (StringUtils.isNotBlank(name)) {
			queryString.append(" and style.name like :name");
			map.put("name", "%" + name + "%");
		}
		if (ValidateUtil.isPK(userId)) {
			queryString.append(" and style.userId = :userId");
			map.put("userId", userId);
		}
		if (StringUtils.isNotBlank(sort) && StringUtils.isNotBlank(order)) {
			queryString.append(" order by style." + sort + " " + order);
		}

		return super.findList(queryString.toString(), (pageNo - 1) * pageSize, pageSize, map);
	}

	@Override
	public int count(String name, Integer userId) {
		StringBuffer queryString = new StringBuffer("select count(*) from Style as style where 1 = 1");
		Map<String, Object> map = new HashMap<>();

		if (StringUtils.isNotBlank(name)) {
			queryString.append(" and style.name like :name");
			map.put("name", "%" + name + "%");
		}
		if (ValidateUtil.isPK(userId)) {
			queryString.append(" and style.userId = :userId");
			map.put("userId", userId);
		}
		return super.count(queryString.toString(), map);
	}

}
