package com.baiyi.order.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.baiyi.order.util.EnumList.TemplateDownEnum;

@Entity
public class TerminalTemplate {

	private Integer id;

	private Integer terminalId;

	private Integer templateId;

	private TemplateDownEnum status;// 状态

	/* 下载进度 */
	private long total;// 总大小
	private long down;// 已下载

	private boolean renew;// 更新

	private boolean used;// 启用

	private Date date;

	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(Integer terminalId) {
		this.terminalId = terminalId;
	}

	public Integer getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}

	@Enumerated(EnumType.STRING)
	public TemplateDownEnum getStatus() {
		return status;
	}

	public void setStatus(TemplateDownEnum status) {
		this.status = status;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public long getDown() {
		return down;
	}

	public void setDown(long down) {
		this.down = down;
	}

	public boolean isRenew() {
		return renew;
	}

	public void setRenew(boolean renew) {
		this.renew = renew;
	}

	public boolean isUsed() {
		return used;
	}

	public void setUsed(boolean used) {
		this.used = used;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
