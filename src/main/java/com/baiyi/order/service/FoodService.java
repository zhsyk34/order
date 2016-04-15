package com.baiyi.order.service;

import java.util.List;

import com.baiyi.order.model.Food;
import com.baiyi.order.vo.FoodVO;

public interface FoodService {

	public void save(Food food);

	public void save(Food food, Integer[] tasteIds, Integer[] styleIds);

	public void delete(Integer id);

	public void delete(Food food);

	public void delete(Integer[] ids);

	public void delete(List<Food> foods);

	public void update(Food food);

	public void update(Food food, Integer[] tasteIds, Integer[] styleIds);

	public void merge(Food food);

	public void merge(Food food, Integer[] tasteIds, Integer[] styleIds);

	public Food find(Integer id);

	public Food find(String name);

	public List<Food> findList();

	public List<Food> findList(String name, Integer typeId, Integer userId);

	public List<Food> findList(String name, Integer typeId, Integer userId, String sort, String order, int pageNo, int pageSize);

	public int count(String name, Integer typeId, Integer userId);

	/* VO */
	public FoodVO findVO(Integer id);

	public List<FoodVO> findVOList();

	public List<FoodVO> findVOList(String name, Integer typeId, Integer userId);

	public List<FoodVO> findVOList(String name, Integer typeId, Integer userId, String sort, String order, int pageNo, int pageSize);

	/**/
	public boolean exist(Integer id, String name);

	public boolean relate(Integer id);

	public boolean relate(Integer[] ids);
}
