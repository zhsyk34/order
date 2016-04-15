package com.baiyi.order.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.baiyi.order.dao.ActivityDao;
import com.baiyi.order.dao.FoodDao;
import com.baiyi.order.dao.FoodStyleDao;
import com.baiyi.order.dao.FoodTasteDao;
import com.baiyi.order.dao.TemplateFoodDao;
import com.baiyi.order.model.Activity;
import com.baiyi.order.model.Food;
import com.baiyi.order.model.FoodStyle;
import com.baiyi.order.model.FoodTaste;
import com.baiyi.order.model.TemplateFood;
import com.baiyi.order.service.FoodService;
import com.baiyi.order.util.ValidateUtil;
import com.baiyi.order.vo.FoodVO;

@Service
public class FoodServiceImpl implements FoodService {

	@Resource
	private FoodDao foodDao;
	@Resource
	private FoodTasteDao foodTasteDao;
	@Resource
	private FoodStyleDao foodStyleDao;
	@Resource
	private TemplateFoodDao templateFoodDao;
	@Resource
	private ActivityDao activityDao;

	@Override
	public void save(Food food) {
		foodDao.save(food);
	}

	@Override
	public void save(Food food, Integer[] tasteIds, Integer[] styleIds) {
		foodDao.save(food);
		Integer foodId = food.getId();

		if (ValidateUtil.isNotEmpty(tasteIds)) {
			for (Integer tasteId : tasteIds) {
				FoodTaste foodTaste = new FoodTaste();
				foodTaste.setFoodId(foodId);
				foodTaste.setTasteId(tasteId);
				foodTasteDao.save(foodTaste);
			}
		}
		if (ValidateUtil.isNotEmpty(styleIds)) {
			for (Integer styleId : styleIds) {
				FoodStyle foodStyle = new FoodStyle();
				foodStyle.setFoodId(foodId);
				foodStyle.setStyleId(styleId);
				foodStyleDao.save(foodStyle);
			}
		}
	}

	@Override
	public void delete(Integer id) {
		List<FoodTaste> foodTastes = foodTasteDao.findList(id, null);
		foodTasteDao.delete(foodTastes);

		List<FoodStyle> foodStyles = foodStyleDao.findList(id, null);
		foodStyleDao.delete(foodStyles);

		List<Activity> activities = activityDao.findList(null, id, null, null);
		activityDao.delete(activities);

		foodDao.delete(id);
	}

	@Override
	public void delete(Food food) {
		this.delete(food.getId());
	}

	@Override
	public void delete(Integer[] ids) {
		if (ValidateUtil.isNotEmpty(ids)) {
			for (Integer id : ids) {
				this.delete(id);
			}
		}
	}

	@Override
	public void delete(List<Food> foods) {
		if (CollectionUtils.isNotEmpty(foods)) {
			for (Food food : foods) {
				this.delete(food);
			}
		}
	}

	@Override
	public void update(Food food) {
		foodDao.update(food);
	}

	@Override
	public void update(Food food, Integer[] tasteIds, Integer[] styleIds) {
		foodDao.update(food);
		Integer foodId = food.getId();

		List<FoodTaste> foodTastes = foodTasteDao.findList(foodId, null);
		foodTasteDao.delete(foodTastes);
		List<FoodStyle> foodStyles = foodStyleDao.findList(foodId, null);
		foodStyleDao.delete(foodStyles);

		if (ValidateUtil.isNotEmpty(tasteIds)) {
			for (Integer tasteId : tasteIds) {
				FoodTaste foodTaste = new FoodTaste();
				foodTaste.setFoodId(foodId);
				foodTaste.setTasteId(tasteId);
				foodTasteDao.save(foodTaste);
			}
		}
		if (ValidateUtil.isNotEmpty(styleIds)) {
			for (Integer styleId : styleIds) {
				FoodStyle foodStyle = new FoodStyle();
				foodStyle.setFoodId(foodId);
				foodStyle.setStyleId(styleId);
				foodStyleDao.save(foodStyle);
			}
		}
	}

	@Override
	public void merge(Food food) {
		foodDao.merge(food);
	}

	@Override
	public void merge(Food food, Integer[] tasteIds, Integer[] styleIds) {
		if (ValidateUtil.isPK(food.getId())) {
			this.update(food, tasteIds, styleIds);
		} else {
			this.save(food, tasteIds, styleIds);
		}
	}

	@Override
	public Food find(Integer id) {
		return foodDao.find(id);
	}

	@Override
	public Food find(String name) {
		return foodDao.find(name);
	}

	@Override
	public List<Food> findList() {
		return foodDao.findList();
	}

	@Override
	public List<Food> findList(String name, Integer typeId, Integer userId) {
		return foodDao.findList(name, typeId, userId);
	}

	@Override
	public List<Food> findList(String name, Integer typeId, Integer userId, String sort, String order, int pageNo, int pageSize) {
		return foodDao.findList(name, typeId, userId, sort, order, pageNo, pageSize);
	}

	@Override
	public int count(String name, Integer typeId, Integer userId) {
		return foodDao.count(name, typeId, userId);
	}

	@Override
	public FoodVO findVO(Integer id) {
		return foodDao.findVO(id);
	}

	@Override
	public List<FoodVO> findVOList() {
		return foodDao.findVOList();
	}

	@Override
	public List<FoodVO> findVOList(String name, Integer typeId, Integer userId) {
		return foodDao.findVOList(name, typeId, userId);
	}

	@Override
	public List<FoodVO> findVOList(String name, Integer typeId, Integer userId, String sort, String order, int pageNo, int pageSize) {
		return foodDao.findVOList(name, typeId, userId, sort, order, pageNo, pageSize);
	}

	@Override
	public boolean exist(Integer id, String name) {
		Food food = foodDao.find(name);
		return food == null ? false : !food.getId().equals(id);
	}

	@Override
	public boolean relate(Integer id) {
		List<TemplateFood> list = templateFoodDao.findList(null, id);
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
