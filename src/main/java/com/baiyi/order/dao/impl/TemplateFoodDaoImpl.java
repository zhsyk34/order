package com.baiyi.order.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.baiyi.order.dao.TemplateFoodDao;
import com.baiyi.order.model.TemplateFood;
import com.baiyi.order.util.ValidateUtil;

@Repository
public class TemplateFoodDaoImpl extends CommonsDaoImpl<TemplateFood> implements TemplateFoodDao {

	@Override
	public void delete(List<TemplateFood> templateFoods) {
		super.delete(templateFoods);
	}

	@Override
	public List<TemplateFood> findList(Integer templateId, Integer foodId) {
		StringBuffer queryString = new StringBuffer("from TemplateFood as templateFood where 1 = 1");
		Map<String, Object> map = new HashMap<>();

		if (ValidateUtil.isPK(templateId)) {
			queryString.append(" and templateFood.templateId = :templateId");
			map.put("templateId", templateId);
		}
		if (ValidateUtil.isPK(foodId)) {
			queryString.append(" and templateFood.foodId = :foodId");
			map.put("foodId", foodId);
		}

		return super.findList(queryString.toString(), -1, -1, map);
	}
}
