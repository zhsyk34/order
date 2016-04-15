package com.baiyi.order.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.baiyi.order.dao.MarqueeDao;
import com.baiyi.order.model.Marquee;
import com.baiyi.order.util.ValidateUtil;

@Repository
public class MarqueeDaoImpl extends CommonsDaoImpl<Marquee> implements MarqueeDao {

	@Override
	public void delete(List<Marquee> marquees) {
		super.delete(marquees);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Marquee find(String title) {
		List<Marquee> list = (List<Marquee>) hibernateTemplate.find("from Marquee as marquee where marquee.title = ?", title);
		return CollectionUtils.isEmpty(list) ? null : list.get(0);
	}

	@Override
	public List<Marquee> findList(String title, String content, Integer userId) {
		return this.findList(title, content, userId, null, null, -1, -1);
	}

	@Override
	public List<Marquee> findList(String title, String content, Integer userId, String sort, String order, int pageNo, int pageSize) {
		StringBuffer queryString = new StringBuffer("from Marquee as marquee where 1 = 1");
		Map<String, Object> map = new HashMap<>();

		if (StringUtils.isNotBlank(title)) {
			queryString.append(" and marquee.title like :title");
			map.put("title", "%" + title + "%");
		}
		if (StringUtils.isNotBlank(content)) {
			queryString.append(" and marquee.content like :content");
			map.put("content", "%" + content + "%");
		}

		if (ValidateUtil.isPK(userId)) {
			queryString.append(" and marquee.userId = :userId");
			map.put("userId", userId);
		}

		if (StringUtils.isNotBlank(sort) && StringUtils.isNotBlank(order)) {
			queryString.append(" order by marquee." + sort + " " + order);
		}

		return super.findList(queryString.toString(), (pageNo - 1) * pageSize, pageSize, map);
	}

	@Override
	public int count(String title, String content, Integer userId) {
		StringBuffer queryString = new StringBuffer("select count(*) from Marquee as marquee where 1 = 1");
		Map<String, Object> map = new HashMap<>();

		if (StringUtils.isNotBlank(title)) {
			queryString.append(" and marquee.title like :title");
			map.put("title", "%" + title + "%");
		}
		if (StringUtils.isNotBlank(content)) {
			queryString.append(" and marquee.content like :content");
			map.put("content", "%" + content + "%");
		}

		if (ValidateUtil.isPK(userId)) {
			queryString.append(" and marquee.userId = :userId");
			map.put("userId", userId);
		}
		return super.count(queryString.toString(), map);
	}

}
