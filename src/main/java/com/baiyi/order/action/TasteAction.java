package com.baiyi.order.action;

import java.util.Date;
import java.util.List;

import com.baiyi.order.model.Style;
import com.baiyi.order.model.Taste;
import com.baiyi.order.util.Feedback;
import com.baiyi.order.vo.TasteVO;

@SuppressWarnings("serial")
public class TasteAction extends CommonsAction {

	public String save() {
		if (tasteService.exist(null, name)) {
			jsonData.put("result", Feedback.EXIST.toString());
			return SUCCESS;
		}
		Taste taste = new Taste();
		taste.setName(name);
		taste.setPrice(price);
		taste.setStyleId(styleId);
		taste.setCreatetime(new Date());
		taste.setUserId(userId);
		tasteService.save(taste);
		jsonData.put("result", Feedback.CREATE.toString());
		return SUCCESS;
	}

	public String delete() {
		if (tasteService.relate(ids)) {
			jsonData.put("result", Feedback.RELATE.toString());
			return SUCCESS;
		}
		tasteService.delete(ids);
		jsonData.put("result", Feedback.DELETE.toString());
		return SUCCESS;
	}

	public String update() {
		if (tasteService.exist(id, name)) {
			jsonData.put("result", Feedback.EXIST.toString());
			return SUCCESS;
		}
		Taste taste = tasteService.find(id);
		taste.setName(name);
		taste.setPrice(price);
		taste.setStyleId(styleId);
		taste.setUpdatetime(new Date());
		taste.setUserId(userId);
		tasteService.update(taste);
		jsonData.put("result", Feedback.UPDATE.toString());
		return SUCCESS;
	}

	public String find() {
		List<TasteVO> list = tasteService.findVOList(name, styleId, userId, sort, order, pageNo, pageSize);
		int count = tasteService.count(name, styleId, userId);
		jsonData.put("list", list);
		jsonData.put("count", count);
		jsonData.put("pageNo", pageNo);
		jsonData.put("pageSize", pageSize);
		return SUCCESS;
	}

	public String exist() {
		boolean isExist = tasteService.exist(id, name);
		jsonData.put("exist", isExist);
		return SUCCESS;
	}

	public String findStyle() {
		List<Style> list = styleService.findList();
		jsonData.put("list", list);
		return SUCCESS;
	}

	/**/
	private Integer id;

	private Integer[] ids;

	private String name;

	private double price;

	private Integer styleId;

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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Integer getStyleId() {
		return styleId;
	}

	public void setStyleId(Integer styleId) {
		this.styleId = styleId;
	}

}
