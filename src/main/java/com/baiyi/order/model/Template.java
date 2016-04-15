package com.baiyi.order.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.baiyi.order.util.EnumList.Effect;
import com.baiyi.order.util.EnumList.TemplateContentEnum;
import com.baiyi.order.util.EnumList.TemplateTypeEnum;

/*
 * 关联:
 * 1.TemplateFood-->List<Food>
 * 2.TemplateMarterial-->List<Marterial>
 * 3.TemplateMarquee-->List<Marquee>
 * */
@Entity
public class Template {

	private Integer id;

	private String name;

	/* 模板类型 提供设置 */
	private TemplateTypeEnum type;
	private int rowcount;
	private int colcount;

	/* 模板内容 */
	private TemplateContentEnum content;
	private int interlude;// content == picture 轮播时间
	private Effect effect;// content == picture 轮播效果

	private Integer userId;

	private Date createtime;

	private Date updatetime;

	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Enumerated(EnumType.STRING)
	public TemplateTypeEnum getType() {
		return type;
	}

	public void setType(TemplateTypeEnum type) {
		this.type = type;
	}

	public int getRowcount() {
		return rowcount;
	}

	public void setRowcount(int rowcount) {
		this.rowcount = rowcount;
	}

	public int getColcount() {
		return colcount;
	}

	public void setColcount(int colcount) {
		this.colcount = colcount;
	}

	@Enumerated(EnumType.STRING)
	public TemplateContentEnum getContent() {
		return content;
	}

	public void setContent(TemplateContentEnum content) {
		this.content = content;
	}

	public int getInterlude() {
		return interlude;
	}

	public void setInterlude(int interlude) {
		this.interlude = interlude;
	}

	@Enumerated(EnumType.STRING)
	public Effect getEffect() {
		return effect;
	}

	public void setEffect(Effect effect) {
		this.effect = effect;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

}
