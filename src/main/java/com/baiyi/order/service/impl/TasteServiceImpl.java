package com.baiyi.order.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.baiyi.order.dao.FoodTasteDao;
import com.baiyi.order.dao.TasteDao;
import com.baiyi.order.model.FoodTaste;
import com.baiyi.order.model.Taste;
import com.baiyi.order.service.TasteService;
import com.baiyi.order.vo.TasteVO;

@Service
public class TasteServiceImpl implements TasteService {

	@Resource
	private TasteDao tasteDao;
	@Resource
	private FoodTasteDao foodTasteDao;

	@Override
	public void save(Taste taste) {
		tasteDao.save(taste);
	}

	@Override
	public void delete(Integer id) {
		tasteDao.delete(id);
	}

	@Override
	public void delete(Taste taste) {
		tasteDao.delete(taste);
	}

	@Override
	public void delete(Integer[] ids) {
		tasteDao.delete(ids);
	}

	@Override
	public void delete(List<Taste> tastes) {
		tasteDao.delete(tastes);
	}

	@Override
	public void update(Taste taste) {
		tasteDao.update(taste);
	}

	@Override
	public void merge(Taste taste) {
		tasteDao.merge(taste);
	}

	@Override
	public Taste find(Integer id) {
		return tasteDao.find(id);
	}

	@Override
	public Taste find(String name) {
		return tasteDao.find(name);
	}

	@Override
	public List<Taste> findList() {
		return tasteDao.findList();
	}

	@Override
	public int count(String name, Integer styleId, Integer userId) {
		return tasteDao.count(name, styleId, userId);
	}

	@Override
	public TasteVO findVO(Integer id) {
		return tasteDao.findVO(id);
	}

	@Override
	public List<TasteVO> findVOList() {
		return tasteDao.findVOList();
	}

	@Override
	public List<TasteVO> findVOList(String name, Integer styleId, Integer userId) {
		return tasteDao.findVOList(name, styleId, userId);
	}

	@Override
	public List<TasteVO> findVOList(String name, Integer styleId, Integer userId, String sort, String order, int pageNo, int pageSize) {
		return tasteDao.findVOList(name, styleId, userId, sort, order, pageNo, pageSize);
	}

	@Override
	public boolean exist(Integer id, String name) {
		Taste taste = this.find(name);
		return taste == null ? false : !taste.getId().equals(id);
	}

	@Override
	public boolean relate(Integer id) {
		List<FoodTaste> list = foodTasteDao.findList(null, id);
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
