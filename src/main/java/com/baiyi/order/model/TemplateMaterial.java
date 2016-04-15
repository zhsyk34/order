package com.baiyi.order.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.baiyi.order.util.EnumList.TemplateMaterialEnum;

@Entity
public class TemplateMaterial {

	private Integer id;

	private TemplateMaterialEnum type;// 模板素材类型

	private Integer templateId;

	private Integer materialId;

	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Enumerated(EnumType.STRING)
	public TemplateMaterialEnum getType() {
		return type;
	}

	public void setType(TemplateMaterialEnum type) {
		this.type = type;
	}

	public Integer getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}

	public Integer getMaterialId() {
		return materialId;
	}

	public void setMaterialId(Integer materialId) {
		this.materialId = materialId;
	}

}
