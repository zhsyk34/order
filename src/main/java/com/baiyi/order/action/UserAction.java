package com.baiyi.order.action;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.baiyi.order.model.User;
import com.baiyi.order.util.DESPlus;
import com.baiyi.order.util.Feedback;
import com.baiyi.order.util.ValidateUtil;

@SuppressWarnings("serial")
public class UserAction extends CommonsAction {

	public String save() {
		if (StringUtils.isBlank(name) || StringUtils.isBlank(password)) {
			jsonData.put(result, Feedback.ERROR.toString());
			return SUCCESS;
		}

		User user = new User();
		user.setName(name);
		password = DESPlus.digest(password);
		user.setPassword(password);

		userService.save(user);
		jsonData.put(result, Feedback.CREATE.toString());
		return SUCCESS;
	}

	public String delete() {
		Integer id = CommonsAction.loginId;// TODO
		if (ValidateUtil.indexOf(ids, id)) {
			// ...
		}
		userService.delete(ids);
		jsonData.put(result, Feedback.DELETE.toString());
		return SUCCESS;
	}

	public String update() {
		if (StringUtils.isBlank(name)) {
			jsonData.put(result, Feedback.ERROR.toString());
			return SUCCESS;
		}

		User user = userService.find(id);
		user.setName(name);

		if (StringUtils.isNotBlank(password)) {
			password = DESPlus.digest(password);
			user.setPassword(password);
		}

		userService.update(user);
		jsonData.put(result, Feedback.UPDATE.toString());
		return SUCCESS;
	}

	public String modify() {// 修改密码
		if (StringUtils.isBlank(original) || StringUtils.isBlank(password)) {
			jsonData.put(result, Feedback.ERROR.toString());
			return SUCCESS;
		}

		User user = userService.find(id);

		// 验证原密码
		original = DESPlus.digest(original);
		if (!original.equals(user.getPassword())) {
			jsonData.put(result, Feedback.NOTEXIST.toString());
			return SUCCESS;
		}

		password = DESPlus.digest(password);
		user.setPassword(password);

		userService.update(user);
		jsonData.put(result, Feedback.UPDATE.toString());
		return SUCCESS;
	}

	public String find() {
		List<User> list = userService.findList(name, sort, order, pageNo, pageSize);
		int count = userService.count(name);
		jsonData.put("list", list);
		jsonData.put("count", count);
		jsonData.put("pageNo", pageNo);
		jsonData.put("pageSize", pageSize);
		return SUCCESS;
	}

	public String exist() {
		jsonData.put("exist", userService.exist(id, name));
		return SUCCESS;
	}

	public String logon() {
		if (StringUtils.isBlank(name) || StringUtils.isBlank(password)) {
			jsonData.put(result, Feedback.ERROR.toString());
			return SUCCESS;
		}

		password = DESPlus.digest(password);
		User user = userService.login(name, password);
		if (user == null) {
			jsonData.put(result, Feedback.FAIL.toString());
			return SUCCESS;
		}
		session.put("user", user);
		jsonData.put(result, Feedback.SUCCESS.toString());
		return SUCCESS;
	}

	public String logout() {
		session.clear();
		return SUCCESS;
	}

	/**/
	private Integer id;

	private Integer[] ids;

	private String name;

	private String original;// 原密码

	private String password;

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

	public String getOriginal() {
		return original;
	}

	public void setOriginal(String original) {
		this.original = original;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
