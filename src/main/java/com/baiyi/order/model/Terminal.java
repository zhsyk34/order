package com.baiyi.order.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.baiyi.order.util.EnumList.TerminalTypeEnum;

/*
 * TerminalTime
 * TerminalSeat
 * TerminalTemplate
 * TerminalConnect
 * Cashbox
 * */
@Entity
public class Terminal {

	private Integer id;

	private String terminalNo;

	private TerminalTypeEnum type;

	private String location;// 位置

	private String version;// 版本

	private Integer userId;

	private Date createtime;

	private Date updatetime;

	/* 设置信息 */
	private String teamViewer;// 远端

	private boolean invoice;// 是否列印发票

	private boolean shut;// 是否远程控制(关机)

	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTerminalNo() {
		return terminalNo;
	}

	public void setTerminalNo(String terminalNo) {
		this.terminalNo = terminalNo;
	}

	@Enumerated(EnumType.STRING)
	public TerminalTypeEnum getType() {
		return type;
	}

	public void setType(TerminalTypeEnum type) {
		this.type = type;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
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

}
