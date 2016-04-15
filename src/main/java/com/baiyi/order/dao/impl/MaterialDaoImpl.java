package com.baiyi.order.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.baiyi.order.dao.MaterialDao;
import com.baiyi.order.model.Material;
import com.baiyi.order.util.EnumList.MaterialTypeEnum;
import com.baiyi.order.util.ValidateUtil;

@Repository
public class MaterialDaoImpl extends CommonsDaoImpl<Material> implements MaterialDao {

	@Override
	public void delete(List<Material> materials) {
		super.delete(materials);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Material find(String name) {
		List<Material> list = (List<Material>) hibernateTemplate.find("from Material as material where material.name = ?", name);
		return CollectionUtils.isEmpty(list) ? null : list.get(0);
	}

	@Override
	public List<Material> findList(String name, MaterialTypeEnum type, Integer userId) {
		return this.findList(name, type, userId, null, null, -1, -1);
	}

	@Override
	public List<Material> findList(String name, MaterialTypeEnum type, Integer userId, String sort, String order, int pageNo, int pageSize) {
		StringBuffer queryString = new StringBuffer("from Material as material where 1 = 1");
		Map<String, Object> map = new HashMap<>();

		if (StringUtils.isNotBlank(name)) {
			queryString.append(" and material.name like :name");
			map.put("name", "%" + name + "%");
		}
		if (type != null) {
			queryString.append(" and material.type = :type");
			map.put("type", type);
		}
		if (ValidateUtil.isPK(userId)) {
			queryString.append(" and material.userId = :userId");
			map.put("userId", userId);
		}
		if (StringUtils.isNotBlank(sort) && StringUtils.isNotBlank(order)) {
			queryString.append(" order by material." + sort + " " + order);
		}
		return super.findList(queryString.toString(), (pageNo - 1) * pageSize, pageSize, map);
	}

	@Override
	public int count(String name, MaterialTypeEnum type, Integer userId) {
		StringBuffer queryString = new StringBuffer("select count(*) from Material as material where 1 = 1");
		Map<String, Object> map = new HashMap<>();

		if (StringUtils.isNotBlank(name)) {
			queryString.append(" and material.name like :name");
			map.put("name", "%" + name + "%");
		}
		if (type != null) {
			queryString.append(" and material.type = :type");
			map.put("type", type);
		}
		if (ValidateUtil.isPK(userId)) {
			queryString.append(" and material.userId = :userId");
			map.put("userId", userId);
		}
		return super.count(queryString.toString(), map);
	}

}
