package com.baiyi.order.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class TemplateMarquee {

	private Integer id;

	private Integer templateId;

	private Integer marqueeId;

	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}

	public Integer getMarqueeId() {
		return marqueeId;
	}

	public void setMarqueeId(Integer marqueeId) {
		this.marqueeId = marqueeId;
	}
}
