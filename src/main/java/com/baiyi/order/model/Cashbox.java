package com.baiyi.order.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Cashbox {

	private Integer id;

	private Integer terminalId;// 终端编号(-1:系统配置最小值,0:系统配置最大值)

	/* 找钞 */
	private int nd100tw100;
	private int nd100cn10;
	/* 收钞 */
	private int nv9tw100;
	private int nv9tw500;
	private int nv9tw1000;
	private int nv9cn1;
	private int nv9cn5;
	private int nv9cn10;
	private int nv9cn20;
	private int nv9cn50;
	private int nv9cn100;
	/* 硬币 */
	private int hoppertw1;
	private int hoppertw5;
	private int hoppertw10;
	private int hoppertw50;
	private int hoppercn01;
	private int hoppercn05;
	private int hoppercn1;

	private Integer userId;

	private Date createtime;

	private Date updatetime;

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

	public int getNd100tw100() {
		return nd100tw100;
	}

	public void setNd100tw100(int nd100tw100) {
		this.nd100tw100 = nd100tw100;
	}

	public int getNd100cn10() {
		return nd100cn10;
	}

	public void setNd100cn10(int nd100cn10) {
		this.nd100cn10 = nd100cn10;
	}

	public int getNv9tw100() {
		return nv9tw100;
	}

	public void setNv9tw100(int nv9tw100) {
		this.nv9tw100 = nv9tw100;
	}

	public int getNv9tw500() {
		return nv9tw500;
	}

	public void setNv9tw500(int nv9tw500) {
		this.nv9tw500 = nv9tw500;
	}

	public int getNv9tw1000() {
		return nv9tw1000;
	}

	public void setNv9tw1000(int nv9tw1000) {
		this.nv9tw1000 = nv9tw1000;
	}

	public int getNv9cn1() {
		return nv9cn1;
	}

	public void setNv9cn1(int nv9cn1) {
		this.nv9cn1 = nv9cn1;
	}

	public int getNv9cn5() {
		return nv9cn5;
	}

	public void setNv9cn5(int nv9cn5) {
		this.nv9cn5 = nv9cn5;
	}

	public int getNv9cn10() {
		return nv9cn10;
	}

	public void setNv9cn10(int nv9cn10) {
		this.nv9cn10 = nv9cn10;
	}

	public int getNv9cn20() {
		return nv9cn20;
	}

	public void setNv9cn20(int nv9cn20) {
		this.nv9cn20 = nv9cn20;
	}

	public int getNv9cn50() {
		return nv9cn50;
	}

	public void setNv9cn50(int nv9cn50) {
		this.nv9cn50 = nv9cn50;
	}

	public int getNv9cn100() {
		return nv9cn100;
	}

	public void setNv9cn100(int nv9cn100) {
		this.nv9cn100 = nv9cn100;
	}

	public int getHoppertw1() {
		return hoppertw1;
	}

	public void setHoppertw1(int hoppertw1) {
		this.hoppertw1 = hoppertw1;
	}

	public int getHoppertw5() {
		return hoppertw5;
	}

	public void setHoppertw5(int hoppertw5) {
		this.hoppertw5 = hoppertw5;
	}

	public int getHoppertw10() {
		return hoppertw10;
	}

	public void setHoppertw10(int hoppertw10) {
		this.hoppertw10 = hoppertw10;
	}

	public int getHoppertw50() {
		return hoppertw50;
	}

	public void setHoppertw50(int hoppertw50) {
		this.hoppertw50 = hoppertw50;
	}

	public int getHoppercn01() {
		return hoppercn01;
	}

	public void setHoppercn01(int hoppercn01) {
		this.hoppercn01 = hoppercn01;
	}

	public int getHoppercn05() {
		return hoppercn05;
	}

	public void setHoppercn05(int hoppercn05) {
		this.hoppercn05 = hoppercn05;
	}

	public int getHoppercn1() {
		return hoppercn1;
	}

	public void setHoppercn1(int hoppercn1) {
		this.hoppercn1 = hoppercn1;
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

}
