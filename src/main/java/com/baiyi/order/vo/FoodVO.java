package com.baiyi.order.vo;

import java.util.Date;
import java.util.List;

import com.baiyi.order.model.Style;
import com.baiyi.order.model.Taste;

public class FoodVO {

	private Integer id;

	private String name;

	private String abbreviation;

	private String nickname;

	private double price;

	private Integer typeId;

	private String typeName;// VO

	private Integer materialId;

	private String path;// VO

	private String introduction;

	private Integer userId;

	private Date createtime;

	private Date updatetime;

	private List<Taste> tasteList;// VO

	private List<Style> styleList;// VO

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

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Integer getMaterialId() {
		return materialId;
	}

	public void setMaterialId(Integer materialId) {
		this.materialId = materialId;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
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

	public List<Taste> getTasteList() {
		return tasteList;
	}

	public void setTasteList(List<Taste> tasteList) {
		this.tasteList = tasteList;
	}

	public List<Style> getStyleList() {
		return styleList;
	}

	public void setStyleList(List<Style> styleList) {
		this.styleList = styleList;
	}

}
