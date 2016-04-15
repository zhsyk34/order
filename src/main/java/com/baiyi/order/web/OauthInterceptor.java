package com.baiyi.order.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.baiyi.order.model.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

@SuppressWarnings("serial")
public class OauthInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {

		// invocation.addPreResultListener(new PreResultListener() {
		// @Override
		// public void beforeResult(ActionInvocation invocation, String
		// resultCode) {
		//
		// }
		// });

		ActionContext context = invocation.getInvocationContext();

		// pass action
		String actionName = context.getName();
		System.out.println("访问地址:" + actionName);
		if (actionName.matches("^(User_logon|Service_\\S+)*")) {
			System.out.println("logon action,pass");
			return invocation.invoke();
		}

		// validate login
		Map<String, Object> session = context.getSession();
		User user = (User) session.get("user");
		if (user != null) {
			return invocation.invoke();
		}
		System.out.println("未登录");

		// check type
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String type = request.getHeader("X-Requested-With");
		if ("XMLHttpRequest".equalsIgnoreCase(type)) {
			System.out.println("ajax visit");
			response.setHeader("sessionstatus", "timeout");
			response.sendError(518, "session timeout.");
			return null;
		} else {
			System.out.println("action redirect");
			return "logon";
		}
	}

}
