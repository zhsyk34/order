package com.baiyi.order.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.baiyi.order.util.EnumList.RemoteTimeEnum;

@Entity
public class TerminalTime {

	private Integer id;

	private Integer terminalId;

	private Date time;

	private RemoteTimeEnum type;

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

	@Temporal(TemporalType.TIME)
	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@Enumerated(EnumType.STRING)
	public RemoteTimeEnum getType() {
		return type;
	}

	public void setType(RemoteTimeEnum type) {
		this.type = type;
	}

}
