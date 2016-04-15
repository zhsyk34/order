package com.baiyi.order.service;

import java.util.List;

import com.baiyi.order.model.Material;
import com.baiyi.order.util.EnumList.MaterialTypeEnum;

public interface MaterialService {

	public void save(Material material);

	public void delete(Integer id);

	public void delete(Material material);

	public void delete(Integer[] ids);

	public void delete(List<Material> materials);

	public void update(Material material);

	public void merge(Material material);

	public Material find(Integer id);

	public Material find(String name);

	public List<Material> findList();

	public List<Material> findList(String name, MaterialTypeEnum type, Integer userId);

	public List<Material> findList(String name, MaterialTypeEnum type, Integer userId, String sort, String order, int pageNo, int pageSize);

	public int count(String name, MaterialTypeEnum type, Integer userId);

	public boolean exist(Integer id, String name);

	public boolean relate(Integer id);

	public boolean relate(Integer[] ids);

}
