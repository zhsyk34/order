package com.baiyi.order.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface CommonsDao<Entity> {

	public void save(Entity entity);

	public void delete(Integer id);

	public void delete(Entity entity);

	public void delete(Integer[] ids);

	public void delete(Collection<Entity> entities);

	public void update(Entity entity);

	public void merge(Entity entity);

	public Entity find(Integer id);

	public List<Entity> findList();

	public List<Entity> findList(String queryString, Map<String, Object> map);

	public List<Entity> findList(String queryString, int offset, int length, Map<String, Object> map);

	public int count(String queryString, Map<String, Object> map);

	public int batch(String queryString, Map<String, Object> map);

}
