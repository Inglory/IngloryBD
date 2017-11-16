package com.yee.yide.modules.report.entity;

import java.math.BigDecimal;

/*
 * @ author 李亮亮
 * 报表名称：供电所低压线损统计表
 * 报表标题：台区线损统计表
 */

public class GdsDyxstjb {
	
	private String tqmc;//台区名称
	private BigDecimal bygdl;//本月供电量
	private BigDecimal bysdl;//本月售电量
	private BigDecimal byssdl;//本月损失电量
	
	private BigDecimal ljgdl;//累计供电量
	private BigDecimal ljsdl;//累计售电量
	private BigDecimal ljssdl;//累计损失电量
	public String getTqmc() {
		return tqmc;
	}
	public void setTqmc(String tqmc) {
		this.tqmc = tqmc;
	}
	public BigDecimal getBygdl() {
		return bygdl;
	}
	public void setBygdl(BigDecimal bygdl) {
		this.bygdl = bygdl;
	}
	public BigDecimal getBysdl() {
		return bysdl;
	}
	public void setBysdl(BigDecimal bysdl) {
		this.bysdl = bysdl;
	}
	public BigDecimal getByssdl() {
		return byssdl;
	}
	public void setByssdl(BigDecimal byssdl) {
		this.byssdl = byssdl;
	}
	public BigDecimal getLjgdl() {
		return ljgdl;
	}
	public void setLjgdl(BigDecimal ljgdl) {
		this.ljgdl = ljgdl;
	}
	public BigDecimal getLjsdl() {
		return ljsdl;
	}
	public void setLjsdl(BigDecimal ljsdl) {
		this.ljsdl = ljsdl;
	}
	public BigDecimal getLjssdl() {
		return ljssdl;
	}
	public void setLjssdl(BigDecimal ljssdl) {
		this.ljssdl = ljssdl;
	}
	
	
	

}
