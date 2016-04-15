package com.baiyi.order.dao;

import java.util.List;

import com.baiyi.order.model.FoodStyle;

public interface FoodStyleDao {

	public void save(FoodStyle foodStyle);

	public void delete(Integer id);

	public void delete(FoodStyle foodStyle);

	public void delete(Integer[] ids);

	public void delete(List<FoodStyle> foodStyles);

	public void update(FoodStyle foodStyle);

	public void merge(FoodStyle foodStyle);

	public FoodStyle find(Integer id);

	public List<FoodStyle> findList();

	public List<FoodStyle> findList(Integer foodId, Integer styleId);

}
