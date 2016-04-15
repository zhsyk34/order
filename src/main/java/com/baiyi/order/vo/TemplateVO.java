package com.baiyi.order.vo;

import java.util.Date;
import java.util.List;

import com.baiyi.order.model.Marquee;
import com.baiyi.order.model.Material;
import com.baiyi.order.util.EnumList.Effect;
import com.baiyi.order.util.EnumList.TemplateContentEnum;
import com.baiyi.order.util.EnumList.TemplateTypeEnum;

public class TemplateVO {

	private Integer id;

	private String name;

	private TemplateTypeEnum type;
	private int rowcount;
	private int colcount;

	private TemplateContentEnum content;
	private int interlude;
	private Effect effect;

	private Integer userId;

	private Date createtime;

	private Date updatetime;

	private List<FoodVO> foodList;// VO

	private Material logo;// VO
	private Material number;// VO
	private List<Material> videoList;// VO
	private List<Material> pictureList;// VO

	private List<Marquee> marqueeList;// VO

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

	public List<FoodVO> getFoodList() {
		return foodList;
	}

	public void setFoodList(List<FoodVO> foodList) {
		this.foodList = foodList;
	}

	public Material getLogo() {
		return logo;
	}

	public void setLogo(Material logo) {
		this.logo = logo;
	}

	public Material getNumber() {
		return number;
	}

	public void setNumber(Material number) {
		this.number = number;
	}

	public List<Material> getVideoList() {
		return videoList;
	}

	public void setVideoList(List<Material> videoList) {
		this.videoList = videoList;
	}

	public List<Material> getPictureList() {
		return pictureList;
	}

	public void setPictureList(List<Material> pictureList) {
		this.pictureList = pictureList;
	}

	public List<Marquee> getMarqueeList() {
		return marqueeList;
	}

	public void setMarqueeList(List<Marquee> marqueeList) {
		this.marqueeList = marqueeList;
	}

}
