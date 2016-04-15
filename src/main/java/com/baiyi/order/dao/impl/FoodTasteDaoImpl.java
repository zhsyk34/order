package com.baiyi.order.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.baiyi.order.dao.FoodTasteDao;
import com.baiyi.order.model.FoodTaste;
import com.baiyi.order.util.ValidateUtil;

@Repository
public class FoodTasteDaoImpl extends CommonsDaoImpl<FoodTaste> implements FoodTasteDao {

	@Override
	public void delete(List<FoodTaste> foodTastes) {
		super.delete(foodTastes);
	}

	@Override
	public List<FoodTaste> findList(Integer foodId, Integer tasteId) {
		StringBuffer queryString = new StringBuffer("from FoodTaste as foodTaste where 1 = 1");
		Map<String, Object> map = new HashMap<>();

		if (ValidateUtil.isPK(foodId)) {
			queryString.append(" and foodTaste.foodId = :foodId");
			map.put("foodId", foodId);
		}
		if (ValidateUtil.isPK(tasteId)) {
			queryString.append(" and foodTaste.tasteId = :tasteId");
			map.put("tasteId", tasteId);
		}
		return super.findList(queryString.toString(), map);
	}
}
