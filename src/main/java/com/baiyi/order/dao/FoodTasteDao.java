package com.baiyi.order.dao;

import java.util.List;

import com.baiyi.order.model.FoodTaste;

public interface FoodTasteDao {

	public void save(FoodTaste foodTaste);

	public void delete(Integer id);

	public void delete(FoodTaste foodTaste);

	public void delete(Integer[] ids);

	public void delete(List<FoodTaste> foodTastes);

	public void update(FoodTaste foodTaste);

	public void merge(FoodTaste foodTaste);

	public FoodTaste find(Integer id);

	public List<FoodTaste> findList();

	public List<FoodTaste> findList(Integer foodId, Integer tasteId);

}
