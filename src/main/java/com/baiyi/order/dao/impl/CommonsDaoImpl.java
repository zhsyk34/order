package com.baiyi.order.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.HibernateTemplate;

import com.baiyi.order.dao.CommonsDao;
import com.baiyi.order.util.ValidateUtil;

public class CommonsDaoImpl<Entity> implements CommonsDao<Entity> {

	private Class<Entity> entityClass;

	@Resource
	protected HibernateTemplate hibernateTemplate;

	@SuppressWarnings("unchecked")
	public CommonsDaoImpl() {
		entityClass = (Class<Entity>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@Override
	public void save(Entity entity) {
		hibernateTemplate.save(entity);
	}

	@Override
	public void delete(Integer id) {
		hibernateTemplate.delete(find(id));
	}

	@Override
	public void delete(Entity entity) {
		hibernateTemplate.delete(entity);
	}

	@Override
	public void delete(Integer[] ids) {
		if (ValidateUtil.isNotEmpty(ids)) {
			hibernateTemplate.execute(new HibernateCallback<Integer>() {
				@Override
				public Integer doInHibernate(Session session) throws HibernateException {
					String queryString = "delete from " + entityClass.getSimpleName() + " where id in (:ids)";
					return session.createQuery(queryString).setParameterList("ids", ids).executeUpdate();
				}
			});
		}
	}

	@Override
	public void delete(Collection<Entity> entities) {
		if (CollectionUtils.isNotEmpty(entities)) {
			hibernateTemplate.deleteAll(entities);
		}
	}

	@Override
	public void update(Entity entity) {
		hibernateTemplate.update(entity);
	}

	@Override
	public void merge(Entity entity) {
		hibernateTemplate.saveOrUpdate(entity);
	}

	@Override
	public Entity find(Integer id) {
		return hibernateTemplate.get(entityClass, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Entity> findList() {
		return (List<Entity>) hibernateTemplate.find("from " + entityClass.getSimpleName());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Entity> findList(String queryString, Map<String, Object> map) {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query query = session.createQuery(queryString);
		if (map != null) {
			query.setProperties(map);
		}
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Entity> findList(String queryString, int offset, int length, Map<String, Object> map) {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query query = session.createQuery(queryString);
		if (map != null) {
			query.setProperties(map);
		}
		if (offset >= 0 && length > 0) {
			query.setFirstResult(offset).setMaxResults(length);
		}
		return query.list();
	}

	@Override
	public int count(String queryString, Map<String, Object> map) {
		/* method -1: error,need obj values... */
		// return ((Long) hibernateTemplate.iterate(queryString,
		// objs...).next()).intValue();

		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query query = session.createQuery(queryString);
		if (map != null) {
			query.setProperties(map);
		}

		return ((Long) query.iterate().next()).intValue();
		/* method 2: */
		// return Integer.parseInt(query.uniqueResult().toString());
	}

	@Override
	public int batch(String queryString, Map<String, Object> map) {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query query = session.createQuery(queryString);
		if (map != null) {
			query.setProperties(map);
		}
		return query.executeUpdate();
	}
}
