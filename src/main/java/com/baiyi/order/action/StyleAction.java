package com.baiyi.order.action;

import java.util.Date;
import java.util.List;

import com.baiyi.order.model.Style;
import com.baiyi.order.util.Feedback;

@SuppressWarnings("serial")
public class StyleAction extends CommonsAction {

	public String save() {
		if (styleService.exist(null, name)) {
			jsonData.put("result", Feedback.EXIST.toString());// 重名
			return SUCCESS;
		}
		Style style = new Style();
		style.setName(name);
		style.setCreatetime(new Date());
		style.setUserId(userId);
		styleService.save(style);
		jsonData.put("result", Feedback.CREATE.toString());
		return SUCCESS;
	}

	public String delete() {
		if (styleService.relate(ids)) {
			jsonData.put("result", Feedback.RELATE.toString());// 关联
			return SUCCESS;
		}
		styleService.delete(ids);
		jsonData.put("result", Feedback.DELETE.toString());
		return SUCCESS;
	}

	public String update() {
		if (styleService.exist(id, name)) {
			jsonData.put("result", Feedback.EXIST.toString());// 重名
			return SUCCESS;
		}
		Style style = styleService.find(id);
		style.setName(name);
		style.setUpdatetime(new Date());
		style.setUserId(userId);
		styleService.update(style);
		jsonData.put("result", Feedback.UPDATE.toString());
		return SUCCESS;
	}

	public String exist() {
		jsonData.put("exist", styleService.exist(id, name));
		return SUCCESS;
	}

	public String find() {
		List<Style> list = styleService.findList(name, userId, sort, order, pageNo, pageSize);
		int count = styleService.count(name, userId);
		jsonData.put("list", list);
		jsonData.put("count", count);
		jsonData.put("pageNo", pageNo);
		jsonData.put("pageSize", pageSize);
		return SUCCESS;
	}

	private Integer id;

	private Integer[] ids;

	private String name;

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

}
