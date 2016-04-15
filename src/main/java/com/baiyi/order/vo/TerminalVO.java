package com.baiyi.order.vo;

import java.util.Date;
import java.util.List;

import com.baiyi.order.util.EnumList.TerminalTypeEnum;

public class TerminalVO {

	private Integer id;

	private String terminalNo;

	private TerminalTypeEnum type;

	private String location;

	private String version;

	private Integer userId;

	private Date createtime;

	private Date updatetime;

	private String teamViewer;

	private boolean invoice;

	private boolean shut;

	private List<Integer> seats;// VO

	private List<String> bootList;// VO

	private List<String> shutList;// VO

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

	public List<Integer> getSeats() {
		return seats;
	}

	public void setSeats(List<Integer> seats) {
		this.seats = seats;
	}

	public List<String> getBootList() {
		return bootList;
	}

	public void setBootList(List<String> bootList) {
		this.bootList = bootList;
	}

	public List<String> getShutList() {
		return shutList;
	}

	public void setShutList(List<String> shutList) {
		this.shutList = shutList;
	}

}
