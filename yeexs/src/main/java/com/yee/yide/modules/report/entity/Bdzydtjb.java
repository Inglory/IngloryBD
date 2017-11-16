package com.yee.yide.modules.report.entity;

import java.math.BigDecimal;

/*
 * @ author 李亮亮
 * 变电站用电统计表 对应报表 站用电
 */

public class Bdzydtjb {
	
	private String bdzmc;//变电站名称
	private BigDecimal by;//本月
	private BigDecimal bytq;//本月同期
	private BigDecimal lj;//累计
	private BigDecimal ljtq;//累计同期
	public String getBdzmc() {
		return bdzmc;
	}
	public void setBdzmc(String bdzmc) {
		this.bdzmc = bdzmc;
	}
	public BigDecimal getBy() {
		return by;
	}
	public void setBy(BigDecimal by) {
		this.by = by;
	}
	public BigDecimal getBytq() {
		return bytq;
	}
	public void setBytq(BigDecimal bytq) {
		this.bytq = bytq;
	}
	public BigDecimal getLj() {
		return lj;
	}
	public void setLj(BigDecimal lj) {
		this.lj = lj;
	}
	public BigDecimal getLjtq() {
		return ljtq;
	}
	public void setLjtq(BigDecimal ljtq) {
		this.ljtq = ljtq;
	}
	
	

}
