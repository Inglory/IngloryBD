package com.yee.yide.modules.report.entity;
/*
 * @author 李亮亮
 * 6kV-110kV专线电量统计表entity
 */

import java.math.BigDecimal;

public class Lsdybysfzsdltjb {
	
	private String dydj;//电压等级
	private String xlmc;//线路名称
	private BigDecimal bygdl;//本月供电量
	
	private BigDecimal bysdl;//本月售电量
	
	private BigDecimal ljgdl;//累计供电量
	private BigDecimal ljsdl;//累计售电量
	public String getDydj() {
		return dydj;
	}
	public void setDydj(String dydj) {
		this.dydj = dydj;
	}
	public String getXlmc() {
		return xlmc;
	}
	public void setXlmc(String xlmc) {
		this.xlmc = xlmc;
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
	
	

}
