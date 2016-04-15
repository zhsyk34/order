package com.baiyi.order.action;

import java.util.Date;
import java.util.List;

import com.baiyi.order.model.Type;
import com.baiyi.order.util.Feedback;

@SuppressWarnings("serial")
public class TypeAction extends CommonsAction {

	public String save() {
		if (typeService.exist(null, name)) {
			jsonData.put(result, Feedback.EXIST.toString());
			return SUCCESS;
		}
		Type type = new Type();
		type.setName(name);
		type.setCreatetime(new Date());
		type.setUserId(userId);
		typeService.save(type);
		jsonData.put(result, Feedback.CREATE.toString());
		return SUCCESS;
	}

	public String delete() {
		if (typeService.relate(ids)) {
			jsonData.put(result, Feedback.RELATE.toString());
			return SUCCESS;
		}
		typeService.delete(ids);
		jsonData.put(result, Feedback.DELETE.toString());
		return SUCCESS;
	}

	public String update() {
		if (typeService.exist(id, name)) {
			jsonData.put(result, Feedback.EXIST.toString());
			return SUCCESS;
		}
		Type type = typeService.find(id);
		type.setName(name);
		type.setUpdatetime(new Date());
		type.setUserId(userId);
		typeService.update(type);
		jsonData.put(result, Feedback.UPDATE.toString());
		return SUCCESS;
	}

	public String find() {
		List<Type> list = typeService.findList(name, userId, sort, order, pageNo, pageSize);
		int count = typeService.count(name, userId);
		jsonData.put("list", list);
		jsonData.put("count", count);
		jsonData.put("pageNo", pageNo);
		jsonData.put("pageSize", pageSize);
		return SUCCESS;
	}

	public String exist() {
		jsonData.put("exist", typeService.exist(id, name));
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
