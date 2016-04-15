package com.baiyi.order.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.baiyi.order.dao.FoodStyleDao;
import com.baiyi.order.model.FoodStyle;
import com.baiyi.order.util.ValidateUtil;

@Repository
public class FoodStyleDaoImpl extends CommonsDaoImpl<FoodStyle> implements FoodStyleDao {

	@Override
	public void delete(List<FoodStyle> foodStyles) {
		super.delete(foodStyles);
	}

	@Override
	public List<FoodStyle> findList(Integer foodId, Integer styleId) {
		StringBuffer queryString = new StringBuffer("from FoodStyle as foodStyle where 1 = 1");
		Map<String, Object> map = new HashMap<>();

		if (ValidateUtil.isPK(foodId)) {
			queryString.append(" and foodStyle.foodId = :foodId");
			map.put("foodId", foodId);
		}
		if (ValidateUtil.isPK(styleId)) {
			queryString.append(" and foodStyle.styleId = :styleId");
			map.put("styleId", styleId);
		}
		return super.findList(queryString.toString(), map);
	}
}
