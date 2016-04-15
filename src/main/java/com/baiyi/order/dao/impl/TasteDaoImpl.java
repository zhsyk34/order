package com.baiyi.order.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.baiyi.order.dao.TasteDao;
import com.baiyi.order.model.Taste;
import com.baiyi.order.util.ValidateUtil;
import com.baiyi.order.vo.TasteVO;

@Repository
public class TasteDaoImpl extends CommonsDaoImpl<Taste> implements TasteDao {

	@Override
	public void delete(List<Taste> tastes) {
		super.delete(tastes);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Taste find(String name) {
		List<Taste> list = (List<Taste>) hibernateTemplate.find("from Taste as taste where taste.name = ?", name);
		return CollectionUtils.isEmpty(list) ? null : list.get(0);
	}

	@Override
	public List<Taste> findList(String name, Integer styleId, Integer userId) {
		return findList(name, styleId, userId, null, null, -1, -1);

	}

	@Override
	public List<Taste> findList(String name, Integer styleId, Integer userId, String sort, String order, int pageNo, int pageSize) {
		StringBuffer queryString = new StringBuffer("from Taste as taste where 1 = 1");
		Map<String, Object> map = new HashMap<>();

		if (StringUtils.isNotBlank(name)) {
			queryString.append(" and taste.name like :name");
			map.put("name", "%" + name + "%");
		}

		if (ValidateUtil.isPK(styleId)) {
			queryString.append(" and taste.styleId = :styleId");
			map.put("styleId", styleId);
		}

		if (ValidateUtil.isPK(userId)) {
			queryString.append(" and taste.userId = :userId");
			map.put("userId", userId);
		}

		if (StringUtils.isNotBlank(sort) && StringUtils.isNotBlank(order)) {
			queryString.append(" order by taste." + sort + " " + order);
		}

		return super.findList(queryString.toString(), (pageNo - 1) * pageSize, pageSize, map);
	}

	@Override
	public int count(String name, Integer styleId, Integer userId) {
		StringBuffer queryString = new StringBuffer("select count(*) from Taste as taste where 1 = 1");
		Map<String, Object> map = new HashMap<>();

		if (StringUtils.isNotBlank(name)) {
			queryString.append(" and taste.name like :name");
			map.put("name", "%" + name + "%");
		}

		if (ValidateUtil.isPK(styleId)) {
			queryString.append(" and taste.styleId = :styleId");
			map.put("styleId", styleId);
		}

		if (ValidateUtil.isPK(userId)) {
			queryString.append(" and taste.userId = :userId");
			map.put("userId", userId);
		}
		return super.count(queryString.toString(), map);
	}

	@SuppressWarnings("unchecked")
	@Override
	public TasteVO findVO(Integer id) {
		StringBuffer queryString = new StringBuffer("select taste.*, style.name as styleName");
		queryString.append(" from Taste as taste left join Style as style");
		queryString.append(" on taste.styleId = style.id where taste.id = :id");

		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query query = session.createSQLQuery(queryString.toString());
		query.setResultTransformer(Transformers.aliasToBean(TasteVO.class));
		query.setParameter("id", id);

		List<TasteVO> list = (List<TasteVO>) query.list();
		return CollectionUtils.isEmpty(list) ? null : list.get(0);
	}

	@Override
	public List<TasteVO> findVOList() {
		return this.findVOList(null, null, null);
	}

	@Override
	public List<TasteVO> findVOList(String name, Integer styleId, Integer userId) {
		return this.findVOList(name, styleId, userId, null, null, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TasteVO> findVOList(String name, Integer styleId, Integer userId, String sort, String order, int pageNo, int pageSize) {
		StringBuffer queryString = new StringBuffer("select taste.*, style.name as styleName");
		queryString.append(" from Taste as taste left join Style as style");
		queryString.append(" on taste.styleId = style.id where 1 = 1");

		Map<String, Object> map = new HashMap<>();

		if (StringUtils.isNotBlank(name)) {
			queryString.append(" and taste.name like :name");
			map.put("name", "%" + name + "%");
		}

		if (ValidateUtil.isPK(styleId)) {
			queryString.append(" and taste.styleId = :styleId");
			map.put("styleId", styleId);
		}

		if (ValidateUtil.isPK(userId)) {
			queryString.append(" and taste.userId = :userId");
			map.put("userId", userId);
		}

		if (StringUtils.isNotBlank(sort) && StringUtils.isNotBlank(order)) {
			queryString.append(" order by taste." + sort + " " + order);
		}

		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query query = session.createSQLQuery(queryString.toString());
		query.setResultTransformer(Transformers.aliasToBean(TasteVO.class));
		query.setProperties(map);

		if (pageNo >= 0 && pageSize > 0) {
			query.setFirstResult((pageNo - 1) * pageSize).setMaxResults(pageSize);
		}

		return (List<TasteVO>) query.list();
	}
}
