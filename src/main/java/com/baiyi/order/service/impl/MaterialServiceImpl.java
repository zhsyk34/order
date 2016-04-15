package com.baiyi.order.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.baiyi.order.dao.FoodDao;
import com.baiyi.order.dao.MaterialDao;
import com.baiyi.order.dao.TemplateMaterialDao;
import com.baiyi.order.model.Food;
import com.baiyi.order.model.Material;
import com.baiyi.order.model.TemplateMaterial;
import com.baiyi.order.service.MaterialService;
import com.baiyi.order.util.EnumList.MaterialTypeEnum;

@Service
public class MaterialServiceImpl implements MaterialService {

	@Resource
	private MaterialDao materialDao;
	@Resource
	private FoodDao foodDao;
	@Resource
	private TemplateMaterialDao templateMaterialDao;

	@Override
	public void save(Material material) {
		materialDao.save(material);
	}

	@Override
	public void delete(Integer id) {
		materialDao.delete(id);
	}

	@Override
	public void delete(Material material) {
		materialDao.delete(material);
	}

	@Override
	public void delete(Integer[] ids) {
		materialDao.delete(ids);
	}

	@Override
	public void delete(List<Material> materials) {
		materialDao.delete(materials);
	}

	@Override
	public void update(Material material) {
		materialDao.update(material);
	}

	@Override
	public void merge(Material material) {
		materialDao.merge(material);
	}

	@Override
	public Material find(Integer id) {
		return materialDao.find(id);
	}

	@Override
	public Material find(String name) {
		return materialDao.find(name);
	}

	@Override
	public List<Material> findList() {
		return materialDao.findList();
	}

	@Override
	public List<Material> findList(String name, MaterialTypeEnum type, Integer userId) {
		return materialDao.findList(name, type, userId);
	}

	@Override
	public List<Material> findList(String name, MaterialTypeEnum type, Integer userId, String sort, String order, int pageNo, int pageSize) {
		return materialDao.findList(name, type, userId, sort, order, pageNo, pageSize);
	}

	@Override
	public int count(String name, MaterialTypeEnum type, Integer userId) {
		return materialDao.count(name, type, userId);
	}

	@Override
	public boolean exist(Integer id, String name) {
		Material material = this.find(name);
		return material == null ? false : !material.getId().equals(id);
	}

	@Override
	public boolean relate(Integer id) {
		Food food = foodDao.findByMaterial(id);
		List<TemplateMaterial> list = templateMaterialDao.findList(null, null, id);
		return food != null || CollectionUtils.isNotEmpty(list);
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
