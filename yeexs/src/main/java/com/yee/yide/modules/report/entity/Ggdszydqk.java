package com.yee.yide.modules.report.entity;
/*
 * @ author 李亮亮
 * 各供电所只用点情况 entity
 */

import java.math.BigDecimal;

public class Ggdszydqk {
	
	private String dwmc;//单位名称
	private String ydqk;//用电情况
	private String gsxltq;//归属线路台区
	private BigDecimal ydl;//用电量
	public String getDwmc() {
		return dwmc;
	}
	public void setDwmc(String dwmc) {
		this.dwmc = dwmc;
	}
	public String getYdqk() {
		return ydqk;
	}
	public void setYdqk(String ydqk) {
		this.ydqk = ydqk;
	}
	public String getGsxltq() {
		return gsxltq;
	}
	public void setGsxltq(String gsxltq) {
		this.gsxltq = gsxltq;
	}
	public BigDecimal getYdl() {
		return ydl;
	}
	public void setYdl(BigDecimal ydl) {
		this.ydl = ydl;
	}
	
	
	
	

}
