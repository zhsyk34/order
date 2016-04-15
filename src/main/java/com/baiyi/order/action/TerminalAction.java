package com.baiyi.order.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.baiyi.order.model.Terminal;
import com.baiyi.order.model.TerminalTemplate;
import com.baiyi.order.util.EnumList.RemoteEnum;
import com.baiyi.order.util.EnumList.TemplateDownEnum;
import com.baiyi.order.util.EnumList.TerminalTypeEnum;
import com.baiyi.order.util.Feedback;
import com.baiyi.order.util.FormatUtil;
import com.baiyi.order.util.ValidateUtil;
import com.baiyi.order.util.WebContext;
import com.baiyi.order.vo.Record;
import com.baiyi.order.vo.TerminalTemplateVO;
import com.baiyi.order.vo.TerminalVO;

@SuppressWarnings("serial")
public class TerminalAction extends CommonsAction {

	public String save() {
		if (terminalService.exist(null, terminalNo)) {
			jsonData.put(result, Feedback.EXIST.toString());
			return SUCCESS;
		}
		Terminal terminal = new Terminal();
		terminal.setTerminalNo(terminalNo);
		terminal.setType(FormatUtil.getEnum(TerminalTypeEnum.class, type));
		terminal.setLocation(location);
		terminal.setCreatetime(new Date());
		terminal.setUserId(userId);
		terminalService.save(terminal);
		jsonData.put(result, Feedback.CREATE.toString());
		return SUCCESS;
	}

	public String delete() {
		terminalService.delete(ids);
		jsonData.put(result, Feedback.DELETE.toString());
		return SUCCESS;
	}

	public String update() {
		if (terminalService.exist(id, terminalNo)) {
			jsonData.put(result, Feedback.EXIST.toString());
			return SUCCESS;
		}
		Terminal terminal = terminalService.find(id);
		terminal.setTerminalNo(terminalNo);
		terminal.setType(FormatUtil.getEnum(TerminalTypeEnum.class, type));
		terminal.setLocation(location);
		terminal.setUpdatetime(new Date());
		terminal.setUserId(userId);
		terminalService.update(terminal);
		jsonData.put(result, Feedback.UPDATE.toString());
		return SUCCESS;
	}

	public String exist() {
		jsonData.put("exist", terminalService.exist(id, terminalNo));
		return SUCCESS;
	}

	public String find() {
		TerminalTypeEnum typeEnum = FormatUtil.getEnum(TerminalTypeEnum.class, type);
		List<Terminal> list = terminalService.findList(terminalNo, typeEnum, null, sort, order, pageNo, pageSize);
		int count = terminalService.count(terminalNo, typeEnum, userId);
		jsonData.put("list", list);
		jsonData.put("count", count);
		jsonData.put("pageNo", pageNo);
		jsonData.put("pageSize", pageSize);

		jsonData.put("version", WebContext.version);
		return SUCCESS;
	}

	// VO
	public String query() {
		TerminalTypeEnum typeEnum = FormatUtil.getEnum(TerminalTypeEnum.class, type);
		List<TerminalVO> list = terminalService.findVOList(terminalNo, typeEnum, null, sort, order, pageNo, pageSize);
		int count = terminalService.count(terminalNo, typeEnum, null);
		jsonData.put("list", list);
		jsonData.put("count", count);
		jsonData.put("pageNo", pageNo);
		jsonData.put("pageSize", pageSize);
		return SUCCESS;
	}

	// 在线查询
	public String search() {
		List<Terminal> terminals = terminalService.findOnLine();
		List<Terminal> list = FormatUtil.subList(terminals, (pageNo - 1) * pageSize, pageNo * pageSize);
		int count = FormatUtil.count(terminals);
		jsonData.put("list", list);
		jsonData.put("count", count);
		jsonData.put("pageNo", pageNo);
		jsonData.put("pageSize", pageSize);
		return SUCCESS;
	}

	// 终端设置
	public String config() {
		Terminal terminal = terminalService.find(id);
		terminal.setTeamViewer(teamViewer);
		terminal.setInvoice(invoice);
		terminal.setShut(shut);
		List<Date> boottimes = new ArrayList<>();
		List<Date> shuttimes = new ArrayList<>();

		if (ValidateUtil.isNotEmpty(boots)) {
			for (String boot : boots) {
				Date date = FormatUtil.stringToDate(boot, "HH:mm:ss");
				boottimes.add(date);
			}
		}
		if (ValidateUtil.isNotEmpty(shuts)) {
			for (String shut : shuts) {
				Date date = FormatUtil.stringToDate(shut, "HH:mm:ss");
				shuttimes.add(date);
			}
		}

		terminalService.update(terminal, boottimes, shuttimes, null);
		jsonData.put(result, Feedback.UPDATE.toString());
		return SUCCESS;
	}

	// 座位设置
	public String seat() {
		terminalService.updateSeat(id, seatIds);
		jsonData.put(result, Feedback.UPDATE.toString());
		return SUCCESS;
	}

	// 连线记录
	public String log() {
		Date beginDate = FormatUtil.stringToDate(begin, null);
		Date endDate = FormatUtil.stringToDate(end, null);
		List<Record> list = terminalService.findRecordList(terminalNo, beginDate, endDate, online, sort, order, pageNo, pageSize);
		int count = terminalService.countRecord(terminalNo, beginDate, endDate, online);
		jsonData.put("list", list);
		jsonData.put("count", count);
		jsonData.put("pageNo", pageNo);
		jsonData.put("pageSize", pageSize);
		return SUCCESS;
	}

	// 终端监控/一览
	public String monitor() {
		List<Record> records = terminalService.findRecord(terminalNo, online);
		List<Record> list = FormatUtil.subList(records, (pageNo - 1) * pageSize, pageNo * pageSize);
		int count = FormatUtil.count(records);
		jsonData.put("list", list);
		jsonData.put("count", count);
		jsonData.put("pageNo", pageNo);
		jsonData.put("pageSize", pageSize);
		return SUCCESS;
	}

	// 模板列表
	public String findTemplate() {
		List<TerminalTemplateVO> ttVos = terminalService.findTemplateList(terminalId, templateName);
		List<TerminalTemplateVO> list = FormatUtil.subList(ttVos, (pageNo - 1) * pageSize, pageNo * pageSize);
		int count = FormatUtil.count(ttVos);
		jsonData.put("list", list);
		jsonData.put("count", count);
		jsonData.put("pageNo", pageNo);
		jsonData.put("pageSize", pageSize);
		return SUCCESS;
	}

	// 更新模板下载状态
	public String mergeTemplate() {
		TemplateDownEnum statusEnum = FormatUtil.getEnum(TemplateDownEnum.class, status);
		TerminalTemplate terminalTemplate = terminalService.findTemplate(terminalId, templateId);
		if (terminalTemplate != null && terminalTemplate.isUsed()) {// 当前正在使用
			jsonData.put(result, Feedback.ERROR.toString());
			return SUCCESS;
		}
		if (terminalTemplate == null) {
			terminalTemplate = new TerminalTemplate();
			terminalTemplate.setTerminalId(terminalId);
			terminalTemplate.setTemplateId(templateId);
		}

		terminalTemplate.setStatus(statusEnum);
		terminalTemplate.setTotal(0);
		terminalTemplate.setDown(0);
		terminalTemplate.setDate(new Date());

		terminalService.mergeTemplate(terminalTemplate);
		jsonData.put(result, Feedback.SUCCESS.toString());
		return SUCCESS;
	}

	// 启用模板
	public String useTemplate() {
		terminalService.setUsedTemplate(terminalId, templateId);
		jsonData.put(result, Feedback.SUCCESS.toString());
		return SUCCESS;
	}

	//
	public String remote() {
		RemoteEnum remoteEnum = FormatUtil.getEnum(RemoteEnum.class, status);
		if (ValidateUtil.isEmpty(ids) || remoteEnum == null) {
			jsonData.put(result, Feedback.ERROR.toString());
			return SUCCESS;
		}

		Map<String, Boolean> map = null;
		switch (remoteEnum) {
		case BOOT:
			map = WebContext.rebootMap;
			break;
		case SHUT:
			map = WebContext.shutDownMap;
			break;
		case CORRECT:
			map = WebContext.checkTimeMap;
			break;
		case OPEN:
			map = WebContext.bootTeamViewer;
			break;
		case CLOSE:
			map = WebContext.closeTeamViewer;
			break;
		}

		for (Integer id : ids) {
			Terminal terminal = terminalService.find(id);
			if (terminal != null) {
				map.put(terminal.getTerminalNo(), true);
			}
		}

		jsonData.put(result, Feedback.SUCCESS.toString());
		return SUCCESS;
	}

	/* 基础信息 */
	private Integer id;

	private Integer[] ids;

	private Integer terminalId;// id

	private String terminalNo;

	private String type;

	private String location;

	private String begin;

	private String end;

	private Boolean online;

	/* 设置 */
	private String teamViewer;

	private boolean invoice;

	private boolean shut;

	private String[] boots;

	private String[] shuts;

	/* 座位 */
	private Integer[] seatIds;

	/* 模板 */
	private Integer templateId;

	private String templateName;

	private String status;

	/* 远程控制 */
	// private String remote;

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

	public Integer getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(Integer terminalId) {
		this.terminalId = terminalId;
	}

	public String getTerminalNo() {
		return terminalNo;
	}

	public void setTerminalNo(String terminalNo) {
		this.terminalNo = terminalNo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getBegin() {
		return begin;
	}

	public void setBegin(String begin) {
		this.begin = begin;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public Boolean getOnline() {
		return online;
	}

	public void setOnline(Boolean online) {
		this.online = online;
	}

	public String getTeamViewer() {
		return teamViewer;
	}

	public void setTeamViewer(String teamViewer) {
		this.teamViewer = teamViewer;
	}

	public boolean isInvoice() {
		return invoice;
	}

	public void setInvoice(boolean invoice) {
		this.invoice = invoice;
	}

	public boolean isShut() {
		return shut;
	}

	public void setShut(boolean shut) {
		this.shut = shut;
	}

	public String[] getBoots() {
		return boots;
	}

	public void setBoots(String[] boots) {
		this.boots = boots;
	}

	public String[] getShuts() {
		return shuts;
	}

	public void setShuts(String[] shuts) {
		this.shuts = shuts;
	}

	public Integer[] getSeatIds() {
		return seatIds;
	}

	public void setSeatIds(Integer[] seatIds) {
		this.seatIds = seatIds;
	}

	public Integer getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
