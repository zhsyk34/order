package com.baiyi.order.action;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.baiyi.order.service.ActivityService;
import com.baiyi.order.service.CashboxService;
import com.baiyi.order.service.ConfigService;
import com.baiyi.order.service.FoodService;
import com.baiyi.order.service.MarqueeService;
import com.baiyi.order.service.MaterialService;
import com.baiyi.order.service.OrderRuleService;
import com.baiyi.order.service.OrderService;
import com.baiyi.order.service.PaySettingService;
import com.baiyi.order.service.RefundService;
import com.baiyi.order.service.SeatService;
import com.baiyi.order.service.StyleService;
import com.baiyi.order.service.TasteService;
import com.baiyi.order.service.TemplateService;
import com.baiyi.order.service.TerminalService;
import com.baiyi.order.service.TypeService;
import com.baiyi.order.service.UserService;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class CommonsAction extends ActionSupport implements RequestAware, SessionAware, ApplicationAware {

	/* web object */
	protected Map<String, Object> request;
	protected Map<String, Object> session;
	protected Map<String, Object> application;

	// commons params
	protected int pageNo = 1;
	protected int pageSize = 10;
	protected String sort = "id";
	protected String order = "desc";
	protected Integer userId;// 用户操作记录
	public static Integer loginId;// 登录账户 TODO

	protected Map<String, Object> jsonData = new HashMap<>();
	protected String result = "result";// jsonData-key

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	// public Integer getLoginId() {
	// return loginId;
	// }
	//
	// public void setLoginId(Integer loginId) {
	// this.loginId = loginId;
	// }

	public Map<String, Object> getJsonData() {
		return jsonData;
	}

	public void setJsonData(Map<String, Object> jsonData) {
		this.jsonData = jsonData;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Map<String, Object> getRequest() {
		return request;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public Map<String, Object> getApplication() {
		return application;
	}

	@Override
	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	@Override
	public void setApplication(Map<String, Object> application) {
		this.application = application;
	}

	// services
	@Resource
	protected FoodService foodService;
	@Resource
	protected MaterialService materialService;
	@Resource
	protected StyleService styleService;
	@Resource
	protected TasteService tasteService;
	@Resource
	protected TypeService typeService;
	@Resource
	protected MarqueeService marqueeService;
	@Resource
	protected TemplateService templateService;
	@Resource
	protected SeatService seatService;
	@Resource
	protected OrderService orderService;
	@Resource
	protected OrderRuleService orderRuleService;
	@Resource
	protected RefundService refundService;
	@Resource
	protected TerminalService terminalService;
	@Resource
	protected ActivityService activityService;
	@Resource
	protected UserService userService;
	@Resource
	protected CashboxService cashboxService;
	@Resource
	protected ConfigService configService;
	@Resource
	protected PaySettingService paySettingService;
}
