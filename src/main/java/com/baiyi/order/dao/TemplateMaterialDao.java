package com.baiyi.order.dao;

import java.util.List;

import com.baiyi.order.model.TemplateMaterial;
import com.baiyi.order.util.EnumList.TemplateMaterialEnum;

public interface TemplateMaterialDao {

	public void save(TemplateMaterial templateMaterial);

	public void delete(Integer id);

	public void delete(TemplateMaterial templateMaterial);

	public void delete(Integer[] ids);

	public void delete(List<TemplateMaterial> templateMaterials);

	public void update(TemplateMaterial templateMaterial);

	public void merge(TemplateMaterial templateMaterial);

	public TemplateMaterial find(Integer id);

	public List<TemplateMaterial> findList();

	public List<TemplateMaterial> findList(TemplateMaterialEnum type, Integer templateId, Integer materialId);

}
