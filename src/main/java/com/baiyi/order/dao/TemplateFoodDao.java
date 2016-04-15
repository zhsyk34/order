package com.baiyi.order.dao;

import java.util.List;

import com.baiyi.order.model.TemplateFood;

public interface TemplateFoodDao {

	public void save(TemplateFood templateFood);

	public void delete(Integer id);

	public void delete(TemplateFood templateFood);

	public void delete(Integer[] ids);

	public void delete(List<TemplateFood> templateFoods);

	public void update(TemplateFood templateFood);

	public void merge(TemplateFood templateFood);

	public TemplateFood find(Integer id);

	public List<TemplateFood> findList();

	public List<TemplateFood> findList(Integer templateId, Integer foodId);

}
