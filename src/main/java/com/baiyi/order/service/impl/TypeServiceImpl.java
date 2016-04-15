package com.baiyi.order.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.baiyi.order.dao.FoodDao;
import com.baiyi.order.dao.TypeDao;
import com.baiyi.order.model.Food;
import com.baiyi.order.model.Type;
import com.baiyi.order.service.TypeService;

@Service
public class TypeServiceImpl implements TypeService {

	@Resource
	private TypeDao typeDao;
	@Resource
	private FoodDao foodDao;

	@Override
	public void save(Type type) {
		typeDao.save(type);
	}

	@Override
	public void delete(Integer id) {
		typeDao.delete(id);
	}

	@Override
	public void delete(Type type) {
		typeDao.delete(type);
	}

	@Override
	public void delete(Integer[] ids) {
		typeDao.delete(ids);
	}

	@Override
	public void delete(List<Type> types) {
		typeDao.delete(types);
	}

	@Override
	public void update(Type type) {
		typeDao.update(type);
	}

	@Override
	public void merge(Type type) {
		typeDao.merge(type);
	}

	@Override
	public Type find(Integer id) {
		return typeDao.find(id);
	}

	@Override
	public Type find(String name) {
		return typeDao.find(name);
	}

	@Override
	public List<Type> findList() {
		return typeDao.findList();
	}

	@Override
	public List<Type> findList(String name, Integer userId) {
		return typeDao.findList(name, userId);
	}

	@Override
	public List<Type> findList(String name, Integer userId, String sort, String order, int pageNo, int pageSize) {
		return typeDao.findList(name, userId, sort, order, pageNo, pageSize);
	}

	@Override
	public int count(String name, Integer userId) {
		return typeDao.count(name, userId);
	}

	@Override
	public boolean exist(Integer id, String name) {
		Type type = this.find(name);
		return type == null ? false : !type.getId().equals(id);
	}

	@Override
	public boolean relate(Integer id) {
		List<Food> list = foodDao.findList(null, id, null);
		return CollectionUtils.isNotEmpty(list);
	}

	@Override
	public boolean relate(Integer[] ids) {
		for (Integer id : ids) {
			if (this.relate(id)) {
				return true;
			}
		}
		return false;
	}

}
