package com.baiyi.order.action;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.baiyi.order.model.Food;
import com.baiyi.order.model.Taste;
import com.baiyi.order.model.Type;
import com.baiyi.order.util.Feedback;
import com.baiyi.order.util.ValidateUtil;
import com.baiyi.order.vo.FoodVO;

@SuppressWarnings("serial")
public class FoodAction extends CommonsAction {

	public String save() {
		if (StringUtils.isBlank(name)) {
			return null;
		}
		if (foodService.exist(null, name)) {
			jsonData.put("result", Feedback.EXIST.toString());
			return null;
		}
		Food food = new Food();
		food.setName(name);
		food.setAbbreviation(abbreviation);
		food.setNickname(nickname);
		food.setPrice(price);
		food.setTypeId(typeId);
		food.setMaterialId(materialId);
		food.setIntroduction(introduction);
		food.setUserId(userId);
		food.setCreatetime(new Date());

		foodService.save(food, tasteIds, styleIds);
		jsonData.put("result", Feedback.CREATE.toString());
		return SUCCESS;
	}

	public String delete() {
		if (foodService.relate(ids)) {
			jsonData.put("result", Feedback.RELATE.toString());
			return SUCCESS;
		}
		foodService.delete(ids);
		jsonData.put("result", Feedback.DELETE.toString());
		return SUCCESS;
	}

	public String update() {
		if (!ValidateUtil.isPK(id)) {
			return null;
		}
		if (foodService.exist(id, name)) {
			jsonData.put("result", Feedback.EXIST.toString());
			return null;
		}
		Food food = foodService.find(id);
		if (food == null) {
			return null;
		}
		food.setName(name);
		food.setAbbreviation(abbreviation);
		food.setNickname(nickname);
		food.setPrice(price);
		food.setTypeId(typeId);
		food.setMaterialId(materialId);
		food.setIntroduction(introduction);
		food.setUserId(userId);
		food.setUpdatetime(new Date());

		foodService.update(food, tasteIds, styleIds);
		jsonData.put("result", Feedback.UPDATE.toString());
		return SUCCESS;
	}

	public String find() {
		List<FoodVO> list = foodService.findVOList(name, typeId, userId, sort, order, pageNo, pageSize);
		int count = foodService.count(name, typeId, userId);
		jsonData.put("list", list);
		jsonData.put("count", count);
		jsonData.put("pageNo", pageNo);
		jsonData.put("pageSize", pageSize);
		return SUCCESS;
	}

	public String exist() {
		jsonData.put("exist", foodService.exist(id, name));
		return SUCCESS;
	}

	public String findType() {
		List<Type> list = typeService.findList();
		jsonData.put("list", list);
		return SUCCESS;
	}

	public String findTaste() {
		List<Taste> list = tasteService.findList();
		jsonData.put("list", list);
		return SUCCESS;
	}

	/**/
	private Integer id;
	private Integer[] ids;
	private String name;
	private String abbreviation;
	private String nickname;
	private double price;
	private Integer typeId;
	private Integer materialId;
	private String introduction;

	private Integer[] tasteIds;
	private Integer[] styleIds;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer[] getIds() {
		return ids;
	}

	public void setIds(Integer[] ids) {
		this.ids = ids;
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

	public Integer getMaterialId() {
		return materialId;
	}

	public void setMaterialId(Integer materialId) {
		this.materialId = materialId;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public Integer[] getTasteIds() {
		return tasteIds;
	}

	public void setTasteIds(Integer[] tasteIds) {
		this.tasteIds = tasteIds;
	}

	public Integer[] getStyleIds() {
		return styleIds;
	}

	public void setStyleIds(Integer[] styleIds) {
		this.styleIds = styleIds;
	}

}
