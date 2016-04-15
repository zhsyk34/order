package com.baiyi.order.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.baiyi.order.dao.FoodStyleDao;
import com.baiyi.order.dao.StyleDao;
import com.baiyi.order.dao.TasteDao;
import com.baiyi.order.model.FoodStyle;
import com.baiyi.order.model.Style;
import com.baiyi.order.model.Taste;
import com.baiyi.order.service.StyleService;

@Service
public class StyleServiceImpl implements StyleService {

	@Resource
	private StyleDao styleDao;
	@Resource
	private TasteDao tasteDao;
	@Resource
	private FoodStyleDao foodStyleDao;

	@Override
	public void save(Style style) {
		styleDao.save(style);
	}

	@Override
	public void delete(Integer id) {
		styleDao.delete(id);
	}

	@Override
	public void delete(Style style) {
		styleDao.delete(style);
	}

	@Override
	public void delete(Integer[] ids) {
		styleDao.delete(ids);
	}

	@Override
	public void delete(List<Style> styles) {
		styleDao.delete(styles);
	}

	@Override
	public void update(Style style) {
		styleDao.update(style);
	}

	@Override
	public void merge(Style style) {
		styleDao.merge(style);
	}

	@Override
	public Style find(Integer id) {
		return styleDao.find(id);
	}

	@Override
	public Style find(String name) {
		return styleDao.find(name);
	}

	@Override
	public List<Style> findList() {
		return styleDao.findList();
	}

	@Override
	public List<Style> findList(String name, Integer userId) {
		return styleDao.findList(name, userId);
	}

	@Override
	public List<Style> findList(String name, Integer userId, String sort, String order, int pageNo, int pageSize) {
		return styleDao.findList(name, userId, sort, order, pageNo, pageSize);
	}

	@Override
	public int count(String name, Integer userId) {
		return styleDao.count(name, userId);
	}

	@Override
	public boolean exist(Integer id, String name) {
		Style style = this.find(name);
		return style == null ? false : !style.getId().equals(id);
	}

	@Override
	public boolean relate(Integer id) {
		List<Taste> tastes = tasteDao.findList(null, id, null);
		List<FoodStyle> foodStyles = foodStyleDao.findList(null, id);
		return CollectionUtils.isNotEmpty(tastes) || CollectionUtils.isNotEmpty(foodStyles);
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
