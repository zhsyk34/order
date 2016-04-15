package com.baiyi.order.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.baiyi.order.dao.TemplateMaterialDao;
import com.baiyi.order.model.TemplateMaterial;
import com.baiyi.order.util.EnumList.TemplateMaterialEnum;
import com.baiyi.order.util.ValidateUtil;

@Repository
public class TemplateMaterialDaoImpl extends CommonsDaoImpl<TemplateMaterial> implements TemplateMaterialDao {

	@Override
	public void delete(List<TemplateMaterial> templateMaterials) {
		super.delete(templateMaterials);
	}

	@Override
	public List<TemplateMaterial> findList(TemplateMaterialEnum type, Integer templateId, Integer materialId) {
		StringBuffer queryString = new StringBuffer("from TemplateMaterial as templateMaterial where 1 = 1");
		Map<String, Object> map = new HashMap<>();

		if (type != null) {
			queryString.append(" and templateMaterial.type = :type");
			map.put("type", type);
		}
		if (ValidateUtil.isPK(templateId)) {
			queryString.append(" and templateMaterial.templateId = :templateId");
			map.put("templateId", templateId);
		}
		if (ValidateUtil.isPK(materialId)) {
			queryString.append(" and templateMaterial.materialId = :materialId");
			map.put("materialId", materialId);
		}

		return super.findList(queryString.toString(), -1, -1, map);
	}

}
