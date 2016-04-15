package com.baiyi.order.dao;

import java.util.List;

import com.baiyi.order.model.Food;
import com.baiyi.order.model.Material;
import com.baiyi.order.model.Style;
import com.baiyi.order.model.Taste;
import com.baiyi.order.model.Type;
import com.baiyi.order.vo.FoodVO;

public interface FoodDao {

	public void save(Food food);

	public void delete(Integer id);

	public void delete(Food food);

	public void delete(Integer[] ids);

	public void delete(List<Food> foods);

	public void update(Food food);

	public void merge(Food food);

	public Food find(Integer id);

	public Food find(String name);

	public Food findByMaterial(Integer materialId);

	public List<Food> findList();

	public List<Food> findList(String name, Integer typeId, Integer userId);

	public List<Food> findList(String name, Integer typeId, Integer userId, String sort, String order, int pageNo, int pageSize);

	public int count(String name, Integer typeId, Integer userId);

	/**/
	public FoodVO findVO(Integer id);

	public List<FoodVO> findVOList();

	public List<FoodVO> findVOList(String name, Integer typeId, Integer userId);

	public List<FoodVO> findVOList(String name, Integer typeId, Integer userId, String sort, String order, int pageNo, int pageSize);

	/* join search */
	public Material findMaterial(Integer id);

	public Type findType(Integer id);

	public List<Taste> findTasteList(Integer id);

	public List<Style> findStyleList(Integer id);

}
