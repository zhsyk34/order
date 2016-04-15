package com.baiyi.order.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class FoodTaste {

	private Integer id;

	private Integer foodId;

	private Integer tasteId;

	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getFoodId() {
		return foodId;
	}

	public void setFoodId(Integer foodId) {
		this.foodId = foodId;
	}

	public Integer getTasteId() {
		return tasteId;
	}

	public void setTasteId(Integer tasteId) {
		this.tasteId = tasteId;
	}
}
